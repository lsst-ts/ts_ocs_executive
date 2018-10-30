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
import org.lsst.ocs.executive.EntityType;
import org.lsst.ocs.executive.Executive;
import org.lsst.sal.SAL_ATScheduler;

/**
 * <h2>OCS Auxiliary Scheduler CSC</h2>
 * <p>
 * {@code CSCATScheduler} is a (Concrete) Receiver class in the command pattern
 */
public class CSCATScheduler implements CommandableSalComponent {

  @Override
  public String getName() {
    return "CSCATScheduler";
  }

  /////////////////////////////////////////////////////
  // SAL middle-ware Commands
  /////////////////////////////////////////////////////

  @Override
  public void enterControl() {

    SAL_ATScheduler publisher = new SAL_ATScheduler();
    publisher.salCommand( "ATScheduler_command_enterControl" );

    publisher.setDebugLevel( 1 );

    ATScheduler.command_enterControl command = new ATScheduler.command_enterControl();
    command.private_revCode = "LSST ATScheduler enterControl COMMAND";
    command.device = "ATScheduler";
    command.property = "state";
    command.action = "enterControl";
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

    SAL_ATScheduler publisher = new SAL_ATScheduler();
    publisher.salCommand( "ATScheduler_command_start" );

    publisher.setDebugLevel( 1 );

    ATScheduler.command_start command = new ATScheduler.command_start();
    command.private_revCode = "LSST ATScheduler start COMMAND";
    command.device = "ATScheduler";
    command.property = "state";

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

    SAL_ATScheduler publisher = new SAL_ATScheduler();
    publisher.salCommand( "ATScheduler_command_enable" );

    publisher.setDebugLevel( 1 );

    ATScheduler.command_enable command = new ATScheduler.command_enable();
    command.private_revCode = "LSST ATScheduler enable COMMAND";
    command.device = "ATScheduler";
    command.property = "state";
    command.action = "enable";
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

    SAL_ATScheduler publisher = new SAL_ATScheduler();
    publisher.salCommand( "ATScheduler_command_disable" );

    publisher.setDebugLevel( 1 );

    ATScheduler.command_disable command = new ATScheduler.command_disable();
    command.private_revCode = "LSST ATScheduler disable COMMAND";
    command.device = "ATScheduler";
    command.property = "state";
    command.action = "disable";
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

    SAL_ATScheduler publisher = new SAL_ATScheduler();
    publisher.salCommand( "ATScheduler_command_standby" );

    publisher.setDebugLevel( 1 );

    ATScheduler.command_standby command = new ATScheduler.command_standby();
    command.private_revCode = "LSST ATScheduler standby COMMAND";
    command.device = "ATScheduler";
    command.property = "state";
    command.action = "standby";
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

    SAL_ATScheduler publisher = new SAL_ATScheduler();
    publisher.salCommand( "ATScheduler_command_exitControl" );

    publisher.setDebugLevel( 1 );

    ATScheduler.command_exitControl command = new ATScheduler.command_exitControl();
    command.private_revCode = "LSST ATScheduler exitControl COMMAND";
    command.device = "ATScheduler";
    command.property = "state";
    command.action = "exitControl";
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
    SAL_ATScheduler subscriber = new SAL_ATScheduler();
    subscriber.salEvent( "ATScheduler_logevent_SummaryState" );

    subscriber.setDebugLevel( 1 );

    ATScheduler.logevent_summaryState event = new ATScheduler.logevent_summaryState();

    Integer status = SAL_ATScheduler.SAL__NO_UPDATES;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_summaryState( event );
      if ( status == SAL_scheduler.SAL__OK ) {

        out.println( "=== Event Logged : " + event );
        out.println( "=== Event Status : " + status );
        out.println( "=== Event SummaryState : " + event.summaryState );

        try {
          Executive.getEntityMap()
              .get( EntityType.ATSCHEDULER.toString() )._modelStateTransitionQ.put( event.summaryState );

          Executive.getEntityMap()
              .get( EntityType.ATSCHEDULER.toString() )._viewStateTransitionQ.put( event.summaryState );
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

    /*
          * cleanup
          */
    subscriber.salShutdown();

    return status;
  }

  @Override
  public void settingsVersion() {

    // Initialize
    SAL_ATScheduler subscriber = new SAL_ATScheduler();
    subscriber.salEvent( "ATScheduler_logevent_settingVersions" );

    subscriber.setDebugLevel( 1 );

    ATScheduler.logevent_settingVersions event = new ATScheduler.logevent_settingVersions();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_settingVersions( event );
      if ( status == SAL_ATScheduler.SAL__OK ) {
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
    SAL_ATScheduler subscriber = new SAL_ATScheduler();
    subscriber.salEvent( "ATScheduler_logevent_appliedSettingsMatchStart" );

    subscriber.setDebugLevel( 1 );

    ATScheduler.logevent_appliedSettingsMatchStart event = new ATScheduler.logevent_appliedSettingsMatchStart();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_appliedSettingsMatchStart( event );
      if ( status == SAL_ATScheduler.SAL__OK ) {
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
