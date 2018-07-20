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
import org.lsst.ocs.executive.Executive;
import org.lsst.sal.SAL_atcamera;

import org.lsst.sal.SAL_scheduler;

/**
 * <h2>Auxiliary Camera Control System (ACCS) CSC</h2>
 *
 * {@code CSCAuxCamera} is a (Concrete) Receiver class in the command pattern
 */
public class CSCAuxCamera implements CommandableSalComponent {

    @Override public String getName() { return "CSCAuxCamera"; }

    @Override public void enterControl() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_enterControl" );

        publisher.setDebugLevel( 1 );
        
        atcamera.command_enterControl command = new atcamera.command_enterControl();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
        command.property = "state";
        command.action = "enterControl";
        command.state = true;

        int cmdId = publisher.issueCommand_enterControl( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_enterControl( cmdId, timeout );

        SAL_atcamera cmd = new SAL_atcamera();
        cmd.salProcessor("atcamera_command_enterControl");
	atcamera.command_enterControl command2 = new atcamera.command_enterControl();   
        cmd.acceptCommand_enterControl(command2);
        cmd.salShutdown();
        //publisher.flushSamples( command );
        /* Remove the DataWriters etc */

        publisher.salShutdown();
    }

    @Override public void start() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_start" );

        publisher.setDebugLevel( 1 );

        atcamera.command_start command = new atcamera.command_start();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();

    }

    @Override public void enable() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_enable" );

        publisher.setDebugLevel( 1 );

        atcamera.command_enable command = new atcamera.command_enable();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
        command.property = "state";
        command.action = "enable";
        command.state = true;

        int cmdId = publisher.issueCommand_enable( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_enable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void disable() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_disable" );

        publisher.setDebugLevel( 1 );

        atcamera.command_disable command = new atcamera.command_disable();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
        command.property = "state";
        command.action = "disable";
        command.state = true;

        int cmdId = publisher.issueCommand_disable( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_disable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void standby() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_standby" );

        publisher.setDebugLevel( 1 );

        atcamera.command_standby command = new atcamera.command_standby();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
        command.property = "state";
        command.action = "standby";
        command.state = true;

        int cmdId = publisher.issueCommand_standby( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_standby( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void exitControl() {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_exitControl" );

        publisher.setDebugLevel( 1 );

        atcamera.command_exitControl command = new atcamera.command_exitControl();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
        command.property = "state";
        command.action = "exitControl";
        command.state = true;

        int cmdId = publisher.issueCommand_exitControl( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
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

        int timeout = 4;
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

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SummaryState( event );
            if ( status == SAL_scheduler.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
                out.println( "=== Event Status : " + status );
                out.println( "=== Event SummaryState : " + event.SummaryStateValue );
                
                try {
                    Executive.getEntityMap().get( "acc" )._stateTransitionQ.put( event.SummaryStateValue );
                    Executive.getEntityMap().get( "acc" )._guiStateTransitionQ.put( event.SummaryStateValue );
                } catch ( InterruptedException ie ) {
                    ie.printStackTrace( out.printf( "GOOD SummaryState" ));
                }
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

        atcamera.logevent_AppliedSettingsMatchStart event =
            new atcamera.logevent_AppliedSettingsMatchStart();

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
