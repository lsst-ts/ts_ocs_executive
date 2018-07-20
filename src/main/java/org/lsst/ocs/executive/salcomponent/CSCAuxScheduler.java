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
import org.lsst.sal.SAL_atScheduler;
import org.lsst.sal.SAL_scheduler;

/**
 * <h2>OCS Auxiliary Scheduler CSC</h2>
 *
 * {@code CSCAuxScheduler} is a (Concrete) Receiver class in the command pattern
 */
public class CSCAuxScheduler implements CommandableSalComponent {

    @Override public String getName() { return "CSCAuxScheduler"; }

    @Override public void enterControl() {

        SAL_atScheduler publisher = new SAL_atScheduler();
        publisher.salCommand( "atScheduler_command_enterControl" );

        publisher.setDebugLevel( 1 );
        
        atScheduler.command_enterControl command = new atScheduler.command_enterControl();
        command.private_revCode = "LSST Aux Scheduler enterControl COMMAND";
        command.device = "atScheduler";
        command.property = "state";
        command.action = "enterControl";
        command.tempValue = true;

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

        SAL_atScheduler publisher = new SAL_atScheduler();
        publisher.salCommand( "atScheduler_command_start" );

        publisher.setDebugLevel( 1 );
        
        atScheduler.command_start command = new atScheduler.command_start();
        command.private_revCode = "LSST Aux Scheduler start COMMAND";
        command.device = "atScheduler";
        command.property = "state";

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

        SAL_atScheduler publisher = new SAL_atScheduler();
        publisher.salCommand( "atScheduler_command_enable" );

        publisher.setDebugLevel( 1 );
        
        atScheduler.command_enable command = new atScheduler.command_enable();
        command.private_revCode = "LSST Aux Scheduler enable COMMAND";
        command.device = "atScheduler";
        command.property = "state";
        command.action = "enable";
        command.tempValue = true;

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

        SAL_atScheduler publisher = new SAL_atScheduler();
        publisher.salCommand( "atScheduler_command_disable" );

        publisher.setDebugLevel( 1 );
        
        atScheduler.command_disable command = new atScheduler.command_disable();
        command.private_revCode = "LSST Aux Scheduler disable COMMAND";
        command.device = "atScheduler";
        command.property = "state";
        command.action = "disable";
        command.tempValue = true;

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

        SAL_atScheduler publisher = new SAL_atScheduler();
        publisher.salCommand( "atScheduler_command_standby" );

        publisher.setDebugLevel( 1 );
        
        atScheduler.command_standby command = new atScheduler.command_standby();
        command.private_revCode = "LSST Aux Scheduler standby COMMAND";
        command.device = "atScheduler";
        command.property = "state";
        command.action = "standby";
        command.tempValue = true;

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

        SAL_atScheduler publisher = new SAL_atScheduler();
        publisher.salCommand( "atScheduler_command_exitControl" );

        publisher.setDebugLevel( 1 );
        
        atScheduler.command_exitControl command = new atScheduler.command_exitControl();
        command.private_revCode = "LSST Aux Scheduler exitControl COMMAND";
        command.device = "atScheduler";
        command.property = "state";
        command.action = "exitControl";
        command.tempValue = true;

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
        SAL_atScheduler subscriber = new SAL_atScheduler();
        subscriber.salEvent( "atScheduler_logevent_SummaryState" );

        subscriber.setDebugLevel( 1 );
        
        atScheduler.logevent_summaryState event = new atScheduler.logevent_summaryState();

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_summaryState( event );
            if ( status == SAL_scheduler.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
                out.println( "=== Event Status : " + status );
                out.println( "=== Event SummaryState : " + event.summaryState );
                
                try {
                    Executive.getEntityMap().get( "asc" )._stateTransitionQ.put( event.summaryState );
                    Executive.getEntityMap().get( "asc" )._guiStateTransitionQ.put( event.summaryState );
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
        SAL_atScheduler subscriber = new SAL_atScheduler();
        subscriber.salEvent( "atScheduler_logevent_SettingVersions" );

        subscriber.setDebugLevel( 1 );
        
        atScheduler.logevent_settingVersions event = new atScheduler.logevent_settingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_settingVersions( event );
            if ( status == SAL_atScheduler.SAL__OK ) {
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
        SAL_atScheduler subscriber = new SAL_atScheduler();
        subscriber.salEvent( "atScheduler_logevent_AppliedSettingsMatchStart" );

        subscriber.setDebugLevel( 1 );
        
        atScheduler.logevent_appliedSettingsMatchStart event = new atScheduler.logevent_appliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_appliedSettingsMatchStart( event );
            if ( status == SAL_atScheduler.SAL__OK ) {
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
