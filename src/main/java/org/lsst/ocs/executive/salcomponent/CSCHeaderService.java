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
import org.lsst.sal.SAL_dmHeaderService;

/**
 *
 * CSCHeaderService is a Receiver class in the command pattern
 *
 */

public class CSCHeaderService implements CommandableSalComponent {
    
    @Override public String getName() { return "CSCHeaderService"; }

    @Override public void enterControl() { 
    
        SAL_dmHeaderService publisher = new SAL_dmHeaderService();
        publisher.salCommand( "dmHeaderService_command_EnterControl" );

        dmHeaderService.command_EnterControl command = new dmHeaderService.command_EnterControl();
        command.private_revCode = "LSST DM HeaderService enterControl COMMAND";
        command.device = "dmHeaderService";
        command.property = "enterControl";
        command.action = "set";

        int cmdId = publisher.issueCommand_EnterControl( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_EnterControl( cmdId, timeout );

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void start() { 
        
        SAL_dmHeaderService publisher = new SAL_dmHeaderService();
        publisher.salCommand( "dmHeaderService_command_Start" );

        dmHeaderService.command_Start command = new dmHeaderService.command_Start();
        command.private_revCode = "LSST DM HeaderService start COMMAND";
        command.device = "dmHeaderService";
        command.property = "start";
        command.action = "set";

        int cmdId = publisher.issueCommand_Start( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_Start( cmdId, timeout );

        // Remove the DataWriters etc
        publisher.salShutdown();
    
    }

    @Override public void enable() {

        SAL_dmHeaderService publisher = new SAL_dmHeaderService();
        publisher.salCommand( "dmHeaderService_command_Enable" );

        dmHeaderService.command_Enable command = new dmHeaderService.command_Enable();
        command.private_revCode = "LSST DM HeaderService enable COMMAND";
        command.device = "dmHeaderService";
        command.property = "enable";
        command.action = "set";

        int cmdId = publisher.issueCommand_Enable( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_Enable( cmdId, timeout );

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void disable() {

        SAL_dmHeaderService publisher = new SAL_dmHeaderService();
        publisher.salCommand( "dmHeaderService_command_Disable" );

        dmHeaderService.command_Disable command = new dmHeaderService.command_Disable();
        command.private_revCode = "LSST DM HeaderService disable COMMAND";
        command.device = "dmHeaderService";
        command.property = "disable";
        command.action = "set";

        int cmdId = publisher.issueCommand_Disable( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_Disable( cmdId, timeout );

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void standby() {

        SAL_dmHeaderService publisher = new SAL_dmHeaderService();
        publisher.salCommand( "dmHeaderService_command_Standby" );

        dmHeaderService.command_Standby command = new dmHeaderService.command_Standby();
        command.private_revCode = "LSST DM HeaderService standby COMMAND";
        command.device = "dmHeaderService";
        command.property = "standby";
        command.action = "set";

        int cmdId = publisher.issueCommand_Standby( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_Standby( cmdId, timeout );

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void exitControl() {

        SAL_dmHeaderService publisher = new SAL_dmHeaderService();
        publisher.salCommand( "dmHeaderService_command_ExitControl" );

        dmHeaderService.command_ExitControl command = new dmHeaderService.command_ExitControl();
        command.private_revCode = "LSST DM HeaderService exitControl COMMAND";
        command.device = "dmHeaderService";
        command.property = "exitControl";
        command.action = "set";

        int cmdId = publisher.issueCommand_ExitControl( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_ExitControl( cmdId, timeout );

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public Integer summaryState() {
    
        // Initialize
        SAL_dmHeaderService subscriber = new SAL_dmHeaderService();
        subscriber.salEvent( "dmHeaderService_logevent_SummaryState" );

        dmHeaderService.logevent_SummaryState event = new dmHeaderService.logevent_SummaryState();

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SummaryState( event );
            if ( status == SAL_dmHeaderService.SAL__OK ) {
                
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
        SAL_dmHeaderService subscriber = new SAL_dmHeaderService();
        subscriber.salEvent( "dmHeaderService_logevent_SettingVersions" );
        
        dmHeaderService.logevent_SettingVersions event = new dmHeaderService.logevent_SettingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SettingVersions( event );
            if ( status == SAL_dmHeaderService.SAL__OK ) {
                
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch ( InterruptedException e ) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        subscriber.salShutdown();
    }
    
    @Override public void appliedSettingsMatchStart() {
    
        // Initialize
        SAL_dmHeaderService subscriber = new SAL_dmHeaderService();
        subscriber.salEvent( "dmHeaderService_logevent_AppliedSettingsMatchStart" );
        
        dmHeaderService.logevent_AppliedSettingsMatchStart event = new dmHeaderService.logevent_AppliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_AppliedSettingsMatchStart( event );
            if ( status == SAL_dmHeaderService.SAL__OK ) {
                
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch ( InterruptedException e ) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
	  subscriber.salShutdown();
    }
}


