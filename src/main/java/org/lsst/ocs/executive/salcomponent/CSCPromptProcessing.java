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
import org.lsst.sal.SAL_promptProcessing;

import org.lsst.sal.SAL_scheduler;

/**
 * <h2>Data Management Prompt Processing Service CSC</h2>
 *
 * {@code CSCPromptProcessing} is a (Concrete) Receiver class in the command pattern
 */
public class CSCPromptProcessing implements CommandableSalComponent {
    
    @Override public String getName() { return "CSCPromptProcessing"; }

    @Override public void enterControl() { 
    
        SAL_promptProcessing publisher = new SAL_promptProcessing();
        publisher.salCommand( "promptprocessing_command_enterControl" );

        publisher.setDebugLevel( 1 );
        
        promptProcessing.command_enterControl command = new promptProcessing.command_enterControl();
        command.private_revCode = "LSST ProcessingCluster enterControl COMMAND";
        command.device = "promptprocessing";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void start() { 
        
        SAL_promptProcessing publisher = new SAL_promptProcessing();
        publisher.salCommand( "promptprocessing_command_start" );

        publisher.setDebugLevel( 1 );
        
        promptProcessing.command_start command = new promptProcessing.command_start();
        command.private_revCode = "LSST PromptProcessing start COMMAND";
        command.device = "promptprocessing";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    
    }

    @Override public void enable() {

        SAL_promptProcessing publisher = new SAL_promptProcessing();
        publisher.salCommand( "promptprocessing_command_enable" );

        publisher.setDebugLevel( 1 );
        
        promptProcessing.command_enable command = new promptProcessing.command_enable();
        command.private_revCode = "LSST PromptProcessing enable COMMAND";
        command.device = "promptprocessing";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void disable() {

        SAL_promptProcessing publisher = new SAL_promptProcessing();
        publisher.salCommand( "promptprocessing_command_disable" );

        publisher.setDebugLevel( 1 );
        
        promptProcessing.command_disable command = new promptProcessing.command_disable();
        command.private_revCode = "LSST PromptProcessing disable COMMAND";
        command.device = "promptprocessing";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void standby() {

        SAL_promptProcessing publisher = new SAL_promptProcessing();
        publisher.salCommand( "promptprocessing_command_standby" );

        publisher.setDebugLevel( 1 );
        
        promptProcessing.command_standby command = new promptProcessing.command_standby();
        command.private_revCode = "LSST PromptProcessing standby COMMAND";
        command.device = "promptprocessing";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void exitControl() {

        SAL_promptProcessing publisher = new SAL_promptProcessing();
        publisher.salCommand( "promptprocessing_command_exitControl" );

        publisher.setDebugLevel( 1 );
        
        promptProcessing.command_exitControl command = new promptProcessing.command_exitControl();
        command.private_revCode = "LSST PromptProcessing exitControl COMMAND";
        command.device = "promptprocessing";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public Integer summaryState() {
    
        // Initialize
        SAL_promptProcessing subscriber = new SAL_promptProcessing();
        subscriber.salEvent( "dmHeaderService_logevent_SummaryState" );

        subscriber.setDebugLevel( 1 );
        
        promptProcessing.logevent_SummaryState event = new promptProcessing.logevent_SummaryState();

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SummaryState( event );
            if ( status == SAL_scheduler.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
                out.println( "=== Event Status : " + status );
                out.println( "=== Event SummaryState : " + event.SummaryStateValue );
                
                try {
                    Executive.getEntityMap().get( "pro" )._stateTransitionQ.put( event.SummaryStateValue );
                    Executive.getEntityMap().get( "pro" )._guiStateTransitionQ.put( event.SummaryStateValue );
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
        SAL_promptProcessing subscriber = new SAL_promptProcessing();
        subscriber.salEvent( "promptprocessing_logevent_SettingVersions" );
        
        subscriber.setDebugLevel( 1 );
        
        promptProcessing.logevent_SettingVersions event = new promptProcessing.logevent_SettingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SettingVersions( event );
            if ( status == SAL_promptProcessing.SAL__OK ) {
                out.println("=== Event Logged : " + event);
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
        SAL_promptProcessing subscriber = new SAL_promptProcessing();
        subscriber.salEvent( "promptprocessing_logevent_AppliedSettingsMatchStart" );
        
        subscriber.setDebugLevel( 1 );
        
        promptProcessing.logevent_AppliedSettingsMatchStart event = new promptProcessing.logevent_AppliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_AppliedSettingsMatchStart( event );
            if ( status == SAL_promptProcessing.SAL__OK ) {
                out.println("=== Event Logged : " + event);
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


