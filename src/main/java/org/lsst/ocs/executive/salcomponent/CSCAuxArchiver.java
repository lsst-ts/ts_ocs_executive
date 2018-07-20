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
import org.lsst.sal.SAL_atArchiver;

import org.lsst.sal.SAL_scheduler;

/**
 * <h2>Data Management Auxiliary Archiver Service CSC</h2>
 *
 * {@code CSCAuxArchiver} is a (Concrete) Receiver class in the command pattern
 */
public class CSCAuxArchiver implements CommandableSalComponent {

    @Override public String getName() { return "CSCAuxArchiver"; }

    @Override public void enterControl() {

        SAL_atArchiver publisher = new SAL_atArchiver();
        publisher.salCommand( "atArchiver_command_enterControl" );

        publisher.setDebugLevel( 1 );
        
        atArchiver.command_enterControl command = new atArchiver.command_enterControl();
        command.private_revCode = "LSST Archiver enterControl COMMAND";
        command.device = "archiver";
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

        SAL_atArchiver publisher = new SAL_atArchiver();
        publisher.salCommand( "atArchiver_command_start" );

        publisher.setDebugLevel( 1 );
        
        atArchiver.command_start command = new atArchiver.command_start();
        command.private_revCode = "LSST Archiver start COMMAND";
        command.device = "configuration";
        command.property = "start";
        command.action = "set";
        command.configuration = "Normal";

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

        SAL_atArchiver publisher = new SAL_atArchiver();
        publisher.salCommand( "atArchiver_command_enable" );

        publisher.setDebugLevel( 1 );
        
        atArchiver.command_enable command = new atArchiver.command_enable();
        command.private_revCode = "LSST Archiver enable COMMAND";
        command.device = "archiver";
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

        SAL_atArchiver publisher = new SAL_atArchiver();
        publisher.salCommand( "atArchiver_command_disable" );

        publisher.setDebugLevel( 1 );
        
        atArchiver.command_disable command = new atArchiver.command_disable();
        command.private_revCode = "LSST Archiver disable COMMAND";
        command.device = "archiver";
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

        SAL_atArchiver publisher = new SAL_atArchiver();
        publisher.salCommand( "atArchiver_command_standby" );

        publisher.setDebugLevel( 1 );
        
        atArchiver.command_standby command = new atArchiver.command_standby();
        command.private_revCode = "LSST Archiver standby COMMAND";
        command.device = "archiver";
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

        SAL_atArchiver publisher = new SAL_atArchiver();
        publisher.salCommand( "atArchiver_command_exitControl" );

        publisher.setDebugLevel( 1 );
        
        atArchiver.command_exitControl command = new atArchiver.command_exitControl();
        command.private_revCode = "LSST Archiver exitControl COMMAND";
        command.device = "archiver";
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
        SAL_atArchiver subscriber = new SAL_atArchiver();
        subscriber.salEvent( "atArchiver_logevent_SummaryState" );

        subscriber.setDebugLevel( 1 );
        
        atArchiver.logevent_SummaryState event = new atArchiver.logevent_SummaryState();

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SummaryState( event );
            if ( status == SAL_scheduler.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
                out.println( "=== Event Status : " + status );
                out.println( "=== Event SummaryState : " + event.SummaryStateValue );
                
                try {
                    Executive.getEntityMap().get( "aar" )._stateTransitionQ.put( event.SummaryStateValue );
                    Executive.getEntityMap().get( "aar" )._guiStateTransitionQ.put( event.SummaryStateValue );
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
        SAL_atArchiver subscriber = new SAL_atArchiver();
        subscriber.salEvent( "atArchiver_logevent_SettingVersions" );

        subscriber.setDebugLevel( 1 );
        
        atArchiver.logevent_SettingVersions event = new atArchiver.logevent_SettingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SettingVersions( event );
            if ( status == SAL_atArchiver.SAL__OK ) {
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
        SAL_atArchiver subscriber = new SAL_atArchiver();
        subscriber.salEvent( "atArchiver_logevent_AppliedSettingsMatchStart" );

        subscriber.setDebugLevel( 1 );
        
        atArchiver.logevent_AppliedSettingsMatchStart event = new atArchiver.logevent_AppliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_AppliedSettingsMatchStart( event );
            if ( status == SAL_atArchiver.SAL__OK ) {
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
