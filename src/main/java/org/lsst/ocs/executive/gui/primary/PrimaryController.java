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

package org.lsst.ocs.executive.gui.primary;

import static java.lang.System.out;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import javafx.concurrent.Service;
import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Glow;
import org.lsst.ocs.executive.Entity;
import org.lsst.ocs.executive.Executive;
import org.lsst.ocs.executive.ExecutiveFX;
import org.lsst.ocs.executive.rCmdTask;
import org.lsst.ocs.executive.cEventTask;
import org.lsst.ocs.executive.cEventTask2;
import org.lsst.ocs.executive.cEventTask3;
import org.lsst.ocs.executive.salcomponent.CommandableSalComponent;
import org.lsst.ocs.executive.salconnect.SalConnect;
import org.lsst.ocs.executive.salservice.SalCmd;
import javafx.collections.ObservableList;

/**
 * <h2>FXML Primary Controller</h2>
 * <p>
 * The controller class for <i>primaryFXML.fxml</i> document (via 
 * {@code fx:controller} attribute).
 * <p>
 * The {@code @FXML} annotation can be used on fields and methods. It CANNOT be
 * used on classes and constructors.
 * <p>
 * By using a {@code @FXML} annotation on a member, you are declaring that the
 * FXML loader can access the member even if it is private. A public member used
 * by the FXML loader does not need to be annotated with {@code @FXML}. However,
 * annotating a public member with {@code @FXML} is not an error.
 */
public class PrimaryController implements Initializable {

    // Reference to ExecutiveFX, the main Application class
    private ExecutiveFX execFX;
    
    @FXML
    private MenuButton tcsStateMenu, ccsStateMenu, arcStateMenu, catStateMenu, proStateMenu, hdrStateMenu;

    @FXML
    private Label tcsLabel, ccsLabel, arcLabel, catLabel, proLabel, hdrLabel;
    private ObservableList<Label> stateLabelList;

    @FXML
    private Tooltip tcsTooltip, ccsTooltip, arcTooltip, catTooltip, proTooltip, hdrTooltip;
    private ObservableList<Tooltip> stateTooltipList;
    
    @FXML
    private TextField tcsStateText, ccsStateText, arcStateText, catStateText, proStateText, hdrStateText;
    private ObservableList<TextField> stateTextList;
    private Map<String, String> STATE_TEXT_MAP;

    @FXML
    private MenuItem enter, start, enable, disable, standby, exit;

    @FXML
    private MenuItem ccsEnter, ccsStart, ccsEnable, ccsDisable, ccsStandby, ccsExit;
    
    @FXML
    private MenuItem arcEnter, arcStart, arcEnable, arcDisable, arcStandby, arcExit;
    
    @FXML
    private MenuItem catEnter, catStart, catEnable, catDisable, catStandby, catExit;
    
    @FXML
    private MenuItem proEnter, proStart, proEnable, proDisable, proStandby, proExit;
    
    @FXML
    private MenuItem hdrEnter, hdrStart, hdrEnable, hdrDisable, hdrStandby, hdrExit;
    
    @FXML
    private MenuButton tcsCmdMenu, ccsCmdMenu;

    @FXML
    private TextField tcsCmdText, ccsCmdText;

    @FXML
    private Menu menuCSC;

    @FXML
    private MenuItem menuitemCreateTCS;
    @FXML
    private MenuItem menuitemCreateCamera;
    @FXML
    private MenuItem menuitemCreateArchiver;
    @FXML
    private MenuItem menuitemCreateCatchupArchiver;
    @FXML
    private MenuItem menuitemCreateProcessingCluster;
    @FXML
    private MenuItem menuitemCreateAll;

    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL locationUrl, ResourceBundle resourceBundle) {
        
        stateLabelList = FXCollections.observableArrayList( 
            tcsLabel, ccsLabel, arcLabel, catLabel, proLabel
        );

        stateTooltipList = FXCollections.observableArrayList( 
            tcsTooltip, ccsTooltip, arcTooltip, catTooltip, proTooltip
        );

        stateTextList = FXCollections.observableArrayList( 
            tcsStateText, ccsStateText, arcStateText, catStateText, proStateText
        );

        STATE_TEXT_MAP = new HashMap<String, String>() {
            {
                put("enterControl", "STANDBY");
                put("start"       , "DISABLED");
                put("enable"      , "ENABLED");
                put("disable"     , "DISABLED");
                put("standby"     , "STANDBY");
                put("exitControl" , "OFFLINE");
            }
        };
    }
    
    /**
     * Is called by the main FX application to give a reference back to itself.
     * 
     * @param refExecFX class which holds {@code main()}
     */
    public void setExecFXApp(ExecutiveFX refExecFX) {

        this.execFX = refExecFX;
    }

    @FXML
    private void tcsState(ActionEvent event) throws Exception {

        // Grab the index of the selected menu item cmd
        int cmdIndex = tcsStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = tcsStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 0 /* cscTCS */ );

        // State Pattern: context.request() [e.g. entityTcs.enterControl()]
        //rCmdTask task = new rCmdTask( entity, cmdString );
        //task.call();
        (new rCmdTask( entity, cmdString )).call();
        
        checkStatus( entity, cmdString );
    }
    
//    void checkStatus( Entity entity, String cmdString) throws InterruptedException, ExecutionException {
//        
//        Service service = new Service() {
//
//            @Override protected Task createTask () {
//                
//                return new Task<Void>() {
//                    
//                    @Override protected Void call() throws Exception {
//                        
//                        out.print( "ServiceTaskcall:"+ Thread.currentThread().getId());
//                        
//                        Future<Integer> futureSumState = 
//                                ( new cEventTask2( entity.getCSC(), "summaryState" ) ).call();
//
//                        Integer result = CommandableSalComponent.CSC_STATUS.SAL__OK.getValue();
//                        result = futureSumState.get();
//
//                        if ( result.equals( CommandableSalComponent.CSC_STATUS.SAL__OK.getValue() ) ) {
//
//                            tcsStateText.setText( STATE_TEXT_MAP.get( cmdString ) );
//                            tcsStateText.setStyle( "-fx-text-fill: darkcyan;" +
//                                                   "-fx-font-weight: bold;" +   
//                                                   "-fx-font-size: 11;" );
//
//                            if ( cmdString.matches("enterControl") ) {
//
//                                tcsLabel.setStyle( "-fx-text-fill: green;"  + 
//                                                   "-fx-font-size: 13;"     +
//                                                   "-fx-font-weight: bold;" +
//                                                   "-fx-border-width: 0 1 0 0;"  );
//                                tcsLabel.setEffect(new Glow(0.9));
//                                tcsTooltip.setText("TCS Online");
//                            }
//
//                            if ( cmdString.matches("exitControl") ) {
//
//                                tcsLabel.setStyle( "-fx-text-fill: gainsboro;" +
//                                                   "-fx-font-size: 13;"        +
//                                                   "-fx-font-weight: normal;" );
//                                tcsLabel.setEffect(new Glow());
//                                tcsTooltip.setText("TCS Offline");
//                            }
//                        }
//                        
//                        return null;
//                    } // end call()
//                }; // end Task()
//            } // end createTask()
//        }; // end new Service()
//        
//       service.start();
//    }
    
    /**
     * The {@code checkStatus()} method subscribes to the SummaryState topic
     * of a specific CSC on a background JavaFX thread.
     * 
     * A {@code Service} class creates & manages a Task that performs the work 
     * on a background (daemon) thread. {@code Service} implements {@code Worker}.
     * 
     * <p>Similar to doing: Thread th = new Thread(new Runnable task)
     * <p>Similar to doing: Executors.newWorkStealingPool().execute(new Runnable task);
     *
     * @see <li>https://docs.oracle.com/javase/8/javafx/api/toc.htm
     * @see <li>https://docs.oracle.com/javase/8/javafx/concurrent/Service.html
     */
    void checkStatus( Entity entity, String cmdString ) throws Exception {

        Service service = new Service() {

            @Override protected Task createTask () {
                
                return new Task<Void>() {
                    
                    @Override protected Void call() throws Exception {
                        
                        Integer sumState = 
                                ( new cEventTask3( entity.getCSC(), "summaryState" ) ).call();

                        if ( sumState.equals( CommandableSalComponent.CSC_STATUS.SAL__OK.getValue() ) ) {
                            
                            int ndx = execFX.getEntityList().indexOf( entity );

                            stateTextList.get(ndx).setText( STATE_TEXT_MAP.get( cmdString ) );
                            stateTextList.get(ndx)
                                         .setStyle( "-fx-text-fill: darkcyan;" +
                                                    "-fx-font-weight: bold;" +   
                                                    "-fx-font-size: 11;" );

                            if ( cmdString.matches( "enterControl" ) ) {

                                stateLabelList.get(ndx)
                                              .setStyle( "-fx-text-fill: green;"  + 
                                                         "-fx-font-size: 13;"     +
                                                         "-fx-font-weight: bold;" +
                                                         "-fx-border-width: 0 1 0 0;"  );
                                stateLabelList.get(ndx).setEffect(new Glow(0.9));
                                stateTooltipList.get(ndx).setText( stateLabelList.get(ndx).getText() + " Online");
                            }

                            if ( cmdString.matches( "exitControl" ) ) {

                                stateLabelList.get(ndx)
                                              .setStyle( "-fx-text-fill: gainsboro;" +
                                                         "-fx-font-size: 13;"        +
                                                         "-fx-font-weight: normal;" );
                                stateLabelList.get(ndx).setEffect(new Glow());
                                stateTooltipList.get(ndx).setText( stateLabelList.get(ndx).getText() + " Offline");
                            }
                        }
                        
                        return null;
                    } // end call()
                }; // end Task()
            } // end createTask()
        }; // end new Service()
        
       service.start();
    }

    @FXML
    private void tcsCmd(ActionEvent event) {
        
        // Grab the index & string of the selected CSC menu item
        int cmdIndex = tcsCmdMenu.getItems().indexOf( event.getSource() );
        String cmdString = tcsCmdMenu.getItems().get(cmdIndex).getText();
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscTCS
        
        // 2a. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        // 2b. Also, assign topic & topic arguments
        SalCmd salCmdTcs = new SalCmd( execFX.getCscList().get( 0 /* cscTCS */ ) );
        salCmdTcs.setTopic( cmdString );

        // 3a. Define Invoker w/ # of threads
        // 3b. Set SalService request (a cmd in this case)
        SalConnect salConnectTcs = new SalConnect( 1 );
        salConnectTcs.setSalService( salCmdTcs );
        
        // 4. Invoker indirectly calls cmd->execute()
        salConnectTcs.connect();
        

//        // Grab the index & string of the selected CSC menu item
//        int cmdIndex = tcsCmdMenu.getItems().indexOf( event.getSource() );
//        String cmdString = tcsCmdMenu.getItems().get(cmdIndex).getText();
//        
//        Executors.newFixedThreadPool( 1 )
//                 .submit( Executive.cEventTask_TCS.get( cmdIndex ) );

        // State Pattern: context.request() [e.g. entityTcs.enterControl()]
        //Entity entity = execFX.getEntityList().get( 0 /* cscTCS */ );
        //Executors.newFixedThreadPool( 1 )
        //         .submit( new rCmdTask( entity, cmdString ) );
    }

    @FXML
    private void ccsState(ActionEvent event) throws Exception {

        // Grab the index of the selected menu item cmd
        int cmdIndex = ccsStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = ccsStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 1 /* cscCCS */ );

        // State Pattern: context.request() [e.g. entityCcs.enterControl()]
        rCmdTask task = new rCmdTask( entity, cmdString );
        task.call();
        
        checkStatus( entity, cmdString );
    }
    
    @FXML
    private void ccsCmd(ActionEvent event) {
    
        // Grab the index & string of the selected CSC menu item
        int cmdIndex = ccsCmdMenu.getItems().indexOf( event.getSource() );
        String cmdString = ccsCmdMenu.getItems().get(cmdIndex).getText();
        
//        Executors.newFixedThreadPool( 1 )
//                 .submit( Executive.cEventTask_CCS.get( cmdIndex ) );

        // 1. SalComponent (Receiver) previously defined: Executive.cscTCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        SalCmd salCmdCcs = new SalCmd( execFX.getCscList().get( 1 /* cscCCS */ ) );
        salCmdCcs.setTopic( cmdString );

        /**********************************/
        
        SalConnect salConnectCcs = new SalConnect( 2 );
        salConnectCcs.setSalService( salCmdCcs );
        salConnectCcs.connect();
       
        if ( cmdString.equalsIgnoreCase("initImage")  )  {
            
            try {
                Thread.sleep( 4000 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            
            SalCmd salCmdCcs2 = new SalCmd( execFX.getCscList().get( 1 /* cscCCS */ ) );
            salCmdCcs2.setTopic( "takeImage" );
            salConnectCcs.setSalService( salCmdCcs2 );

            salConnectCcs.connect();
        }

        //int num = 2;
        // 3. Define Invoker w/ # of threads & set SalService request
//        SalConnect salConnectCcs = new SalConnect( 1 );
//        salConnectCcs.setSalService( salCmdCcs );
//        salConnectCcs.connect();

//        try {
//            Thread.sleep( 10000 );
//        } catch ( InterruptedException e ) {
//            e.printStackTrace();
//        }
//
//        if ( cmdString.equalsIgnoreCase("initImage")  )  {
//            
//            SalCmd salCmdCcs2 = new SalCmd( execFX.getCscList().get( 5 /* cscCCS2 */ ) );
//            salCmdCcs2.setTopic( "takeImage" );
//            
//            SalConnect salConnectCcs2 = new SalConnect(1);
//            salConnectCcs2.setSalService( salCmdCcs2 );
//            salConnectCcs2.connect();
//        }
    }
    
    @FXML
    private void createCSC(ActionEvent event) {

        out.print( Thread.currentThread().getStackTrace()[1].getMethodName() + "::" +
                   "Threadid: " + 
                   Thread.currentThread().getId() + "\n");
        
        // Grab the index & string of the selected CSC menu item
        int cscIndex = menuCSC.getItems().indexOf( event.getSource() );
        String cmdString = menuCSC.getItems().get(cscIndex).getText();
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscTCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        SalCmd salCmdCsc = new SalCmd( execFX.getCscList(). get( cscIndex ) );
        salCmdCsc.setTopic( "enterControl" );
        // 3. Define Invoker w/ # of threads & set SalService request
        SalConnect salConnectCsc = new SalConnect(1);
        salConnectCsc.setSalService( salCmdCsc );
        //salConnectCamera.setSalCmd(new SalCmd(rCCS), "enterControl");
        // 4. Invoker indirectly calls cmd->execute()
        salConnectCsc.connect();

        Future<Integer> futureSumState = 
                Executors.newFixedThreadPool( 1 )
                         .submit( Executive.cEventTask_SUMSTATE.get( cscIndex ) );

//        try {
//            //Integer result = futureSumState.get(5, TimeUnit.SECONDS);
//            if (futureSumState.get(5, TimeUnit.SECONDS) == 
//                    CSC_STATUS.SAL__OK.getValue()) {
//                
//                // Set text of tcsStateText to a GREEN "cmdString"
//                tcsStateText.setText(cmdString);
//                tcsStateText.setStyle("-fx-text-fill: green");
//                
//            }
//        } catch (InterruptedException|ExecutionException|TimeoutException ex) {
//            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        tcsStateText.setText(cmdString);
        tcsStateText.setStyle("-fx-text-fill: green; -fx-font-size: 12;");
        
    }
    
    @FXML
    private void arcState(ActionEvent event) {

       // Grab the index of the selected menu item cmd
        int cmdIndex = arcStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = arcStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 2 /* cscARC */ );

        //Future<Integer> futureSumState =
        //    Executors.newFixedThreadPool( 1 )
        //             .submit( new cEventTask( entity.getCSC(), "summaryState" ));

        // State Pattern: context.request() [e.g. entityArc.enterControl()]
        rCmdTask task = new rCmdTask( entity, cmdString );
        task.call();
        //Executors.newFixedThreadPool( 1 )
        //         .submit( new rCmdTask( entity, cmdString ) );
        
        arcStateText.setText( STATE_TEXT_MAP.get( cmdString ) );
        arcStateText.setStyle( "-fx-text-fill: darkcyan;" +
                               "-fx-font-weight: bold;" +
                               "-fx-font-size: 11;" );
        
        if ( cmdString.matches("enterControl") ) {
            
            arcLabel.setStyle( "-fx-text-fill: green;"  + 
                               "-fx-font-size: 13;"     +
                               "-fx-font-weight: bold;" +
                               "-fx-border-width: 1 1 1 1;"  );
            arcLabel.setEffect(new Glow(0.9));
            arcTooltip.setText("DM ARCHIVER Online");
        }
        
        if ( cmdString.matches("exitControl") ) {
            
            arcLabel.setStyle( "-fx-text-fill: gainsboro;" +
                               "-fx-font-size: 13;"        +
                               "-fx-font-weight: normal;" );
            arcLabel.setEffect(new Glow());
            arcTooltip.setText("DM ARCHIVER Offline");
        }
    }
    
    @FXML
    private void catState(ActionEvent event) {

       // Grab the index of the selected menu item cmd
        int cmdIndex = catStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = catStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 3 /* cscCAT */ );

        //Future<Integer> futureSumState =
        //    Executors.newFixedThreadPool( 1 )
        //             .submit( new cEventTask( entity.getCSC(), "summaryState" ));

        // State Pattern: context.request() [e.g. entityCat.enterControl()]
        rCmdTask task = new rCmdTask( entity, cmdString );
        task.call();
        //Executors.newFixedThreadPool( 1 )
        //         .submit( new rCmdTask( entity, cmdString ) );
        
        catStateText.setText( STATE_TEXT_MAP.get( cmdString ) );
        catStateText.setStyle( "-fx-text-fill: darkcyan;" +
                               "-fx-font-weight: bold;" +
                               "-fx-font-size: 11;" );
        
        if ( cmdString.matches("enterControl") ) {
            
            catLabel.setStyle( "-fx-text-fill: green;"  + 
                               "-fx-font-size: 13;"     +
                               "-fx-font-weight: bold;" +
                               "-fx-border-width: 1 1 1 1;"  );
            catLabel.setEffect(new Glow(0.9));
            catTooltip.setText("DM CATCHUP ARCHIVER Online");
        }
        
        if ( cmdString.matches("exitControl") ) {
            
            catLabel.setStyle( "-fx-text-fill: gainsboro;" +
                               "-fx-font-size: 13;"        +
                               "-fx-font-weight: normal;" );
            catLabel.setEffect(new Glow());
            catTooltip.setText("DM CATCHUP ARCHIVER Offline");
        }
    }
    
    @FXML
    private void proState(ActionEvent event) {

       // Grab the index of the selected menu item cmd
        int cmdIndex = proStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = proStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 4 /* cscPRO */ );

        //Future<Integer> futureSumState =
        //    Executors.newFixedThreadPool( 1 )
        //             .submit( new cEventTask( entity.getCSC(), "summaryState" ));

        // State Pattern: context.request() [e.g. entityPro.enterControl()]
        rCmdTask task = new rCmdTask( entity, cmdString );
        task.call();
        //Executors.newFixedThreadPool( 1 )
        //         .submit( new rCmdTask( entity, cmdString ) );
        
        proStateText.setText( STATE_TEXT_MAP.get( cmdString ) );
        proStateText.setStyle( "-fx-text-fill: darkcyan;" +
                               "-fx-font-weight: bold;" +
                               "-fx-font-size: 11;" );
        
        if ( cmdString.matches("enterControl") ) {
            
            proLabel.setStyle( "-fx-text-fill: green;"  + 
                               "-fx-font-size: 13;"     +
                               "-fx-font-weight: bold;" +
                               "-fx-border-width: 1 1 1 1;"  );
            proLabel.setEffect(new Glow(0.9));
            proTooltip.setText("DM PROCESSING CLUSTER Online");
        }
        
        if ( cmdString.matches("exitControl") ) {
            
            proLabel.setStyle( "-fx-text-fill: gainsboro;" +
                               "-fx-font-size: 13;"        +
                               "-fx-font-weight: normal;" );
            proLabel.setEffect(new Glow());
            proTooltip.setText("DM PROCESSING CLUSTER Offline");
        }
    }
    
    @FXML
    private void hdrState(ActionEvent event) {

       // Grab the index of the selected menu item cmd
        int cmdIndex = hdrStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = hdrStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 5 /* cscHDR */ );

        //Future<Integer> futureSumState =
        //    Executors.newFixedThreadPool( 1 )
        //             .submit( new cEventTask( entity.getCSC(), "summaryState" ));

        // State Pattern: context.request() [e.g. entityHdr.enterControl()]
        rCmdTask task = new rCmdTask( entity, cmdString );
        task.call();
        //Executors.newFixedThreadPool( 1 )
        //         .submit( new rCmdTask( entity, cmdString ) );
        
        hdrStateText.setText( STATE_TEXT_MAP.get( cmdString ) );
        hdrStateText.setStyle( "-fx-text-fill: darkcyan;" +
                               "-fx-font-weight: bold;" +
                               "-fx-font-size: 11;" );
        
        if ( cmdString.matches("enterControl") ) {
            
            hdrLabel.setStyle( "-fx-text-fill: green;"  + 
                               "-fx-font-size: 13;"     +
                               "-fx-font-weight: bold;" +
                               "-fx-border-width: 1 1 1 1;"  );
            hdrLabel.setEffect(new Glow(0.9));
            hdrTooltip.setText("DM HEADER SERVICE Online");
        }
        
        if ( cmdString.matches("exitControl") ) {
            
            hdrLabel.setStyle( "-fx-text-fill: gainsboro;" +
                               "-fx-font-size: 13;"        +
                               "-fx-font-weight: normal;" );
            hdrLabel.setEffect(new Glow());
            hdrTooltip.setText("DM HEADER SERVICE Offline");
        }
    }
    
    @FXML
    private void createAllCSC(ActionEvent event) throws InterruptedException {

        out.print( Thread.currentThread().getStackTrace()[1].getMethodName() + "::" +
                   "Threadid: " + 
                   Thread.currentThread().getId() + "\n");
        
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Executive.cEventTask_SUMSTATE.forEach( (task) -> {
            executorService.submit(task);
        });

        // 1. SalComponent (Receiver) previously defined: Executive.cscTCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        
        SalConnect salConnectCsc = new SalConnect( execFX.getCscList().size() );
        execFX.getCscList().forEach( csc -> {
            
            SalCmd salCmdCsc = new SalCmd(csc);
            salCmdCsc.setTopic( "enterControl" );
            salConnectCsc.setSalService( salCmdCsc );
            }    
        );
        
        salConnectCsc.connect();
    }
}
