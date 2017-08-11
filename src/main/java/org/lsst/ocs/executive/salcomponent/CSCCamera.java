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
import org.lsst.sal.SAL_camera;

/**
 *
 * CSCCamera is a Receiver class in the command pattern
 *
 */

public class CSCCamera extends CommandableSalComponent {
    
    @Override public String getName() { return "SalCmdCamera"; }
    
    @Override public void enterControl() { 
    
        //out.println("SalCmdSequencer.enterControl");
        SAL_camera cmd = new SAL_camera();
        cmd.salCommand("camera_command_enterControl");

        camera.command_enterControl command = new camera.command_enterControl();
        command.private_revCode = "LSST Camera enterControl COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "allow";
        command.state = true;

        int cmdId = cmd.issueCommand_enterControl(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 6;
        cmd.waitForCompletion_enterControl(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }

    @Override public void start() { 
        
        SAL_camera cmd = new SAL_camera();
        cmd.salCommand("camera_command_start");

        camera.command_start command = new camera.command_start();
        command.private_revCode = "LSST Camera start COMMAND";
        command.device = "configuration";
        command.property = "set";
        command.action = "apply";
        command.configuration = "normal";

        int cmdId = cmd.issueCommand_start(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 6;
        cmd.waitForCompletion_start(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    
    }

    @Override public void enable() {

        SAL_camera cmd = new SAL_camera();
        cmd.salCommand("camera_command_enable");

        camera.command_enable command = new camera.command_enable();
        command.private_revCode = "LSST Camera enable COMMAND";
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

        int timeout = 6;
        cmd.waitForCompletion_enable(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }

    @Override public void disable() {

        SAL_camera cmd = new SAL_camera();
        cmd.salCommand("camera_command_disable");

        camera.command_disable command = new camera.command_disable();
        command.private_revCode = "LSST Camera disable COMMAND";
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

        int timeout = 6;
        cmd.waitForCompletion_disable(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }

    @Override public void standby() {

        SAL_camera cmd = new SAL_camera();
        cmd.salCommand("camera_command_standby");

        camera.command_standby command = new camera.command_standby();
        command.private_revCode = "LSST Camera standby COMMAND";
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

        int timeout = 6;
        cmd.waitForCompletion_standby(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }

    @Override public void exitControl() {

        SAL_camera cmd = new SAL_camera();
        cmd.salCommand("camera_command_exitControl");

        camera.command_exitControl command = new camera.command_exitControl();
        command.private_revCode = "LSST Camera exitControl COMMAND";
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

        int timeout = 6;
        cmd.waitForCompletion_exitControl(cmdId, timeout);

        // Remove the DataWriters etc
        cmd.salShutdown();
    }

    @Override public void summaryState() {
    
        // Initialize
        SAL_camera evt = new SAL_camera();
        evt.salEvent("camera_logevent_SummaryState");

        camera.logevent_SummaryState event = new camera.logevent_SummaryState();

        out.print(this.getClass()
                      .getSimpleName()+"::"
                                      +Thread.currentThread().getStackTrace()[1].getMethodName()
                                      +"::");
        Thread.currentThread().setName(new String().concat("CSCCameraSummaryStateThread"));
        out.print(Thread.currentThread().getName());
        out.println(" "+"id: "+Thread.currentThread().getId());
        
        out.println("Camera Event SummaryState logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = evt.getEvent_SummaryState(event);
            if (status == SAL_camera.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }

            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        evt.salShutdown();
    }
    
    @Override public void settingsVersion() {
    
        // Initialize
        SAL_camera evt = new SAL_camera();
        evt.salEvent("camera_logevent_SettingVersions");
        
        camera.logevent_SettingVersions event = new camera.logevent_SettingVersions();
        out.println("Camera Event SettingVersions logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = evt.getEvent_SettingVersions(event);
            if (status == SAL_camera.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        evt.salShutdown();
    }
    
    @Override public void appliedSettingsMatchStartTest() {
    
        // Initialize
        SAL_camera evt = new SAL_camera();
        evt.salEvent("camera_logevent_AppliedSettingsMatchStart");
        
        camera.logevent_AppliedSettingsMatchStart event = new camera.logevent_AppliedSettingsMatchStart();
        out.println("Camera Event AppliedSettingsMatchStart logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = evt.getEvent_AppliedSettingsMatchStart(event);
            if (status == SAL_camera.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
	  evt.salShutdown();
    }
}


