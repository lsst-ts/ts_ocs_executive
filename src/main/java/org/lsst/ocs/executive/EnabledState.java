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

import org.lsst.ocs.executive.salservice.SalCmd;
import org.lsst.ocs.executive.salconnect.SalConnect;
import org.lsst.ocs.executive.salservice.SalEvent;

import static java.lang.System.out;

/**
 * <h2>Enabled Entity State</h2>
 * <p>
 * {@code EnabledState} is a Concrete State class implementation.
 * <p>
 * Transitions to: {@code DisabledState} or {@code FaultState}
 */
public class EnabledState implements EntityState {

  @Override
  public String getName() {
    return "EnabledState";
  }

  @Override
  public void disable( Entity entity ) {

    String salactor = entity.getClass()
        .getSimpleName() + "."
                      + entity.getCSC().getClass().getSimpleName()
                      + "."
                      + this.getName()
                      + ".disable";

    out.println( salactor + ": " + Thread.currentThread().getId() );

    // Cmd Sequencer, TCS, MTCAMERA or DMCS via SAL
    // 1. SalComponent (Rcvr) reference is entity data member
    // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
    SalCmd salCmd = new SalCmd( entity._salComponent );

    // 3. Also, assign topic & topic arguments
    salCmd.setTopic( "disable" );

    // 4. Define Invoker & set up command request
    SalConnect salConnect = new SalConnect( 1 );
    salConnect.setSalService( salCmd );

    // 5. Invoker indirectly calls cmd->execute()
    salConnect.connect();

    if ( EntityType.OCS.toString().equalsIgnoreCase( salactor ) ) {

      // 1. Publish SummaryState if not previously pub'd
      SalEvent salEvent = new SalEvent( entity._salComponent );
      salEvent.setTopic( "summaryState" );

      salConnect.setSalService( salEvent );
      salConnect.connect();

      // 2. Check settings match (or differ) from start values
      //    a. Publish Topic->AppliedSettingsMatchStart (or they differ??)
      // 3. Full control features are allowed
      //    a. Entity reads/loads & applies control settings            
    }

    // Cmd local entity state from EnabledState to DisabledState 
    waitForSummaryState( salactor, entity, new DisabledState() );
  }

  @Override
  public void fault( Entity entity ) {

    String salactor = entity.getClass()
        .getSimpleName() + "."
                      + entity.getCSC().getClass().getSimpleName()
                      + "."
                      + this.getName()
                      + ".fault";

    out.println( salactor + ": " + Thread.currentThread().getId() );

    // Can't set other entities to FaultState, only myself
    if ( EntityType.OCS.toString().equalsIgnoreCase( salactor ) ) {

      // 1. Publish SummaryState == fault if not previously pub'd
      SalEvent salEvent = new SalEvent( entity._salComponent );
      salEvent.setTopic( "summaryState" );

      SalConnect salConnect = new SalConnect( 1 );
      salConnect.setSalService( salEvent );
      salConnect.connect();

      // 2. Set error code
      // Via Detailed State event ???
      // 3. Cmd local entity state from EnabledState to FaultState
      entity.setState( new FaultState() );
    }
  }
}
