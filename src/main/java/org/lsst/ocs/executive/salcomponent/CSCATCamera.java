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
import org.lsst.ocs.utilities.SequenceIdGenerator;
import org.lsst.sal.SAL_ATCamera;

import static java.lang.System.out;

/**
 * <h2>Auxiliary Camera Control System (ATCAMERA) CSC</h2>
 * <p>
 * {@code CSCATCamera} is a (Concrete) Receiver class in the command pattern
 */
public class CSCATCamera implements CommandableSalComponent {

  static final SequenceIdGenerator seqIdGen = SequenceIdGenerator.getInstance();

  @Override
  public String getName() {
    return "CSCATCamera";
  }

  /////////////////////////////////////////////////////
  // SAL middle-ware Commands
  /////////////////////////////////////////////////////

  @Override
  public void enterControl( Object[] args ) {

    SAL_ATCamera publisher = new SAL_ATCamera();
    publisher.salCommand( "ATCamera_command_enterControl" );

    publisher.setDebugLevel( 1 );

    ATCamera.command_enterControl command = new ATCamera.command_enterControl();
    command.private_revCode = "LSST ATCamera enterControl COMMAND";
    command.device = "ATCamera";
    command.property = "state";
    command.action = "enterControl";
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

    SAL_ATCamera publisher = new SAL_ATCamera();
    publisher.salCommand( "ATCamera_command_start" );

    publisher.setDebugLevel( 1 );

    ATCamera.command_start command = new ATCamera.command_start();
    command.private_revCode = "LSST ATCamera enterControl COMMAND";
    command.device = "ATCamera";
    command.property = "state";
    command.action = "start";

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

    SAL_ATCamera publisher = new SAL_ATCamera();
    publisher.salCommand( "ATCamera_command_enable" );

    publisher.setDebugLevel( 1 );

    ATCamera.command_enable command = new ATCamera.command_enable();
    command.private_revCode = "LSST ATCamera enterControl COMMAND";
    command.device = "ATCamera";
    command.property = "state";
    command.action = "enable";
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

    SAL_ATCamera publisher = new SAL_ATCamera();
    publisher.salCommand( "ATCamera_command_disable" );

    publisher.setDebugLevel( 1 );

    ATCamera.command_disable command = new ATCamera.command_disable();
    command.private_revCode = "LSST ATCamera enterControl COMMAND";
    command.device = "ATCamera";
    command.property = "state";
    command.action = "disable";
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

    SAL_ATCamera publisher = new SAL_ATCamera();
    publisher.salCommand( "ATCamera_command_standby" );

    publisher.setDebugLevel( 1 );

    ATCamera.command_standby command = new ATCamera.command_standby();
    command.private_revCode = "LSST ATCamera enterControl COMMAND";
    command.device = "ATCamera";
    command.property = "state";
    command.action = "standby";
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

    SAL_ATCamera publisher = new SAL_ATCamera();
    publisher.salCommand( "ATCamera_command_exitControl" );

    publisher.setDebugLevel( 1 );

    ATCamera.command_exitControl command = new ATCamera.command_exitControl();
    command.private_revCode = "LSST ATCamera enterControl COMMAND";
    command.device = "ATCamera";
    command.property = "state";
    command.action = "exitControl";
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

  public void takeImage( Object[] args ) {

    SAL_ATCamera publisher = new SAL_ATCamera();
    publisher.salCommand( "ATCamera_command_takeImages" );

    publisher.setDebugLevel( 1 );

    ATCamera.command_takeImages command = new ATCamera.command_takeImages();
    command.private_revCode = "LSST TEST COMMAND";
    command.device = "accs";
    command.property = "takeImages";
    command.action = "set";

    command.imageSequenceName = (String) args[0] + seqIdGen.getNextId();

    command.numImages = (int) args[1];
    command.expTime = (double) args[2];

    command.shutter = (boolean) args[3];
    command.science = (boolean) args[4];
    command.guide = (boolean) args[5];
    command.wfs = (boolean) args[6];

//        out.println( "imageSequenceName: " + command.imageSequenceName );
//        out.println( "shutter: " + command.shutter  );
//        out.println( "science: " + command.science  );
    int cmdId = publisher.issueCommand_takeImages( command );

    try {
      Thread.sleep( 250 );
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    }

    int timeout = 20;
    publisher.waitForCompletion_takeImages( cmdId, timeout );

    /*
          * cleanup
          */
    publisher.salShutdown();
  }

  public void initImage( Object[] args ) {

    SAL_ATCamera publisher = new SAL_ATCamera();
    publisher.salCommand( "ATCamera_command_takeImages" );

    publisher.setDebugLevel( 1 );

    ATCamera.command_initImage command = new ATCamera.command_initImage();
    command.private_revCode = "LSST TEST COMMAND";
    command.device = "accs";
    command.property = "initImage";
    command.action = "apply";

    command.deltaT = (double) 5.0;

    int cmdId = publisher.issueCommand_initImage( command );

    try {
      Thread.sleep( 250 );
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    }

    int timeout = 4;
    publisher.waitForCompletion_initImage( cmdId, timeout );

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
    SAL_ATCamera subscriber = new SAL_ATCamera();
    subscriber.salEvent( "ATCamera_logevent_summaryState" );
    subscriber.setDebugLevel( 1 );

    ATCamera.logevent_summaryState event = new ATCamera.logevent_summaryState();

    Integer status = SAL_ATCamera.SAL__NO_UPDATES;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_summaryState( event );
      if ( status == SAL_ATCamera.SAL__OK ) {

        out.println( "=== Event Logged : " + event );
        out.println( "=== Event Status : " + status );
        out.println( "=== Event SummaryState : " + event.summaryState );

        try {
          Executive.getEntityMap()
              .get(EntityType.ATCAMERA.toSubString() )._modelStateTransitionQ.put( event.summaryState );

          Executive.getEntityMap()
              .get(EntityType.ATCAMERA.toSubString() )._viewStateTransitionQ.put( event.summaryState );
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
    SAL_ATCamera subscriber = new SAL_ATCamera();
    subscriber.salEvent( "ATCamera_logevent_settingVersions" );

    subscriber.setDebugLevel( 1 );

    ATCamera.logevent_settingVersions event = new ATCamera.logevent_settingVersions();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_settingVersions( event );
      if ( status == SAL_ATCamera.SAL__OK ) {

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
    SAL_ATCamera subscriber = new SAL_ATCamera();
    subscriber.salEvent( "ATCamera_logevent_appliedSettingsMatchStart" );

    subscriber.setDebugLevel( 1 );

    ATCamera.logevent_appliedSettingsMatchStart event
        = new ATCamera.logevent_appliedSettingsMatchStart();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_appliedSettingsMatchStart( event );
      if ( status == SAL_ATCamera.SAL__OK ) {

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
