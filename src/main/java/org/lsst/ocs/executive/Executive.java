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

import org.lsst.ocs.executive.salcomponent.*;

import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>Executive</h2>
 * <p>
 * The {@code Executive} is the application model class
 */
public class Executive {

//    public static final CommandableSalComponent _cscSCH  = new CSCScheduler();
//    public static final CommandableSalComponent _cscMTCS = new CSCMainTelescope();
//    public static final CommandableSalComponent _cscCCS  = new CSCCamera();
//    public static final CommandableSalComponent _cscARC  = new CSCArchiver();
//    public static final CommandableSalComponent _cscCAT  = new CSCCatchupArchiver();
//    public static final CommandableSalComponent _cscPRO  = new CSCPromptProcessing();
//    public static final CommandableSalComponent _cscHDR  = new CSCHeaderService();
//    public static final CommandableSalComponent _cscASCH = new CSCAuxScheduler();
  public static final CommandableSalComponent _cscATCS = new CSCAuxTelescope();
  public static final CommandableSalComponent _cscACCS = new CSCATCamera();
  public static final CommandableSalComponent _cscAARC = new CSCATArchiver();
  public static final CommandableSalComponent _cscAHDR = new CSCATHeaderService();

//    public static final Entity _entitySCH  = new Entity( _cscSCH  );
//    public static final Entity _entityMTCS = new Entity( _cscMTCS );
//    public static final Entity _entityCCS  = new Entity( _cscCCS  );
//    public static final Entity _entityARC  = new Entity( _cscARC  );
//    public static final Entity _entityCAT  = new Entity( _cscCAT  );
//    public static final Entity _entityPRO  = new Entity( _cscPRO  );
//    public static final Entity _entityHDR  = new Entity( _cscHDR  );
//    public static final Entity _entityASCH = new Entity( _cscASCH );
  public static final Entity _entityATCS = new Entity( _cscATCS );
  public static final Entity _entityACCS = new Entity( _cscACCS );
  public static final Entity _entityAARC = new Entity( _cscAARC );
  public static final Entity _entityAHDR = new Entity( _cscAHDR );

  /**
   * Returns a Map of CSCs.
   *
   * @return Map of pre-instantiated CSCs
   */
  @SuppressWarnings ("serial")
  public static Map<String, CommandableSalComponent> getCscMap() {

    return Collections.unmodifiableMap( new HashMap<String, CommandableSalComponent>() {

      {
        // (CSC key, CSC value)
//                put( EntityType.MTSCHEDULER.toSubString()       , _cscSCH  );
//                put( EntityType.MTCS.toSubString()            , _cscMTCS );
//                put( EntityType.MTCAMERA.toSubString()             , _cscCCS  );
//                put( EntityType.MTARCHIVER.toSubString()        , _cscARC  );
//                put( EntityType.CATCHUPARCHIVER.toSubString() , _cscCAT  );
//                put( EntityType.PROMPTPROCESSING.toSubString(), _cscPRO  );
//                put( EntityType.MTHEADERSERVICE.toSubString()   , _cscHDR  );
//                put( EntityType.ATSCHEDULER.toSubString()      , _cscASCH );
        put( EntityType.ATCS.toSubString(), _cscATCS );
        put( EntityType.ATCAMERA.toSubString(), _cscACCS );
        put( EntityType.ATARCHIVER.toSubString(), _cscAARC );
        put( EntityType.ATHEADERSERVICE.toSubString(), _cscAHDR );
      }
    } );
  }

  /**
   * Returns the list of CSCs.
   *
   * @return List of pre-instantiated CSCs
   */
  public static List<CommandableSalComponent> getCscList() {

    return Collections.unmodifiableList( Arrays.asList(
        //                _cscSCH,
        //                _cscMTCS,
        //                _cscCCS,
        //                _cscARC,
        //                _cscCAT,
        //                _cscPRO,
        //                _cscHDR,
        //                _cscHDR,
        //                _cscHDR,
        //                _cscASCH,
        _cscATCS,
        _cscACCS,
        _cscAARC,
        _cscAHDR
    )
    );
  }

  /**
   * Returns a Map of the Entity objects.
   *
   * @return Map of pre-instantiated Entity objects
   */
  @SuppressWarnings ("serial")
  public static Map<String, Entity> getEntityMap() {

    return Collections.unmodifiableMap( new HashMap<String, Entity>() {

      {
        // (Entity key, Entity value)
//                put( EntityType.MTSCHEDULER.toSubString()       , _entitySCH  );
//                put( EntityType.MTCS.toSubString()            , _entityMTCS );
//                put( EntityType.MTCAMERA.toSubString()             , _entityCCS  );
//                put( EntityType.MTARCHIVER.toSubString()        , _entityARC  );
//                put( EntityType.CATCHUPARCHIVER.toSubString() , _entityCAT  );
//                put( EntityType.PROMPTPROCESSING.toSubString(), _entityPRO  );
//                put( EntityType.MTHEADERSERVICE.toSubString()   , _entityHDR  );
//                put( EntityType.ATSCHEDULER.toSubString()      , _entityASCH );
        put( EntityType.ATCS.toSubString(), _entityATCS );
        put( EntityType.ATCAMERA.toSubString(), _entityACCS );
        put( EntityType.ATARCHIVER.toSubString(), _entityAARC );
        put( EntityType.ATHEADERSERVICE.toSubString(), _entityAHDR );
      }
    } );
  }

  /**
   * Returns a List of Entity objects.
   *
   * @return List of pre-instantiated Entity objects
   */
  public static List<Entity> getEntityList() {

    return Collections.unmodifiableList( Arrays.asList(
        //                _entitySCH,
        //                _entityMTCS,
        //                _entityCCS,
        //                _entityARC,
        //                _entityCAT,
        //                _entityPRO,
        //                _entityHDR,
        //                _entityASCH,
        _entityATCS,
        _entityACCS,
        _entityAARC,
        _entityAHDR
    )
    );
  }

  public static final List<CmdTask> rCmdTasks_ENTERCTRL = Collections.unmodifiableList( Arrays.asList(
      //        new CmdTask( _entitySCH,  "enterControl" ),
      //        new CmdTask( _entityMTCS, "enterControl" ),
      //        new CmdTask( _entityCCS,  "enterControl" ),
      //        new CmdTask( _entityARC,  "enterControl" ),
      //        new CmdTask( _entityCAT,  "enterControl" ),
      //        new CmdTask( _entityPRO,  "enterControl" ),
      //        new CmdTask( _entityHDR,  "enterControl" ),
      //        new CmdTask( _entityASCH, "enterControl" ),
      new CmdTask( _entityATCS, "enterControl" ),
      new CmdTask( _entityACCS, "enterControl" ),
      new CmdTask( _entityAARC, "enterControl" ),
      new CmdTask( _entityAHDR, "enterControl" )
  ) );

  public static final List<CmdTask> rCmdTasks_START = Collections.unmodifiableList( Arrays.asList(
      //        new CmdTask( _entitySCH,  "start" ),
      //        new CmdTask( _entityMTCS, "start" ),
      //        new CmdTask( _entityCCS,  "start" ),
      //        new CmdTask( _entityARC,  "start" ),
      //        new CmdTask( _entityCAT,  "start" ),
      //        new CmdTask( _entityPRO,  "start" ),
      //        new CmdTask( _entityHDR,  "start" ),
      //        new CmdTask( _entityASCH, "start" ),
      new CmdTask( _entityATCS, "start" ),
      new CmdTask( _entityACCS, "start" ),
      new CmdTask( _entityAARC, "start" ),
      new CmdTask( _entityAHDR, "start" )
  ) );

  public static final List<CmdTask> rCmdTasks_ENABLE = Collections.unmodifiableList( Arrays.asList(
      //        new CmdTask( _entitySCH, "enable" ),
      //        new CmdTask( _entityMTCS, "enable" ),
      //        new CmdTask( _entityCCS,  "enable" ),
      //        new CmdTask( _entityARC,  "enable" ),
      //        new CmdTask( _entityCAT,  "enable" ),
      //        new CmdTask( _entityPRO,  "enable" ),
      //        new CmdTask( _entityHDR,  "enable" ),
      //        new CmdTask( _entityASCH, "enable" ),
      new CmdTask( _entityATCS, "enable" ),
      new CmdTask( _entityACCS, "enable" ),
      new CmdTask( _entityAARC, "enable" ),
      new CmdTask( _entityAHDR, "enable" )
  ) );

  public static final List<CmdTask> rCmdTasks_DISABLE = Collections.unmodifiableList( Arrays.asList(
      //        new CmdTask( _entitySCH, "disable" ),
      //        new CmdTask( _entityMTCS, "disable" ),
      //        new CmdTask( _entityCCS,  "disable" ),
      //        new CmdTask( _entityARC,  "disable" ),
      //        new CmdTask( _entityCAT,  "disable" ),
      //        new CmdTask( _entityPRO,  "disable" ),
      //        new CmdTask( _entityHDR,  "disable" ),
      //        new CmdTask( _entityASCH, "disable" ),
      new CmdTask( _entityATCS, "disable" ),
      new CmdTask( _entityACCS, "disable" ),
      new CmdTask( _entityAARC, "disable" ),
      new CmdTask( _entityAHDR, "disable" )
  ) );

  public static final List<CmdTask> rCmdTasks_STANDBY = Collections.unmodifiableList( Arrays.asList(
      //        new CmdTask( _entitySCH,  "standby" ),
      //        new CmdTask( _entityMTCS, "standby" ),
      //        new CmdTask( _entityCCS,  "standby" ),
      //        new CmdTask( _entityARC,  "standby" ),
      //        new CmdTask( _entityCAT,  "standby" ),
      //        new CmdTask( _entityPRO,  "standby" ),
      //        new CmdTask( _entityHDR,  "standby" ),
      //        new CmdTask( _entityASCH, "standby" ),
      new CmdTask( _entityATCS, "standby" ),
      new CmdTask( _entityACCS, "standby" ),
      new CmdTask( _entityAARC, "standby" ),
      new CmdTask( _entityAHDR, "standby" )
  ) );

  public static final List<CmdTask> rCmdTasks_EXITCTRL = Collections.unmodifiableList( Arrays.asList(
      //        new CmdTask( _entitySCH,  "exitControl" ),
      //        new CmdTask( _entityMTCS, "exitControl" ),
      //        new CmdTask( _entityCCS,  "exitControl" ),
      //        new CmdTask( _entityARC,  "exitControl" ),
      //        new CmdTask( _entityCAT,  "exitControl" ),
      //        new CmdTask( _entityPRO,  "exitControl" ),
      //        new CmdTask( _entityHDR,  "exitControl" ),
      //        new CmdTask( _entityASCH, "exitControl" ),
      new CmdTask( _entityATCS, "exitControl" ),
      new CmdTask( _entityACCS, "exitControl" ),
      new CmdTask( _entityAARC, "exitControl" ),
      new CmdTask( _entityAHDR, "exitControl" )
  ) );

//    public static final List<CmdTask> rCmdTasks_MTCS = Collections.unmodifiableList( Arrays.asList (
//        new CmdTask( _cscMTCS, "filterChange" ),
//        new CmdTask( _cscMTCS, "target"       )
//    ));
//
//    public static final List<CmdTask> rCmdTasks_CCS = Collections.unmodifiableList( Arrays.asList (
//        new CmdTask( _cscCCS, "setFilter" ),
//        new CmdTask( _cscCCS, "takeImage" )
//    ));
//
//    public static final List<CmdTask> rCmdTasks_ATCS = Collections.unmodifiableList( Arrays.asList (
//        new CmdTask( _cscATCS, "filterChange" ),
//        new CmdTask( _cscATCS, "target"       )
//    ));
  public static final List<CmdTask> rCmdTasks_ACCS = Collections.unmodifiableList( Arrays.asList(
      new CmdTask( _cscACCS, "setFilter" ),
      new CmdTask( _cscACCS, "takeImage" )
  ) );

//    public static final List<EventCallable> cEventTask_MTCS = Collections.unmodifiableList( Arrays.asList (
//        new EventCallable( _cscMTCS, "filterChangeInPosition" ),
//        new EventCallable( _cscMTCS, "targetInPosition"       )
//    ));
//    
//    public static final List<EventCallable> cEventTask_CCS = Collections.unmodifiableList( Arrays.asList (
//        new EventCallable( _cscCCS, "tbd" ),
//        new EventCallable( _cscCCS, "tbd" )
//    ));
  public static final List<EventCallable> cEventTask_ATCS = Collections.unmodifiableList( Arrays.asList(
      new EventCallable( _cscATCS, "filterChangeInPosition" ),
      new EventCallable( _cscATCS, "targetInPosition" )
  ) );

  public static final List<EventCallable> cEventTask_ACCS = Collections.unmodifiableList( Arrays.asList(
      new EventCallable( _cscACCS, "tbd" ),
      new EventCallable( _cscACCS, "tbd" )
  ) );

  //public static final List<EventCallable> cEventTask_SUMSTATE = Collections.unmodifiableList( Arrays.asList (
  //    new EventCallable( _cscSCH,  "summaryState" )
  public static final List<EventTask> cEventTask_SUMSTATE = Collections.unmodifiableList( Arrays.asList(
      //        new EventTask( _cscSCH,  "summaryState" ),
      //        new EventTask( _cscMTCS, "summaryState" ),
      //        new EventTask( _cscCCS,  "summaryState" ),
      //        new EventTask( _cscARC,  "summaryState" ),
      //        new EventTask( _cscCAT,  "summaryState" ),
      //        new EventTask( _cscPRO,  "summaryState" ),
      //        new EventTask( _cscHDR,  "summaryState" ),
      //        new EventTask( _cscASCH, "summaryState" ),
      new EventTask( _cscATCS, "summaryState" ),
      new EventTask( _cscACCS, "summaryState" ),
      new EventTask( _cscAARC, "summaryState" ),
      new EventTask( _cscAHDR, "summaryState" )
  ) );

  public static final List<EventTask> cEventTask_SETTINGS = Collections.unmodifiableList( Arrays.asList(
      //        new EventTask( _cscSCH,  "settingsVersion" ),
      //        new EventTask( _cscMTCS, "settingsVersion" ),
      //        new EventTask( _cscCCS,  "settingsVersion" ),
      //        new EventTask( _cscARC,  "settingsVersion" ),
      //        new EventTask( _cscCAT,  "settingsVersion" ),
      //        new EventTask( _cscPRO,  "settingsVersion" ),
      //        new EventTask( _cscHDR,  "settingsVersion" ),
      //        new EventTask( _cscASCH, "settingsVersion" ),
      new EventTask( _cscATCS, "settingsVersion" ),
      new EventTask( _cscACCS, "settingsVersion" ),
      new EventTask( _cscAARC, "settingsVersion" ),
      new EventTask( _cscAHDR, "settingsVersion" )
  ) );

  public static final List<EventTask> cEventTask_APPLIEDSETTINGS = Collections.unmodifiableList( Arrays.asList(
      //        new EventTask( _cscSCH,  "appliedSettingsMatchStartTest" ),
      //        new EventTask( _cscMTCS, "appliedSettingsMatchStartTest" ),
      //        new EventTask( _cscCCS,  "appliedSettingsMatchStartTest" ),
      //        new EventTask( _cscARC,  "appliedSettingsMatchStartTest" ),
      //        new EventTask( _cscCAT,  "appliedSettingsMatchStartTest" ),
      //        new EventTask( _cscPRO,  "appliedSettingsMatchStartTest" ),
      //        new EventTask( _cscHDR,  "appliedSettingsMatchStartTest" ),
      //        new EventTask( _cscASCH, "appliedSettingsMatchStartTest" ),
      new EventTask( _cscATCS, "appliedSettingsMatchStartTest" ),
      new EventTask( _cscACCS, "appliedSettingsMatchStartTest" ),
      new EventTask( _cscAARC, "appliedSettingsMatchStartTest" ),
      new EventTask( _cscAHDR, "appliedSettingsMatchStartTest" )
  ) );

  //@Override
  // start() method is the main entry point for all JavaFX applications
  //public void start(Stage primaryStage) { // Stage class is the top-level JavaFX container
  //public void start( Stage primaryStage ) throws IOException, InterruptedException, ExecutionException {
  public static void main( String[] args ) throws IOException, InterruptedException, ExecutionException {

    out.print( Thread.currentThread()
        .getClass()
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
     * sumStateFutures.parallelStream() .map((Future<SalEvent> future) -> {
     * try { return future.get(); } catch (Exception e) { throw new
     * IllegalStateException(e); } }) .collect(Collectors.toList())
     * .forEach(event -> event.getName());
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
    //SalCmd salCmdTCS = new SalCmd( Executive._cscMTCS );
    //salCmdTCS.setTopic( "enterControl" );
    // 3. Define Invoker & set SalService request
    //SalConnect salConnectTCS = new SalConnect( 1 );
    //salConnectTCS.setSalService( salCmdTCS );
    //salConnectCamera.setSalCmd(new SalCmd(rCCS), "enterControl");
    // 4. Invoker indirectly calls cmd->execute()
    //salConnectTCS.connect();
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
       * ex.submit(CmdTask.rENTERCTRL_TASKS.get(0));
       * ex.submit(CmdTask.rENTERCTRL_TASKS.get(1));
       * ex.submit(CmdTask.rENTERCTRL_TASKS.get(2));
       * ex.submit(CmdTask.rENTERCTRL_TASKS.get(3));
       * ex.submit(CmdTask.rENTERCTRL_TASKS.get(4));
       * ex.submit(CmdTask.rENTERCTRL_TASKS.get(5));
       */
 /*
       * BufferedReader ss2 = new BufferedReader(new
       * InputStreamReader(System.in)); out.println("\n" + " EnterControl
       * tasks invoking..."); ss2.readLine();
       */

 /*
       * enterCtrlFutures.parallelStream() .map((Future<Entity> future) ->
       * { try { return future.get(); } catch (Exception e) { throw new
       * IllegalStateException(e); } }) .collect(Collectors.toList())
       * .parallelStream() .map(entity -> ex.submit(new CmdTask(entity,
       * "start"))); //.forEach(entity -> ex.submit(new CmdTask(entity,
       * "start"))); //.forEach(out::println);
       */
      /**
       * *********************************
       */
      /**
       * * OCS Sequencer STM Init **
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
       * ExecutorService executor = Executors.newSingleThreadExecutor();
       *
       * Runnable taskSeq = () -> { try { out.println("Executing
       * enterControl thread task"); //seqEntity.enterControl();
       * CmdTask.SEQ.enterControl(); } catch (Exception e) {
       * e.printStackTrace(out.printf("taskSeq enterControl
       * interrupted")); } }; // submit method returns a Future so curry
       * to Future's get() method // Block until task finishes
       * executor.submit(taskSeq); //.get();
       *
       * // 3. Transition to 'DisabledState' // Verify settings; verify
       * OCS SummaryState of 'DisabledState' out.print("\n" +
       * EntityType.SEQUENCER.toString()+ " start..."); br.readLine();
       *
       * Runnable taskSeq1 = () -> { try { out.println("Executing start
       * thread task"); //seqEntity.start(); CmdTask.SEQ.start(); } catch
       * (Exception e) { e.printStackTrace(out.printf("taskSeq enable
       * interrupted")); } }; // Block until task finishes
       * executor.submit(taskSeq1); //.get();
       *
       * // 4. Transition to 'EnabledState' // Verify settings; verify OCS
       * SummaryState of out.print("\n" + EntityType.SEQUENCER.toString()+
       * " enable..."); br.readLine();
       *
       * Runnable taskSeq2 = () -> { try { out.println("Executing enable
       * thread task"); //seqEntity.enable(); CmdTask.SEQ.enable(); }
       * catch (Exception e) { e.printStackTrace(out.printf("taskSeq
       * enable interrupted")); } }; // Block until task finishes
       * executor.submit(taskSeq2); //.get();
       *
       * // Shutdown single thread executor try { out.println("shutting
       * down executor..."); executor.shutdown();
       * executor.awaitTermination(1, TimeUnit.SECONDS); } catch
       * (InterruptedException e) { out.println("task interrupted"); }
       * finally { if(!executor.isTerminated()) { err.println("cancel
       * non-finished tasks"); } executor.shutdownNow();
       * out.println("executor shutdown finished"); }
       */
      /**
       * ***********************
       */
      /**
       * * CCS STM Init **
       */
      /**
       * ***********************
       */
      // MTCAMERA Entity object created
      //Entity ccsEntity = new Entity(EntityType.MTCAMERA);
      //Entity ccsEntity = new Entity(EntityType.Camera);
      // 1. Verify SummaryState of 'OfflineState[AvailableState]'
      // --- Add code ---
      // 2. cmd MTCAMERA to 'StandbyState'
      out.print( "\n" + EntityType.MTCAMERA.toString() + " sequence cmd enterControl..." );
      //br.readLine();

      // [A.]
      // The rENTERCTRL_TASKS list has defin ed rcvr(rCCS) & cmd(enterControl)
      // new SalCmd(rCCS, "enterControl")
      //CmdTask.ENTERCTRL_TASKS.get(0).call();
      //rCmdTask.rENTERCTRL_TASKS.get( 0 ).run();
      // [B.]
      // Receiver previously defined: rCCS = new CSCCamera();
      //rCmdTask.rCCS.enterControl();
      // [C.]
      // 1. SalComponent (Receiver) previously defined: rCCS
      // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
      //    Also, assign topic & topic arguments
      //SalCmd salCmdCamera = new SalCmd( Executive._cscCCS );
      //salCmdCamera.setTopic( "enterControl" );
      // 3. Define Invoker & set SalService request
      //SalConnect salConnectCamera = new SalConnect( 1 );
      //salConnectCamera.setSalService( salCmdCamera );
      //salConnectCamera.setSalCmd(new SalCmd(rCCS), "enterControl");
      // 4. Invoker indirectly calls cmd->execute()
      //salConnectCamera.connect();
      // 2a. Verify SummaryState of 'StandbyState'
      // --- Add code ---
      // 2b. Determine a 'RecommendedSettingsVersion'
      // --- Add code ---
      // 3. cmd MTCAMERA to 'DisabledState'
      out.print( "\n" + EntityType.MTCAMERA.toString() + " sequence cmd start..." );
      //br.readLine();
      //Executive._cscCCS.start();

      //salCmdCamera.setTopic( "start" );
      //salConnectCamera.connect();
      // 3a. Verify SummaryState of 'DisabledState'
      // --- Add code ---
      // 3b. Verify 'SettingsApplied'
      // --- Add code ---
      // 3c. Verify 'AppliedSettingsMatchStart'
      // --- Add code ---
      // 4. cmd MTCAMERA to 'EnabledState'
      out.print( "\n" + EntityType.MTCAMERA.toString() + " sequence cmd enable..." );
      //br.readLine();
      //Executive._cscCCS.enable();

      //salCmdCamera.setTopic( "start" );
      //salConnectCamera.connect();
      // 4a. Verify SummaryState of 'EnabledState'
      // --- Add code ---
      /**
       * ***********************
       */
      /**
       * * TCS STM Init **
       */
      /**
       * ***********************
       */
      out.print( "\n" + EntityType.MTCS.toString() + " sequence cmd enterControl..." );
      // br.readLine();
      //Executive._cscMTCS.enterControl();

      //SalCmd salCmdTcs = new SalCmd( Executive._cscMTCS );
      //salCmdTcs.setTopic( "enterControl" );
      //SalConnect salConnectTcs = new SalConnect( 1 );
      //salConnectTcs.setSalService( salCmdTcs );
      //salConnectTcs.connect();
      out.print( "\n" + EntityType.MTCS.toString() + " sequence cmd start..." );
      // br.readLine();
      //Executive._cscMTCS.start();

      //salCmdTcs.setTopic( "start" );
      //salConnectTcs.connect();
      out.print( "\n" + EntityType.MTCS.toString() + " sequence cmd enable..." );
      //br.readLine();
      //Executive._cscMTCS.enable();

      //salCmdTcs.setTopic( "enable" );
      //salConnectTcs.connect();
      /**
       * **************************
       */
      /**
       * * DM STM Init **
       */
      /**
       * **************************
       */
      // 1. Verify SummaryState of 'OfflineState[AvailableState]'
      // --- Add code ---
      // 2. cmd MTCAMERA to 'StandbyState'
      out.print( "\n" + EntityType.MTARCHIVER.toString() + " sequence cmd enterControl..." );
      //br.readLine();
      //Executive._cscARC.enterControl();

      // 2a. Verify SummaryState of 'StandbyState'
      // --- Add code ---
      // 2b. Determine a 'RecommendedSettingsVersion'
      // --- Add code ---
      // 3. cmd MTCAMERA to 'DisabledState'
      out.print( "\n" + EntityType.MTARCHIVER.toString() + " sequence cmd start..." );
      // br.readLine();
      //Executive._cscARC.start();

      // 3a. Verify SummaryState of 'DisabledState'
      // --- Add code ---
      // 3b. Verify 'SettingsApplied'
      // --- Add code ---
      // 3c. Verify 'AppliedSettingsMatchStart'
      // --- Add code ---
      // 4. cmd MTCAMERA to 'EnabledState'
      out.print( "\n" + EntityType.MTARCHIVER.toString() + " sequence cmd enable..." );
      //br.readLine();
      //Executive._cscARC.enable();

      // 4a. Verify SummaryState of 'EnabledState'
      // --- Add code ---
      out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd enterControl..." );
      // br.readLine();
      //Executive._cscCAT.enterControl();
      out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd start..." );
      // br.readLine();
      //Executive._cscCAT.start();
      out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd enable..." );
      // br.readLine();
      //Executive._cscCAT.enable();

      out.print( "\n" + EntityType.PROMPTPROCESSING.toString() + " sequence cmd enterControl..." );
      //br.readLine();
      //Executive._cscPRO.enterControl();
      out.print( "\n" + EntityType.PROMPTPROCESSING.toString() + " sequence cmd start..." );
      //br.readLine();
      //Executive._cscPRO.start();
      out.print( "\n" + EntityType.PROMPTPROCESSING.toString() + " sequence cmd enable..." );
      //br.readLine();
      //Executive._cscPRO.enable();

      /**
       * ***********************
       */
      /**
       * * DM STM Shutdown **
       */
      /**
       * ***********************
       */
      out.print( "\n" + EntityType.PROMPTPROCESSING.toString() + " sequence cmd disable..." );
      // br.readLine();
      //Executive._cscPRO.disable();
      out.print( "\n" + EntityType.PROMPTPROCESSING.toString() + " sequence cmd standby..." );
      //br.readLine();
      //Executive._cscPRO.standby();
      out.print( "\n" + EntityType.PROMPTPROCESSING.toString() + " sequence cmd exitControl..." );
      // br.readLine();
      //Executive._cscPRO.exitControl();

      out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd disable..." );
      // br.readLine();
      //Executive._cscCAT.disable();
      out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd standby..." );
      // br.readLine();
      //Executive._cscCAT.standby();
      out.print( "\n" + EntityType.CATCHUPARCHIVER.toString() + " sequence cmd exitControl..." );
      // br.readLine();
      //Executive._cscCAT.exitControl();

      out.print( "\n" + EntityType.MTARCHIVER.toString() + " sequence cmd disable..." );
      // br.readLine();
      //Executive._cscARC.disable();
      out.print( "\n" + EntityType.MTARCHIVER.toString() + " sequence cmd standby..." );
      // br.readLine();
      //Executive._cscARC.standby();
      out.print( "\n" + EntityType.MTARCHIVER.toString() + " sequence cmd exitControl..." );
      //br.readLine();
      //Executive._cscARC.exitControl();

      /**
       * ***********************
       */
      /**
       * * TCS STM Shutdown **
       */
      /**
       * ***********************
       */
      out.print( "\n" + EntityType.MTCS.toString() + " sequence cmd disable..." );
      // br.readLine();
      //Executive._cscMTCS.disable();

      out.print( "\n" + EntityType.MTCS.toString() + " sequence cmd standby..." );
      // br.readLine();
      //Executive._cscMTCS.standby();

      out.print( "\n" + EntityType.MTCS.toString() + " sequence cmd exitControl..." );
      // br.readLine();
      //Executive._cscMTCS.exitControl();

      /**
       * ***********************
       */
      /**
       * * CCS STM Shutdown **
       */
      /**
       * ***********************
       */
      out.print( "\n" + EntityType.MTCAMERA.toString() + " sequence cmd disable..." );
      // br.readLine();
      //Executive._cscCCS.disable();

      out.print( "\n" + EntityType.MTCAMERA.toString() + " sequence cmd standby..." );
      // br.readLine();
      //Executive._cscCCS.standby();

      out.print( "\n" + EntityType.MTCAMERA.toString() + " sequence cmd exitControl..." );
      // br.readLine();
      //Executive._cscCCS.exitControl();

      /**
       * ***********************
       */
      /**
       * * TCS STM Init **
       */
      /**
       * ***********************
       */
      /*
       * // TCS Entity object created Entity tcsEntity = new
       * Entity(EntityType.TCS);
       *
       * // 1. Verify SummaryState of 'OfflineState[AvailableState]' //
       * --- Add code ---
       *
       * // 2. cmd tcs to 'StandbyState' // if (
       * tcsEntity.getName().equals( tcsBoundary.SummaryState() )
       * tcsEntity.enterControl();
       *
       * // 2a. Verify SummaryState of 'StandbyState' // --- Add code ---
       *
       * // 2b. Determine a 'RecommendedSettingsVersion' // --- Add code
       * ---
       *
       * // 3. cmd tcs to 'DisabledState' tcsEntity.start();
       *
       * // 3a. Verify SummaryState of 'DisabledState' // --- Add code ---
       *
       * // 3b. Verify 'SettingsApplied' // --- Add code ---
       *
       * // 3c. Verify 'AppliedSettingsMatchStart' // --- Add code ---
       *
       * // 4. cmd tcs to 'EnabledState' tcsEntity.enable();
       *
       * // 4a. Verify SummaryState of 'EnabledState' // --- Add code ---
       */
      /**
       * ***********************
       */
      /**
       * * DMCS STM Init **
       */
      /**
       * ***********************
       */
      /*
       * // DMCS Entity object created Entity dmcsEntity = new
       * Entity(EntityType.DMCS);
       *
       * // 1. Verify SummaryState of 'OfflineState[AvailableState]' //
       * --- Add code ---
       *
       * // 2. cmd DMCS to 'StandbyState' dmcsEntity.enterControl();
       *
       * // 2a. Verify SummaryState of 'StandbyState' // --- Add code ---
       *
       * // 2b. Determine a 'RecommendedSettingsVersion' // --- Add code
       * ---
       *
       * // 3. cmd DMCS to 'DisabledState' dmcsEntity.start();
       *
       * // 3a. Verify SummaryState of 'DisabledState' // --- Add code ---
       *
       * // 3b. Verify 'SettingsApplied' // --- Add code ---
       *
       * // 3c. Verify 'AppliedSettingsMatchStart' // --- Add code ---
       *
       * // 4. cmd DMCS to 'EnabledState' dmcsEntity.enable();
       *
       * // 4a. Verify SummaryState of 'EnabledState' // --- Add code ---
       */
    }

  }
}
