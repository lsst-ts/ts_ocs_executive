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
import org.lsst.sal.SAL_atHeaderService;

import org.lsst.sal.SAL_scheduler;

/**
 * <h2>Data Management Header Service CSC</h2>
 *
 * {@code CSCHeaderService} is a (Concrete) Receiver class in the command pattern
 */
public class CSCAuxHeaderService implements CommandableSalComponent {
    
    @Override public String getName() { return "CSCAuxHeaderService"; }

    @Override public void enterControl() { 
    
        SAL_atHeaderService publisher = new SAL_atHeaderService();
        publisher.salCommand( "atHeaderService_command_enterControl" );

        publisher.setDebugLevel( 1 );
        
        atHeaderService.command_enterControl command = new atHeaderService.command_enterControl();
        command.private_revCode = "LSST DM HeaderService enterControl COMMAND";
        command.device = "auxHeaderService";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void start() { 
        
        SAL_atHeaderService publisher = new SAL_atHeaderService();
        publisher.salCommand( "atHeaderService_command_start" );

        publisher.setDebugLevel( 1 );
        
        atHeaderService.command_start command = new atHeaderService.command_start();
        command.private_revCode = "LSST DM HeaderService start COMMAND";
        command.device = "auxHeaderService";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    
    }

    @Override public void enable() {

        SAL_atHeaderService publisher = new SAL_atHeaderService();
        publisher.salCommand( "atHeaderService_command_enable" );

        publisher.setDebugLevel( 1 );
        
        atHeaderService.command_enable command = new atHeaderService.command_enable();
        command.private_revCode = "LSST DM HeaderService enable COMMAND";
        command.device = "auxHeaderService";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void disable() {

        SAL_atHeaderService publisher = new SAL_atHeaderService();
        publisher.salCommand( "atHeaderService_command_disable" );

        publisher.setDebugLevel( 1 );
        
        atHeaderService.command_disable command = new atHeaderService.command_disable();
        command.private_revCode = "LSST DM HeaderService disable COMMAND";
        command.device = "auxHeaderService";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void standby() {

        SAL_atHeaderService publisher = new SAL_atHeaderService();
        publisher.salCommand( "atHeaderService_command_standby" );

        publisher.setDebugLevel( 1 );
        
        atHeaderService.command_standby command = new atHeaderService.command_standby();
        command.private_revCode = "LSST DM HeaderService standby COMMAND";
        command.device = "auxHeaderService";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void exitControl() {

        SAL_atHeaderService publisher = new SAL_atHeaderService();
        publisher.salCommand( "atHeaderService_command_exitControl" );

        publisher.setDebugLevel( 1 );
        
        atHeaderService.command_exitControl command = new atHeaderService.command_exitControl();
        command.private_revCode = "LSST DM HeaderService exitControl COMMAND";
        command.device = "auxHeaderService";
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

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public Integer summaryState() {
    
        // Initialize
        SAL_atHeaderService subscriber = new SAL_atHeaderService();
        subscriber.salEvent( "atHeaderService_logevent_SummaryState" );

        subscriber.setDebugLevel( 1 );
        
        atHeaderService.logevent_SummaryState event = new atHeaderService.logevent_SummaryState();

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SummaryState( event );
            if ( status == SAL_scheduler.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
                out.println( "=== Event Status : " + status );
                out.println( "=== Event SummaryState : " + event.summaryState );
                
                try {
                    Executive.getEntityMap().get( "ahd" )._stateTransitionQ.put( event.summaryState );
                    Executive.getEntityMap().get( "ahd" )._guiStateTransitionQ.put( event.summaryState );
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
        SAL_atHeaderService subscriber = new SAL_atHeaderService();
        subscriber.salEvent( "atHeaderService_logevent_SettingVersions" );
        
        subscriber.setDebugLevel( 1 );
        
        atHeaderService.logevent_SettingVersions event = 
            new atHeaderService.logevent_SettingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SettingVersions( event );
            if ( status == SAL_atHeaderService.SAL__OK ) {
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
        SAL_atHeaderService subscriber = new SAL_atHeaderService();
        subscriber.salEvent( "atHeaderService_logevent_AppliedSettingsMatchStart" );
        
        subscriber.setDebugLevel( 1 );
        
        atHeaderService.logevent_AppliedSettingsMatchStart event = 
            new atHeaderService.logevent_AppliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_AppliedSettingsMatchStart( event );
            if ( status == SAL_atHeaderService.SAL__OK ) {
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


