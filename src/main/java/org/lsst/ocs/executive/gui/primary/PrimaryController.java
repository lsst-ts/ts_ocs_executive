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
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import org.lsst.ocs.executive.Entity;
import org.lsst.ocs.executive.Executive;
import org.lsst.ocs.executive.ExecutiveFX;
import org.lsst.ocs.executive.cEventTask;
import org.lsst.ocs.executive.rCmdTask;
import org.lsst.ocs.executive.salcomponent.CommandableSalComponent;
import org.lsst.ocs.executive.salcomponent.CommandableSalComponent.CSC_STATUS;
import org.lsst.ocs.executive.salconnect.SalConnect;
import org.lsst.ocs.executive.salservice.SalCmd;

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
    private Label tcsLabel, ccsLabel, arcLabel, catLabel, proLabel;
    
    @FXML
    private Tooltip tcsToolTip;
    
    @FXML
    private Menu menuCSC;

    @FXML
    private MenuButton menuTcsCmd;

    @FXML
    private MenuButton menuTcsState;

    @FXML
    private MenuButton menuCameraCmd;

    @FXML
    private MenuButton menuCcsState;

    @FXML
    private MenuButton menuArcState;

    @FXML
    private TextField tcsStateText, ccsStateText, arcStateText, catStateText, proStateText;

    @FXML
    private TextField tcsCmdText, ccsCmdText;

    public final List<TextField> stateTextList = Arrays.asList(
            
        tcsStateText,
        ccsStateText,
        arcStateText,
        catStateText,
        proStateText
    );
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
    @FXML
    private MenuItem enter;
    @FXML
    private MenuItem start;
    @FXML
    private MenuItem enable;
    @FXML
    private MenuItem disable;
    @FXML
    private MenuItem standby;
    @FXML
    private MenuItem exit;
    @FXML
    private MenuItem enterCcs;
    @FXML
    private MenuItem startCcs;
    @FXML
    private MenuItem enableCcs;
    @FXML
    private MenuItem disableCcs;
    @FXML
    private MenuItem standbyCcs;
    @FXML
    private MenuItem exitCcs;
    @FXML
    private MenuItem enterArc;
    @FXML
    private MenuItem startArc;
    @FXML
    private MenuItem enableArc;
    @FXML
    private MenuItem disableArc;
    @FXML
    private MenuItem standbyArc;
    @FXML
    private MenuItem exitArc;
    
    @FXML
    private void tcsState(ActionEvent event) {

        // Grab the index of the selected menu item cmd
        int cmdIndex = menuTcsState.getItems().indexOf( event.getSource() );
        String cmdString = menuTcsState.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 0 /* cscTCS */ );

        Future<Integer> futureSumState =
            Executors.newFixedThreadPool( 1 )
                     .submit( new cEventTask( entity.getCSC(), "summaryState" ));

        // State Pattern: context.request() [e.g. entityTcs.enterControl()]
        Executors.newFixedThreadPool( 1 )
                 .submit( new rCmdTask( entity, cmdString ) );
        
        tcsStateText.setText( cmdString );
        tcsStateText.setStyle( "-fx-text-fill: darkcyan;" +
                               "-fx-font-size: 11;" );
        
        if ( cmdString.matches("enterControl") ) {
            
            tcsLabel.setStyle( "-fx-text-fill: green;"  + 
                               "-fx-font-size: 15;"     +
                               "-fx-font-weight: bold;" +
                               "-fx-border-width: 1 1 1 1;"  );
            tcsLabel.setEffect(new Glow(0.9));
            tcsToolTip.setText("TCS Online");
        }
        
        if ( cmdString.matches("exitControl") ) {
            
            tcsLabel.setStyle( "-fx-text-fill: gainsboro;" +
                               "-fx-font-size: 13;"        +
                               "-fx-font-weight: normal;" );
            tcsLabel.setEffect(new Glow());
            tcsToolTip.setText("TCS Offline");
        }
    }
    
    @FXML
    private void tcsCmd(ActionEvent event) {

        // Grab the index & string of the selected CSC menu item
        int cmdIndex = menuTcsCmd.getItems().indexOf( event.getSource() );
        String cmdString = menuTcsCmd.getItems().get(cmdIndex).getText();
        
        Executors.newFixedThreadPool( 1 )
                 .submit( Executive.cEventTask_TCS.get( cmdIndex ) );

        // State Pattern: context.request() [e.g. entityTcs.enterControl()]
        Entity entity = execFX.getEntityList().get( 0 /* cscTCS */ );
        Executors.newFixedThreadPool( 1 )
                 .submit( new rCmdTask( entity, cmdString ) );
    }

    @FXML
    private void ccsState(ActionEvent event) {

        // Grab the index of the selected menu item cmd
        int cmdIndex = menuTcsState.getItems().indexOf( event.getSource() );
        String cmdString = menuTcsState.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 1 /* cscCCS */ );

        Future<Integer> futureSumState =
            Executors.newFixedThreadPool( 1 )
                     .submit( new cEventTask( entity.getCSC(), "summaryState" ));

        // State Pattern: context.request() [e.g. entityCcs.enterControl()]
        Executors.newFixedThreadPool( 1 )
                 .submit( new rCmdTask( entity, cmdString ) );
        
        tcsStateText.setText( cmdString );
        tcsStateText.setStyle( "-fx-text-fill: darkcyan;" +
                               "-fx-font-size: 11;" );
        
        if ( cmdString.matches("enterControl") ) {
            
            tcsLabel.setStyle( "-fx-text-fill: green;"  + 
                               "-fx-font-size: 15;"     +
                               "-fx-font-weight: bold;" +
                               "-fx-border-width: 1 1 1 1;"  );
            tcsLabel.setEffect(new Glow(0.9));
            tcsToolTip.setText("TCS Online");
        }
        
        if ( cmdString.matches("exitControl") ) {
            
            tcsLabel.setStyle( "-fx-text-fill: gainsboro;" +
                               "-fx-font-size: 13;"        +
                               "-fx-font-weight: normal;" );
            tcsLabel.setEffect(new Glow());
            tcsToolTip.setText("TCS Offline");
        }
        
    }
    
    @FXML
    private void cameraCmd(ActionEvent event) {
    
        // Grab the index & string of the selected CSC menu item
        int cmdIndex = menuCameraCmd.getItems().indexOf( event.getSource() );
        String cmdString = menuCameraCmd.getItems().get(cmdIndex).getText();
        
//        Executors.newFixedThreadPool( 1 )
//                 .submit( Executive.cEventTask_CCS.get( cmdIndex ) );

        // 1. SalComponent (Receiver) previously defined: Executive.cscTCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        SalCmd salCmdCcs = new SalCmd( execFX.getCscList().get( 1 /* cscCCS */ ) );
        salCmdCcs.setTopic( cmdString );

        //int num = 2;
        // 3. Define Invoker w/ # of threads & set SalService request
        SalConnect salConnectCcs = new SalConnect( 1 );
        salConnectCcs.setSalService( salCmdCcs );
        salConnectCcs.connect();
        
        try {
            Thread.sleep( 10000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        if ( cmdString.equalsIgnoreCase("initImage")  )  {
            
            SalCmd salCmdCcs2 = new SalCmd( execFX.getCscList().get( 5 /* cscCCS2 */ ) );
            salCmdCcs2.setTopic( "takeImage" );
            
            SalConnect salConnectCcs2 = new SalConnect(1);
            salConnectCcs2.setSalService( salCmdCcs2 );
            salConnectCcs2.connect();
        }
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
        int cmdIndex = menuArcState.getItems().indexOf( event.getSource() );
        String cmdString = menuArcState.getItems().get(cmdIndex).getText();
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscTCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        SalCmd salCmdCsc = new SalCmd( execFX.getCscList().get( 2 /* cscCCS */ ) );
        salCmdCsc.setTopic( cmdString );
        // 3. Define Invoker w/ # of threads & set SalService request
        SalConnect salConnectCsc = new SalConnect(1);
        salConnectCsc.setSalService( salCmdCsc );
        // 4. Invoker indirectly calls cmd->execute()
        salConnectCsc.connect();    
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
    
    /**
     * Is called by the main FX application to give a reference back to itself.
     * 
     * @param refExecFX
     */
    public void setExecFXApp(ExecutiveFX refExecFX) {

        this.execFX = refExecFX;
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize( URL url, ResourceBundle rb ) {
        // TODO
    }

}
