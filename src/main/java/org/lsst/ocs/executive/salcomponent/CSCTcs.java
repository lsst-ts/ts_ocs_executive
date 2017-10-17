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
import org.lsst.sal.SAL_tcs;

/**
 *
 * SalCmd is OCS Executive's interface to SAL middle-ware
 *
 */

public class CSCTcs extends CommandableSalComponent {
    
    @Override public String getName() { return "CSCTcs"; }
    
    @Override public void enterControl() { 

//        out.print( this.getName() + "::" + 
//                   Thread.currentThread().getStackTrace()[1].getMethodName() + "::" +
//                   "Threadid: " + 
//                   Thread.currentThread().getId() + "\n" );
        
        SAL_tcs cmd = new SAL_tcs();
        cmd.salCommand("tcs_command_enterControl");

        tcs.command_enterControl command = new tcs.command_enterControl();
        command.private_revCode = "LSST TCS enterControl COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "allow";
        command.state = true;

        int cmdId = cmd.issueCommand_enterControl(command);

//        out.println("TCS Command enterControl ready ");

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        cmd.waitForCompletion_enterControl(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }

    @Override public void start() { 
        
        SAL_tcs cmd = new SAL_tcs();
        cmd.salCommand("tcs_command_start");

        tcs.command_start command = new tcs.command_start();
        command.private_revCode = "LSST TCS start COMMAND";
        command.device = "configuration";
        command.property = "set";
        command.action = "apply";
        command.configuration = "Default";

        int cmdId = cmd.issueCommand_start(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        cmd.waitForCompletion_start(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    
    }

    @Override public void enable() {

        SAL_tcs cmd = new SAL_tcs();
        cmd.salCommand("tcs_command_enable");

        tcs.command_enable command = new tcs.command_enable();
        command.private_revCode = "LSST TCS enable COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "";
        command.state = true;

        int cmdId = cmd.issueCommand_enable(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        cmd.waitForCompletion_enable(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }

    @Override public void disable() {

        SAL_tcs cmd = new SAL_tcs();
        cmd.salCommand("tcs_command_disable");

        tcs.command_disable command = new tcs.command_disable();
        command.private_revCode = "LSST TCS disable COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "";
        command.state = true;

        int cmdId = cmd.issueCommand_disable(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        cmd.waitForCompletion_disable(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }

    @Override public void standby() {

        SAL_tcs cmd = new SAL_tcs();
        cmd.salCommand("tcs_command_standby");

        tcs.command_standby command = new tcs.command_standby();
        command.private_revCode = "LSST TCS standby COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "stop";
        command.state = true;

        int cmdId = cmd.issueCommand_standby(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        cmd.waitForCompletion_standby(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }

    @Override public void exitControl() {

        SAL_tcs cmd = new SAL_tcs();
        cmd.salCommand("tcs_command_exitControl");

        tcs.command_exitControl command = new tcs.command_exitControl();
        command.private_revCode = "LSST TCS exitControl COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "exit";
        command.state = true;

        int cmdId = cmd.issueCommand_exitControl(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        cmd.waitForCompletion_exitControl(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }

    public void filterChange() {

        SAL_tcs cmd = new SAL_tcs();
        cmd.salCommand("tcs_command_filterChangeRequest");

        tcs.command_filterChangeRequest command = new tcs.command_filterChangeRequest();
        command.private_revCode = "LSST TCS filterChangeRequest COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "exit";
        command.filterChangeRequest = "g";

        int cmdId = cmd.issueCommand_filterChangeRequest(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        cmd.waitForCompletion_filterChangeRequest(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }

    public void target() {

        SAL_tcs cmd = new SAL_tcs();
        cmd.salCommand("tcs_command_target");

        tcs.command_target command = new tcs.command_target();
        command.private_revCode = "LSST TCS target COMMAND";
        command.device   = "";
        command.property = "";
        command.action   = "";
        
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
            
        int cmdId = cmd.issueCommand_target(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 10;
        cmd.waitForCompletion_target(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }
    
    
    @Override public void summaryState() {
    
        // Initialize
        SAL_tcs evt = new SAL_tcs();
        evt.salEvent("tcs_logevent_SummaryState");

        tcs.logevent_SummaryState event = new tcs.logevent_SummaryState();
//        out.println("TCS Event SummaryState logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = evt.getEvent_SummaryState(event);
            if (status == SAL_tcs.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }

            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        evt.salShutdown();
    }
    
    @Override public void settingsVersion() {
    
        // Initialize
        SAL_tcs evt = new SAL_tcs();
        evt.salEvent("tcs_logevent_SettingVersions");
        
        tcs.logevent_SettingVersions event = new tcs.logevent_SettingVersions();
//        out.println("TCS Event SettingVersions logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = evt.getEvent_SettingVersions(event);
            if (status == SAL_tcs.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        evt.salShutdown();
    }
    
    @Override public void appliedSettingsMatchStart() {
    
        // Initialize
        SAL_tcs evt = new SAL_tcs();
        evt.salEvent("tcs_logevent_AppliedSettingsMatchStart");
        
        tcs.logevent_AppliedSettingsMatchStart event = new tcs.logevent_AppliedSettingsMatchStart();
//        out.println("TCS Event AppliedSettingsMatchStart logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = evt.getEvent_AppliedSettingsMatchStart(event);
            if (status == SAL_tcs.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
	  evt.salShutdown();
    }
    
    public void filterChangeInPosition() {
    
        // Initialize
        SAL_tcs evt = new SAL_tcs();
        evt.salEvent("tcs_logevent_FilterChangeInPosition");
        
        tcs.logevent_FilterChangeInPosition event = new tcs.logevent_FilterChangeInPosition();
//        out.println("Event FilterChangeInPosition logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = evt.getEvent_FilterChangeInPosition(event);
            if (status == SAL_tcs.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
	  evt.salShutdown();
    }
    
    public void targetInPosition() {
    
        // Initialize
        SAL_tcs evt = new SAL_tcs();
        evt.salEvent("tcs_logevent_TargetInPosition");
        
        tcs.logevent_TargetInPosition event = new tcs.logevent_TargetInPosition();
//        out.println("Event TargetInPosition logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = evt.getEvent_TargetInPosition(event);
            if (status == SAL_tcs.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
	  evt.salShutdown();
    }
}


