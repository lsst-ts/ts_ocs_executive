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
import org.lsst.sal.SAL_PromptProcessing;

/**
 * <h2>Data Management Prompt Processing Service CSC</h2>
 * <p>
 * {@code CSCPromptProcessing} is a (Concrete) Receiver class in the command pattern
 */
public class CSCPromptProcessing implements CommandableSalComponent {

  @Override
  public String getName() {
    return "CSCPromptProcessing";
  }

  /////////////////////////////////////////////////////
  // SAL middle-ware Commands
  /////////////////////////////////////////////////////

  @Override
  public void enterControl() {

    SAL_PromptProcessing publisher = new SAL_PromptProcessing();
    publisher.salCommand( "PromptProcessing_command_enterControl" );

    publisher.setDebugLevel( 1 );

    PromptProcessing.command_enterControl command = new PromptProcessing.command_enterControl();
    command.private_revCode = "LSST PromptProcessing_ enterControl COMMAND";
    command.device = "PromptProcessing";
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

    SAL_PromptProcessing publisher = new SAL_PromptProcessing();
    publisher.salCommand( "PromptProcessing_command_start" );

    publisher.setDebugLevel( 1 );

    PromptProcessing.command_start command = new PromptProcessing.command_start();
    command.private_revCode = "LSST PromptProcessing start COMMAND";
    command.device = "PromptProcessing";
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

    SAL_PromptProcessing publisher = new SAL_PromptProcessing();
    publisher.salCommand( "PromptProcessing_command_enable" );

    publisher.setDebugLevel( 1 );

    PromptProcessing.command_enable command = new PromptProcessing.command_enable();
    command.private_revCode = "LSST PromptProcessing enable COMMAND";
    command.device = "PromptProcessing";
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

    SAL_PromptProcessing publisher = new SAL_PromptProcessing();
    publisher.salCommand( "PromptProcessing_command_disable" );

    publisher.setDebugLevel( 1 );

    PromptProcessing.command_disable command = new PromptProcessing.command_disable();
    command.private_revCode = "LSST PromptProcessing disable COMMAND";
    command.device = "PromptProcessing";
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

    SAL_PromptProcessing publisher = new SAL_PromptProcessing();
    publisher.salCommand( "PromptProcessing_command_standby" );

    publisher.setDebugLevel( 1 );

    PromptProcessing.command_standby command = new PromptProcessing.command_standby();
    command.private_revCode = "LSST PromptProcessing standby COMMAND";
    command.device = "PromptProcessing";
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

    SAL_PromptProcessing publisher = new SAL_PromptProcessing();
    publisher.salCommand( "PromptProcessing_command_exitControl" );

    publisher.setDebugLevel( 1 );

    PromptProcessing.command_exitControl command = new PromptProcessing.command_exitControl();
    command.private_revCode = "LSST PromptProcessing exitControl COMMAND";
    command.device = "PromptProcessing";
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
    SAL_PromptProcessing subscriber = new SAL_PromptProcessing();
    subscriber.salEvent( "dmHeaderService_logevent_SummaryState" );

    subscriber.setDebugLevel( 1 );

    PromptProcessing.logevent_SummaryState event = new PromptProcessing.logevent_SummaryState();

    Integer status = SAL_PromptProcessing.SAL__NO_UPDATES;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_SummaryState( event );
      if ( status == SAL_PromptProcessing.SAL__OK ) {

        out.println( "=== Event Logged : " + event );
        out.println( "=== Event Status : " + status );
        out.println( "=== Event SummaryState : " + event.SummaryStateValue );

        try {
          Executive.getEntityMap()
              .get( EntityType.PROMPTPROCESSING.toString() )._modelStateTransitionQ.put( event.summaryState );

          Executive.getEntityMap()
              .get( EntityType.PROMPTPROCESSING.toString() )._viewStateTransitionQ.put( event.summaryState );
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
    SAL_PromptProcessing subscriber = new SAL_PromptProcessing();
    subscriber.salEvent( "PromptProcessing_logevent_settingVersions" );

    subscriber.setDebugLevel( 1 );

    PromptProcessing.logevent_settingVersions event = new PromptProcessing.logevent_settingVersions();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_settingVersions( event );
      if ( status == SAL_PromptProcessing.SAL__OK ) {
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
    SAL_PromptProcessing subscriber = new SAL_PromptProcessing();
    subscriber.salEvent( "PromptProcessing_logevent_appliedSettingsMatchStart" );

    subscriber.setDebugLevel( 1 );

    PromptProcessing.logevent_appliedSettingsMatchStart event = new PromptProcessing.logevent_appliedSettingsMatchStart();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_appliedSettingsMatchStart( event );
      if ( status == SAL_PromptProcessing.SAL__OK ) {
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
