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
import org.lsst.sal.SAL_MTHeaderService;

/**
 * <h2>Data Management Header Service CSC</h2>
 * <p>
 * {@code CSCMTHeaderService} is a (Concrete) Receiver class in the command pattern
 */
public class CSCMTHeaderService implements CommandableSalComponent {

  @Override
  public String getName() {
    return "CSCMTHeaderService";
  }

  /////////////////////////////////////////////////////
  // SAL middle-ware Commands
  /////////////////////////////////////////////////////

  @Override
  public void enterControl() {

    SAL_MTHeaderService publisher = new SAL_MTHeaderService();
    publisher.salCommand( "MTHeaderService_command_enterControl" );

    publisher.setDebugLevel( 1 );

    MTHeaderService.command_enterControl command = new MTHeaderService.command_enterControl();
    command.private_revCode = "LSST DM MTHeaderService enterControl COMMAND";
    command.device = "MTHeaderService";
    command.property = "enterControl";
    command.action = "set";

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

    SAL_MTHeaderService publisher = new SAL_MTHeaderService();
    publisher.salCommand( "MTHeaderService_command_start" );

    publisher.setDebugLevel( 1 );

    MTHeaderService.command_start command = new MTHeaderService.command_start();
    command.private_revCode = "LSST DM MTHeaderService start COMMAND";
    command.device = "MTHeaderService";
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

    SAL_MTHeaderService publisher = new SAL_MTHeaderService();
    publisher.salCommand( "MTHeaderService_command_enable" );

    publisher.setDebugLevel( 1 );

    MTHeaderService.command_enable command = new MTHeaderService.command_enable();
    command.private_revCode = "LSST DM MTHeaderService enable COMMAND";
    command.device = "MTHeaderService";
    command.property = "enable";
    command.action = "set";

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

    SAL_MTHeaderService publisher = new SAL_MTHeaderService();
    publisher.salCommand( "MTHeaderService_command_disable" );

    publisher.setDebugLevel( 1 );

    MTHeaderService.command_disable command = new MTHeaderService.command_disable();
    command.private_revCode = "LSST DM MTHeaderService disable COMMAND";
    command.device = "MTHeaderService";
    command.property = "disable";
    command.action = "set";

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

    SAL_MTHeaderService publisher = new SAL_MTHeaderService();
    publisher.salCommand( "MTHeaderService_command_standby" );

    publisher.setDebugLevel( 1 );

    MTHeaderService.command_standby command = new MTHeaderService.command_standby();
    command.private_revCode = "LSST DM MTHeaderService standby COMMAND";
    command.device = "MTHeaderService";
    command.property = "standby";
    command.action = "set";

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

    SAL_MTHeaderService publisher = new SAL_MTHeaderService();
    publisher.salCommand( "MTHeaderService_command_exitControl" );

    publisher.setDebugLevel( 1 );

    MTHeaderService.command_exitControl command = new MTHeaderService.command_exitControl();
    command.private_revCode = "LSST DM MTHeaderService exitControl COMMAND";
    command.device = "MTHeaderService";
    command.property = "exitControl";
    command.action = "set";

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
    SAL_MTHeaderService subscriber = new SAL_MTHeaderService();
    subscriber.salEvent( "MTHeaderService_logevent_SummaryState" );

    subscriber.setDebugLevel( 1 );

    MTHeaderService.logevent_SummaryState event = new MTHeaderService.logevent_SummaryState();

    Integer status = SAL_MTHeaderService.SAL__NO_UPDATES;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_SummaryState( event );
      if ( status == SAL_MTHeaderService.SAL__OK ) {

        out.println( "=== Event Logged : " + event );
        out.println( "=== Event Status : " + status );
        out.println( "=== Event SummaryState : " + event.summaryState );

        try {
          Executive.getEntityMap()
              .get( EntityType.MTHEADERSERVICE.toString() )._modelStateTransitionQ.put( event.summaryState );

          Executive.getEntityMap()
              .get( EntityType.MTHEADERSERVICE.toString() )._viewStateTransitionQ.put( event.summaryState );
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
    SAL_MTHeaderService subscriber = new SAL_MTHeaderService();
    subscriber.salEvent( "MTHeaderService_logevent_settingVersions" );

    subscriber.setDebugLevel( 1 );

    MTHeaderService.logevent_settingVersions event
        = new MTHeaderService.logevent_settingVersions();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_settingVersions( event );
      if ( status == SAL_MTHeaderService.SAL__OK ) {
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
    SAL_MTHeaderService subscriber = new SAL_MTHeaderService();
    subscriber.salEvent( "MTHeaderService_logevent_appliedSettingsMatchStart" );

    subscriber.setDebugLevel( 1 );

    MTHeaderService.logevent_appliedSettingsMatchStart event
        = new MTHeaderService.logevent_appliedSettingsMatchStart();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_appliedSettingsMatchStart( event );
      if ( status == SAL_MTHeaderService.SAL__OK ) {
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
