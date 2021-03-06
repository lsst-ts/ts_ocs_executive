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
import org.lsst.sal.SAL_camera;

/**
 * <h2>Main Camera Control System (MTCAMERA) CSC</h2>
 * <p>
 * {@code CSCMTCamera} is a (Concrete) Receiver class in the command pattern
 */
public class CSCMTCamera implements CommandableSalComponent {

  @Override
  public String getName() {
    return "CSCCamera";
  }

  @Override
  public void enterControl() {

    SAL_camera publisher = new SAL_camera();
    publisher.salCommand( "camera_command_enterControl" );

    publisher.setDebugLevel( 1 );

    camera.command_enterControl command = new camera.command_enterControl();
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
     * Remove the DataWriters etc
     */
    publisher.salShutdown();
  }

  @Override
  public void start() {

    SAL_camera publisher = new SAL_camera();
    publisher.salCommand( "camera_command_start" );

    publisher.setDebugLevel( 1 );

    camera.command_start command = new camera.command_start();
    command.private_revCode = "LSST Camera start COMMAND";
    command.device = "ccs";
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
     * Remove the DataWriters etc
     */
    publisher.salShutdown();

  }

  @Override
  public void enable() {

    SAL_camera publisher = new SAL_camera();
    publisher.salCommand( "camera_command_enable" );

    publisher.setDebugLevel( 1 );

    camera.command_enable command = new camera.command_enable();
    command.private_revCode = "LSST Camera enable COMMAND";
    command.device = "ccs";
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
     * Remove the DataWriters etc
     */
    publisher.salShutdown();
  }

  @Override
  public void disable() {

    SAL_camera publisher = new SAL_camera();
    publisher.salCommand( "camera_command_disable" );

    publisher.setDebugLevel( 1 );

    camera.command_disable command = new camera.command_disable();
    command.private_revCode = "LSST Camera disable COMMAND";
    command.device = "ccs";
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
     * Remove the DataWriters etc
     */
    publisher.salShutdown();
  }

  @Override
  public void standby() {

    SAL_camera publisher = new SAL_camera();
    publisher.salCommand( "camera_command_standby" );

    publisher.setDebugLevel( 1 );

    camera.command_standby command = new camera.command_standby();
    command.private_revCode = "LSST Camera standby COMMAND";
    command.device = "ccs";
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
     * Remove the DataWriters etc
     */
    publisher.salShutdown();
  }

  @Override
  public void exitControl() {

    SAL_camera publisher = new SAL_camera();
    publisher.salCommand( "camera_command_exitControl" );

    publisher.setDebugLevel( 1 );

    camera.command_exitControl command = new camera.command_exitControl();
    command.private_revCode = "LSST Camera exitControl COMMAND";
    command.device = "ccs";
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
     * Remove the DataWriters etc
     */
    publisher.salShutdown();
  }

  public void setFilter() {

    SAL_camera publisher = new SAL_camera();
    publisher.salCommand( "camera_command_setFilter" );

    publisher.setDebugLevel( 1 );

    camera.command_setFilter command = new camera.command_setFilter();
    command.private_revCode = "LSST TEST COMMAND";
    command.device = "ccs";
    command.property = "setFilter";
    command.action = "apply";
    command.name = "i-9";

    int cmdId = publisher.issueCommand_setFilter( command );

    try {
      Thread.sleep( 250 );
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    }

    int timeout = 4;
    publisher.waitForCompletion_setFilter( cmdId, timeout );

    /*
     * Remove the DataWriters etc
     */
    publisher.salShutdown();
  }

  public void takeImage() {

    SAL_camera publisher = new SAL_camera();
    publisher.salCommand( "camera_command_takeImages" );

    publisher.setDebugLevel( 1 );

    camera.command_takeImages command = new camera.command_takeImages();
    command.private_revCode = "LSST TEST COMMAND";
    command.device = "ccs";
    command.property = "takeImages";
    command.action = "set";
    command.numImages = (int) 2;
    command.expTime = (double) 15.0;
    command.shutter = true;
    command.science = true;
    command.guide = true;
    command.wfs = true;
    command.imageSequenceName = "testing";

    int cmdId = publisher.issueCommand_takeImages( command );

    try {
      Thread.sleep( 250 );
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    }

    int timeout = 20;
    publisher.waitForCompletion_takeImages( cmdId, timeout );

    /*
     * Remove the DataWriters etc
     */
    publisher.salShutdown();
  }

  public void initImage() {

    SAL_camera publisher = new SAL_camera();
    publisher.salCommand( "camera_command_takeImages" );

    publisher.setDebugLevel( 1 );

    camera.command_initImage command = new camera.command_initImage();
    command.private_revCode = "LSST TEST COMMAND";
    command.device = "ccs";
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
     * Remove the DataWriters etc
     */
    publisher.salShutdown();
  }

  @Override
  public Integer summaryState() {

    // Initialize
    SAL_camera subscriber = new SAL_camera();
    subscriber.salEvent( "camera_logevent_SummaryState" );

    subscriber.setDebugLevel( 1 );

    camera.logevent_SummaryState event = new camera.logevent_SummaryState();

    Integer status = SAL_camera.SAL__NO_UPDATES;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_SummaryState( event );
      if ( status == SAL_camera.SAL__OK ) {

        out.println( "=== Event Logged : " + event );
        out.println( "=== Event Status : " + status );
        out.println( "=== Event SummaryState : " + event.SummaryStateValue );

        try {
          Executive.getEntityMap()
              .get(EntityType.MTCAMERA.toString() )._modelStateTransitionQ.put( event.summaryState );

          Executive.getEntityMap()
              .get(EntityType.MTCAMERA.toString() )._viewStateTransitionQ.put( event.summaryState );
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
     * Remove the DataWriters etc
     */
    subscriber.salShutdown();

    return status;
  }

  @Override
  public void settingsVersion() {

    // Initialize
    SAL_camera subscriber = new SAL_camera();
    subscriber.salEvent( "camera_logevent_SettingVersions" );

    subscriber.setDebugLevel( 1 );

    camera.logevent_SettingVersions event = new camera.logevent_SettingVersions();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_SettingVersions( event );
      if ( status == SAL_camera.SAL__OK ) {
        out.println( "=== Event Logged : " + event );
      }

      try {
        Thread.sleep( 100 );
      } catch ( InterruptedException e ) {
        e.printStackTrace();
      }
    }

    /*
     * Remove the DataWriters etc
     */
    subscriber.salShutdown();
  }

  @Override
  public void appliedSettingsMatchStart() {

    // Initialize
    SAL_camera subscriber = new SAL_camera();
    subscriber.salEvent( "camera_logevent_AppliedSettingsMatchStart" );

    subscriber.setDebugLevel( 1 );

    camera.logevent_AppliedSettingsMatchStart event = new camera.logevent_AppliedSettingsMatchStart();

    int status;
    while ( Boolean.TRUE ) {

      status = subscriber.getEvent_AppliedSettingsMatchStart( event );
      if ( status == SAL_camera.SAL__OK ) {
        out.println( "=== Event Logged : " + event );
      }

      try {
        Thread.sleep( 100 );
      } catch ( InterruptedException e ) {
        e.printStackTrace();
      }
    }

    /*
     * Remove the DataWriters etc
     */
    subscriber.salShutdown();
  }
}
