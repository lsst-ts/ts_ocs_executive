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
 * StandbyState is a Concrete State class implementation
 * 
 * Transitions to: OfflineState, DisabledState or FaultState
 *
 */

public class StandbyState implements EntityState {

    // Entity is in StandbyState & ready for start trigger
    @Override public String getName() { return "StandbyState"; }
    
    @Override public void start(Entity entity) {
        
        // Cmd Sequencer, TCS, CCS or DMCS via SAL
        // Send msg
        String salactor = entity.etype_.toString();
        out.println(salactor + "." + this.getName() + ".start");
        SalCmd salCmd = new SalCmd(salactor);
        salCmd.start();

        if ( EntityType.OCS.toString().equalsIgnoreCase(salactor) ) {
            
            // 1. Publish SummaryState->StandbyState if not previously pub'd
            // 2. Publishes heartbeat; some configuration settings applied
            //    a. Publish Topic->SettingsApplied
            //    b. Publish Topic->SettingsVersion
        }
        
        // Cmd local entity state from StandbyState to DisabledState
        entity.setState(new DisabledState());
    }

    @Override public void exitControl(Entity entity) {
        
        // Cmd Sequencer, TCS, CCS or DMCS via SAL
        // Send msg
        String salactor = entity.etype_.toString();
        out.println(salactor + "." + this.getName() + ".exitControl");
        SalCmd salCmd = new SalCmd(salactor);
        salCmd.exitControl();

        if ( EntityType.OCS.toString().equalsIgnoreCase(salactor) ) {
            
            // 1. Publish SummaryState->StandbyState if not previously pub'd
            // 2. Apply some settings
        }
        
        // Cmd local entity state from StandbyState to OfflineState
        entity.setState(new OfflineState());
    }

    @Override public void fault(Entity entity) {
        
        out.println(entity.etype_.toString() + "." + this.getName() + ".fault");

        // Can't set other entities to FaultState, only myself
        if ( EntityType.OCS.equals(entity.etype_) ) {
            
            // 1. Publish SummaryState->StandbyState if not previously pub'd
            // 2. Set error code
            // 3. Cmd local entity state from StandbyState to FaultState
            entity.setState(new FaultState());
        }
    }

}
