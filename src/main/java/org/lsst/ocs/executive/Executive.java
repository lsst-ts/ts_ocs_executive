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
import static javafx.geometry.Pos.CENTER;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.lsst.ocs.executive.salcomponent.*;
import org.lsst.ocs.executive.salconnect.SalConnect;
import org.lsst.ocs.executive.salservice.SalCmd;

/**
 * Executive is the Context class implementation
 */

public class Executive {
    
    public static final CommandableSalComponent cscTCS  = new CSCTcs();
    public static final CommandableSalComponent cscCCS  = new CSCCcs();
    public static final CommandableSalComponent cscARC  = new CSCArchiver();
    public static final CommandableSalComponent cscCAT  = new CSCCatchupArchiver();
    public static final CommandableSalComponent cscPRO  = new CSCProcessingCluster();
    public static final CommandableSalComponent cscHDR  = new CSCHeaderService();
    
    public static final Entity entityTCS = new Entity( cscTCS );
    public static final Entity entityCCS = new Entity( cscCCS );
    public static final Entity entityARC = new Entity( cscARC );
    public static final Entity entityCAT = new Entity( cscCAT );
    public static final Entity entityPRO = new Entity( cscPRO );
    public static final Entity entityHDR = new Entity( cscHDR );
    
    public static final List<CmdTask> rCmdTasks_TCS = Arrays.asList(
            
        new CmdTask( cscTCS, "filterChange" ),
        new CmdTask( cscTCS, "target" )
    );
    
    public static final List<CmdTask> rCmdTasks_CCS = Arrays.asList(
            
        new CmdTask( cscCCS, "setFilter" ),
        new CmdTask( cscCCS, "takeImage" )
    );
    
    public static final List<CmdTask> rCmdTasks_ENTERCTRL = Arrays.asList(
            
        new CmdTask( cscTCS, "enterControl" ),
        new CmdTask( cscCCS, "enterControl" ),
        new CmdTask( cscARC, "enterControl" ),
        new CmdTask( cscCAT, "enterControl" ),
        new CmdTask( cscPRO, "enterControl" ),
        new CmdTask( cscHDR, "enterControl" )
    );
    
    public static final List<CmdTask> rCmdTasks_START = Arrays.asList(
            
        new CmdTask( cscTCS, "start" ),
        new CmdTask( cscCCS, "start" ),
        new CmdTask( cscARC, "start" ),
        new CmdTask( cscCAT, "start" ),
        new CmdTask( cscPRO, "start" ),
        new CmdTask( cscHDR, "start" )        
    );

    public static final List<CmdTask> rCmdTasks_ENABLE = Arrays.asList(
            
        new CmdTask( cscTCS, "enable" ),
        new CmdTask( cscCCS, "enable" ),
        new CmdTask( cscARC, "enable" ),
        new CmdTask( cscCAT, "enable" ),
        new CmdTask( cscPRO, "enable" ),
        new CmdTask( cscHDR, "enable" )                
    );
    
    public static final List<EventCallable> cEventTask_TCS = Arrays.asList(
            
        new EventCallable( cscTCS, "filterChangeInPosition" ),
        new EventCallable( cscTCS, "targetInPosition" )
    );

    public static final List<EventCallable> cEventTask_CCS = Arrays.asList(
            
        new EventCallable( cscCCS, "tbd" ),
        new EventCallable( cscCCS, "tbd" )
    );

    public static final List<EventCallable> cEventTask_SUMSTATE = Arrays.asList(
            
        new EventCallable( cscTCS, "summaryState" ),
        new EventCallable( cscCCS, "summaryState" ),
        new EventCallable( cscARC, "summaryState" ),
        new EventCallable( cscCAT, "summaryState" ),
        new EventCallable( cscPRO, "summaryState" ),
        new EventCallable( cscHDR, "summaryState" )
    );

    public static final List<EventCallable> cEventTask_SETTINGS = Arrays.asList(
            
        new EventCallable( cscTCS, "settingsVersion" ),
        new EventCallable( cscCCS, "settingsVersion" ),
        new EventCallable( cscARC, "settingsVersion" ),
        new EventCallable( cscCAT, "settingsVersion" ),
        new EventCallable( cscPRO, "settingsVersion" ),
        new EventCallable( cscHDR, "settingsVersion" )
);

    public static final List<EventCallable> cEventTask_APPLIEDSETTINGS = Arrays.asList(
            
        new EventCallable( cscTCS, "appliedSettingsMatchStartTest" ),
        new EventCallable( cscCCS, "appliedSettingsMatchStartTest" ),
        new EventCallable( cscARC, "appliedSettingsMatchStartTest" ),
        new EventCallable( cscCAT, "appliedSettingsMatchStartTest" ),
        new EventCallable( cscPRO, "appliedSettingsMatchStartTest" ),
        new EventCallable( cscHDR, "appliedSettingsMatchStartTest" )
    );

    public void invokeWindow( Stage primaryStage ) {

        Button btn = new Button();
        Text msg = new Text();

        btn.setText( "Say 'Hello World'" );

        btn.setOnAction( event -> {

            msg.setText( "Hello World! JavaFX style :)" );
        } );

        btn.setVisible( true );

        // root node of the scene is a vertical box
        // btn control added to 1st row in column; text msg control added to 2nd row in same column
        VBox root = new VBox( 10, btn, msg );
        root.setAlignment( CENTER );

        // Scene class is the container for all content
        Scene scene = new Scene( root, 300, 250 );

        primaryStage.setTitle( "Hello JavaFX 8 World!" );
        primaryStage.setScene( scene );
        primaryStage.show();

        out.print( this.getClass()
            .getSimpleName() + "::"
                   + Thread.currentThread().getStackTrace()[1].getMethodName()
                   + "::" );

        out.print( Thread.currentThread().getName() );
        out.println( " " + "id: " + Thread.currentThread().getId() );

    }

    //@Override
    // start() method is the main entry point for all JavaFX applications
    //public void start(Stage primaryStage) { // Stage class is the top-level JavaFX container
    //public void start( Stage primaryStage ) throws IOException, InterruptedException, ExecutionException {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        out.print( Thread.currentThread().getClass()
            .getSimpleName() + "::"
                   + Thread.currentThread().getStackTrace()[1].getMethodName()
                   + "::" );

        //out.print( Thread.currentThread().getName() );
        out.println( " " + "id: " + Thread.currentThread().getId() );

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
        
        // buffReader = new BufferedReader( new InputStreamReader( System.in ) );
        //out.println( "\n" + " Event Subscribers invoking..." );
        //buffReader.readLine();

        //out.print( "\n" + EntityType.TCS.toString() + " sequence cmd enterControl..." );
        //buffReader.readLine();

        // [C.]
        // 1. SalComponent (Receiver) previously defined: rCCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        SalCmd salCmdTCS = new SalCmd( Executive.cscTCS );
        salCmdTCS.setTopic( "enterControl" );
        // 3. Define Invoker & set SalService request
        SalConnect salConnectTCS = new SalConnect(1);
        salConnectTCS.setSalService( salCmdTCS );
        //salConnectCamera.setSalCmd(new SalCmd(rCCS), "enterControl");
        // 4. Invoker indirectly calls cmd->execute()
        salConnectTCS.connect();
        
        if ( false ) {

            //BufferedReader ss1 = new BufferedReader( new InputStreamReader( System.in ) );
            //out.println( "\n" + " Event Subscribers done..." );
            //ss1.readLine();

            /**
             * *******************************************************************
             */
            //ExecutorService ex = Executors.newWorkStealingPool();
            //ExecutorService ex = Executors.newFixedThreadPool(6);
            //List<Future<Entity>> enterCtrlFutures  = ex.invokeAll(CmdTask.ENTERCTRL_TASKS);
            /*
        ex.submit(CmdTask.rENTERCTRL_TASKS.get(0));
        ex.submit(CmdTask.rENTERCTRL_TASKS.get(1));
        ex.submit(CmdTask.rENTERCTRL_TASKS.get(2));
        ex.submit(CmdTask.rENTERCTRL_TASKS.get(3));
        ex.submit(CmdTask.rENTERCTRL_TASKS.get(4));
        ex.submit(CmdTask.rENTERCTRL_TASKS.get(5));
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
            /**
             * *********************************
             */
            /**
             * * OCS Sequencer STM Init    **
             */
            /**
             * *********************************
             */
            // 1. Wait for StartNight cmd from operator
            ObservingMode observingMode;

            // OCS Entity object created & initialized to OfflineState
            //Entity ocsEntity = new Entity(EntityType.OCS, observingMode);
            //Entity ocsEntity = new Entity(EntityType.OCS, ObservingMode.SCIENCE);
            //Entity seqEntity = new Entity(EntityType.SEQUENCER);
            // 2. Transition to 'StandbyState'
            //    Verify OCS SummaryState of 'StandbyState'
            //BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );

            /*
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Runnable taskSeq = () -> {
            try { 
                out.println("Executing enterControl thread task");
                //seqEntity.enterControl();
                CmdTask.SEQ.enterControl();
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
                CmdTask.SEQ.start();
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
                CmdTask.SEQ.enable();
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
            /**
             * ***********************
             */
            /**
             * * CCS STM Init    **
             */
            /**
             * ***********************
             */
            // CCS Entity object created
            //Entity ccsEntity = new Entity(EntityType.CCS);
            //Entity ccsEntity = new Entity(EntityType.Camera);
            // 1. Verify SummaryState of 'OfflineState[AvailableState]'
            // --- Add code ---
            // 2. cmd CCS to 'StandbyState'
            out.print( "\n" + EntityType.CCS.toString() + " sequence cmd enterControl..." );
            //br.readLine();

            // [A.]
            // The rENTERCTRL_TASKS list has defin ed rcvr(rCCS) & cmd(enterControl)
            // new SalCmd(rCCS, "enterControl")
            //CmdTask.ENTERCTRL_TASKS.get(0).call();
            //rCmdTask.rENTERCTRL_TASKS.get( 0 ).run();

            // [B.]
            // Receiver previously defined: rCCS = new CSCCcs();
            //rCmdTask.rCCS.enterControl();

            // [C.]
            // 1. SalComponent (Receiver) previously defined: rCCS
            // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
            //    Also, assign topic & topic arguments
            SalCmd salCmdCamera = new SalCmd( Executive.cscCCS );
            salCmdCamera.setTopic( "enterControl" );
            // 3. Define Invoker & set SalService request
            SalConnect salConnectCamera = new SalConnect(1);
            salConnectCamera.setSalService( salCmdCamera );
            //salConnectCamera.setSalCmd(new SalCmd(rCCS), "enterControl");
            // 4. Invoker indirectly calls cmd->execute()
            salConnectCamera.connect();

            // 2a. Verify SummaryState of 'StandbyState'
            // --- Add code ---
            // 2b. Determine a 'RecommendedSettingsVersion'
            // --- Add code ---
            // 3. cmd CCS to 'DisabledState'
            out.print( "\n" + EntityType.CCS.toString() + " sequence cmd start..." );
            //br.readLine();
            //Executive.cscCCS.start();

            salCmdCamera.setTopic( "start" );
            salConnectCamera.connect();

            // 3a. Verify SummaryState of 'DisabledState'
            // --- Add code ---
            // 3b. Verify 'SettingsApplied'
            // --- Add code ---
            // 3c. Verify 'AppliedSettingsMatchStart'
            // --- Add code ---
            // 4. cmd CCS to 'EnabledState'
            out.print( "\n" + EntityType.CCS.toString() + " sequence cmd enable..." );
            //br.readLine();
            //Executive.cscCCS.enable();

            salCmdCamera.setTopic( "start" );
            salConnectCamera.connect();

            // 4a. Verify SummaryState of 'EnabledState'
            // --- Add code ---
            /**
             * ***********************
             */
            /**
             * * TCS STM Init    **
             */
            /**
             * ***********************
             */
            out.print( "\n" + EntityType.TCS.toString() + " sequence cmd enterControl..." );
           // br.readLine();
            //Executive.cscTCS.enterControl();

            SalCmd salCmdTcs = new SalCmd( Executive.cscTCS );
            salCmdTcs.setTopic( "enterControl" );
            SalConnect salConnectTcs = new SalConnect(1);
            salConnectTcs.setSalService( salCmdTcs );
            salConnectTcs.connect();

            out.print( "\n" + EntityType.TCS.toString() + " sequence cmd start..." );
           // br.readLine();
            //Executive.cscTCS.start();

            salCmdTcs.setTopic( "start" );
            salConnectTcs.connect();

            out.print( "\n" + EntityType.TCS.toString() + " sequence cmd enable..." );
            //br.readLine();
            //Executive.cscTCS.enable();

            salCmdTcs.setTopic( "enable" );
            salConnectTcs.connect();

            /**
             * **************************
             */
            /**
             * * DM STM Init   **
             */
            /**
             * **************************
             */
            // 1. Verify SummaryState of 'OfflineState[AvailableState]'
            // --- Add code ---
            // 2. cmd CCS to 'StandbyState'
            out.print( "\n" + EntityType.ARCHIVER.toString() + " sequence cmd enterControl..." );
            //br.readLine();
            //Executive.cscARC.enterControl();

            // 2a. Verify SummaryState of 'StandbyState'
            // --- Add code ---
            // 2b. Determine a 'RecommendedSettingsVersion'
            // --- Add code ---
            // 3. cmd CCS to 'DisabledState'
            out.print( "\n" + EntityType.ARCHIVER.toString() + " sequence cmd start..." );
           // br.readLine();
            //Executive.cscARC.start();

            // 3a. Verify SummaryState of 'DisabledState'
            // --- Add code ---
            // 3b. Verify 'SettingsApplied'
            // --- Add code ---
            // 3c. Verify 'AppliedSettingsMatchStart'
            // --- Add code ---
            // 4. cmd CCS to 'EnabledState'
            out.print( "\n" + EntityType.ARCHIVER.toString() + " sequence cmd enable..." );
            //br.readLine();
            //Executive.cscARC.enable();

            // 4a. Verify SummaryState of 'EnabledState'
            // --- Add code ---
            out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd enterControl..." );
           // br.readLine();
            //Executive.cscCAT.enterControl();
            out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd start..." );
           // br.readLine();
            //Executive.cscCAT.start();
            out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd enable..." );
           // br.readLine();
            //Executive.cscCAT.enable();

            out.print( "\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd enterControl..." );
            //br.readLine();
            //Executive.cscPRO.enterControl();
            out.print( "\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd start..." );
            //br.readLine();
            //Executive.cscPRO.start();
            out.print( "\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd enable..." );
            //br.readLine();
            //Executive.cscPRO.enable();

            /**
             * ***********************
             */
            /**
             * * DM STM Shutdown  **
             */
            /**
             * ***********************
             */
            out.print( "\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd disable..." );
           // br.readLine();
            //Executive.cscPRO.disable();
            out.print( "\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd standby..." );
            //br.readLine();
            //Executive.cscPRO.standby();
            out.print( "\n" + EntityType.PROCESSINGCLUSTER.toString() + " sequence cmd exitControl..." );
           // br.readLine();
            //Executive.cscPRO.exitControl();

            out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd disable..." );
           // br.readLine();
            //Executive.cscCAT.disable();
            out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd standby..." );
           // br.readLine();
            //Executive.cscCAT.standby();
            out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd exitControl..." );
           // br.readLine();
            //Executive.cscCAT.exitControl();

            out.print( "\n" + EntityType.ARCHIVER.toString() + " sequence cmd disable..." );
           // br.readLine();
            //Executive.cscARC.disable();
            out.print( "\n" + EntityType.ARCHIVER.toString() + " sequence cmd standby..." );
           // br.readLine();
            //Executive.cscARC.standby();
            out.print( "\n" + EntityType.ARCHIVER.toString() + " sequence cmd exitControl..." );
            //br.readLine();
            //Executive.cscARC.exitControl();

            /**
             * ***********************
             */
            /**
             * * TCS STM Shutdown    **
             */
            /**
             * ***********************
             */
            out.print( "\n" + EntityType.TCS.toString() + " sequence cmd disable..." );
           // br.readLine();
            //Executive.cscTCS.disable();

            out.print( "\n" + EntityType.TCS.toString() + " sequence cmd standby..." );
           // br.readLine();
            //Executive.cscTCS.standby();

            out.print( "\n" + EntityType.TCS.toString() + " sequence cmd exitControl..." );
           // br.readLine();
            //Executive.cscTCS.exitControl();

            /**
             * ***********************
             */
            /**
             * * CCS STM Shutdown  **
             */
            /**
             * ***********************
             */
            out.print( "\n" + EntityType.CCS.toString() + " sequence cmd disable..." );
           // br.readLine();
            //Executive.cscCCS.disable();

            out.print( "\n" + EntityType.CCS.toString() + " sequence cmd standby..." );
           // br.readLine();
            //Executive.cscCCS.standby();

            out.print( "\n" + EntityType.CCS.toString() + " sequence cmd exitControl..." );
           // br.readLine();
            //Executive.cscCCS.exitControl();

            /**
             * ***********************
             */
            /**
             * * TCS STM Init    **
             */
            /**
             * ***********************
             */
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
            /**
             * ***********************
             */
            /**
             * * DMCS STM Init    **
             */
            /**
             * ***********************
             */
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
