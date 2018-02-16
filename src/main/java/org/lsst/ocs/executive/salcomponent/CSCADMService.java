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

import org.lsst.sal.SAL_archiver;
import static java.lang.System.out;

/**
 * <h2>Auxiliary Data Management Service CSC</h2>
 * <p>
 * {@code CSCADMService} is a (Concrete) Receiver class in the command pattern
 * 
 */

public class CSCADMService implements CommandableSalComponent {

    @Override public String getName() { return "CSCArchiver"; }

    @Override public void enterControl() {

        SAL_archiver publisher = new SAL_archiver();
        publisher.salCommand( "archiver_command_enterControl" );

        archiver.command_enterControl command = new archiver.command_enterControl();
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

        int timeout = 3;
        publisher.waitForCompletion_enterControl( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void start() {

        SAL_archiver publisher = new SAL_archiver();
        publisher.salCommand( "archiver_command_start" );

        archiver.command_start command = new archiver.command_start();
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

        int timeout = 3;
        publisher.waitForCompletion_start( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();

    }

    @Override public void enable() {

        SAL_archiver publisher = new SAL_archiver();
        publisher.salCommand( "archiver_command_enable" );

        archiver.command_enable command = new archiver.command_enable();
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

        int timeout = 3;
        publisher.waitForCompletion_enable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void disable() {

        SAL_archiver publisher = new SAL_archiver();
        publisher.salCommand( "archiver_command_disable" );

        archiver.command_disable command = new archiver.command_disable();
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

        int timeout = 3;
        publisher.waitForCompletion_disable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void standby() {

        SAL_archiver publisher = new SAL_archiver();
        publisher.salCommand( "archiver_command_standby" );

        archiver.command_standby command = new archiver.command_standby();
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

        int timeout = 3;
        publisher.waitForCompletion_standby( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void exitControl() {

        SAL_archiver publisher = new SAL_archiver();
        publisher.salCommand( "archiver_command_exitControl" );

        archiver.command_exitControl command = new archiver.command_exitControl();
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

        int timeout = 3;
        publisher.waitForCompletion_exitControl( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public Integer summaryState() {
    
        // Initialize
        SAL_archiver subscriber = new SAL_archiver();
        subscriber.salEvent( "archiver_logevent_SummaryState" );

        archiver.logevent_SummaryState event = new archiver.logevent_SummaryState();

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SummaryState( event );
            if ( status == SAL_archiver.SAL__OK ) {
                
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
        SAL_archiver subscriber = new SAL_archiver();
        subscriber.salEvent( "archiver_logevent_SettingVersions" );

        archiver.logevent_SettingVersions event = new archiver.logevent_SettingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SettingVersions( event );
            if ( status == SAL_archiver.SAL__OK ) {
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
        SAL_archiver subscriber = new SAL_archiver();
        subscriber.salEvent( "archiver_logevent_AppliedSettingsMatchStart" );

        archiver.logevent_AppliedSettingsMatchStart event = new archiver.logevent_AppliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_AppliedSettingsMatchStart( event );
            if ( status == SAL_archiver.SAL__OK ) {
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