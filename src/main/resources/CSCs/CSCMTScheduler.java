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

package org.lsst.ocs.executive.salcomponent;

import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lsst.ocs.executive.EntityType;
import org.lsst.ocs.executive.Executive;
import org.lsst.sal.SAL_MTScheduler;

/**
 * <h2>OCS Main Scheduler CSC</h2>
 * <p>
 * {@code CSCMTScheduler} is a (Concrete) Receiver class in the command pattern
 */
public class CSCMTScheduler implements CommandableSalComponent {

  @Override
  public String getName() {
    return "CSCMTScheduler";
  }

  /////////////////////////////////////////////////////
  // SAL middle-ware Commands
  /////////////////////////////////////////////////////

  @Override
  public void enterControl() {

    SAL_MTScheduler publisher = new SAL_MTScheduler();
    publisher.salCommand( "MTScheduler_command_enterControl" );

    publisher.setDebugLevel( 1 );

    MTScheduler.command_enterControl command = new MTScheduler.command_enterControl();
    command.private_revCode = "LSST MTScheduler enterControl COMMAND";
    command.device = "MTScheduler";
    command.property = "enterControl";
    command.action = "set";
    command.tempValue = true;

    int cmdId = publisher.issueCommand_enterControl( command );

    try {
      Thread.sleep( 250 );
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    }

    int timeout = 4;
    publisher.waitForCompletion_enterControl( cmdId, timeout );

    /*
          * cleanup
          */
    publisher.salShutdown();
  }

  @Override
  public void start() {

    SAL_MTScheduler publisher = new SAL_MTScheduler();
    publisher.salCommand( "MTScheduler_command_start" );

    publisher.setDebugLevel( 1 );

    MTScheduler.command_start command = new MTScheduler.command_start();
    command.private_revCode = "LSST MTScheduler start COMMAND";
    command.device = "configuration";
    command.property = "start";
    command.action = "set";

    int cmdId = publisher.issueCommand_start( command );

    try {
      Thread.sleep( 250 );
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    }

    int timeout = 4;
    publisher.waitForCompletion_start( cmdId, timeout );

    /*
          * cleanup
          */
    publisher.salShutdown();

  }

  @Override
  public void enable() {

    SAL_MTScheduler publisher = new SAL_MTScheduler();
    publisher.salCommand( "MTScheduler_command_enable" );

    publisher.setDebugLevel( 1 );

    MTScheduler.command_enable command = new MTScheduler.command_enable();
    command.private_revCode = "LSST MTScheduler enable COMMAND";
    command.device = "MTScheduler";
    command.property = "enable";
    command.action = "set";
    command.tempValue = true;

    int cmdId = publisher.issueCommand_enable( command );

    try {
      Thread.sleep( 250 );
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    }

    int timeout = 4;
    publisher.waitForCompletion_enable( cmdId, timeout );

    /*
          * cleanup
          */
    publisher.salShutdown();
  }

  @Override
  public void disable() {

    SAL_MTScheduler publisher = new SAL_MTScheduler();
    publisher.salCommand( "MTScheduler_command_disable" );

    publisher.setDebugLevel( 1 );

    MTScheduler.command_disable command = new MTScheduler.command_disable();
    command.private_revCode = "LSST MTScheduler disable COMMAND";
    command.device = "MTScheduler";
    command.property = "disable";
    command.action = "set";
    command.tempValue = true;

    int cmdId = publisher.issueCommand_disable( command );

    try {
      Thread.sleep( 250 );
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    }

    int timeout = 4;
    publisher.waitForCompletion_disable( cmdId, timeout );

    /*
          * cleanup
          */
    publisher.salShutdown();
  }

  @Override
  public void standby() {

    SAL_MTScheduler publisher = new SAL_MTScheduler();
    publisher.salCommand( "MTScheduler_command_standby" );

    publisher.setDebugLevel( 1 );

    MTScheduler.command_standby command = new MTScheduler.command_standby();
    command.private_revCode = "LSST MTScheduler standby COMMAND";
    command.device = "MTScheduler";
    command.property = "standby";
    command.action = "set";
    command.tempValue = true;

    int cmdId = publisher.issueCommand_standby( command );

    try {
      Thread.sleep( 250 );
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    }

    int timeout = 4;
    publisher.waitForCompletion_standby( cmdId, timeout );

    /*
          * cleanup
          */
    publisher.salShutdown();
  }

  @Override
  public void exitControl() {

    SAL_MTScheduler publisher = new SAL_MTScheduler();
    publisher.salCommand( "MTScheduler_command_exitControl" );

    publisher.setDebugLevel( 1 );

    MTScheduler.command_exitControl command = new MTScheduler.command_exitControl();
    command.private_revCode = "LSST MTScheduler exitControl COMMAND";
    command.device = "MTScheduler";
    command.property = "exitControl";
    command.action = "set";
    command.tempValue = true;

    int cmdId = publisher.issueCommand_exitControl( command );

    try {
      Thread.sleep( 250 );
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    }

    int timeout = 4;
    publisher.waitForCompletion_exitControl( cmdId, timeout );

    /*
          * cleanup
          */
    publisher.salShutdown();
  }

  /////////////////////////////////////////////////////
  // SAL middle-ware Events
  /////////////////////////////////////////////////////
  
  @Override
  public Integer summaryState() {

    // Initialize
    SAL_MTScheduler subscriber = new SAL_MTScheduler();
    subscriber.salEvent( "MTScheduler_logevent_summaryState" );

    subscriber.setDebugLevel( 1 );

    MTScheduler.logevent_summaryState event = new MTScheduler.logevent_summaryState();

    Integer status = SAL_MTScheduler.SAL__NO_UPDATES;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_summaryState( event );
      if ( status == SAL_MTScheduler.SAL__OK ) {

        out.println( "=== Event Logged : " + event );
        out.println( "=== Event Status : " + status );
        out.println( "=== Event SummaryState : " + event.summaryState );

        try {
          Executive.getEntityMap()
              .get( EntityType.MTSCHEDULER.toString() )._modelStateTransitionQ.put( event.summaryState );

          Executive.getEntityMap()
              .get( EntityType.MTSCHEDULER.toString() )._viewStateTransitionQ.put( event.summaryState );
        } catch ( InterruptedException ie ) {
          ie.printStackTrace( out.printf( "GOOD SummaryState" ) );
        }
      }

      try {
        Thread.sleep( 100 );
      } catch ( InterruptedException e ) {
        e.printStackTrace();
      }
    }

//        if ( count >= 50 ) {
//            
//            out.println( "=== Event SummaryState Was NOT received");
//            out.println( "=== Event Status : " + status );
//            
//            try {
//                //Executive.getEntityMap().get( "sch")._stateTransitionQ.put( status );
//                Executive._entitySCH._stateTransitionQ.put( status );
//            } catch ( InterruptedException ie ) {
//                ie.printStackTrace( out.printf( "BAD SummaryState" ));
//            }
//        }

    /*
          * cleanup
          */
    subscriber.salShutdown();
    
    return status;
  }

  @Override
  public void settingsVersion() {

    // Initialize
    SAL_MTScheduler subscriber = new SAL_MTScheduler();
    subscriber.salEvent( "MTScheduler_logevent_settingVersions" );

    subscriber.setDebugLevel( 1 );

    MTScheduler.logevent_settingVersions event = new MTScheduler.logevent_settingVersions();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.logEvent_settingVersions( event, 1 );
      if ( status == SAL_MTScheduler.SAL__OK ) {
        out.println( "=== Event Logged : " + event );
      }

      try {
        Thread.sleep( 100 );
      } catch ( InterruptedException e ) {
        e.printStackTrace();
      }
    }

    /*
          * cleanup
          */
    subscriber.salShutdown();
  }

  @Override
  public void appliedSettingsMatchStart() {

    // Initialize
    SAL_MTScheduler subscriber = new SAL_MTScheduler();
    subscriber.salEvent( "MTScheduler_logevent_appliedSettingsMatchStart" );

    subscriber.setDebugLevel( 1 );

    MTScheduler.logevent_appliedSettingsMatchStart event = new MTScheduler.logevent_appliedSettingsMatchStart();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_appliedSettingsMatchStart( event );
      if ( status == SAL_MTScheduler.SAL__OK ) {
        out.println( "=== Event Logged : " + event );
      }

      try {
        Thread.sleep( 100 );
      } catch ( InterruptedException e ) {
        e.printStackTrace();
      }
    }

    /*
          * cleanup
          */
    subscriber.salShutdown();
  }

}
