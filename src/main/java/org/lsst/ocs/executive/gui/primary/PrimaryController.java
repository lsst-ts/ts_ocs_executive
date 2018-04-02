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
    
    @FXML private MenuButton schStateMenu, mtcsStateMenu, ccsStateMenu, arcStateMenu, catStateMenu, proStateMenu, hdrStateMenu,
                             aschStateMenu, atcsStateMenu, accsStateMenu, aarcStateMenu, ahdrStateMenu;

    @FXML private MenuItem schEnter, schStart, schEnable, schDisable, schStandby, schExit,
                           aschEnter, aschStart, aschEnable, aschDisable, aschStandby, aschExit;

    @FXML private MenuItem mtcsEnter, mtcsStart, mtcsEnable, mtcsDisable, mtcsStandby, mtcsExit,
                           atcsEnter, atcsStart, atcsEnable, atcsDisable, atcsStandby, atcsExit;

    @FXML private MenuItem ccsEnter, ccsStart, ccsEnable, ccsDisable, ccsStandby, ccsExit,
                           accsEnter, accsStart, accsEnable, accsDisable, accsStandby, accsExit;
    
    @FXML private MenuItem arcEnter, arcStart, arcEnable, arcDisable, arcStandby, arcExit;

    @FXML private MenuItem aarcEnter, aarcStart, aarcEnable, aarcDisable, aarcStandby, aarcExit;
    
    @FXML private MenuItem catEnter, catStart, catEnable, catDisable, catStandby, catExit;
    
    @FXML private MenuItem proEnter, proStart, proEnable, proDisable, proStandby, proExit;
    
    @FXML private MenuItem hdrEnter, hdrStart, hdrEnable, hdrDisable, hdrStandby, hdrExit,
                           ahdrEnter, ahdrStart, ahdrEnable, ahdrDisable, ahdrStandby, ahdrExit;
    
    @FXML private MenuButton mtcsCmdMenu, ccsCmdMenu, atcsCmdMenu, accsCmdMenu;

    @FXML private TextField mtcsCmdText, ccsCmdText, atcsCmdText, accsCmdText;

    @FXML private Menu menuCSC;

    @FXML private MenuItem menuitemCreateTCS, menuitemCreateCamera,
                           menuitemCreateArchiver, menuitemCreateCatchupArchiver, 
                           menuitemCreateProcessingCluster, menuitemCreateAll;

    @FXML private Label schLabel, mtcsLabel, ccsLabel, arcLabel, catLabel, proLabel, hdrLabel,
                        aschLabel, atcsLabel, accsLabel, aarcLabel, ahdrLabel;
    private ObservableList<Label> stateLabelList;

    @FXML private Tooltip schTooltip, mtcsTooltip, ccsTooltip, arcTooltip, catTooltip, proTooltip, hdrTooltip,
                          aschTooltip, atcsTooltip, accsTooltip, aarcTooltip, ahdrTooltip;
    private ObservableList<Tooltip> stateTooltipList;
    
    @FXML private TextField schStateText, mtcsStateText, ccsStateText, arcStateText, catStateText, proStateText, hdrStateText,
                            aschStateText, atcsStateText, accsStateText, aarcStateText, ahdrStateText;
    private ObservableList<TextField> stateTextList;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize( URL locationUrl, ResourceBundle resourceBundle ) {
        stateLabelList = FXCollections.observableArrayList( 
            schLabel, mtcsLabel, ccsLabel, arcLabel, catLabel, proLabel,
            aschLabel, atcsLabel, accsLabel, aarcLabel, ahdrLabel
        );

        stateTooltipList = FXCollections.observableArrayList(
            schTooltip, mtcsTooltip, ccsTooltip, arcTooltip, catTooltip, proTooltip,
            aschTooltip, atcsTooltip, accsTooltip, aarcTooltip, ahdrTooltip
        );

        stateTextList = FXCollections.observableArrayList(
            schStateText, mtcsStateText, ccsStateText, arcStateText, catStateText, proStateText,
            aschStateText, atcsStateText, accsStateText, aarcStateText, ahdrStateText
        );
    }
    
    /**
     * Is called by the main FX application to give a reference back to itself.
     * 
     * @param refExecFX class which holds {@code main()}
     */
    public void setExecFXApp( ExecutiveFX refExecFX ) { this.execFX = refExecFX; }

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
                        
                        TextField stateText = stateTextList.get( ndx );
                        Label stateLabel = stateLabelList.get( ndx );
                        Tooltip stateTooltip = stateTooltipList.get( ndx );

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
                                                                                    new BorderWidths( 1, 1, 1, 0 ) ) ) );
                                
                                stateTooltip.setText( stateLabel.getText() + " Online");
                            }

                            if ( cmdString.matches( "exitControl" ) ) {

                                stateLabel.setStyle( "-fx-text-fill: gainsboro;" );
                                stateLabel.setEffect( new Glow() );
                                stateLabel.setFont( Font.font( "System", FontWeight.NORMAL, 11 ) );
                                stateLabel.setBorder( new Border( new BorderStroke( Color.BLACK,
                                                                                    BorderStrokeStyle.SOLID,
                                                                                    CornerRadii.EMPTY,
                                                                                    new BorderWidths( 1, 1, 1, 0 ) ) ) );
                                
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

    @FXML private void cscState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        
        String cmdString = mi.getText();
        // Grab the first 3 characters of the command string
        Entity entity = execFX.STATE_ENTITY_MAP.get( mi.getId().substring( 0, 3 ) ); /* e.g. entitySCH */

        // State Pattern: context.request() [e.g. entitySCH.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void schState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        
        String cmdString = mi.getText();
        Entity entity = execFX.STATE_ENTITY_MAP.get( mi.getId() ); /* entitySCH */
        //Entity entity = execFX.getEntityList().get( 0 /* entitySCH */ );

        // State Pattern: context.request() [e.g. entitySCH.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void mtcsState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        
        String cmdString = mi.getText();
        Entity entity = execFX.STATE_ENTITY_MAP.get( mi.getId().substring( 0, 3 ) ); /* entityMTCS */
        //Entity entity = execFX.getEntityList().get( 0 /* entityMTCS */ );

        // State Pattern: context.request() [e.g. entityMTCS.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void mtcsCmd( ActionEvent event ) {
        
        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscMTCS
        
        // 2a. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        // 2b. Also, assign topic & topic arguments
        SalCmd salCmdMTCS = new SalCmd( execFX.getCscList().get( 0 /* cscMTCS */ ) );
        salCmdMTCS.setTopic( cmdString );

        // 3a. Define Invoker w/ # of threads
        // 3b. Set SalService request (a cmd in this case)
        SalConnect salConnectMTCS = new SalConnect( 1 );
        salConnectMTCS.setSalService( salCmdMTCS );
        
        // 4. Invoker indirectly calls cmd->execute()
        salConnectMTCS.connect();
    }

    @FXML private void ccsState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();

        String cmdString = mi.getText();
        Entity entity = execFX.STATE_ENTITY_MAP.get( mi.getId() ); /* entityCCS */
        //Entity entity = execFX.getEntityList().get( 1 /* entityCCS */ );

        // State Pattern: context.request() [e.g. entityCCS.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void ccsCmd( ActionEvent event ) {
    
        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscCCS

        // 2a. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        // 2b. Also, assign topic & topic arguments
        SalCmd salCmdCCS = new SalCmd( execFX.getCscList().get( 1 /* cscCCS */ ) );
        salCmdCCS.setTopic( cmdString );

        // 3a. Define Invoker w/ # of threads
        // 3b. Set SalService request (a cmd in this case)
        SalConnect salConnectCCS = new SalConnect( 2 );
        salConnectCCS.setSalService( salCmdCCS );

        // 4. Invoker indirectly calls cmd->execute()
        salConnectCCS.connect();
       
        if ( cmdString.equalsIgnoreCase( "initImage" )  )  {
            
            try {
                Thread.sleep( 4000 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            
            SalCmd salCmdCCS2 = new SalCmd( execFX.getCscList().get( 1 /* cscCCS */ ) );
            salCmdCCS2.setTopic( "takeImage" );
            salConnectCCS.setSalService( salCmdCCS2 );

            salConnectCCS.connect();
        }
    }
    
    @FXML private void arcState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 2 /* entityARC */ );

        // State Pattern: context.request() [e.g. entityARC.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void catState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 3 /* entityCAT */ );

        // State Pattern: context.request() [e.g. entityCAT.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void proState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 4 /* entityPRO */ );

        // State Pattern: context.request() [e.g. entityPRO.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void hdrState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 5 /* entityHDR */ );

        // State Pattern: context.request() [e.g. entityHDR.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }

    @FXML private void aschState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        
        String cmdString = mi.getText();
        Entity entity = execFX.STATE_ENTITY_MAP.get( mi.getId() ); /* entityASCH */
        //Entity entity = execFX.getEntityList().get( 0 /* entityASCH */ );

        // State Pattern: context.request() [e.g. entityASCH.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void atcsState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 6 /* entityATCS */ );

        // State Pattern: context.request() [e.g. entityATCS.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void atcsCmd( ActionEvent event ) {
        
        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscATCS
        
        // 2a. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        // 2b. Also, assign topic & topic arguments
        SalCmd salCmdATCS = new SalCmd( execFX.getCscList().get( 6 /* cscATCS */ ) );
        salCmdATCS.setTopic( cmdString );

        // 3a. Define Invoker w/ # of threads
        // 3b. Set SalService request (a cmd in this case)
        SalConnect salConnectATCS = new SalConnect( 1 );
        salConnectATCS.setSalService( salCmdATCS );
        
        // 4. Invoker indirectly calls cmd->execute()
        salConnectATCS.connect();
    }

    @FXML private void accsState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 7 /* entityACCS */ );

        // State Pattern: context.request() [e.g. entityACCS.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void accsCmd( ActionEvent event ) {
    
        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscACCS

        // 2a. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        // 2b. Also, assign topic & topic arguments
        SalCmd salCmdACCS = new SalCmd( execFX.getCscList().get( 7 /* cscACCS */ ) );
        salCmdACCS.setTopic( cmdString );

        // 3a. Define Invoker w/ # of threads
        // 3b. Set SalService request (a cmd in this case)
        SalConnect salConnectACCS = new SalConnect( 2 );
        salConnectACCS.setSalService( salCmdACCS );

        // 4. Invoker indirectly calls cmd->execute()
        salConnectACCS.connect();
       
        if ( cmdString.equalsIgnoreCase( "initImage" )  )  {
            
            try {
                Thread.sleep( 4000 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            
            SalCmd salCmdACCS2 = new SalCmd( execFX.getCscList().get( 7 /* cscACCS */ ) );
            salCmdACCS2.setTopic( "takeImage" );
            salConnectACCS.setSalService( salCmdACCS2 );

            salConnectACCS.connect();
        }
    }
    
    @FXML private void aarcState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 8 /* entityAARC */ );

        // State Pattern: context.request() [e.g. entityAARC.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    @FXML private void ahdrState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();

        Entity entity = execFX.getEntityList().get( 9 /* entityAHDR */ );

        // State Pattern: context.request() [e.g. entityAHDR.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }

    @FXML private void createCSC( ActionEvent event ) throws Exception {

        out.print( Thread.currentThread()
                         .getStackTrace()[1]
                         .getMethodName() + "::" 
                                          + "Threadid: "
                                          + Thread.currentThread().getId() + "\n" );
        
        // Grab the index & string of the selected CSC menu item
        int cscIndex = menuCSC.getItems().indexOf( event.getSource() );
        String cmdString = "enterControl";

        
        Entity entity = execFX.getEntityList().get( cscIndex /* e.g. entityMTCS */ );
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscMTCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        SalCmd salCmdCsc = new SalCmd( execFX.getCscList().get( cscIndex ) );
        salCmdCsc.setTopic( cmdString );

        // 3. Define Invoker w/ # of threads & set SalService request
        SalConnect salConnectCsc = new SalConnect( 1 );
        salConnectCsc.setSalService( salCmdCsc );

        // 4. Invoker indirectly calls cmd->execute()
        salConnectCsc.connect();
        
        checkSummaryState( entity, cmdString );        
    }
    
    @FXML private void createAllCSC( ActionEvent event ) throws Exception {

        out.print( Thread.currentThread()
                         .getStackTrace()[1]
                         .getMethodName() + "::" 
                                          + "Threadid: "
                                          + Thread.currentThread().getId() + "\n" );
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscMTCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        
        SalConnect salConnectCsc = new SalConnect( execFX.getCscList().size() );
        String cmdString = "enterControl";

        execFX.getCscList().forEach( csc -> {
            
            SalCmd salCmdCsc = new SalCmd( csc );
            salCmdCsc.setTopic( cmdString );
            salConnectCsc.setSalService( salCmdCsc );
        });
        salConnectCsc.connect();

        execFX.getEntityList().forEach( entity -> {
            
            try {
                checkSummaryState( entity, cmdString );
            } catch ( Exception ex ) {
                ex.printStackTrace( out.printf( "InterruptedException from  PrimaryController.createAllCSC()" ) );
                //Logger.getLogger( PrimaryController.class.getName() ).log( Level.SEVERE, null, ex );
            }
        });
    }
}
