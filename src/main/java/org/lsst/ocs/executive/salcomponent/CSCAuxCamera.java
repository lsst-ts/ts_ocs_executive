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

import org.lsst.sal.SAL_atcamera;
import static java.lang.System.out;

/**
 * <h2>Auxiliary Camera Control System (ACCS) CSC</h2>
 * <p>
 * {@code CSCAuxCamera} is a (Concrete) Receiver class in the command pattern
 * 
 */

public class CSCAuxCamera implements CommandableSalComponent {

    @Override public String getName() { return "CSCAuxCamera"; }

    @Override public void enterControl() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_enterControl" );

        publisher.setDebugLevel( 1 );
        
        atcamera.command_enterControl command = new atcamera.command_enterControl();
        command.state = true;

        int cmdId = publisher.issueCommand_enterControl( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_enterControl( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void start() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_start" );

        publisher.setDebugLevel( 1 );

        atcamera.command_start command = new atcamera.command_start();
        command.private_revCode = "LSST Camera start COMMAND";
        command.device = "accs";
        command.property = "start";
        command.action = "set";
        command.configuration = "normal";

        int cmdId = publisher.issueCommand_start( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_start( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();

    }

    @Override public void enable() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_enable" );

        publisher.setDebugLevel( 1 );

        atcamera.command_enable command = new atcamera.command_enable();
        command.private_revCode = "LSST Camera enable COMMAND";
        command.device = "accs";
        command.property = "enable";
        command.action = "set";
        command.state = true;

        int cmdId = publisher.issueCommand_enable( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_enable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void disable() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_disable" );

        publisher.setDebugLevel( 1 );

        atcamera.command_disable command = new atcamera.command_disable();
        command.private_revCode = "LSST Camera disable COMMAND";
        command.device = "accs";
        command.property = "disable";
        command.action = "set";
        command.state = true;

        int cmdId = publisher.issueCommand_disable( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_disable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void standby() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_standby" );

        publisher.setDebugLevel( 1 );

        atcamera.command_standby command = new atcamera.command_standby();
        command.private_revCode = "LSST Camera standby COMMAND";
        command.device = "accs";
        command.property = "standby";
        command.action = "set";
        command.state = true;

        int cmdId = publisher.issueCommand_standby( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_standby( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void exitControl() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_exitControl" );

        publisher.setDebugLevel( 1 );

        atcamera.command_exitControl command = new atcamera.command_exitControl();
        command.private_revCode = "LSST Camera exitControl COMMAND";
        command.device = "accs";
        command.property = "exitControl";
        command.action = "set";
        command.state = true;

        int cmdId = publisher.issueCommand_exitControl( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_exitControl( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    public void takeImage() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_takeImages" );

        publisher.setDebugLevel( 1 );

        atcamera.command_takeImages command  = new atcamera.command_takeImages();
        command.private_revCode = "LSST TEST COMMAND";
        command.device   = "accs";
        command.property = "takeImages";
        command.action   = "set";
        command.numImages = (int) 2;
        command.expTime = (double) 15.0;
        command.shutter = true;
        command.science = true;
        command.guide = true;
        command.wfs = true;
        command.imageSequenceName = "testing";

        int cmdId = publisher.issueCommand_takeImages(command);

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 20;
        publisher.waitForCompletion_takeImages( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }
   
    public void initImage() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_takeImages" );

        publisher.setDebugLevel( 1 );

        atcamera.command_initImage command  = new atcamera.command_initImage();
        command.private_revCode = "LSST TEST COMMAND";
        command.device   = "accs";
        command.property = "initImage";
        command.action   = "apply";
        command.deltaT = (double) 5.0;

        int cmdId = publisher.issueCommand_initImage(command);

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_initImage( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }
   
    @Override public Integer summaryState() {

        // Initialize
        SAL_atcamera subscriber = new SAL_atcamera();
        subscriber.salEvent( "atcamera_logevent_SummaryState" );

        subscriber.setDebugLevel( 1 );

        atcamera.logevent_SummaryState event = new atcamera.logevent_SummaryState();

//        out.print( this.getClass()
//            .getSimpleName() + "::"
//                   + Thread.currentThread().getStackTrace()[1].getMethodName()
//                   + "::" );
//        Thread.currentThread().setName( new String().concat( "CSCCameraSummaryStateThread" ) );
//        out.print( Thread.currentThread().getName() );
//        out.println( " " + "id: " + Thread.currentThread().getId() );

//        out.println( "Camera Event SummaryState logger ready " );

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SummaryState( event );
            if ( status == SAL_atcamera.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
                
                /* Remove the DataWriters etc */
                subscriber.salShutdown();
                
                return status;
            }

            try {
                Thread.sleep( 100 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        /* Remove the DataWriters etc */
        subscriber.salShutdown();
        
        return status;
    }

    @Override public void settingsVersion() {
        
        // Initialize
        SAL_atcamera subscriber = new SAL_atcamera();
        subscriber.salEvent( "atcamera_logevent_SettingVersions" );

        subscriber.setDebugLevel( 1 );

        atcamera.logevent_SettingVersions event = new atcamera.logevent_SettingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SettingVersions( event );
            if ( status == SAL_atcamera.SAL__OK ) {
                out.println( "=== Event Logged : " + event );
            }

            try {
                Thread.sleep( 100 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        /* Remove the DataWriters etc */
        subscriber.salShutdown();
    }

    @Override public void appliedSettingsMatchStart() {

        // Initialize
        SAL_atcamera subscriber = new SAL_atcamera();
        subscriber.salEvent( "atcamera_logevent_AppliedSettingsMatchStart" );

        subscriber.setDebugLevel( 1 );

        atcamera.logevent_AppliedSettingsMatchStart event = new atcamera.logevent_AppliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_AppliedSettingsMatchStart( event );
            if ( status == SAL_atcamera.SAL__OK ) {
                out.println( "=== Event Logged : " + event );
            }

            try {
                Thread.sleep( 100 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        /* Remove the DataWriters etc */
        subscriber.salShutdown();
    }
}
