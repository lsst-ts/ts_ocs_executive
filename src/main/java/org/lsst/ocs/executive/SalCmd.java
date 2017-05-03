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
import java.util.HashMap;
import java.util.Map;
import org.lsst.sal.SAL_sequencer;
import static java.lang.System.out;

/**
 *
 * SalCmd is OCS Executive's interface to SAL middle-ware
 *
 */

public class SalCmd {
    
    private final String salActor_;
    private String payload_;
    
    public SalCmd(String salActor) { 
 
        salActor_ = salActor;
        payload_ = null;
    }
    
    /*
    static Map<String, Method> cmdSeqMap1 = 
        new HashMap<String, Method>() {
        {
            try {
            put("enterControl", SalCmd.class.getMethod("cmdSequence", (Class<?>[])null));
            put("start", SalCmd.class.getMethod("cmdSequence", (Class<?>[])null));
            put("enable", SalCmd.class.getMethod("cmdSequence", (Class<?>[])null));
            put("disable", SalCmd.class.getMethod("cmdSequence", (Class<?>[])null));
            put("standby", SalCmd.class.getMethod("cmdSequence", (Class<?>[])null));
            put("exitControl", SalCmd.class.getMethod("cmdSequence", (Class<?>[])null));
            }
            catch (NoSuchMethodException e) {System.out.println(e.toString());}
        }
    };
    */ 
    
    /*
    static final Map<String, Map<String, Method>> CMDS_MAP1 = 
        new HashMap<String, Map<String, Method>>() {
        {
            put(EntityType.SEQUENCER.toString(), new HashMap<String, Method>() { 
                {
                try {
                put("enterControl", SalCmdSequencer.class.getMethod("enterControl", (Class<?>[])null));
                put("start", SalCmdSequencer.class.getMethod("start", (Class<?>[])null));
                put("enable", SalCmdSequencer.class.getMethod("enable", (Class<?>[])null));
                put("disable", SalCmdSequencer.class.getMethod("disable", (Class<?>[])null));
                put("standby", SalCmdSequencer.class.getMethod("standby", (Class<?>[])null));
                put("exitControl", SalCmdSequencer.class.getMethod("exitControl", (Class<?>[])null));
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
    */
    
    /*
    public void enterControl1() 
        throws IllegalAccessException, InvocationTargetException {

            CMDS_MAP1.get(salActor_).get("enterControl").invoke(null);
    }
    */
    
    public void enterControl() {
        
        //out.println("SalCmd.enterControl");
        
        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.enterControl();
        else {
            payload_ = String.format("enterControl entity=%s timeout=1",  
                                    salActor_);
            this.cmdSequence();
        }
    }
    
    public void start() {
     
        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.start();
        else {
            payload_ = String.format("start entity=%s startid=Normal timeout=1",  
                                    salActor_);
            this.cmdSequence();
        }
    }
    
    public void enable() {
     
        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.enable();
        else {
            payload_ = String.format("enable entity=%s timeout=1",  
                                    salActor_);
            this.cmdSequence();
        }
    }
    
    public void disable() {
     
        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.disable();
        else {
            payload_ = String.format("disable entity=%s timeout=1",  
                                    salActor_);
            this.cmdSequence();
        }
    }
    
    public void standby() {
     
        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.standby();
        else {
            payload_ = String.format("standby entity=%s timeout=1",  
                                    salActor_);
            this.cmdSequence();
        }
    }
    
    public void exitControl() {
     
        if ( EntityType.SEQUENCER.toString().equalsIgnoreCase(salActor_) )
            SalCmdSequencer.exitControl();
        else {
            payload_ = String.format("exitControl entity=%s timeout=1",  
                                    salActor_);
            this.cmdSequence();
        }
    }
    
    private void cmdSequence() {
     
        //out.println("SalCmd.cmdSequence");
        
        SAL_sequencer cmd = new SAL_sequencer();
  	cmd.salCommand("sequencer_command_sequence");

        sequencer.command_sequence command  = new sequencer.command_sequence();
	command.private_revCode = "LSST OCS sequence COMMAND";
        command.device          = "sequencer";
        command.property        = "command";
        command.action          = "send";
        command.value           = "true";
        command.command         = payload_;

	int cmdId = cmd.issueCommand_sequence(command);

	try {Thread.sleep(250);} catch (InterruptedException e)  { e.printStackTrace(); }

        int timeout = 6;
        //status = ocssal.waitForCompletion_cmdsequence(cmdId, timeout);
	cmd.waitForCompletion_sequence(cmdId, timeout);

	// Remove the DataWriters etc
	cmd.salShutdown();
    }
    
}


