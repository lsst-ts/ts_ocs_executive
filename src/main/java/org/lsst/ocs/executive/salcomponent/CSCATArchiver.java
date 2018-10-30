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
import org.lsst.sal.SAL_ATArchiver;

import static java.lang.System.out;

/**
 * <h2>Data Management Auxiliary Archiver Service CSC</h2>
 * <p>
 * {@code CSCATArchiver} is a (Concrete) Receiver class in the command pattern
 */
public class CSCATArchiver implements CommandableSalComponent {

  @Override
  public String getName() {
    return "CSCATArchiver";
  }

  /////////////////////////////////////////////////////
  // SAL middle-ware Commands
  /////////////////////////////////////////////////////

  @Override
  public void enterControl( Object[] args ) {

    SAL_ATArchiver publisher = new SAL_ATArchiver();
    publisher.salCommand( "ATArchiver_command_enterControl" );

    publisher.setDebugLevel( 1 );

    ATArchiver.command_enterControl command = new ATArchiver.command_enterControl();
    command.private_revCode = "LSST ATArchiver enterControl COMMAND";
    command.device = "ATArchiver";
    command.property = "enterControl";
    command.action = "set";
    command.value = true;

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
  public void start( Object[] args ) {

    SAL_ATArchiver publisher = new SAL_ATArchiver();
    publisher.salCommand( "ATArchiver_command_start" );

    publisher.setDebugLevel( 1 );

    ATArchiver.command_start command = new ATArchiver.command_start();
    command.private_revCode = "LSST ATArchiver start COMMAND";
    command.device = "configuration";
    command.property = "start";
    command.action = "set";
    command.settingsToApply = "Normal";

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
  public void enable( Object[] args ) {

    SAL_ATArchiver publisher = new SAL_ATArchiver();
    publisher.salCommand( "ATArchiver_command_enable" );

    publisher.setDebugLevel( 1 );

    ATArchiver.command_enable command = new ATArchiver.command_enable();
    command.private_revCode = "LSST ATArchiver enable COMMAND";
    command.device = "ATArchiver";
    command.property = "enable";
    command.action = "set";
    command.value = true;

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
  public void disable( Object[] args ) {

    SAL_ATArchiver publisher = new SAL_ATArchiver();
    publisher.salCommand( "ATArchiver_command_disable" );

    publisher.setDebugLevel( 1 );

    ATArchiver.command_disable command = new ATArchiver.command_disable();
    command.private_revCode = "LSST ATArchiver disable COMMAND";
    command.device = "ATArchiver";
    command.property = "disable";
    command.action = "set";
    command.value = true;

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
  public void standby( Object[] args ) {

    SAL_ATArchiver publisher = new SAL_ATArchiver();
    publisher.salCommand( "ATArchiver_command_standby" );

    publisher.setDebugLevel( 1 );

    ATArchiver.command_standby command = new ATArchiver.command_standby();
    command.private_revCode = "LSST ATArchiver standby COMMAND";
    command.device = "ATArchiver";
    command.property = "standby";
    command.action = "set";
    command.value = true;

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
  public void exitControl( Object[] args ) {

    SAL_ATArchiver publisher = new SAL_ATArchiver();
    publisher.salCommand( "ATArchiver_command_exitControl" );

    publisher.setDebugLevel( 1 );

    ATArchiver.command_exitControl command = new ATArchiver.command_exitControl();
    command.private_revCode = "LSST ATArchiver exitControl COMMAND";
    command.device = "ATArchiver";
    command.property = "exitControl";
    command.action = "set";
    command.value = true;

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
    SAL_ATArchiver subscriber = new SAL_ATArchiver();
    subscriber.salEvent( "ATArchiver_logevent_summaryState" );

    subscriber.setDebugLevel( 1 );

    ATArchiver.logevent_summaryState event = new ATArchiver.logevent_summaryState();

    Integer status = SAL_ATArchiver.SAL__NO_UPDATES;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_summaryState( event );
      if ( status == SAL_ATArchiver.SAL__OK ) {

        out.println( "=== Event Logged : " + event );
        out.println( "=== Event Status : " + status );
        out.println( "=== Event SummaryState : " + event.summaryState );

        try {
          Executive.getEntityMap()
              .get( EntityType.ATARCHIVER.toString() )._modelStateTransitionQ.put( event.summaryState );

          Executive.getEntityMap()
              .get( EntityType.ATARCHIVER.toString() )._viewStateTransitionQ.put( event.summaryState );
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
    SAL_ATArchiver subscriber = new SAL_ATArchiver();
    subscriber.salEvent( "ATArchiver_logevent_settingVersions" );

    subscriber.setDebugLevel( 1 );

    ATArchiver.logevent_settingVersions event = new ATArchiver.logevent_settingVersions();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_settingVersions( event );
      if ( status == SAL_ATArchiver.SAL__OK ) {
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
    SAL_ATArchiver subscriber = new SAL_ATArchiver();
    subscriber.salEvent( "ATArchiver_logevent_appliedSettingsMatchStart" );

    subscriber.setDebugLevel( 1 );

    ATArchiver.logevent_appliedSettingsMatchStart event = new ATArchiver.logevent_appliedSettingsMatchStart();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_appliedSettingsMatchStart( event );
      if ( status == SAL_ATArchiver.SAL__OK ) {
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
