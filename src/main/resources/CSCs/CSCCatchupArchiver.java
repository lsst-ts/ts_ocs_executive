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

import org.lsst.ocs.executive.EntityType;
import org.lsst.ocs.executive.Executive;
import org.lsst.sal.SAL_CatchupArchiver;

import static java.lang.System.out;

/**
 * <h2>Data Management Catchup Archiver Service CSC</h2>
 * <p>
 * {@code CSCCatchupArchiver} is a (Concrete) Receiver class in the command pattern
 */
public class CSCCatchupArchiver implements CommandableSalComponent {

  @Override
  public String getName() {
    return "CSCCatchupArchiver";
  }

   /////////////////////////////////////////////////////
  // SAL middle-ware Commands
  /////////////////////////////////////////////////////

 @Override
  public void enterControl() {

    SAL_CatchupArchiver publisher = new SAL_CatchupArchiver();
    publisher.salCommand( "CatchupArchiver_command_enterControl" );

    publisher.setDebugLevel( 1 );

    CatchupArchiver.command_enterControl command = new CatchupArchiver.command_enterControl();
    command.private_revCode = "LSST CatchupArchiver enterControl COMMAND";
    command.device = "CatchupArchiver";
    command.property = "enterControl";
    command.action = "set";
    command.state = true;

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

    SAL_CatchupArchiver publisher = new SAL_CatchupArchiver();
    publisher.salCommand( "CatchupArchiver_command_start" );

    publisher.setDebugLevel( 1 );

    CatchupArchiver.command_start command = new CatchupArchiver.command_start();
    command.private_revCode = "LSST CatchupArchiver start COMMAND";
    command.device = "CatchupArchiver";
    command.property = "start";
    command.action = "set";
    command.configuration = "normal";

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

    SAL_CatchupArchiver publisher = new SAL_CatchupArchiver();
    publisher.salCommand( "CatchupArchiver_command_enable" );

    publisher.setDebugLevel( 1 );

    CatchupArchiver.command_enable command = new CatchupArchiver.command_enable();
    command.private_revCode = "LSST CatchupArchiver enable COMMAND";
    command.device = "CatchupArchiver";
    command.property = "enable";
    command.action = "set";
    command.state = true;

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

    SAL_CatchupArchiver publisher = new SAL_CatchupArchiver();
    publisher.salCommand( "CatchupArchiver_command_disable" );

    publisher.setDebugLevel( 1 );

    CatchupArchiver.command_disable command = new CatchupArchiver.command_disable();
    command.private_revCode = "LSST CatchupArchiver disable COMMAND";
    command.device = "CatchupArchiver";
    command.property = "disable";
    command.action = "set";
    command.state = true;

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

    SAL_CatchupArchiver publisher = new SAL_CatchupArchiver();
    publisher.salCommand( "CatchupArchiver_command_standby" );

    publisher.setDebugLevel( 1 );

    CatchupArchiver.command_standby command = new CatchupArchiver.command_standby();
    command.private_revCode = "LSST CatchupArchiver standby COMMAND";
    command.device = "CatchupArchiver";
    command.property = "standby";
    command.action = "set";
    command.state = true;

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

    SAL_CatchupArchiver publisher = new SAL_CatchupArchiver();
    publisher.salCommand( "CatchupArchiver_command_exitControl" );

    publisher.setDebugLevel( 1 );

    CatchupArchiver.command_exitControl command = new CatchupArchiver.command_exitControl();
    command.private_revCode = "LSST CatchupArchiver exitControl COMMAND";
    command.device = "CatchupArchiver";
    command.property = "exitControl";
    command.action = "set";
    command.state = true;

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
    SAL_CatchupArchiver subscriber = new SAL_CatchupArchiver();
    subscriber.salEvent( "CatchupArchiver_logevent_SummaryState" );

    subscriber.setDebugLevel( 1 );

    CatchupArchiver.logevent_SummaryState event = new CatchupArchiver.logevent_SummaryState();

    Integer status = SAL_CatchupArchiver.SAL__NO_UPDATES;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_SummaryState( event );
      if ( status == SAL_CatchupArchiver.SAL__OK ) {

        out.println( "=== Event Logged : " + event );
        out.println( "=== Event Status : " + status );
        out.println( "=== Event SummaryState : " + event.SummaryStateValue );

        try {
          Executive.getEntityMap()
              .get( EntityType.CATCHUPARCHIVER.toString() )._modelStateTransitionQ.put( event.summaryState );

          Executive.getEntityMap()
              .get( EntityType.CATCHUPARCHIVER.toString() )._viewStateTransitionQ.put( event.summaryState );
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
    SAL_CatchupArchiver subscriber = new SAL_CatchupArchiver();
    subscriber.salEvent( "CatchupArchiver_logevent_settingVersions" );

    subscriber.setDebugLevel( 1 );

    CatchupArchiver.logevent_settingVersions event = new CatchupArchiver.logevent_settingVersions();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_settingVersions( event );
      if ( status == SAL_CatchupArchiver.SAL__OK ) {
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
    SAL_CatchupArchiver subscriber = new SAL_CatchupArchiver();
    subscriber.salEvent( "CatchupArchiver_logevent_appliedSettingsMatchStart" );

    subscriber.setDebugLevel( 1 );

    CatchupArchiver.logevent_appliedSettingsMatchStart event = new CatchupArchiver.logevent_appliedSettingsMatchStart();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_appliedSettingsMatchStart( event );
      if ( status == SAL_CatchupArchiver.SAL__OK ) {
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
