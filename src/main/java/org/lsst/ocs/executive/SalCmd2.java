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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
//import static org.lsst.ocs.executive.SalCmd.cmdSeqMap1;
import org.lsst.sal.SAL_sequencer;

/**
 *
 * SalCmdExecutive is OCS Executive's interface to SAL middle-ware
 *
 */

class SalCmdExecutiveOcs2 {

    public static void enterControl() {
     
        SAL_sequencer salseq = new SAL_sequencer();

  	salseq.salCommand("ocs_command_enterControl");
	sequencer.command_enterControl command  = new sequencer.command_enterControl();

	command.private_revCode = "LSST OCS enterControl COMMAND";
        command.device   = "controller";
        command.property = "command";
        command.action   = "allow";
        command.state = true;

	int cmdId = salseq.issueCommand_enterControl(command);

	try {Thread.sleep(250);} catch (InterruptedException e)  { e.printStackTrace(); }

        int timeout = 6;
	salseq.waitForCompletion_enterControl(cmdId, timeout);

	// Remove the DataWriters etc
	salseq.salShutdown();
    }

    public static void start() {
     
        SAL_sequencer salseq = new SAL_sequencer();

  	salseq.salCommand("ocs_command_start");
	sequencer.command_start command  = new sequencer.command_start();

	command.private_revCode = "LSST OCS start COMMAND";
        command.device   = "configuration";
        command.property = "set";
        command.action   = "apply";
        command.configuration = "normal";

	int cmdId = salseq.issueCommand_start(command);

	try {Thread.sleep(250);} catch (InterruptedException e)  { e.printStackTrace(); }

        int timeout = 6;
	salseq.waitForCompletion_start(cmdId, timeout);

	// Remove the DataWriters etc
	salseq.salShutdown();
    }

    public static void enable() {
     
        SAL_sequencer salseq = new SAL_sequencer();

  	salseq.salCommand("ocs_command_enable");
	sequencer.command_enable command  = new sequencer.command_enable();

	command.private_revCode = "LSST OCS enable COMMAND";
        command.device   = "controller";
        command.property = "command";
        command.action   = "";
        command.state = true;

	int cmdId = salseq.issueCommand_enable(command);

	try {Thread.sleep(250);} catch (InterruptedException e)  { e.printStackTrace(); }

        int timeout = 6;
	salseq.waitForCompletion_enable(cmdId, timeout);

	// Remove the DataWriters etc
	salseq.salShutdown();
    }

    public static void disable() {
     
        SAL_sequencer salseq = new SAL_sequencer();

  	salseq.salCommand("ocs_command_enable");
	sequencer.command_disable command  = new sequencer.command_disable();

	command.private_revCode = "LSST OCS disable COMMAND";
        command.device   = "controller";
        command.property = "command";
        command.action   = "";
        command.state = true;

	int cmdId = salseq.issueCommand_disable(command);

	try {Thread.sleep(250);} catch (InterruptedException e)  { e.printStackTrace(); }

        int timeout = 6;
	salseq.waitForCompletion_disable(cmdId, timeout);

	// Remove the DataWriters etc
	salseq.salShutdown();
    }

    public static void standby() {
     
        SAL_sequencer salseq = new SAL_sequencer();

  	salseq.salCommand("ocs_command_enable");
	sequencer.command_standby command  = new sequencer.command_standby();

	command.private_revCode = "LSST OCS standby COMMAND";
        command.device   = "controller";
        command.property = "command";
        command.action   = "stop";
        command.state = true;

	int cmdId = salseq.issueCommand_standby(command);

	try {Thread.sleep(250);} catch (InterruptedException e)  { e.printStackTrace(); }

        int timeout = 6;
	salseq.waitForCompletion_standby(cmdId, timeout);

	// Remove the DataWriters etc
	salseq.salShutdown();
    }

    public static void exitControl() {
     
        SAL_sequencer salseq = new SAL_sequencer();

  	salseq.salCommand("ocs_command_enable");
	sequencer.command_exitControl command  = new sequencer.command_exitControl();

	command.private_revCode = "LSST OCS exitControl COMMAND";
        command.device   = "controller";
        command.property = "command";
        command.action   = "exit";
        command.state = true;

	int cmdId = salseq.issueCommand_exitControl(command);

	try {Thread.sleep(250);} catch (InterruptedException e)  { e.printStackTrace(); }

        int timeout = 6;
	salseq.waitForCompletion_exitControl(cmdId, timeout);

	// Remove the DataWriters etc
	salseq.salShutdown();
    }
}


class Cmds {
    
    public void enterControl() {}
    public void start() {}
    public void enable() {}
    
    interface CmdInfc {

        void sendCmd();
    }

    private CmdInfc[] cmdInfcs = new CmdInfc[] {

        new CmdInfc() { public void sendCmd() { enterControl(); } },
        new CmdInfc() { public void sendCmd() { start(); } },
        new CmdInfc() { public void sendCmd() { enable(); } }
    };

    public void sendCmd(int ndx) {
        
        cmdInfcs[ndx].sendCmd();
    }
}


public class SalCmd2 {
    
    private static  String salActor_;
    private static String payload_;
    
    public SalCmd2(String salActor) { 
 
        salActor_ = salActor;
        payload_ = null;
    }
    
    static Map<String, Runnable> cmdSeqMap = 
        new HashMap<String, Runnable>() {
        {
            put(String.format("enterControl entity=%s timeout=1",salActor_), SalCmd2::cmdSequence);
            put(String.format("start entity=%s startid=Normal timeout=1",salActor_), SalCmd2::cmdSequence);
            put(String.format("enable entity=%s timeout=1",salActor_), SalCmd2::cmdSequence);
            put(String.format("disable entity=%s timeout=1",salActor_), SalCmd2::cmdSequence);
            put(String.format("standby entity=%s timeout=1",salActor_), SalCmd2::cmdSequence);
            put(String.format("exitControl entity=%s timeout=1",salActor_), SalCmd2::cmdSequence);
        }
    };
            
    static Map<String, Runnable> cmdSeqMap0 = 
        new HashMap<String, Runnable>() {
        {
            put("enterControl", SalCmd2::cmdSequence);
            put("start", SalCmd2::cmdSequence);
            put("enable", SalCmd2::cmdSequence);
            put("disable", SalCmd2::cmdSequence);
            put("standby", SalCmd2::cmdSequence);
            put("exitControl", SalCmd2::cmdSequence);
        }
    };
            
    static Map<String, Method> cmdSeqMap1 = 
        new HashMap<String, Method>() {
        {
            try {
            put("enterControl", SalCmd2.class.getMethod("cmdSequence", (Class<?>[])null));
            put("start", SalCmd2.class.getMethod("cmdSequence", (Class<?>[])null));
            put("enable", SalCmd2.class.getMethod("cmdSequence", (Class<?>[])null));
            put("disable", SalCmd2.class.getMethod("cmdSequence", (Class<?>[])null));
            put("standby", SalCmd2.class.getMethod("cmdSequence", (Class<?>[])null));
            put("exitControl", SalCmd2.class.getMethod("cmdSequence", (Class<?>[])null));
            }
            catch (NoSuchMethodException e) {System.out.println(e.toString());}
        }
    };
            
    private static final Map<String, Map<String, Method>> CMDS_MAP1 = 
        new HashMap<String, Map<String, Method>>() {
        {
            put(EntityType.SEQUENCER.toString(), new HashMap<String, Method>() { 
                {
                try {
                put("enterControl", SalCmdExecutiveOcs2.class.getMethod("enterControl", (Class<?>[])null));
                put("start", SalCmdExecutiveOcs2.class.getMethod("start", (Class<?>[])null));
                put("enable", SalCmdExecutiveOcs2.class.getMethod("enable", (Class<?>[])null));
                put("disable", SalCmdExecutiveOcs2.class.getMethod("disable", (Class<?>[])null));
                put("standby", SalCmdExecutiveOcs2.class.getMethod("standby", (Class<?>[])null));
                put("exitControl", SalCmdExecutiveOcs2.class.getMethod("exitControl", (Class<?>[])null));
            }
            catch (NoSuchMethodException e) {System.out.println(e.toString());}
                }
            });
            
            put(EntityType.CAMERA.toString(), cmdSeqMap1);
            put(EntityType.TCS.toString(), cmdSeqMap1);
            put(EntityType.ARCHIVER.toString(), cmdSeqMap1);
            put(EntityType.CATCHUPARCHIVER.toString(), cmdSeqMap1);
            put(EntityType.PROCESSINGCLUSTER.toString(), cmdSeqMap1);
        }
    };
    
    public static void enterControl1() 
        throws IllegalAccessException, InvocationTargetException {

            CMDS_MAP1.get(salActor_).get("enterControl").invoke(null);
    }
    
   
    private static final Map<String, Map<String, Runnable>> CMDS_MAP0 = 
        new HashMap<String, Map<String, Runnable>>() {
        {
            put(EntityType.SEQUENCER.toString(), new HashMap<String, Runnable>() { 
                {
                    put("enterControl", SalCmdExecutiveOcs2::enterControl);
                    put("start", SalCmdExecutiveOcs2::start);
                    put("enable", SalCmdExecutiveOcs2::enable);
                    put("disable", SalCmdExecutiveOcs2::disable);
                    put("standby", SalCmdExecutiveOcs2::standby);
                    put("exitControl", SalCmdExecutiveOcs2::exitControl);
                }
            });
            
            put(EntityType.CAMERA.toString(), cmdSeqMap0);
            put(EntityType.TCS.toString(), cmdSeqMap0);
            put(EntityType.ARCHIVER.toString(), cmdSeqMap0);
            put(EntityType.CATCHUPARCHIVER.toString(), cmdSeqMap0);
            put(EntityType.PROCESSINGCLUSTER.toString(), cmdSeqMap0);
            
        }
    };
    
    public static void enterControl0() {

        CMDS_MAP0.get(salActor_).get("enterControl").run();
    }
    
    private static final Map<String, Map<String, Runnable>> CMDS_MAP = 
        new HashMap<String, Map<String, Runnable>>() {
        {
            put(EntityType.SEQUENCER.toString(), new HashMap<String, Runnable>() { 
                {
                    put("enterControl", SalCmdExecutiveOcs2::enterControl);
                    put("start", SalCmdExecutiveOcs2::start);
                    put("enable", SalCmdExecutiveOcs2::enable);
                    put("disable", SalCmdExecutiveOcs2::disable);
                    put("standby", SalCmdExecutiveOcs2::standby);
                    put("exitControl", SalCmdExecutiveOcs2::exitControl);
                }
            });
            put(EntityType.CAMERA.toString(), new HashMap<String, Runnable>() { 
                {
                    put("enterControl", SalCmd2::cmdSequence);
                    put("start", SalCmd2::cmdSequence);
                    put("enable", SalCmd2::cmdSequence);
                    put("disable", SalCmd2::cmdSequence);
                    put("standby", SalCmd2::cmdSequence);
                    put("exitControl", SalCmd2::cmdSequence);
                }
            });
        }
    };
    
    public void lookup() {
    
        CMDS_MAP.get(salActor_).get("enterControl").run();
    }
    
    public static void enterControl() {

        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.enterControl();
        else {
            payload_ = String.format("enterControl entity=%s timeout=1",  
                                    salActor_);
            cmdSequence();
        }
    }
    
    public static void start() {
     
        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.start();
        else {
            payload_ = String.format("start entity=%s startid=Normal timeout=1",  
                                    salActor_);
            cmdSequence();
        }
    }
    
    public static void enable() {
     
        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.enable();
        else {
            payload_ = String.format("enable entity=%s timeout=1",  
                                    salActor_);
            cmdSequence();
        }
    }
    
    public static void disable() {
     
        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.disable();
        else {
            payload_ = String.format("disable entity=%s timeout=1",  
                                    salActor_);
            cmdSequence();
        }
    }
    
    public static void standby() {
     
        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.standby();
        else {
            payload_ = String.format("standby entity=%s timeout=1",  
                                    salActor_);
            cmdSequence();
        }
    }
    
    public static void exitControl() {
     
        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.exitControl();
        else {
            payload_ = String.format("exitControl entity=%s timeout=1",  
                                    salActor_);
            cmdSequence();
        }
    }
    
    public static void cmdSequence() {
     
        SAL_sequencer salseq = new SAL_sequencer();

        // 2. Send msg to sequencer
  	salseq.salCommand("ocs_command_sequence");
	sequencer.command_sequence command  = new sequencer.command_sequence();

	command.private_revCode = "LSST OCS sequence COMMAND";
        command.device          = "sequencer";
        command.property        = "command";
        command.action          = "send";
        command.value           = "true";
        command.command         = payload_;

	int cmdId = salseq.issueCommand_sequence(command);

	try {Thread.sleep(250);} catch (InterruptedException e)  { e.printStackTrace(); }

        int timeout = 6;
        //status = ocssal.waitForCompletion_cmdsequence(cmdId, timeout);
	salseq.waitForCompletion_sequence(cmdId, timeout);

	// Remove the DataWriters etc
	salseq.salShutdown();
    }
}


