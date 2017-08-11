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
import org.lsst.sal.SAL_processingcluster;

/**
 *
 * SalCmd is OCS Executive's interface to SAL middle-ware
 *
 */

public class CSCProcessingCluster extends CommandableSalComponent {
    
    @Override public String getName() { return "SalCmdProcessingCluster"; }

    @Override public void enterControl() { 
    
        //out.println("SalCmdSequencer.enterControl");
        SAL_processingcluster cmd = new SAL_processingcluster();
        cmd.salCommand("processingcluster_command_enterControl");

        processingcluster.command_enterControl command = new processingcluster.command_enterControl();
        command.private_revCode = "LSST ProcessingCluster enterControl COMMAND";
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
        
        SAL_processingcluster cmd = new SAL_processingcluster();
        cmd.salCommand("processingcluster_command_start");

        processingcluster.command_start command = new processingcluster.command_start();
        command.private_revCode = "LSST ProcessingCluster start COMMAND";
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

        SAL_processingcluster cmd = new SAL_processingcluster();
        cmd.salCommand("processingcluster_command_enable");

        processingcluster.command_enable command = new processingcluster.command_enable();
        command.private_revCode = "LSST ProcessingCluster enable COMMAND";
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

        SAL_processingcluster cmd = new SAL_processingcluster();
        cmd.salCommand("processingcluster_command_disable");

        processingcluster.command_disable command = new processingcluster.command_disable();
        command.private_revCode = "LSST ProcessingCluster disable COMMAND";
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

        SAL_processingcluster cmd = new SAL_processingcluster();
        cmd.salCommand("processingcluster_command_standby");

        processingcluster.command_standby command = new processingcluster.command_standby();
        command.private_revCode = "LSST ProcessingCluster standby COMMAND";
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

        SAL_processingcluster cmd = new SAL_processingcluster();
        cmd.salCommand("processingcluster_command_exitControl");

        processingcluster.command_exitControl command = new processingcluster.command_exitControl();
        command.private_revCode = "LSST ProcessingCluster exitControl COMMAND";
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
        SAL_processingcluster evt = new SAL_processingcluster();
        evt.salEvent("processingcluster_logevent_SummaryState");

        processingcluster.logevent_SummaryState event = new processingcluster.logevent_SummaryState();
        out.println("ProcessingCluster Event SummaryState logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = evt.getEvent_SummaryState(event);
            if (status == SAL_processingcluster.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }

            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        evt.salShutdown();
    }
    
    @Override public void settingsVersion() {
    
        // Initialize
        SAL_processingcluster evt = new SAL_processingcluster();
        evt.salEvent("processingcluster_logevent_SettingVersions");
        
        processingcluster.logevent_SettingVersions event = new processingcluster.logevent_SettingVersions();
        out.println("ProcessingCluster Event SettingVersions logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = evt.getEvent_SettingVersions(event);
            if (status == SAL_processingcluster.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        evt.salShutdown();
    }
    
    @Override public void appliedSettingsMatchStartTest() {
    
        // Initialize
        SAL_processingcluster evt = new SAL_processingcluster();
        evt.salEvent("processingcluster_logevent_AppliedSettingsMatchStart");
        
        processingcluster.logevent_AppliedSettingsMatchStart event = new processingcluster.logevent_AppliedSettingsMatchStart();
        out.println("ProcessingCluster Event AppliedSettingsMatchStart logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = evt.getEvent_AppliedSettingsMatchStart(event);
            if (status == SAL_processingcluster.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
	  evt.salShutdown();
    }
}


