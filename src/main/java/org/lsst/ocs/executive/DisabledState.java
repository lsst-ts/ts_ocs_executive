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
 * {@code DisabledState} is a Concrete State class implementation.
 * <p>
 * Transitions to: {@code StandbyState}, {@code EnabledState} or {@code FaultState}
 * <p>
 */
public class DisabledState implements EntityState {

    // Entity has applied settings & may acquire data
    @Override
    public String getName () {
        return "DisabledState";
    }

    @Override
    public void enable ( Entity entity ) {

        String salactor = entity.getClass().getSimpleName() + "." + entity.getCSC().getClass().getSimpleName();
        out.println(salactor + "." + this.getName() + ".enable");

        // Cmd Sequencer, TCS, CCS or DMCS via SAL
        // 1. SalComponent (Rcvr) reference is entity data member
        
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        SalCmd salCmd = new SalCmd(entity._salComponent);

        // 3. Also, assign topic & topic arguments
        salCmd.setTopic("enable");
        
        // 4. Define Invoker & set up command request
        SalConnect salConnect = new SalConnect(1);
        salConnect.setSalService(salCmd);
        
        // 5. Invoker indirectly calls cmd->execute()
        salConnect.connect();

        if ( EntityType.OCS.toString().equalsIgnoreCase(salactor) ) {

            // 1. Publish SummaryState if not previously pub'd
            SalEvent salEvent = new SalEvent(entity._salComponent);
            salEvent.setTopic("summaryState");
            
            salConnect.setSalService(salEvent);
            salConnect.connect();
            
            // 2. Check settings match (or differ) from start values
            //    a. Publish Topic->AppliedSettingsMatchStart (or they differ??)
            
            // 3. Full control features are allowed
            //    a. Entity reads/loads & applies control settings
        }

        // Cmd local entity state from DisabledState to EnabledState
        entity.setState(new EnabledState());
    }

    @Override
    public void standby ( Entity entity ) {

        String salactor = entity.getClass().getSimpleName() + entity.getCSC().getClass().getSimpleName();
        out.println(salactor + "." + this.getName() + ".standby");

        // Cmd Sequencer, TCS, CCS or DMCS via SAL
        // 1. SalComponent (Rcvr) reference is entity data member
        
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        SalCmd salCmd = new SalCmd(entity._salComponent);

        // 3. Also, assign topic & topic arguments
        salCmd.setTopic("standby");
        
        // 4. Define Invoker & set up command request
        SalConnect salConnect = new SalConnect(1);
        salConnect.setSalService(salCmd);
        
        // 5. Invoker indirectly calls cmd->execute()
        salConnect.connect();

        if ( EntityType.OCS.toString().equalsIgnoreCase(salactor) ) {

            // 1. Publish SummaryState if not previously pub'd
            SalEvent salEvent = new SalEvent(entity._salComponent);
            salEvent.setTopic("summaryState");
            
            salConnect.setSalService(salEvent);
            salConnect.connect();

            // 2. Entity reads/loads & applies control settings
        }

        // Cmd local entity state from DisabledState to StandbyState
        entity.setState(new StandbyState());
    }

    @Override
    public void fault ( Entity entity ) {

        String salactor = entity.getClass().getSimpleName() + entity.getCSC().getClass().getSimpleName();
        out.println(salactor + "." + this.getName() + ".fault");

        // Can't set other entities to FaultState, only myself
        if ( EntityType.OCS.toString().equalsIgnoreCase(salactor) ) {

            // 1. Publish SummaryState == fault if not previously pub'd
            SalEvent salEvent = new SalEvent(entity._salComponent);
            salEvent.setTopic("summaryState");
            
            SalConnect salConnect = new SalConnect(1);
            salConnect.setSalService(salEvent);
            salConnect.connect();
            
            // 2. Set error code
            // Via Detailed State event ???

            // 3. Cmd local entity state from DisabledState to FaultState
            entity.setState(new FaultState());
        }
    }
}
