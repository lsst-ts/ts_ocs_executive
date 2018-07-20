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

import org.lsst.sal.SAL_scheduler;

import org.lsst.ocs.executive.Executive;

import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h2>OCS Main Scheduler CSC</h2>
 *
 * {@code CSCScheduler} is a (Concrete) Receiver class in the command pattern
 */
public class CSCScheduler implements CommandableSalComponent {

    
    
    @Override public String getName() { return "CSCScheduler"; }

    @Override public void enterControl() {

        SAL_scheduler publisher = new SAL_scheduler();
        publisher.salCommand( "scheduler_command_enterControl" );

        publisher.setDebugLevel( 1 );
        
        scheduler.command_enterControl command = new scheduler.command_enterControl();
        command.private_revCode = "LSST Archiver enterControl COMMAND";
        command.device = "scheduler";
        command.property = "enterControl";
        command.action = "set";
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

        SAL_scheduler publisher = new SAL_scheduler();
        publisher.salCommand( "scheduler_command_start" );

        publisher.setDebugLevel( 1 );
        
        scheduler.command_start command = new scheduler.command_start();
        command.private_revCode = "LSST Archiver start COMMAND";
        command.device = "configuration";
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

        SAL_scheduler publisher = new SAL_scheduler();
        publisher.salCommand( "scheduler_command_enable" );

        publisher.setDebugLevel( 1 );
        
        scheduler.command_enable command = new scheduler.command_enable();
        command.private_revCode = "LSST Archiver enable COMMAND";
        command.device = "scheduler";
        command.property = "enable";
        command.action = "set";
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

        SAL_scheduler publisher = new SAL_scheduler();
        publisher.salCommand( "scheduler_command_disable" );

        publisher.setDebugLevel( 1 );
        
        scheduler.command_disable command = new scheduler.command_disable();
        command.private_revCode = "LSST Archiver disable COMMAND";
        command.device = "scheduler";
        command.property = "disable";
        command.action = "set";
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

        SAL_scheduler publisher = new SAL_scheduler();
        publisher.salCommand( "scheduler_command_standby" );

        publisher.setDebugLevel( 1 );
        
        scheduler.command_standby command = new scheduler.command_standby();
        command.private_revCode = "LSST Archiver standby COMMAND";
        command.device = "scheduler";
        command.property = "standby";
        command.action = "set";
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

        SAL_scheduler publisher = new SAL_scheduler();
        publisher.salCommand( "scheduler_command_exitControl" );

        publisher.setDebugLevel( 1 );
        
        scheduler.command_exitControl command = new scheduler.command_exitControl();
        command.private_revCode = "LSST Archiver exitControl COMMAND";
        command.device = "scheduler";
        command.property = "exitControl";
        command.action = "set";
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
        SAL_scheduler subscriber = new SAL_scheduler();
        subscriber.salEvent( "scheduler_logevent_summaryState" );

        subscriber.setDebugLevel( 1 );
        
        scheduler.logevent_summaryState event = new scheduler.logevent_summaryState();
        
        Integer status = CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_summaryState( event );
            if ( status == SAL_scheduler.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
                out.println( "=== Event Status : " + status );
                out.println( "=== Event SummaryState : " + event.summaryState );
                
                try {
                    Executive.getEntityMap().get( "sch" )._stateTransitionQ.put( event.summaryState );
                    Executive.getEntityMap().get( "sch" )._guiStateTransitionQ.put( event.summaryState );
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
        
//        if ( count >= 50 ) {
//            
//            out.println( "=== Event SummaryState Was NOT received");
//            out.println( "=== Event Status : " + status );
//            
//            try {
//                //Executive.getEntityMap().get( "sch")._stateTransitionQ.put( status );
//                Executive._entitySCH._stateTransitionQ.put( status );
//            } catch ( InterruptedException ie ) {
//                ie.printStackTrace( out.printf( "BAD SummaryState" ));
//            }
//        }

        /* Remove the DataWriters etc */
        //subscriber.salShutdown();
        
        return status;
    }

    @Override public void settingsVersion() {

        // Initialize
        SAL_scheduler subscriber = new SAL_scheduler();
        subscriber.salEvent( "scheduler_logevent_settingVersions" );

        subscriber.setDebugLevel( 1 );
        
        scheduler.logevent_settingVersions event = new scheduler.logevent_settingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.logEvent_settingVersions( event, 1 );
            if ( status == SAL_scheduler.SAL__OK ) {
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
        SAL_scheduler subscriber = new SAL_scheduler();
        subscriber.salEvent( "scheduler_logevent_appliedSettingsMatchStart" );

        subscriber.setDebugLevel( 1 );
        
        scheduler.logevent_appliedSettingsMatchStart event = new scheduler.logevent_appliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_appliedSettingsMatchStart( event );
            if ( status == SAL_scheduler.SAL__OK ) {
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
