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
 * EnabledState is a Concrete State class implementation
 *
 * Transitions to: DisabledState or FaultState
 * 
 */

public class EnabledState implements EntityState {

    @Override public String getName() { return "EnabledState"; }

    @Override public void disable(Entity entity) {

        // Cmd Sequencer, TCS, CCS or DMCS via SAL
        // Send msg
        String salactor = entity.etype_.toString();
        out.println(salactor + "." + this.getName() + ".disable");
        SalCmd salCmd = new SalCmd(salactor);
        salCmd.disable();

        // Cmd local entity state from EnabledState to DisabledState 
        entity.setState(new DisabledState());
    }

    @Override public void fault(Entity entity) {
        
        out.println(entity.etype_.toString() + "." + this.getName() + ".fault");
        
        // Can't set other entities to FaultState, only myself
        if ( EntityType.OCS.equals(entity.etype_) ) {
            
            // 1. Set error code
            // 2. Cmd local entity state from EnabledState to FaultState
            entity.setState(new FaultState());
        }
    }
    
}
