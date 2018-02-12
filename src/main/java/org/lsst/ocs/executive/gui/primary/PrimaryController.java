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
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import org.lsst.ocs.executive.Entity;
import org.lsst.ocs.executive.ExecutiveFX;
import org.lsst.ocs.executive.CmdTask;
import org.lsst.ocs.executive.EventTask;
import org.lsst.ocs.executive.salcomponent.CommandableSalComponent;
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
    
    @FXML private MenuButton tcsStateMenu, ccsStateMenu, arcStateMenu, catStateMenu, proStateMenu, hdrStateMenu,
                             atcsStateMenu, accsStateMenu, admStateMenu, ahdrStateMenu;

    @FXML private MenuItem enter, start, enable, disable, standby, exit,
                           aenter, astart, aenable, adisable, astandby, aexit;

    @FXML private MenuItem ccsEnter, ccsStart, ccsEnable, ccsDisable, ccsStandby, ccsExit,
                           accsEnter, accsStart, accsEnable, accsDisable, accsStandby, accsExit;
    
    @FXML private MenuItem arcEnter, arcStart, arcEnable, arcDisable, arcStandby, arcExit;

    @FXML private MenuItem admEnter, admStart, admEnable, admDisable, admStandby, admExit;
    
    @FXML private MenuItem catEnter, catStart, catEnable, catDisable, catStandby, catExit;
    
    @FXML private MenuItem proEnter, proStart, proEnable, proDisable, proStandby, proExit;
    
    @FXML private MenuItem hdrEnter, hdrStart, hdrEnable, hdrDisable, hdrStandby, hdrExit,
                           ahdrEnter, ahdrStart, ahdrEnable, ahdrDisable, ahdrStandby, ahdrExit;
    
    @FXML private MenuButton tcsCmdMenu, ccsCmdMenu, atcsCmdMenu, accsCmdMenu;

    @FXML private TextField tcsCmdText, ccsCmdText, atcsCmdText, accsCmdText;

    @FXML private Menu menuCSC;

    @FXML private MenuItem menuitemCreateTCS, menuitemCreateCamera,
                           menuitemCreateArchiver, menuitemCreateCatchupArchiver, 
                           menuitemCreateProcessingCluster, menuitemCreateAll;

    @FXML private Label tcsLabel, ccsLabel, arcLabel, catLabel, proLabel, hdrLabel,
                        atcsLabel, accsLabel, admLabel, ahdrLabel;
    private ObservableList<Label> stateLabelList;

    @FXML private Tooltip tcsTooltip, ccsTooltip, arcTooltip, catTooltip, proTooltip, hdrTooltip,
                          atcsTooltip, accsTooltip, admTooltip, ahdrTooltip;
    private ObservableList<Tooltip> stateTooltipList;
    
    @FXML private TextField tcsStateText, ccsStateText, arcStateText, catStateText, proStateText, hdrStateText,
                            atcsStateText, accsStateText, admStateText, ahdrStateText;
    private ObservableList<TextField> stateTextList;
    
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
    }
    
    /**
     * Is called by the main FX application to give a reference back to itself.
     * 
     * @param refExecFX class which holds {@code main()}
     */
    public void setExecFXApp(ExecutiveFX refExecFX) {

        this.execFX = refExecFX;
    }

    /**
     * The {@code checkSummaryState()} method subscribes to the SummaryState topic
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
    void checkSummaryState( Entity entity, String cmdString ) throws Exception {

        Service service = new Service() {

            @Override protected Task createTask () {
                
                return new Task<Void>() {
                    
                    @Override protected Void call() throws Exception {
                        
                        Integer sumState = 
                                ( new EventTask( entity.getCSC(), "summaryState" ) ).call();
                        
                        int ndx = execFX.getEntityList().indexOf( entity );
                        TextField stateText = stateTextList.get(ndx);
                        Label stateLabel = stateLabelList.get(ndx);
                        Tooltip stateTooltip = stateTooltipList.get(ndx);

                        if ( sumState.equals( CommandableSalComponent.CSC_STATUS.SAL__OK.getValue() ) ) {
                            
                            stateText.setText( execFX.STATE_TEXT_MAP.get( cmdString ) );
                            stateText.setStyle( "-fx-text-fill: darkcyan;" );
                            stateText.setFont( Font.font( "System", FontWeight.BOLD, 11 ) );

                            if ( cmdString.matches( "enterControl" ) ) {

                                stateLabel.setStyle( "-fx-text-fill: green;" );
                                stateLabel.setEffect( new Glow( 0.9 ) );
                                stateLabel.setFont( Font.font( "System", FontWeight.BOLD, 14 ) );
                                stateLabel.setBorder( new Border( new BorderStroke( Color.BLACK,
                                                                                    BorderStrokeStyle.SOLID,
                                                                                    CornerRadii.EMPTY,
                                                                                    new BorderWidths( 1, 1, 1, 0 ))));
                                stateTooltip.setText( stateLabel.getText() + " Online");
                            }

                            if ( cmdString.matches( "exitControl" ) ) {

                                stateLabel.setStyle( "-fx-text-fill: gainsboro;" );
                                stateLabel.setEffect( new Glow() );
                                stateLabel.setFont( Font.font( "System", FontWeight.NORMAL, 11 ) );
                                stateLabel.setBorder( new Border( new BorderStroke( Color.BLACK,
                                                                                    BorderStrokeStyle.SOLID,
                                                                                    CornerRadii.EMPTY,
                                                                                    new BorderWidths( 1, 1, 1, 0 ))));
                                stateTooltip.setText( stateLabel.getText() + " Offline");
                            }
                        }
                        
                        return null;
                    } // end call()
                }; // end Task()
            } // end createTask()
        }; // end new Service()
        
       service.start();
    }

    void checkSettingsVersion( Entity entity, String cmdString ) throws Exception { ;}
    void checkAppliedSettings( Entity entity, String cmdString ) throws Exception { ;}
    void checkFilterChange( Entity entity, String cmdString ) throws Exception { ;}
    void checkTarget( Entity entity, String cmdString ) throws Exception { ;}
    
    @FXML private void tcsState(ActionEvent event) throws Exception {

        MenuItem mi = (MenuItem) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 0 /* cscTCS */ );

        // State Pattern: context.request() [e.g. entityTcs.enterControl()]
        (new CmdTask( entity, cmdString )).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void tcsCmd(ActionEvent event) {
        
        MenuItem mi = (MenuItem) event.getSource();
        String cmdString = mi.getText();
        
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
    }

    @FXML private void ccsState(ActionEvent event) throws Exception {

        MenuItem mi = (MenuItem) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 1 /* cscCCS */ );

        // State Pattern: context.request() [e.g. entityCcs.enterControl()]
        (new CmdTask( entity, cmdString )).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void ccsCmd(ActionEvent event) {
    
        MenuItem mi = (MenuItem) event.getSource();
        String cmdString = mi.getText();
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscTCS

        // 2a. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        // 2b. Also, assign topic & topic arguments
        SalCmd salCmdCcs = new SalCmd( execFX.getCscList().get( 1 /* cscCCS */ ) );
        salCmdCcs.setTopic( cmdString );

        // 3a. Define Invoker w/ # of threads
        // 3b. Set SalService request (a cmd in this case)
        SalConnect salConnectCcs = new SalConnect( 2 );
        salConnectCcs.setSalService( salCmdCcs );

        // 4. Invoker indirectly calls cmd->execute()
        salConnectCcs.connect();
       
        if ( cmdString.equalsIgnoreCase( "initImage" )  )  {
            
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
    }
    
    @FXML private void arcState(ActionEvent event) throws Exception {

       // Grab the index of the selected menu item cmd
        int cmdIndex = arcStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = arcStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 2 /* cscARC */ );

        // State Pattern: context.request() [e.g. entityArc.enterControl()]
        (new CmdTask( entity, cmdString )).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void catState(ActionEvent event) throws Exception {

       // Grab the index of the selected menu item cmd
        int cmdIndex = catStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = catStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 3 /* cscCAT */ );

        // State Pattern: context.request() [e.g. entityCat.enterControl()]
        (new CmdTask( entity, cmdString )).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void proState(ActionEvent event) throws Exception {

       // Grab the index of the selected menu item cmd
        int cmdIndex = proStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = proStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 4 /* cscPRO */ );

        // State Pattern: context.request() [e.g. entityPro.enterControl()]
        (new CmdTask( entity, cmdString )).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void hdrState(ActionEvent event) throws Exception {

       // Grab the index of the selected menu item cmd
        int cmdIndex = hdrStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = hdrStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 5 /* cscHDR */ );

        // State Pattern: context.request() [e.g. entityHdr.enterControl()]
        (new CmdTask( entity, cmdString )).call();
        
        checkSummaryState( entity, cmdString );
    }

    @FXML private void atcsState(ActionEvent event) throws Exception {

        MenuItem mi = (MenuItem) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 0 /* cscTCS */ );

        // State Pattern: context.request() [e.g. entityTcs.enterControl()]
        (new CmdTask( entity, cmdString )).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void atcsCmd(ActionEvent event) {
        
        MenuItem mi = (MenuItem) event.getSource();
        String cmdString = mi.getText();
        
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
    }

    @FXML private void accsState(ActionEvent event) throws Exception {

        MenuItem mi = (MenuItem) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 1 /* cscCCS */ );

        // State Pattern: context.request() [e.g. entityCcs.enterControl()]
        (new CmdTask( entity, cmdString )).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void accsCmd(ActionEvent event) {
    
        MenuItem mi = (MenuItem) event.getSource();
        String cmdString = mi.getText();
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscTCS

        // 2a. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        // 2b. Also, assign topic & topic arguments
        SalCmd salCmdCcs = new SalCmd( execFX.getCscList().get( 1 /* cscCCS */ ) );
        salCmdCcs.setTopic( cmdString );

        // 3a. Define Invoker w/ # of threads
        // 3b. Set SalService request (a cmd in this case)
        SalConnect salConnectCcs = new SalConnect( 2 );
        salConnectCcs.setSalService( salCmdCcs );

        // 4. Invoker indirectly calls cmd->execute()
        salConnectCcs.connect();
       
        if ( cmdString.equalsIgnoreCase( "initImage" )  )  {
            
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
    }
    
    @FXML private void admState(ActionEvent event) throws Exception {

       // Grab the index of the selected menu item cmd
        int cmdIndex = arcStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = arcStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 2 /* cscARC */ );

        // State Pattern: context.request() [e.g. entityArc.enterControl()]
        (new CmdTask( entity, cmdString )).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void ahdrState(ActionEvent event) throws Exception {

       // Grab the index of the selected menu item cmd
        int cmdIndex = hdrStateMenu.getItems().indexOf( event.getSource() );
        String cmdString = hdrStateMenu.getItems().get(cmdIndex).getText();

        Entity entity = execFX.getEntityList().get( 5 /* cscHDR */ );

        // State Pattern: context.request() [e.g. entityHdr.enterControl()]
        (new CmdTask( entity, cmdString )).call();
        
        checkSummaryState( entity, cmdString );
    }

    @FXML private void createCSC(ActionEvent event) throws Exception {

        out.print( Thread.currentThread().getStackTrace()[1].getMethodName() + "::" +
                   "Threadid: " + 
                   Thread.currentThread().getId() + "\n");
        
        // Grab the index & string of the selected CSC menu item
        int cscIndex = menuCSC.getItems().indexOf( event.getSource() );
        String cmdString = "enterControl";
        
        Entity entity = execFX.getEntityList().get( cscIndex /* e.g. cscTCS */ );
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscTCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        SalCmd salCmdCsc = new SalCmd( execFX.getCscList().get( cscIndex ) );
        salCmdCsc.setTopic( cmdString );

        // 3. Define Invoker w/ # of threads & set SalService request
        SalConnect salConnectCsc = new SalConnect(1);
        salConnectCsc.setSalService( salCmdCsc );

        // 4. Invoker indirectly calls cmd->execute()
        salConnectCsc.connect();
        
        checkSummaryState( entity, cmdString );        
    }
    
    @FXML private void createAllCSC(ActionEvent event) throws Exception {

        out.print( Thread.currentThread().getStackTrace()[1].getMethodName() + "::" +
                   "Threadid: " + 
                   Thread.currentThread().getId() + "\n");
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscTCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        
        SalConnect salConnectCsc = new SalConnect( execFX.getCscList().size() );
        String cmdString = "enterControl";

        execFX.getCscList() .forEach( csc -> {
            
            SalCmd salCmdCsc = new SalCmd(csc);
            salCmdCsc.setTopic( cmdString );
            salConnectCsc.setSalService( salCmdCsc );
        }) ;
        salConnectCsc.connect();

        execFX.getEntityList().forEach(entity -> {
            
            try { 
                checkSummaryState( entity, cmdString );
            } catch ( Exception ex ) {
                ex.printStackTrace( out.printf( "InterruptedException from  PrimaryController.createAllCSC()" ) );
                //Logger.getLogger( PrimaryController.class.getName() ).log( Level.SEVERE, null, ex );
            }
        });
    }
}
