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
import org.lsst.sal.SAL_atHeaderService;

import static java.lang.System.out;

/**
 * <h2>Data Management Header Service CSC</h2>
 *
 * {@code CSCHeaderService} is a (Concrete) Receiver class in the command pattern
 */
public class CSCAuxHeaderService implements CommandableSalComponent {
    
    @Override public String getName() { return "CSCAuxHeaderService"; }

    @Override public void enterControl( Object [] args ) { 
    
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

    @Override public void start( Object [] args ) { 
        
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

    @Override public void enable( Object [] args ) {

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

    @Override public void disable( Object [] args ) {

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

    @Override public void standby( Object [] args ) {

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

    @Override public void exitControl( Object [] args ) {

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
        subscriber.salEvent( "atHeaderService_logevent_summaryState" );

        subscriber.setDebugLevel( 1 );
        
        atHeaderService.logevent_summaryState event = new atHeaderService.logevent_summaryState();

        Integer status = SAL_atHeaderService.SAL__NO_UPDATES;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_summaryState( event );
            if ( status == SAL_atHeaderService.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
                out.println( "=== Event Status : " + status );
                out.println( "=== Event SummaryState : " + event.summaryState );
                
                try {
                    Executive.getEntityMap()
                             .get( EntityType.AHEADERSERVICE.toString() )
                             ._modelStateTransitionQ.put( event.summaryState );
                    
                    Executive.getEntityMap()
                             .get( EntityType.AHEADERSERVICE.toString() )
                             ._viewStateTransitionQ.put( event.summaryState );
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
        
        atHeaderService.logevent_settingVersions event = 
            new atHeaderService.logevent_settingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_settingVersions( event );
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
        
        atHeaderService.logevent_appliedSettingsMatchStart event = 
            new atHeaderService.logevent_appliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_appliedSettingsMatchStart( event );
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


