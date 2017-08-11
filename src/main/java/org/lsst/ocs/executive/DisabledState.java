/*
 * LSST Observatory Control System (OCS) Software
 * Copyright 2008-2017 AURA/LSST.
 * 
 * This product includes software developed by the
 * LSST Project (http://www.lsst.org/) with contributions made at LSST partner
 * institutions.  The list of partner institutions is found at:
 * http://www.lsst.org/lsst/about/contributors .
 * 
 * Use and redistribution of this software is covered by the GNU Public License 
 * Version 3 (GPLv3) or later, as detailed below.  A copy of the GPLv3 is also 
 * available at <http://www.gnu.org/licenses/>.
 */

package org.lsst.ocs.executive;

import static java.lang.System.out;

/**
 *
 * DisabledState is a Concrete State class implementation
 *
 * Transitions to: StandbyState, EnabledState or FaultState
 * 
 */

public class DisabledState implements EntityState {

    // Entity has applied settings & may acquire data
    @Override public String getName() { return "DisabledState"; }
    
    @Override public void enable(Entity entity) {
       
        String salactor = entity._etype.toString();
        out.println(salactor + "." + this.getName() + ".enable");

        // Cmd Sequencer, TCS, CCS or DMCS via SAL
        // Send msg
        entity._salComponent.enable();

        if ( EntityType.OCS.toString().equalsIgnoreCase(salactor) ) {
            // 1. Publish SummaryState if not previously pub'd
            entity._salComponent.summaryState(1);
            
            // 2. Check settings match (or differ) from start values
            //    a. Publish Topic->AppliedSettingsMatchStart (or they differ??)
            // 3. Full control features are allowed
            //    a. Entity reads/loads & applies control settings
        }

        // Cmd local entity state from DisabledState to EnabledState
        entity.setState(new EnabledState());
    }

    @Override public void standby(Entity entity) {

        String salactor = entity._etype.toString();
        out.println(salactor + "." + this.getName() + ".standby");

        // Cmd Sequencer, TCS, CCS or DMCS via SAL
        // Send msg
        entity._salComponent.standby();

        if ( EntityType.OCS.toString().equalsIgnoreCase(salactor) ) {
            // 1. Publish SummaryState if not previously pub'd
            entity._salComponent.summaryState(1);
            
            // 2. Entity reads/loads & applies control settings
        }

        // Cmd local entity state from DisabledState to StandbyState
        entity.setState(new StandbyState());
    }

    @Override public void fault(Entity entity) {

        String salactor = entity._etype.toString();
        out.println(salactor + "." + this.getName() + ".fault");

        // Can't set other entities to FaultState, only myself
        if ( EntityType.OCS.toString().equalsIgnoreCase(salactor) ) {
            
            // 1. Set error code
            // 2. Cmd local entity state from DisabledState to FaultState
            entity.setState(new FaultState());
        }
    }
}
