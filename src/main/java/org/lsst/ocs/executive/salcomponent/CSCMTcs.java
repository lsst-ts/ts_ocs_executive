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

import org.lsst.sal.SAL_tcs;
import static java.lang.System.out;

/**
 * <h2>Main Telescope Control System (MTCS) CSC</h2>
 * <p>
 * {@code CSCMTcs} is a (Concrete) Receiver class in the command pattern
 * <p>
 */
public class CSCMTcs implements CommandableSalComponent {

    @Override
    public String getName() { return "CSCMTcs"; }

    @Override
    public void enterControl() {

//        out.print( this.getName() + "::" + 
//                   Thread.currentThread().getStackTrace()[1].getMethodName() + "::" +
//                   "Threadid: " + 
//                   Thread.currentThread().getId() + "\n" );
        SAL_tcs publisher = new SAL_tcs();
        publisher.salCommand( "tcs_command_enterControl" );

        tcs.command_enterControl command = new tcs.command_enterControl();
        command.private_revCode = "LSST TCS enterControl COMMAND";
        command.device = "tcs";
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

    @Override
    public void start() {

        SAL_tcs publisher = new SAL_tcs();
        publisher.salCommand( "tcs_command_start" );

        tcs.command_start command = new tcs.command_start();
        command.private_revCode = "LSST TCS start COMMAND";
        command.device = "tcs";
        command.property = "start";
        command.action = "set";
        command.configuration = "Default";

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

    @Override
    public void enable() {

        SAL_tcs publisher = new SAL_tcs();
        publisher.salCommand( "tcs_command_enable" );

        tcs.command_enable command = new tcs.command_enable();
        command.private_revCode = "LSST TCS enable COMMAND";
        command.device = "tcs";
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

    @Override
    public void disable() {

        SAL_tcs publisher = new SAL_tcs();
        publisher.salCommand( "tcs_command_disable" );

        tcs.command_disable command = new tcs.command_disable();
        command.private_revCode = "LSST TCS disable COMMAND";
        command.device = "tcs";
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

    @Override
    public void standby() {

        SAL_tcs publisher = new SAL_tcs();
        publisher.salCommand( "tcs_command_standby" );

        tcs.command_standby command = new tcs.command_standby();
        command.private_revCode = "LSST TCS standby COMMAND";
        command.device = "tcs";
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

    @Override
    public void exitControl() {

        SAL_tcs publisher = new SAL_tcs();
        publisher.salCommand( "tcs_command_exitControl" );

        tcs.command_exitControl command = new tcs.command_exitControl();
        command.private_revCode = "LSST TCS exitControl COMMAND";
        command.device = "tcs";
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

    public void filterChange() {

        SAL_tcs publisher = new SAL_tcs();
        publisher.salCommand( "tcs_command_filterChangeRequest" );

        tcs.command_filterChangeRequest command = new tcs.command_filterChangeRequest();
        command.private_revCode = "LSST TCS filterChangeRequest COMMAND";
        command.device = "tcs";
        command.property = "filterChangeRequest";
        command.action = "apply";
        command.filterChangeRequest = "g";

        int cmdId = publisher.issueCommand_filterChangeRequest( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_filterChangeRequest( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    public void target() {

        SAL_tcs publisher = new SAL_tcs();
        publisher.salCommand( "tcs_command_target" );

        tcs.command_target command = new tcs.command_target();
        command.private_revCode = "LSST TCS target COMMAND";
        command.device = "tcs";
        command.property = "target";
        command.action = "apply";

        command.targetId = (int) 2;
        command.fieldId = (int) 2653;
        command.groupId = (int) 1;
        command.filter = (String) "z";
        command.requestTime = (double) 1664583885.2;
        command.ra = (double) 289.583;
        command.dec = (double) 0.187;
        command.angle = (double) 156.887;
        command.num_exposures = (int) 2;
        command.exposure_times = (int) 15;
        command.slew_time = (double) 41.717;

        int cmdId = publisher.issueCommand_target( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_target( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override
    public Integer summaryState() {

        // Initialize
        SAL_tcs subscriber = new SAL_tcs();
        subscriber.salEvent( "tcs_logevent_SummaryState" );

        tcs.logevent_SummaryState event = new tcs.logevent_SummaryState();

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {

            status = subscriber.getEvent_SummaryState( event );
            if ( status == SAL_tcs.SAL__OK ) {

                out.println( "=== Event Logged : " + event );

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

    @Override
    public void settingsVersion() {

        // Initialize
        SAL_tcs subscriber = new SAL_tcs();
        subscriber.salEvent( "tcs_logevent_SettingVersions" );

        tcs.logevent_SettingVersions event = new tcs.logevent_SettingVersions();

        int status;
        while ( Boolean.TRUE ) {

            status = subscriber.getEvent_SettingVersions( event );
            if ( status == SAL_tcs.SAL__OK ) {
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

    @Override
    public void appliedSettingsMatchStart() {

        // Initialize
        SAL_tcs subscriber = new SAL_tcs();
        subscriber.salEvent( "tcs_logevent_AppliedSettingsMatchStart" );

        tcs.logevent_AppliedSettingsMatchStart event = new tcs.logevent_AppliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {

            status = subscriber.getEvent_AppliedSettingsMatchStart( event );
            if ( status == SAL_tcs.SAL__OK ) {
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

    public void filterChangeInPosition() {

        // Initialize
        SAL_tcs subscriber = new SAL_tcs();
        subscriber.salEvent( "tcs_logevent_FilterChangeInPosition" );

        tcs.logevent_FilterChangeInPosition event = new tcs.logevent_FilterChangeInPosition();

        int status;
        while ( Boolean.TRUE ) {

            status = subscriber.getEvent_FilterChangeInPosition( event );
            if ( status == SAL_tcs.SAL__OK ) {
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

    public void targetInPosition() {

        // Initialize
        SAL_tcs subscriber = new SAL_tcs();
        subscriber.salEvent( "tcs_logevent_TargetInPosition" );

        tcs.logevent_TargetInPosition event = new tcs.logevent_TargetInPosition();

        int status;
        while ( Boolean.TRUE ) {

            status = subscriber.getEvent_TargetInPosition( event );
            if ( status == SAL_tcs.SAL__OK ) {
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
