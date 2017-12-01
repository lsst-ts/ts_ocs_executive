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

import org.lsst.sal.SAL_processingcluster;

import static java.lang.System.out;

/**
 *
 * SalCmd is OCS Executive's interface to SAL middle-ware
 *
 */

public class CSCHeaderService implements CommandableSalComponent {
    
    @Override public String getName() { return "CSCHeaderService"; }

    @Override public void enterControl() { 
    
        //out.println("SalCmdSequencer.enterControl");
        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand("headerservice_command_enterControl");

        processingcluster.command_enterControl command = new processingcluster.command_enterControl();
        command.private_revCode = "LSST HeaderService enterControl COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "allow";
        command.state = true;

        int cmdId = publisher.issueCommand_enterControl(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_enterControl(cmdId, timeout);

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void start() { 
        
        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand("headerservice_command_start");

        processingcluster.command_start command = new processingcluster.command_start();
        command.private_revCode = "LSST HeaderService start COMMAND";
        command.device = "configuration";
        command.property = "set";
        command.action = "apply";
        command.configuration = "normal";

        int cmdId = publisher.issueCommand_start(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_start(cmdId, timeout);

        // Remove the DataWriters etc
        publisher.salShutdown();
    
    }

    @Override public void enable() {

        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand("headerservice_command_enable");

        processingcluster.command_enable command = new processingcluster.command_enable();
        command.private_revCode = "LSST HeaderService enable COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "";
        command.state = true;

        int cmdId = publisher.issueCommand_enable(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_enable(cmdId, timeout);

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void disable() {

        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand("headerservice_command_disable");

        processingcluster.command_disable command = new processingcluster.command_disable();
        command.private_revCode = "LSST HeaderService disable COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "";
        command.state = true;

        int cmdId = publisher.issueCommand_disable(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_disable(cmdId, timeout);

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void standby() {

        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand("headerservice_command_standby");

        processingcluster.command_standby command = new processingcluster.command_standby();
        command.private_revCode = "LSST HeaderService standby COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "stop";
        command.state = true;

        int cmdId = publisher.issueCommand_standby(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_standby(cmdId, timeout);

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void exitControl() {

        SAL_processingcluster publisher = new SAL_processingcluster();
        publisher.salCommand("headerservice_command_exitControl");

        processingcluster.command_exitControl command = new processingcluster.command_exitControl();
        command.private_revCode = "LSST HeaderService exitControl COMMAND";
        command.device = "controller";
        command.property = "command";
        command.action = "exit";
        command.state = true;

        int cmdId = publisher.issueCommand_exitControl(command);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int timeout = 3;
        publisher.waitForCompletion_exitControl(cmdId, timeout);

        // Remove the DataWriters etc
        publisher.salShutdown();
    }

    @Override public void summaryState() {
    
        // Initialize
        SAL_processingcluster subscriber = new SAL_processingcluster();
        subscriber.salEvent("headerservice_logevent_SummaryState");

        processingcluster.logevent_SummaryState event = new processingcluster.logevent_SummaryState();
//        out.println("HeaderService Event SummaryState logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = subscriber.getEvent_SummaryState(event);
            if (status == SAL_processingcluster.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }

            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        subscriber.salShutdown();
    }
    
    @Override public void settingsVersion() {
    
        // Initialize
        SAL_processingcluster subscriber = new SAL_processingcluster();
        subscriber.salEvent("headerservice_logevent_SettingVersions");
        
        processingcluster.logevent_SettingVersions event = new processingcluster.logevent_SettingVersions();
//        out.println("HeaderService Event SettingVersions logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = subscriber.getEvent_SettingVersions(event);
            if (status == SAL_processingcluster.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        subscriber.salShutdown();
    }
    
    @Override public void appliedSettingsMatchStart() {
    
        // Initialize
        SAL_processingcluster subscriber = new SAL_processingcluster();
        subscriber.salEvent("headerservice_logevent_AppliedSettingsMatchStart");
        
        processingcluster.logevent_AppliedSettingsMatchStart event = new processingcluster.logevent_AppliedSettingsMatchStart();
//        out.println("HeaderService Event AppliedSettingsMatchStart logger ready ");

        int status;
        while (Boolean.TRUE) {
            status = subscriber.getEvent_AppliedSettingsMatchStart(event);
            if (status == SAL_processingcluster.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
	  subscriber.salShutdown();
    }
}


