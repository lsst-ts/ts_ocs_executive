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

package org.lsst.ocs.executive;

import org.lsst.sal.SAL_sequencer;

/**
 *
 * SalCmdSequencer is OCS Executive's interface to SAL middle-ware 
 * for Sequencer commands
 *
 */

public class SalCmdSequencer {

    public static void enterControl() {

        //out.println("SalCmdSequencer.enterControl");
        SAL_sequencer cmd = new SAL_sequencer();
        cmd.salCommand("sequencer_command_enterControl");

        sequencer.command_enterControl command = new sequencer.command_enterControl();
        command.private_revCode = "LSST SEQUENCER enterControl COMMAND";
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

    public static void start() {

        SAL_sequencer cmd = new SAL_sequencer();
        cmd.salCommand("sequencer_command_start");

        sequencer.command_start command = new sequencer.command_start();
        command.private_revCode = "LSST SEQUENCER start COMMAND";
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

    public static void enable() {

        SAL_sequencer cmd = new SAL_sequencer();
        cmd.salCommand("sequencer_command_enable");

        sequencer.command_enable command = new sequencer.command_enable();
        command.private_revCode = "LSST SEQUENCER enable COMMAND";
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

    public static void disable() {

        SAL_sequencer cmd = new SAL_sequencer();
        cmd.salCommand("sequencer_command_enable");

        sequencer.command_disable command = new sequencer.command_disable();
        command.private_revCode = "LSST SEQUENCER disable COMMAND";
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

    public static void standby() {

        SAL_sequencer cmd = new SAL_sequencer();
        cmd.salCommand("sequencer_command_enable");

        sequencer.command_standby command = new sequencer.command_standby();
        command.private_revCode = "LSST SEQUENCER standby COMMAND";
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

    public static void exitControl() {

        SAL_sequencer cmd = new SAL_sequencer();
        cmd.salCommand("sequencer_command_enable");

        sequencer.command_exitControl command = new sequencer.command_exitControl();
        command.private_revCode = "LSST SEQUENCER exitControl COMMAND";
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

}
