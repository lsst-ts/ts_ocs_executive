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
import org.lsst.ocs.executive.salconnect.SalConnect;
import org.lsst.ocs.executive.salservice.SalCmd;
import org.lsst.ocs.executive.salservice.SalEvent;

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

        String salactor = entity._etype.toString();
        out.println(salactor + "." + this.getName() + ".disable");

        // Cmd Sequencer, TCS, CCS or DMCS via SAL
        // 1. SalComponent (Rcvr) reference is entity data member
        
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        SalCmd salCmdCamera = new SalCmd(entity._salComponent);

        // 3. Also, assign topic & topic arguments
        salCmdCamera.setTopic("enterControl");
        
        // 4. Define Invoker & set up command request
        SalConnect salConnectCamera = new SalConnect(1);
        salConnectCamera.setSalService(salCmdCamera);
        
        // 5. Invoker indirectly calls cmd->execute()
        salConnectCamera.connect();

        
        if ( EntityType.OCS.toString().equalsIgnoreCase(salactor) ) {
            
            // 1. Publish SummaryState if not previously pub'd
            SalEvent salEventCamera = new SalEvent(entity._salComponent);
            salEventCamera.setTopic("summaryState");
            
            salConnectCamera.setSalService(salEventCamera);
            salConnectCamera.connect();
        }        

        // Cmd local entity state from EnabledState to DisabledState 
        entity.setState(new DisabledState());
    }

    @Override public void fault(Entity entity) {
        
        String salactor = entity._etype.toString();
        out.println(salactor + "." + this.getName() + ".fault");
        
        // Can't set other entities to FaultState, only myself
        if ( EntityType.OCS.toString().equalsIgnoreCase(salactor) ) {
            
            // 1. Set error code
            // 2. Cmd local entity state from EnabledState to FaultState
            entity.setState(new FaultState());
        }
    }
}
