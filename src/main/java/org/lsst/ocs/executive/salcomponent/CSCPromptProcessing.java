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
import org.lsst.sal.SAL_processingcluster;

/**
 * <h2>Data Management Processing Cluster Service CSC</h2>
 * <p>
 * {@code CSCPromptProcessing} is a (Concrete) Receiver class in the command pattern
 *
 */

public class CSCPromptProcessing implements CommandableSalComponent {
    
    @Override public String getName() { return "CSCPromptProcessing"; }

    @Override public void enterControl() { 
    
        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand( "processingcluster_command_enterControl" );

        publisher.setDebugLevel( 1 );
        
        processingcluster.command_enterControl command = new processingcluster.command_enterControl();
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

        int timeout = 3;
        publisher.waitForCompletion_enterControl( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void start() { 
        
        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand( "processingcluster_command_start" );

        publisher.setDebugLevel( 1 );
        
        processingcluster.command_start command = new processingcluster.command_start();
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

        int timeout = 3;
        publisher.waitForCompletion_start( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    
    }

    @Override public void enable() {

        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand( "processingcluster_command_enable" );

        publisher.setDebugLevel( 1 );
        
        processingcluster.command_enable command = new processingcluster.command_enable();
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

        int timeout = 3;
        publisher.waitForCompletion_enable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void disable() {

        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand( "processingcluster_command_disable" );

        publisher.setDebugLevel( 1 );
        
        processingcluster.command_disable command = new processingcluster.command_disable();
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

        int timeout = 3;
        publisher.waitForCompletion_disable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void standby() {

        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand( "processingcluster_command_standby" );

        publisher.setDebugLevel( 1 );
        
        processingcluster.command_standby command = new processingcluster.command_standby();
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

        int timeout = 3;
        publisher.waitForCompletion_standby( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void exitControl() {

        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand( "processingcluster_command_exitControl" );

        publisher.setDebugLevel( 1 );
        
        processingcluster.command_exitControl command = new processingcluster.command_exitControl();
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

        int timeout = 3;
        publisher.waitForCompletion_exitControl( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public Integer summaryState() {
    
        // Initialize
        SAL_processingcluster subscriber = new SAL_processingcluster();
        subscriber.salEvent( "dmHeaderService_logevent_SummaryState" );

        subscriber.setDebugLevel( 1 );
        
        processingcluster.logevent_SummaryState event = new processingcluster.logevent_SummaryState();

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SummaryState( event );
            if ( status == SAL_processingcluster.SAL__OK ) {
                
                out.println("=== Event Logged : " + event);

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
        SAL_processingcluster subscriber = new SAL_processingcluster();
        subscriber.salEvent( "processingcluster_logevent_SettingVersions" );
        
        subscriber.setDebugLevel( 1 );
        
        processingcluster.logevent_SettingVersions event = new processingcluster.logevent_SettingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SettingVersions( event );
            if ( status == SAL_processingcluster.SAL__OK ) {
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
        SAL_processingcluster subscriber = new SAL_processingcluster();
        subscriber.salEvent( "processingcluster_logevent_AppliedSettingsMatchStart" );
        
        subscriber.setDebugLevel( 1 );
        
        processingcluster.logevent_AppliedSettingsMatchStart event = new processingcluster.logevent_AppliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_AppliedSettingsMatchStart( event );
            if ( status == SAL_processingcluster.SAL__OK ) {
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

