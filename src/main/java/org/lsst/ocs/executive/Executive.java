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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class EventTask implements Callable<SalEvent> {
    
    private final String name_;
    private final SalEvent event_;
    private final String cmd_;
    
    public EventTask (SalEvent evt, String cmd) {
        this.name_ = "task." + evt.getName() + "." + cmd;
        this.event_ = evt;
        this.cmd_ = cmd;
    }

    public String getName() {
        return name_;
    }
    
    @Override public SalEvent call() {
        // cmd_ options: enterControl, start, enable, disable, standby, exitcontrol
        try {
            event_.getClass().getMethod(this.cmd_, new Class[]{}).invoke(event_, new Object[]{});
        } 
        catch (Exception e) {
            e.printStackTrace(out.printf(this.getName() + "interrupted"));
        }
        
        return event_;
    }
    
    public static final SalEventOcs OCS_EVENT = new SalEventOcs();
    public static final SalEventSequencer SEQ_EVENT = new SalEventSequencer();
    public static final SalEventCamera CCS_EVENT = new SalEventCamera();
    public static final SalEventTcs TCS_EVENT = new SalEventTcs();
    public static final SalEventArchiver ARC_EVENT = new SalEventArchiver();
    public static final SalEventCatchupArchiver CAT_EVENT = new SalEventCatchupArchiver();
    public static final SalEventProcessingCluster PRO_EVENT = new SalEventProcessingCluster();

    public static final List<EventTask> SUMSTATE_TASKS = Arrays.asList (
        //new EventTask(OCS_EVENT, "summaryState"),
        new EventTask(SEQ_EVENT, "summaryState"),
        new EventTask(CCS_EVENT, "summaryState"),
        new EventTask(TCS_EVENT, "summaryState"),
        new EventTask(ARC_EVENT, "summaryState"),
        new EventTask(CAT_EVENT, "summaryState"),
        new EventTask(PRO_EVENT, "summaryState")
    );

    public static final List<EventTask> SETTINGS_TASKS = Arrays.asList (
        //new EventTask(OCS_EVENT, "summaryState"),
        new EventTask(SEQ_EVENT, "settingsVersion"),
        new EventTask(CCS_EVENT, "settingsVersion"),
        new EventTask(TCS_EVENT, "settingsVersion"),
        new EventTask(ARC_EVENT, "settingsVersion"),
        new EventTask(CAT_EVENT, "settingsVersion"),
        new EventTask(PRO_EVENT, "settingsVersion")
    );

    public static final List<EventTask> APPLIEDSETTINGS_TASKS = Arrays.asList (
        //new EventTask(OCS_EVENT, "summaryState"),
        new EventTask(SEQ_EVENT, "appliedSettingsMatchStartTest"),
        new EventTask(CCS_EVENT, "appliedSettingsMatchStartTest"),
        new EventTask(TCS_EVENT, "appliedSettingsMatchStartTest"),
        new EventTask(ARC_EVENT, "appliedSettingsMatchStartTest"),
        new EventTask(CAT_EVENT, "appliedSettingsMatchStartTest"),
        new EventTask(PRO_EVENT, "appliedSettingsMatchStartTest")
    );

}

class rEventTask implements Runnable {
    
    private final String name_;
    private final SalEvent event_;
    private final String cmd_;
    
    public rEventTask (SalEvent evt, String cmd) {
        this.name_ = "task." + evt.getName() + "." + cmd;
        this.event_ = evt;
        this.cmd_ = cmd;
    }

    public String getName() {
        return name_;
    }
    
    @Override public void run() {
        // cmd_ options: enterControl, start, enable, disable, standby, exitcontrol
        try {
            event_.getClass()
                  .getMethod(this.cmd_, new Class[]{})
                  .invoke(event_, new Object[]{});
        } 
        catch (Exception e) {
            e.printStackTrace(out.printf(this.getName() + "interrupted"));
        }
    }
    
    public static final SalEventOcs rOCS_EVENT = new SalEventOcs();
    public static final SalEventSequencer rSEQ_EVENT = new SalEventSequencer();
    public static final SalEventCamera rCCS_EVENT = new SalEventCamera();
    public static final SalEventTcs rTCS_EVENT = new SalEventTcs();
    public static final SalEventArchiver rARC_EVENT = new SalEventArchiver();
    public static final SalEventCatchupArchiver rCAT_EVENT = new SalEventCatchupArchiver();
    public static final SalEventProcessingCluster rPRO_EVENT = new SalEventProcessingCluster();

    public static final List<rEventTask> rSUMSTATE_TASKS = Arrays.asList (
        new rEventTask(rSEQ_EVENT, "summaryState"),
        new rEventTask(rCCS_EVENT, "summaryState"),
        new rEventTask(rTCS_EVENT, "summaryState"),
        new rEventTask(rARC_EVENT, "summaryState"),
        new rEventTask(rCAT_EVENT, "summaryState"),
        new rEventTask(rPRO_EVENT, "summaryState")
    );
    
    public static final List<rEventTask> rSETTINGS_TASKS = Arrays.asList (
        new rEventTask(rSEQ_EVENT, "settingsVersion"),
        new rEventTask(rCCS_EVENT, "settingsVersion"),
        new rEventTask(rTCS_EVENT, "settingsVersion"),
        new rEventTask(rARC_EVENT, "settingsVersion"),
        new rEventTask(rCAT_EVENT, "settingsVersion"),
        new rEventTask(rPRO_EVENT, "settingsVersion")
    );

    public static final List<rEventTask> rAPPLIEDSETTINGS_TASKS = Arrays.asList (
        new rEventTask(rSEQ_EVENT, "appliedSettingsMatchStartTest"),
        new rEventTask(rCCS_EVENT, "appliedSettingsMatchStartTest"),
        new rEventTask(rTCS_EVENT, "appliedSettingsMatchStartTest"),
        new rEventTask(rARC_EVENT, "appliedSettingsMatchStartTest"),
        new rEventTask(rCAT_EVENT, "appliedSettingsMatchStartTest"),
        new rEventTask(rPRO_EVENT, "appliedSettingsMatchStartTest")
    );    

}


class CmdTask implements Callable<Entity> {
    
    private final String name_;
    private final Entity entity_;
    private final String cmd_;
    
    public CmdTask (Entity entity, String cmd) {
        this.name_ = "task." + entity.etype_.toString() + "." + cmd;
        this.entity_ = entity;
        this.cmd_ = cmd;
    }

    public String getName() {
        return name_;
    }
    
    @Override public Entity call() {
        // cmd_ options: enterControl, start, enable, disable, standby, exitcontrol
        try {
            entity_.getClass().getMethod(this.cmd_, (Class<?>[])null).invoke(null);
        } 
        catch (Exception e) {
            e.printStackTrace(out.printf(this.getName() + "interrupted"));
        }
        
        return entity_;
    }
    
    public static final Entity OCS_ENTITY = new Entity(EntityType.OCS);
    public static final Entity SEQ_ENTITY = new Entity(EntityType.Sequencer);
    public static final Entity CCS_ENTITY = new Entity(EntityType.Camera);
    public static final Entity TCS_ENTITY = new Entity(EntityType.Tcs);
    public static final Entity ARC_ENTITY = new Entity(EntityType.Archiver);
    public static final Entity CAT_ENTITY = new Entity(EntityType.CatchupArchiver);
    public static final Entity PRO_ENTITY = new Entity(EntityType.ProcessingCluster);

    public static final List<CmdTask> ENTERCTRL_TASKS = Arrays.asList (
        new CmdTask(OCS_ENTITY, "enterControl"),
        new CmdTask(SEQ_ENTITY, "enterControl"),
        new CmdTask(CCS_ENTITY, "enterControl"),
        new CmdTask(TCS_ENTITY, "enterControl"),
        new CmdTask(ARC_ENTITY, "enterControl"),
        new CmdTask(CAT_ENTITY, "enterControl"),
        new CmdTask(PRO_ENTITY, "enterControl")
    );

    public static final List<CmdTask> START_TASKS = Arrays.asList (
        new CmdTask(OCS_ENTITY, "start"),
        new CmdTask(SEQ_ENTITY, "start"),
        new CmdTask(CCS_ENTITY, "start"),
        new CmdTask(TCS_ENTITY, "start"),
        new CmdTask(ARC_ENTITY, "start"),
        new CmdTask(CAT_ENTITY, "start"),
        new CmdTask(PRO_ENTITY, "start")
    );

    public static final List<CmdTask> ENABLE_TASKS = Arrays.asList (
        new CmdTask(OCS_ENTITY, "enable"),
        new CmdTask(SEQ_ENTITY, "enable"),
        new CmdTask(CCS_ENTITY, "enable"),
        new CmdTask(TCS_ENTITY, "enable"),
        new CmdTask(ARC_ENTITY, "enable"),
        new CmdTask(CAT_ENTITY, "enable"),
        new CmdTask(PRO_ENTITY, "enable")
    );

    Entity callEntity;

    //Class ocsClass = ocsEntity.getClass();
    //Method m = ocsClass.getMethod("enterControl", (Class<?>[]) null);
    //Class ccsClass = ccsEntity.getClass();
    
    static final Map<String, Map<String, Method>> ENTITY_CMDS_MAP = 
        new HashMap<String, Map<String, Method>>() {
        {
            put(EntityType.OCS.toString(), new HashMap<String, Method>() { 
                {
                try {
                put("enterControl", OCS_ENTITY.getClass().getMethod("enterControl", (Class<?>[])null));
                put("start", OCS_ENTITY.getClass().getMethod("start", (Class<?>[])null));
                put("enable", OCS_ENTITY.getClass().getMethod("enable", (Class<?>[])null));
                put("disable", OCS_ENTITY.getClass().getMethod("disable", (Class<?>[])null));
                put("standby", OCS_ENTITY.getClass().getMethod("standby", (Class<?>[])null));
                put("exitControl", OCS_ENTITY.getClass().getMethod("exitControl", (Class<?>[])null));
            }
            catch (NoSuchMethodException e) {out.println(e.toString());}
                }
            });
            
            put(EntityType.Camera.toString(), new HashMap<String, Method>() { 
                {
                try {
                put("enterControl", CCS_ENTITY.getClass().getMethod("enterControl", (Class<?>[])null));
                put("start", CCS_ENTITY.getClass().getMethod("start", (Class<?>[])null));
                put("enable", CCS_ENTITY.getClass().getMethod("enable", (Class<?>[])null));
                put("disable", CCS_ENTITY.getClass().getMethod("disable", (Class<?>[])null));
                put("standby", CCS_ENTITY.getClass().getMethod("standby", (Class<?>[])null));
                put("exitControl", CCS_ENTITY.getClass().getMethod("exitControl", (Class<?>[])null));
            }
            catch (NoSuchMethodException e) {out.println(e.toString());}
                }
            });
            
        }
    };
    
    public void enterControl() 
        throws IllegalAccessException, InvocationTargetException {

            ENTITY_CMDS_MAP.get(callEntity.etype_.toString())
                           .get("enterControl")
                           .invoke(null);
    }
    
}

class rCmdTask implements Runnable {
    
    private final String name_;
    private final Entity entity_;
    private final String cmd_;
    
    public rCmdTask (Entity entity, String cmd) {
        this.name_ = "task." + entity.etype_.toString() + "." + cmd;
        this.entity_ = entity;
        this.cmd_ = cmd;
    }

    public String getName() {
        return name_;
    }
    
    @Override public void run() {
        // cmd_ options: enterControl, start, enable, disable, standby, exitcontrol
        try {
            entity_.getClass()
                   .getMethod(this.cmd_, new Class[]{})
                   .invoke(entity_, new Object[]{});
        } 
        catch (Exception e) {
            e.printStackTrace(out.printf(this.getName() + "interrupted"));
        }
    }
    
    public static final Entity rOCS_ENTITY = new Entity(EntityType.OCS);
    public static final Entity rSEQ_ENTITY = new Entity(EntityType.Sequencer);
    public static final Entity rCCS_ENTITY = new Entity(EntityType.Camera);
    public static final Entity rTCS_ENTITY = new Entity(EntityType.Tcs);
    public static final Entity rARC_ENTITY = new Entity(EntityType.Archiver);
    public static final Entity rCAT_ENTITY = new Entity(EntityType.CatchupArchiver);
    public static final Entity rPRO_ENTITY = new Entity(EntityType.ProcessingCluster);

    public static final List<rCmdTask> rENTERCTRL_TASKS = Arrays.asList (
        new rCmdTask(rSEQ_ENTITY, "enterControl"),
        new rCmdTask(rCCS_ENTITY, "enterControl"),
        new rCmdTask(rTCS_ENTITY, "enterControl"),
        new rCmdTask(rARC_ENTITY, "enterControl"),
        new rCmdTask(rCAT_ENTITY, "enterControl"),
        new rCmdTask(rPRO_ENTITY, "enterControl")
    );
}
/**
 *
 * Executive is the Context class implementation
 *
 */

public class Executive {
    
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        //ExecutorService ex_sumstate = Executors.newWorkStealingPool();
        ExecutorService ex_sumstate = Executors.newFixedThreadPool(6);
        //List<Future<SalEvent>> sumStateFutures  = ex_sumstate.invokeAll(EventTask.SUMSTATE_TASKS);
        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(0));
        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(1));
        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(2));
        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(3));
        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(4));
        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(5));
        
        ExecutorService ex_settings = Executors.newFixedThreadPool(6);
        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(0));
        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(1));
        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(2));
        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(3));
        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(4));
        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(5));
        
        ExecutorService ex_appliedsettings = Executors.newFixedThreadPool(6);
        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(0));
        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(1));
        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(2));
        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(3));
        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(4));
        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(5));
        
        /*
          sumStateFutures.parallelStream()
                        .map((Future<SalEvent> future) -> {
                            try {
                                return future.get();
                            }
                            catch (Exception e) {
                                throw new IllegalStateException(e);
                            }
                        })
                        .collect(Collectors.toList())
                        .forEach(event -> event.getName());
          */
        
        BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));
        out.println("\n" + " Event Subscribers invoking...");
        ss.readLine();

        BufferedReader ss1 = new BufferedReader(new InputStreamReader(System.in));
        out.println("\n" + " Event Subscribers done...");
        ss1.readLine();

        /**********************************************************************/
        
        //ExecutorService ex = Executors.newWorkStealingPool();
        //ExecutorService ex = Executors.newFixedThreadPool(6);

        
        //List<Future<Entity>> enterCtrlFutures  = ex.invokeAll(CmdTask.ENTERCTRL_TASKS);
        
        /*
        ex.submit(rCmdTask.rENTERCTRL_TASKS.get(0));
        ex.submit(rCmdTask.rENTERCTRL_TASKS.get(1));
        ex.submit(rCmdTask.rENTERCTRL_TASKS.get(2));
        ex.submit(rCmdTask.rENTERCTRL_TASKS.get(3));
        ex.submit(rCmdTask.rENTERCTRL_TASKS.get(4));
        ex.submit(rCmdTask.rENTERCTRL_TASKS.get(5));
        */
        
        /*
        BufferedReader ss2 = new BufferedReader(new InputStreamReader(System.in));
        out.println("\n" + " EnterControl tasks invoking...");
        ss2.readLine();
        */

        /*
        enterCtrlFutures.parallelStream()
                        .map((Future<Entity> future) -> {
                            try {
                                return future.get();
                            }
                            catch (Exception e) {
                                throw new IllegalStateException(e);
                            }
                        })
                        .collect(Collectors.toList())
                        .parallelStream()
                        .map(entity -> ex.submit(new CmdTask(entity, "start")));
                        //.forEach(entity -> ex.submit(new CmdTask(entity, "start")));
                        //.forEach(out::println);
        */
        
    /************************************/
    /***    OCS Sequencer STM Init    ***/
    /************************************/
        // 1. Wait for StartNight cmd from operator
        ObservingMode observingMode;

        // OCS Entity object created & initialized to OfflineState
        //Entity ocsEntity = new Entity(EntityType.OCS, observingMode);
        //Entity ocsEntity = new Entity(EntityType.OCS, ObservingMode.SCIENCE);
        //Entity seqEntity = new Entity(EntityType.SEQUENCER);
        
        // 2. Transition to 'StandbyState'
        //    Verify OCS SummaryState of 'StandbyState'
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        out.print("\n" + EntityType.SEQUENCER.toString()+ " enterControl...");
        br.readLine();
        
        /*
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Runnable taskSeq = () -> {
            try { 
                out.println("Executing enterControl thread task");
                //seqEntity.enterControl();
                rCmdTask.SEQ_ENTITY.enterControl();
            } 
            catch (Exception e) {
                e.printStackTrace(out.printf("taskSeq enterControl interrupted"));
            }
        };
        // submit method returns a Future so curry to Future's get() method
        // Block until task finishes
        executor.submit(taskSeq);
                //.get();
        
        // 3. Transition to 'DisabledState'
        //    Verify settings; verify OCS SummaryState of 'DisabledState'
        out.print("\n" + EntityType.SEQUENCER.toString()+ " start...");
        br.readLine();
        
        Runnable taskSeq1 = () -> {
            try { 
                out.println("Executing start thread task");
                //seqEntity.start();
                rCmdTask.SEQ_ENTITY.start();
            } 
            catch (Exception e) {
                e.printStackTrace(out.printf("taskSeq enable interrupted"));
            }
        };
        //Block until task finishes
        executor.submit(taskSeq1);
                //.get();

        // 4. Transition to 'EnabledState'
        //    Verify settings; verify OCS SummaryState of 
        out.print("\n" + EntityType.SEQUENCER.toString()+ " enable...");
        br.readLine();
        
        Runnable taskSeq2 = () -> {
            try { 
                out.println("Executing enable thread task");
                //seqEntity.enable();
                rCmdTask.SEQ_ENTITY.enable();
            } 
            catch (Exception e) {
                e.printStackTrace(out.printf("taskSeq enable interrupted"));
            }
        };
        //Block until task finishes
        executor.submit(taskSeq2);
                //.get();
        
        // Shutdown single thread executor
        try {
            out.println("shutting down executor...");
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            out.println("task interrupted");
        }
        finally {
            if(!executor.isTerminated()) {
                err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            out.println("executor shutdown finished");
        }
    */
        
        rCmdTask.rSEQ_ENTITY.enterControl();
        
        out.print("\n" + EntityType.SEQUENCER.toString()+ " start...");
        br.readLine();
        rCmdTask.rSEQ_ENTITY.start();

        out.print("\n" + EntityType.SEQUENCER.toString()+ " enable...");
        br.readLine();
        rCmdTask.rSEQ_ENTITY.enable();
        
    /**************************/
    /***    CCS STM Init    ***/
    /**************************/
    
        // CCS Entity object created
        //Entity ccsEntity = new Entity(EntityType.CCS);
        //Entity ccsEntity = new Entity(EntityType.Camera);
        
        // 1. Verify SummaryState of 'OfflineState[AvailableState]'
        // --- Add code ---
        
        // 2. cmd CCS to 'StandbyState'
        out.print("\n" + EntityType.CAMERA.toString() + " sequence cmd enterControl...");
        br.readLine();
        rCmdTask.rCCS_ENTITY.enterControl();
        
        // 2a. Verify SummaryState of 'StandbyState'
        // --- Add code ---
        
        // 2b. Determine a 'RecommendedSettingsVersion'
        // --- Add code ---

        // 3. cmd CCS to 'DisabledState'
        out.print("\n" + EntityType.CAMERA.toString() + " sequence cmd start...");
        br.readLine();
        rCmdTask.rCCS_ENTITY.start();
        
        // 3a. Verify SummaryState of 'DisabledState'
        // --- Add code ---

        // 3b. Verify 'SettingsApplied'
        // --- Add code ---

        // 3c. Verify 'AppliedSettingsMatchStart'
        // --- Add code ---

        // 4. cmd CCS to 'EnabledState'
        out.print("\n" + EntityType.CAMERA.toString() + " sequence cmd enable...");
        br.readLine();
        rCmdTask.rCCS_ENTITY.enable();
        
        // 4a. Verify SummaryState of 'EnabledState'
        // --- Add code ---
        
    /**************************/
    /***    TCS STM Init    ***/
    /**************************/
    
        out.print("\n" + EntityType.TCS.toString() + " sequence cmd enterControl...");
        br.readLine();
        rCmdTask.rTCS_ENTITY.enterControl();
        out.print("\n" + EntityType.TCS.toString() + " sequence cmd start...");
        br.readLine();
        rCmdTask.rTCS_ENTITY.start();
        out.print("\n" + EntityType.TCS.toString() + " sequence cmd enable...");
        br.readLine();
        rCmdTask.rTCS_ENTITY.enable();
    
    /*****************************/
    /***   DM STM Init   ***/
    /*****************************/
    
        // 1. Verify SummaryState of 'OfflineState[AvailableState]'
        // --- Add code ---
        
        // 2. cmd CCS to 'StandbyState'
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd enterControl...");
        br.readLine();
        rCmdTask.rARC_ENTITY.enterControl();
        
        // 2a. Verify SummaryState of 'StandbyState'
        // --- Add code ---
        
        // 2b. Determine a 'RecommendedSettingsVersion'
        // --- Add code ---

        // 3. cmd CCS to 'DisabledState'
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd start...");
        br.readLine();
        rCmdTask.rARC_ENTITY.start();
        
        // 3a. Verify SummaryState of 'DisabledState'
        // --- Add code ---

        // 3b. Verify 'SettingsApplied'
        // --- Add code ---

        // 3c. Verify 'AppliedSettingsMatchStart'
        // --- Add code ---

        // 4. cmd CCS to 'EnabledState'
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd enable...");
        br.readLine();
        rCmdTask.rARC_ENTITY.enable();
        
        // 4a. Verify SummaryState of 'EnabledState'
        // --- Add code ---
        
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd enterControl...");
        br.readLine();
        rCmdTask.rCAT_ENTITY.enterControl();
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd start...");
        br.readLine();
        rCmdTask.rCAT_ENTITY.start();
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd enable...");
        br.readLine();
        rCmdTask.rCAT_ENTITY.enable();

        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd enterControl...");
        br.readLine();
        rCmdTask.rPRO_ENTITY.enterControl();
        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd start...");
        br.readLine();
        rCmdTask.rPRO_ENTITY.start();
        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd enable...");
        br.readLine();
        rCmdTask.rPRO_ENTITY.enable();


    /**************************/
    /***  DM STM Shutdown  ***/
    /**************************/

        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd disable...");
        br.readLine();
        rCmdTask.rPRO_ENTITY.disable();
        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd standby...");
        br.readLine();
        rCmdTask.rPRO_ENTITY.standby();
        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd exitControl...");
        br.readLine();
        rCmdTask.rPRO_ENTITY.exitControl();
    
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd disable...");
        br.readLine();
        rCmdTask.rCAT_ENTITY.disable();
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd standby...");
        br.readLine();
        rCmdTask.rCAT_ENTITY.standby();
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd exitControl...");
        br.readLine();
        rCmdTask.rCAT_ENTITY.exitControl();
    
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd disable...");
        br.readLine();
        rCmdTask.rARC_ENTITY.disable();
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd standby...");
        br.readLine();
        rCmdTask.rARC_ENTITY.standby();
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd exitControl...");
        br.readLine();
        rCmdTask.rARC_ENTITY.exitControl();
    
    /**************************/
    /***    TCS STM Shutdown    ***/
    /**************************/
    
        out.print("\n" + EntityType.TCS.toString() + " sequence cmd disable...");
        br.readLine();
        rCmdTask.rTCS_ENTITY.disable();
        
        out.print("\n" + EntityType.TCS.toString() + " sequence cmd standby...");
        br.readLine();
        rCmdTask.rTCS_ENTITY.standby();
        
        out.print("\n" + EntityType.TCS.toString() + " sequence cmd exitControl...");
        br.readLine();
        rCmdTask.rTCS_ENTITY.exitControl();

    /**************************/
    /***  CCS STM Shutdown  ***/
    /**************************/

        out.print("\n" + EntityType.CAMERA.toString() + " sequence cmd disable...");
        br.readLine();
        rCmdTask.rCCS_ENTITY.disable();
        
        out.print("\n" + EntityType.CAMERA.toString() + " sequence cmd standby...");
        br.readLine();
        rCmdTask.rCCS_ENTITY.standby();
        
        out.print("\n" + EntityType.CAMERA.toString() + " sequence cmd exitControl...");
        br.readLine();
        rCmdTask.rCCS_ENTITY.exitControl();
        
    /************************************/
    /***  OCS Sequencer STM Shutdown  ***/
    /************************************/
    
        out.print("\n" + EntityType.SEQUENCER.toString()+ " disable...");
        br.readLine();
        //seqEntity.disable();
        rCmdTask.rSEQ_ENTITY.disable();
        
        out.print("\n" + EntityType.SEQUENCER.toString()+ " standby...");
        br.readLine();
        //seqEntity.standby();
        rCmdTask.rSEQ_ENTITY.standby();
                
        out.print("\n" + EntityType.SEQUENCER.toString()+ " exitControl...");
        br.readLine();
        //seqEntity.exitControl();
        rCmdTask.rSEQ_ENTITY.exitControl();
                

    /**************************/
    /***    TCS STM Init    ***/
    /**************************/
/*    
        // TCS Entity object created
        Entity tcsEntity = new Entity(EntityType.TCS);
        
        // 1. Verify SummaryState of 'OfflineState[AvailableState]'
        // --- Add code ---
        
        // 2. cmd tcs to 'StandbyState'
        // if ( tcsEntity.getName().equals( tcsBoundary.SummaryState() )
        tcsEntity.enterControl();
        
        // 2a. Verify SummaryState of 'StandbyState'
        // --- Add code ---
        
        // 2b. Determine a 'RecommendedSettingsVersion'
        // --- Add code ---

        // 3. cmd tcs to 'DisabledState'
        tcsEntity.start();
        
        // 3a. Verify SummaryState of 'DisabledState'
        // --- Add code ---

        // 3b. Verify 'SettingsApplied'
        // --- Add code ---

        // 3c. Verify 'AppliedSettingsMatchStart'
        // --- Add code ---

        // 4. cmd tcs to 'EnabledState'
        tcsEntity.enable();
        
        // 4a. Verify SummaryState of 'EnabledState'
        // --- Add code ---
*/
        
    /**************************/
    /***    DMCS STM Init    ***/
    /**************************/
/*    
        // DMCS Entity object created
        Entity dmcsEntity = new Entity(EntityType.DMCS);
        
        // 1. Verify SummaryState of 'OfflineState[AvailableState]'
        // --- Add code ---
        
        // 2. cmd DMCS to 'StandbyState'
        dmcsEntity.enterControl();
        
        // 2a. Verify SummaryState of 'StandbyState'
        // --- Add code ---
        
        // 2b. Determine a 'RecommendedSettingsVersion'
        // --- Add code ---

        // 3. cmd DMCS to 'DisabledState'
        dmcsEntity.start();
        
        // 3a. Verify SummaryState of 'DisabledState'
        // --- Add code ---

        // 3b. Verify 'SettingsApplied'
        // --- Add code ---

        // 3c. Verify 'AppliedSettingsMatchStart'
        // --- Add code ---

        // 4. cmd DMCS to 'EnabledState'
        dmcsEntity.enable();
        
        // 4a. Verify SummaryState of 'EnabledState'
        // --- Add code ---
*/        
        
    } 
    
}
