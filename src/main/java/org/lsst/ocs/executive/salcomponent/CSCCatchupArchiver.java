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
import org.lsst.sal.SAL_catchuparchiver;

/**
 *
 * CSCCatchupArchiver is a Receiver class in the command pattern
 *
 */

public class CSCCatchupArchiver implements CommandableSalComponent {
    
    @Override public String getName() { return "CSCCatchupArchiver"; }
    
    @Override public void enterControl() { 
    
        SAL_catchuparchiver publisher = new SAL_catchuparchiver();
        publisher.salCommand( "catchuparchiver_command_enterControl" );

        catchuparchiver.command_enterControl command = new catchuparchiver.command_enterControl();
        command.private_revCode = "LSST CatchupArchiver enterControl COMMAND";
        command.device = "catchuparchiver";
        command.property = "enterControl";
        command.action = "set";
        command.state = true;

        int cmdId = publisher.issueCommand_enterControl( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_enterControl( cmdId, timeout );

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void start() { 
        
        SAL_catchuparchiver publisher = new SAL_catchuparchiver();
        publisher.salCommand( "catchuparchiver_command_start" );

        catchuparchiver.command_start command = new catchuparchiver.command_start();
        command.private_revCode = "LSST CatchupArchiver start COMMAND";
        command.device = "catchuparchiver";
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

        // Remove the DataWriters etc
        publisher.salShutdown();
    
    }

    @Override public void enable() {

        SAL_catchuparchiver publisher = new SAL_catchuparchiver();
        publisher.salCommand( "catchuparchiver_command_enable" );

        catchuparchiver.command_enable command = new catchuparchiver.command_enable();
        command.private_revCode = "LSST CatchupArchiver enable COMMAND";
        command.device = "catchuparchiver";
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

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void disable() {

        SAL_catchuparchiver publisher = new SAL_catchuparchiver();
        publisher.salCommand( "catchuparchiver_command_disable" );

        catchuparchiver.command_disable command = new catchuparchiver.command_disable();
        command.private_revCode = "LSST CatchupArchiver disable COMMAND";
        command.device = "catchuparchiver";
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

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void standby() {

        SAL_catchuparchiver publisher = new SAL_catchuparchiver();
        publisher.salCommand( "catchuparchiver_command_standby" );

        catchuparchiver.command_standby command = new catchuparchiver.command_standby();
        command.private_revCode = "LSST CatchupArchiver standby COMMAND";
        command.device = "catchuparchiver";
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

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void exitControl() {

        SAL_catchuparchiver publisher = new SAL_catchuparchiver();
        publisher.salCommand( "catchuparchiver_command_exitControl" );

        catchuparchiver.command_exitControl command = new catchuparchiver.command_exitControl();
        command.private_revCode = "LSST CatchupArchiver exitControl COMMAND";
        command.device = "catchuparchiver";
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

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public Integer summaryState() {
    
        // Initialize
        SAL_catchuparchiver subscriber = new SAL_catchuparchiver();
        subscriber.salEvent( "catchuparchiver_logevent_SummaryState" );

        catchuparchiver.logevent_SummaryState event = new catchuparchiver.logevent_SummaryState();

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SummaryState( event );
            if ( status == SAL_catchuparchiver.SAL__OK ) {
                
                out.println("=== Event Logged : " + event);

                /* Remove the DataWriters etc */
                subscriber.salShutdown();
                return status;
            }

            try { Thread.sleep(100); } catch ( InterruptedException e ) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        subscriber.salShutdown();

        return status;
    }

    @Override public void settingsVersion() {
    
        // Initialize
        SAL_catchuparchiver subscriber = new SAL_catchuparchiver();
        subscriber.salEvent( "catchuparchiver_logevent_SettingVersions" );
        
        catchuparchiver.logevent_SettingVersions event = new catchuparchiver.logevent_SettingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SettingVersions( event );
            if ( status == SAL_catchuparchiver.SAL__OK ) {
                
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch ( InterruptedException e ) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        subscriber.salShutdown();
    }
    
    @Override public void appliedSettingsMatchStart() {
    
        // Initialize
        SAL_catchuparchiver subscriber = new SAL_catchuparchiver();
        subscriber.salEvent( "catchuparchiver_logevent_AppliedSettingsMatchStart" );
        
        catchuparchiver.logevent_AppliedSettingsMatchStart event = new catchuparchiver.logevent_AppliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_AppliedSettingsMatchStart( event );
            if ( status == SAL_catchuparchiver.SAL__OK ) {
                
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch ( InterruptedException e ) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
	  subscriber.salShutdown();
    }
}


