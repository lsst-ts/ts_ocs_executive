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

import static java.lang.System.out;

import java.util.logging.*;

import java.util.Arrays;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.lsst.ocs.executive.salcomponent.*;
import org.lsst.ocs.executive.salconnect.SalConnect;
import org.lsst.ocs.executive.salservice.*;


class EventTask implements Callable<CommandableSalComponent> {
    
    private final CommandableSalComponent csc_;
    private final String event_;
    private final String name_;
    
    public EventTask (CommandableSalComponent csc, String event) {
        this.csc_ = csc;
        this.event_ = event;
        this.name_ = this.csc_.getName() + ".task"; 
    }

    public String getName() { return name_; }
    
    @Override public CommandableSalComponent call() {
        // cmd_ options: enterControl, start, enable, disable, standby, exitcontrol
        try {
            csc_.getClass()
                .getMethod(this.event_, new Class[]{}) // invoke w/ null args
                .invoke(csc_, new Object[]{}); // invoke w/ null args
        } 
        catch (Exception e) {
            e.printStackTrace(out.printf(this.getName() + "interrupted"));
        }
        
        return csc_;
    }

    static final CommandableSalComponent CCS = new CSCCamera();
    static final CommandableSalComponent TCS = new CSCTcs();
    static final CommandableSalComponent ARC = new CSCArchiver();
    static final CommandableSalComponent CAT = new CSCCatchupArchiver();
    static final CommandableSalComponent PRO = new CSCProcessingCluster();

    public static final List<EventTask> SUMSTATE_TASKS = Arrays.asList (
        new EventTask(CCS, "summaryState"),
        new EventTask(TCS, "summaryState"),
        new EventTask(ARC, "summaryState"),
        new EventTask(CAT, "summaryState"),
        new EventTask(PRO, "summaryState")
    );

    public static final List<EventTask> SETTINGS_TASKS = Arrays.asList (
        new EventTask(CCS, "settingsVersion"),
        new EventTask(TCS, "settingsVersion"),
        new EventTask(ARC, "settingsVersion"),
        new EventTask(CAT, "settingsVersion"),
        new EventTask(PRO, "settingsVersion")
    );

    public static final List<EventTask> APPLIEDSETTINGS_TASKS = Arrays.asList (
        new EventTask(CCS, "appliedSettingsMatchStartTest"),
        new EventTask(TCS, "appliedSettingsMatchStartTest"),
        new EventTask(ARC, "appliedSettingsMatchStartTest"),
        new EventTask(CAT, "appliedSettingsMatchStartTest"),
        new EventTask(PRO, "appliedSettingsMatchStartTest")
    );
}

class rEventTask implements Runnable {
    
    private final CommandableSalComponent csc_;
    private final String event_;
    private final String name_;
    
    public rEventTask (CommandableSalComponent csc, String event) {
        this.csc_ = csc;
        this.event_ = event;
        this.name_ = this.csc_.getName() + ".task"; 
    }

    public String getName() { return name_; }
    
    @Override public void run() {
        // cmd_ options: enterControl, start, enable, disable, standby, exitcontrol
        try {
            csc_.getClass()
                .getMethod(this.event_, new Class[]{}) // invoke w/ null args
                .invoke(csc_, new Object[]{}); // invoke w/ null args
        } 
        catch (Exception e) {
            e.printStackTrace(out.printf(this.getName() + "interrupted"));
        }
    }
    
    public static final CommandableSalComponent rCCS = new CSCCamera();
    public static final CommandableSalComponent rTCS = new CSCTcs();
    public static final CommandableSalComponent rARC = new CSCArchiver();
    public static final CommandableSalComponent rCAT = new CSCCatchupArchiver();
    public static final CommandableSalComponent rPRO = new CSCProcessingCluster();

    public static final List<rEventTask> rSUMSTATE_TASKS = Arrays.asList (
        new rEventTask(rCCS, "summaryState"),
        new rEventTask(rTCS, "summaryState"),
        new rEventTask(rARC, "summaryState"),
        new rEventTask(rCAT, "summaryState"),
        new rEventTask(rPRO, "summaryState")
    );

    public static final List<rEventTask> rSETTINGS_TASKS = Arrays.asList (
        new rEventTask(rCCS, "settingsVersion"),
        new rEventTask(rTCS, "settingsVersion"),
        new rEventTask(rARC, "settingsVersion"),
        new rEventTask(rCAT, "settingsVersion"),
        new rEventTask(rPRO, "settingsVersion")
    );

    public static final List<rEventTask> rAPPLIEDSETTINGS_TASKS = Arrays.asList (
        new rEventTask(rCCS, "appliedSettingsMatchStartTest"),
        new rEventTask(rTCS, "appliedSettingsMatchStartTest"),
        new rEventTask(rARC, "appliedSettingsMatchStartTest"),
        new rEventTask(rCAT, "appliedSettingsMatchStartTest"),
        new rEventTask(rPRO, "appliedSettingsMatchStartTest")
    );
}

class CmdTask implements Callable<SalCmd> {
    
    private final SalCmd command_;
    private final String method_;
    private final String name_;
    
    public CmdTask (SalCmd cmd, String method) {
        this.command_ = cmd;
        this.method_ = method;
        this.name_ = cmd.getName() + ".task"; 
    }

    public String getName() { return name_; }
    
    @Override public SalCmd call() {
        // cmd_ options: enterControl, start, enable, disable, standby, exitcontrol
        try {
            command_.getClass()
                    .getMethod(this.method_, new Class[]{}) // invoke w/ null args
                    .invoke(command_, new Object[]{}); // invoke w/ null args
        } 
        catch (Exception e) {
            e.printStackTrace(out.printf(this.getName() + "interrupted"));
        }
        
        return command_;
    }
    
    public static final CommandableSalComponent CCS = new CSCCamera();
    public static final CommandableSalComponent TCS = new CSCTcs();
    public static final CommandableSalComponent ARC = new CSCArchiver();
    public static final CommandableSalComponent CAT = new CSCCatchupArchiver();
    public static final CommandableSalComponent PRO = new CSCProcessingCluster();

    public static final List<CmdTask> ENTERCTRL_TASKS = Arrays.asList (
        new CmdTask(new SalCmd(CCS), "enterControl"),
        new CmdTask(new SalCmd(TCS), "enterControl"),
        new CmdTask(new SalCmd(ARC), "enterControl"),
        new CmdTask(new SalCmd(CAT), "enterControl"),
        new CmdTask(new SalCmd(PRO), "enterControl")
    );

    public static final List<CmdTask> START_TASKS = Arrays.asList (
        new CmdTask(new SalCmd(CCS), "start"),
        new CmdTask(new SalCmd(TCS), "start"),
        new CmdTask(new SalCmd(ARC), "start"),
        new CmdTask(new SalCmd(CAT), "start"),
        new CmdTask(new SalCmd(PRO), "start")
    );

    public static final List<CmdTask> ENABLE_TASKS = Arrays.asList (
        new CmdTask(new SalCmd(CCS), "enable"),
        new CmdTask(new SalCmd(TCS), "enable"),
        new CmdTask(new SalCmd(ARC), "enable"),
        new CmdTask(new SalCmd(CAT), "enable"),
        new CmdTask(new SalCmd(PRO), "enable")
    );
}

class rCmdTask implements Runnable {
    
    private final CommandableSalComponent csc_;
    private final String cmd_;
    private final String name_;
    
    public rCmdTask (CommandableSalComponent csc, String cmd) {
        this.csc_ = csc;
        this.cmd_ = cmd;
        this.name_ = this.csc_.getName() + ".task"; 
    }

    public String getName() { return name_; }
    
    @Override public void run() {
        // cmd_ options: enterControl, start, enable, disable, standby, exitcontrol
        try {
            csc_.getClass()
                .getMethod(this.cmd_, new Class[]{}) // invoke w/ null args
                .invoke(this.csc_, new Object[]{}); // invoke w/ null args
        } 
        catch (Exception e) {
            e.printStackTrace(out.printf(this.getName() + "interrupted"));
        }
    }
    
    public static final CommandableSalComponent rCCS = new CSCCamera();
    public static final CommandableSalComponent rTCS = new CSCTcs();
    public static final CommandableSalComponent rARC = new CSCArchiver();
    public static final CommandableSalComponent rCAT = new CSCCatchupArchiver();
    public static final CommandableSalComponent rPRO = new CSCProcessingCluster();

    public static final List<rCmdTask> rENTERCTRL_TASKS = Arrays.asList (
        new rCmdTask(rCCS, "enterControl"),
        new rCmdTask(rTCS, "enterControl"),
        new rCmdTask(rARC, "enterControl"),
        new rCmdTask(rCAT, "enterControl"),
        new rCmdTask(rPRO, "enterControl")
    );
}
/**
 *
 * Executive is the Context class implementation
 *
 */

public class Executive {
//public class Executive extends Application {
    
    //private final static Logger LOGGER = Logger.getGlobal();
    //private final Logger logger = Logger.getLogger(this.getClass().getPackage().getName());

//    final static Logger logger = Logger.getLogger("org.lsst.ocs.executive");
//    final static Handler chandler = new ConsoleHandler();
//    
//    static {
//        
//        //LOGGER.addHandler(new ConsoleHandler());
//        //logger.addHandler(new FileHandler());
//
//        try { Logger.getLogger("").addHandler(new FileHandler()); } 
//        catch (IOException e) {}
//                
//        logger.setLevel(Level.CONFIG);
//        logger.setUseParentHandlers(false);
//        chandler.setLevel(Level.CONFIG);
//        logger.addHandler(chandler);
//        
//        Exception processed = null;
//        try {
//            logger.config("About to load Logging configuration file");
//            
//            LogManager.getLogManager()
//                      .readConfiguration(new FileInputStream("/home/jbuffill/logging.properties"));
//        }
//        catch (SecurityException | IOException e) {
//            processed = e;
//            LogManager.getLogManager()
//                      .getLogger("conflogger")
//                      .log(Level.SEVERE, "Error in loading logging configuration", e);
//            //e.printStackTrace();
//        }
//        finally {
//            if(processed == null) {
//                logger.setLevel(Level.CONFIG);
//                logger.setUseParentHandlers(false);
//                chandler.setLevel(Level.CONFIG);
//                logger.addHandler(chandler);
//                
//                logger.config("Logging configuration file loaded successfully");
//            }
//        }
//    }

//    Button btn = new Button();
//    Text msg = new Text();
        
    public void invokeWindow(Stage primaryStage) {
        
        Button btn = new Button();
        Text msg = new Text();
        
        btn.setText("Say 'Hello World'");
        
        btn.setOnAction(event -> {
            
            msg.setText("Hello World! JavaFX style :)");
        });
        
        btn.setVisible(true);
        
        
        // root node of the scene is a vertical box
        // btn control added to 1st row in column; text msg control added to 2nd row in same column
        VBox root = new VBox(10, btn, msg);
        root.setAlignment(CENTER);
        
        // Scene class is the container for all content
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello JavaFX 8 World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.toFront();
        //primaryStage.requestFocus();
        
        //Thread.currentThread().setName(myname);
        
        out.print(this.getClass()
                      .getSimpleName()+"::"
                                      +Thread.currentThread().getStackTrace()[1].getMethodName()
                                      +"::");
        
        out.print(Thread.currentThread().getName());
        out.println(" "+"id: "+Thread.currentThread().getId());
        
    }

    //@Override
    // start() method is the main entry point for all JavaFX applications
    //public void start(Stage primaryStage) { // Stage class is the top-level JavaFX container
    public void start(Stage primaryStage) throws IOException, InterruptedException, ExecutionException {
    //public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        out.print(this.getClass()
                      .getSimpleName()+"::"
                                      +Thread.currentThread().getStackTrace()[1].getMethodName()
                                      +"::");
        
        out.print(Thread.currentThread().getName());
        out.println(" "+"id: "+Thread.currentThread().getId());

//        Callable<String> winTask = () -> { 
//            invokeWindow(primaryStage); 
//            return "done";
//        };
//        ExecutorService es = Executors.newSingleThreadExecutor();
//        Future<String> winFuture = es.submit(winTask);
//        /*Future<String> winFuture = Executors.newSingleThreadExecutor().submit(winTask);*/
//        winFuture.get();

//        Future<String> winFuture = 
//                Executors.newSingleThreadExecutor()
//                         .submit((Callable<String>) () -> {
//                             invokeWindow(primaryStage);
//                             return "done";
//                         }
//        );
//        winFuture.get();

//        Platform.runLater(() -> { 
//            invokeWindow(primaryStage);
//        });
        
//        javafx.concurrent.Task fxtask = new javafx.concurrent.Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                invokeWindow(primaryStage);
//                return null;
//            }
//        };
//        new Thread(fxtask).start();

//        javafx.concurrent.Task fxtask = new javafx.concurrent.Task<Void>() {
//            @Override protected Void call() throws Exception {
//                Platform.runLater(new Runnable() {
//                    @Override public void run() {
//                        invokeWindow(primaryStage);
//                    }
//                });
//                return null;
//            }
//        };
//        //new Thread(fxtask).start();
//        ExecutorService es = Executors.newSingleThreadExecutor();
//        Future<?> winFuture = es.submit(fxtask);
//        winFuture.get(); // blocks until thread returns
        
        //new Thread(() -> invokeWindow(primaryStage) ).start();

        //ExecutorService ex_sumstate = Executors.newWorkStealingPool();
        //List<Future<SalEvent>> sumStateFutures  = ex_sumstate.invokeAll(EventTask.SUMSTATE_TASKS);
        ExecutorService ex_sumstate = Executors.newFixedThreadPool(1);
        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(0));
//        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(1));
//        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(2));
//        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(3));
//        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(4));
//        ex_sumstate.submit(rEventTask.rSUMSTATE_TASKS.get(5));
        
//        ExecutorService ex_settings = Executors.newFixedThreadPool(6);
//        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(0));
//        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(1));
//        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(2));
//        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(3));
//        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(4));
//        ex_settings.submit(rEventTask.rSETTINGS_TASKS.get(5));
        
//        ExecutorService ex_appliedsettings = Executors.newFixedThreadPool(6);
//        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(0));
//        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(1));
//        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(2));
//        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(3));
//        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(4));
//        ex_appliedsettings.submit(rEventTask.rAPPLIEDSETTINGS_TASKS.get(5));
        
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
        
        out.println("\n" + " Event Subscribers invoking...");
        BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));
        ss.readLine();

        if (false) {
        
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
        
        /*
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Runnable taskSeq = () -> {
            try { 
                out.println("Executing enterControl thread task");
                //seqEntity.enterControl();
                rCmdTask.SEQ.enterControl();
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
                rCmdTask.SEQ.start();
            } 
            catch (Exception e) {
                e.printStackTrace(out.printf("taskSeq enable interrupted"));
            }
        };
        // Block until task finishes
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
                rCmdTask.SEQ.enable();
            } 
            catch (Exception e) {
                e.printStackTrace(out.printf("taskSeq enable interrupted"));
            }
        };
        // Block until task finishes
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

        // [A.]
        // The rENTERCTRL_TASKS list has defined rcvr(rCCS) & cmd(enterControl)
        // new SalCmd(rCCS, "enterControl")
        //CmdTask.ENTERCTRL_TASKS.get(0).call();
        rCmdTask.rENTERCTRL_TASKS.get(0).run();

        // [B.]
        // Receiver previously defined: rCCS = new CSCCamera();
        rCmdTask.rCCS.enterControl();
        
        // [C.]
        // 1. SalComponent (Receiver) previously defined: rCCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        SalCmd salCmdCamera = new SalCmd(rCmdTask.rCCS);
        salCmdCamera.setTopic("enterControl");
        // 3. Define Invoker & set SalService request
        SalConnect salConnectCamera = new SalConnect();
        salConnectCamera.setSalService(salCmdCamera);
        //salConnectCamera.setSalCmd(new SalCmd(rCCS), "enterControl");
        // 4. Invoker indirectly calls cmd->execute()
        salConnectCamera.connect();
        
        // 2a. Verify SummaryState of 'StandbyState'
        // --- Add code ---
        
        // 2b. Determine a 'RecommendedSettingsVersion'
        // --- Add code ---

        // 3. cmd CCS to 'DisabledState'
        out.print("\n" + EntityType.CAMERA.toString() + " sequence cmd start...");
        br.readLine();
        rCmdTask.rCCS.start();

        salCmdCamera.setTopic("start");
        salConnectCamera.connect();
        
        // 3a. Verify SummaryState of 'DisabledState'
        // --- Add code ---

        // 3b. Verify 'SettingsApplied'
        // --- Add code ---

        // 3c. Verify 'AppliedSettingsMatchStart'
        // --- Add code ---

        // 4. cmd CCS to 'EnabledState'
        out.print("\n" + EntityType.CAMERA.toString() + " sequence cmd enable...");
        br.readLine();
        rCmdTask.rCCS.enable();
        
        salCmdCamera.setTopic("start");
        salConnectCamera.connect();

        // 4a. Verify SummaryState of 'EnabledState'
        // --- Add code ---
        
    /**************************/
    /***    TCS STM Init    ***/
    /**************************/

        out.print("\n" + EntityType.TCS.toString() + " sequence cmd enterControl...");
        br.readLine();
        rCmdTask.rTCS.enterControl();
        
        SalCmd salCmdTcs = new SalCmd(rCmdTask.rTCS);
        salCmdTcs.setTopic("enterControl");
        SalConnect salConnectTcs = new SalConnect();
        salConnectTcs.setSalService(salCmdTcs);
        salConnectTcs.connect();
    
        out.print("\n" + EntityType.TCS.toString() + " sequence cmd start...");
        br.readLine();
        rCmdTask.rTCS.start();
        
        salCmdTcs.setTopic("start");
        salConnectTcs.connect();

        out.print("\n" + EntityType.TCS.toString() + " sequence cmd enable...");
        br.readLine();
        rCmdTask.rTCS.enable();
    
        salCmdTcs.setTopic("enable");
        salConnectTcs.connect();

    /*****************************/
    /***   DM STM Init   ***/
    /*****************************/
    
        // 1. Verify SummaryState of 'OfflineState[AvailableState]'
        // --- Add code ---
        
        // 2. cmd CCS to 'StandbyState'
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd enterControl...");
        br.readLine();
        rCmdTask.rARC.enterControl();
        
        // 2a. Verify SummaryState of 'StandbyState'
        // --- Add code ---
        
        // 2b. Determine a 'RecommendedSettingsVersion'
        // --- Add code ---

        // 3. cmd CCS to 'DisabledState'
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd start...");
        br.readLine();
        rCmdTask.rARC.start();
        
        // 3a. Verify SummaryState of 'DisabledState'
        // --- Add code ---

        // 3b. Verify 'SettingsApplied'
        // --- Add code ---

        // 3c. Verify 'AppliedSettingsMatchStart'
        // --- Add code ---

        // 4. cmd CCS to 'EnabledState'
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd enable...");
        br.readLine();
        rCmdTask.rARC.enable();
        
        // 4a. Verify SummaryState of 'EnabledState'
        // --- Add code ---
        
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd enterControl...");
        br.readLine();
        rCmdTask.rCAT.enterControl();
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd start...");
        br.readLine();
        rCmdTask.rCAT.start();
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd enable...");
        br.readLine();
        rCmdTask.rCAT.enable();

        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd enterControl...");
        br.readLine();
        rCmdTask.rPRO.enterControl();
        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd start...");
        br.readLine();
        rCmdTask.rPRO.start();
        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd enable...");
        br.readLine();
        rCmdTask.rPRO.enable();


    /**************************/
    /***  DM STM Shutdown  ***/
    /**************************/

        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd disable...");
        br.readLine();
        rCmdTask.rPRO.disable();
        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd standby...");
        br.readLine();
        rCmdTask.rPRO.standby();
        out.print("\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd exitControl...");
        br.readLine();
        rCmdTask.rPRO.exitControl();
    
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd disable...");
        br.readLine();
        rCmdTask.rCAT.disable();
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd standby...");
        br.readLine();
        rCmdTask.rCAT.standby();
        out.print("\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd exitControl...");
        br.readLine();
        rCmdTask.rCAT.exitControl();
    
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd disable...");
        br.readLine();
        rCmdTask.rARC.disable();
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd standby...");
        br.readLine();
        rCmdTask.rARC.standby();
        out.print("\n" + EntityType.ARCHIVER.toString() + " sequence cmd exitControl...");
        br.readLine();
        rCmdTask.rARC.exitControl();
    
    /**************************/
    /***    TCS STM Shutdown    ***/
    /**************************/
    
        out.print("\n" + EntityType.TCS.toString() + " sequence cmd disable...");
        br.readLine();
        rCmdTask.rTCS.disable();
        
        out.print("\n" + EntityType.TCS.toString() + " sequence cmd standby...");
        br.readLine();
        rCmdTask.rTCS.standby();
        
        out.print("\n" + EntityType.TCS.toString() + " sequence cmd exitControl...");
        br.readLine();
        rCmdTask.rTCS.exitControl();

    /**************************/
    /***  CCS STM Shutdown  ***/
    /**************************/

        out.print("\n" + EntityType.CAMERA.toString() + " sequence cmd disable...");
        br.readLine();
        rCmdTask.rCCS.disable();
        
        out.print("\n" + EntityType.CAMERA.toString() + " sequence cmd standby...");
        br.readLine();
        rCmdTask.rCCS.standby();
        
        out.print("\n" + EntityType.CAMERA.toString() + " sequence cmd exitControl...");
        br.readLine();
        rCmdTask.rCCS.exitControl();

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
}
    
//    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
//        
//        Thread.currentThread().setName(new String().concat("OCSMainThread"));
//        out.print(Thread.currentThread().getName());
//        out.println(" "+"id: "+Thread.currentThread().getId());
//        
//        launch(args);
//    }    
//   
//}
