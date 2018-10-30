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
import org.lsst.sal.SAL_MTArchiver;

/**
 * <h2>Data Management Archiver Service CSC</h2>
 * <p>
 * {@code CSCMTArchiver} is a (Concrete) Receiver class in the command pattern
 */
public class CSCMTArchiver implements CommandableSalComponent {

  @Override
  public String getName() {
    return "CSCMTArchiver";
  }

  /////////////////////////////////////////////////////
  // SAL middle-ware Commands
  /////////////////////////////////////////////////////

  @Override
  public void enterControl() {

    SAL_MTArchiver publisher = new SAL_MTArchiver();
    publisher.salCommand( "MTArchiver_command_enterControl" );

    publisher.setDebugLevel( 1 );

    MTArchiver.command_enterControl command = new MTArchiver.command_enterControl();
    command.private_revCode = "LSST MTArchiver enterControl COMMAND";
    command.device = "MTArchiver";
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

    SAL_MTArchiver publisher = new SAL_MTArchiver();
    publisher.salCommand( "MTArchiver_command_start" );

    publisher.setDebugLevel( 1 );

    MTArchiver.command_start command = new MTArchiver.command_start();
    command.private_revCode = "LSST MTArchiver start COMMAND";
    command.device = "configuration";
    command.property = "start";
    command.action = "set";
    command.configuration = "Normal";

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

    SAL_MTArchiver publisher = new SAL_MTArchiver();
    publisher.salCommand( "MTArchiver_command_enable" );

    publisher.setDebugLevel( 1 );

    MTArchiver.command_enable command = new MTArchiver.command_enable();
    command.private_revCode = "LSST MTArchiver enable COMMAND";
    command.device = "MTArchiver";
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

    SAL_MTArchiver publisher = new SAL_MTArchiver();
    publisher.salCommand( "MTArchiver_command_disable" );

    publisher.setDebugLevel( 1 );

    MTArchiver.command_disable command = new MTArchiver.command_disable();
    command.private_revCode = "LSST MTArchiver disable COMMAND";
    command.device = "MTArchiver";
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

    SAL_MTArchiver publisher = new SAL_MTArchiver();
    publisher.salCommand( "MTArchiver_command_standby" );

    publisher.setDebugLevel( 1 );

    MTArchiver.command_standby command = new MTArchiver.command_standby();
    command.private_revCode = "LSST MTArchiver standby COMMAND";
    command.device = "MTArchiver";
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

    SAL_MTArchiver publisher = new SAL_MTArchiver();
    publisher.salCommand( "MTArchiver_command_exitControl" );

    publisher.setDebugLevel( 1 );

    MTArchiver.command_exitControl command = new MTArchiver.command_exitControl();
    command.private_revCode = "LSST MTArchiver exitControl COMMAND";
    command.device = "MTArchiver";
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
    SAL_MTArchiver subscriber = new SAL_MTArchiver();
    subscriber.salEvent( "MTArchiver_logevent_SummaryState" );

    subscriber.setDebugLevel( 1 );

    MTArchiver.logevent_SummaryState event = new MTArchiver.logevent_SummaryState();

    Integer status = SAL_MTArchiver.SAL__NO_UPDATES;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_SummaryState( event );
      if ( status == SAL_MTArchiver.SAL__OK ) {

        out.println( "=== Event Logged : " + event );
        out.println( "=== Event Status : " + status );
        out.println( "=== Event SummaryState : " + event.SummaryStateValue );

        try {
          Executive.getEntityMap()
              .get( EntityType.MTARCHIVER.toString() )._modelStateTransitionQ.put( event.summaryState );

          Executive.getEntityMap()
              .get( EntityType.MTARCHIVER.toString() )._viewStateTransitionQ.put( event.summaryState );
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
    SAL_MTArchiver subscriber = new SAL_MTArchiver();
    subscriber.salEvent( "MTArchiver_logevent_settingVersions" );

    MTArchiver.logevent_settingVersions event = new MTArchiver.logevent_settingVersions();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_settingVersions( event );
      if ( status == SAL_MTArchiver.SAL__OK ) {
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
    SAL_MTArchiver subscriber = new SAL_MTArchiver();
    subscriber.salEvent( "MTArchiver_logevent_appliedSettingsMatchStart" );

    MTArchiver.logevent_appliedSettingsMatchStart event = new MTArchiver.logevent_appliedSettingsMatchStart();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_appliedSettingsMatchStart( event );
      if ( status == SAL_MTArchiver.SAL__OK ) {
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
