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

package org.lsst.ocs.executive.gui.controllers;

import org.lsst.ocs.executive.CmdTask;
import org.lsst.ocs.executive.Entity;
import org.lsst.ocs.executive.Executive;
import org.lsst.ocs.executive.gui.fx.*;
import org.lsst.ocs.executive.salcomponent.CommandableSalComponent.*;
import org.lsst.ocs.executive.salconnect.SalConnect;
import org.lsst.ocs.executive.salservice.SalCmd;

import static java.lang.System.out;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Platform;
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

/**
 * <h2>FXML Primary Controller</h2>
 *
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

    @FXML private MenuItem primaryExit;

    @FXML private MenuButton schStateMenu, mtcsStateMenu, ccsStateMenu, arcStateMenu,
                             catStateMenu, proStateMenu, hdrStateMenu, aschStateMenu,
                             atcsStateMenu, accsStateMenu, aarcStateMenu, ahdrStateMenu;

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
    
    @FXML private Menu accsTakeImage;

    @FXML private MenuItem setTakeImageMenui;
        
    @FXML private Label schLabel, mtcsLabel, ccsLabel, arcLabel, catLabel, proLabel,
                        hdrLabel, aschLabel, atcsLabel, accsLabel, aarcLabel, ahdrLabel;
    private static final List<Label> STATE_LABEL_LIST = new ArrayList<Label>();

    @FXML private Tooltip schTooltip, mtcsTooltip, ccsTooltip, arcTooltip, catTooltip,
                          proTooltip, hdrTooltip, aschTooltip, atcsTooltip, accsTooltip,
                          aarcTooltip, ahdrTooltip;
    private static final List<Tooltip> STATE_TOOLTIP_LIST = new ArrayList<Tooltip>();
    
    @FXML private TextField schStateText, mtcsStateText, ccsStateText, arcStateText,
                            catStateText, proStateText, hdrStateText, aschStateText,
                            atcsStateText, accsStateText, aarcStateText, ahdrStateText;
    private static final List<TextField> STATE_TEXT_LIST = new ArrayList<TextField>();
    
    public static final Map<String, String> STATE_TEXT_MAP = new HashMap<>();

    // Reference to ExecutiveFX, the main Application class
    private Executive _exec;
    
    {
//        STATE_LABEL_LIST.add( schLabel  ); STATE_LABEL_LIST.add( mtcsLabel );
//        STATE_LABEL_LIST.add( ccsLabel  ); STATE_LABEL_LIST.add( arcLabel  );
//        STATE_LABEL_LIST.add( catLabel  ); STATE_LABEL_LIST.add( proLabel  );
//        STATE_LABEL_LIST.add( hdrLabel  ); STATE_LABEL_LIST.add( aschLabel );
//        STATE_LABEL_LIST.add( atcsLabel );
        STATE_LABEL_LIST.add( accsLabel );
        STATE_LABEL_LIST.add( aarcLabel ); STATE_LABEL_LIST.add( ahdrLabel );

//        STATE_TOOLTIP_LIST.add( schTooltip  ); STATE_TOOLTIP_LIST.add( mtcsTooltip );
//        STATE_TOOLTIP_LIST.add( ccsTooltip  ); STATE_TOOLTIP_LIST.add( arcTooltip  );
//        STATE_TOOLTIP_LIST.add( catTooltip  ); STATE_TOOLTIP_LIST.add( proTooltip  );
//        STATE_TOOLTIP_LIST.add( hdrTooltip  ); STATE_TOOLTIP_LIST.add( aschTooltip );
//        STATE_TOOLTIP_LIST.add( atcsTooltip );
        STATE_TOOLTIP_LIST.add( accsTooltip );
        STATE_TOOLTIP_LIST.add( aarcTooltip ); STATE_TOOLTIP_LIST.add( ahdrTooltip );

//        STATE_TEXT_LIST.add( schStateText  ); STATE_TEXT_LIST.add( mtcsStateText );
//        STATE_TEXT_LIST.add( ccsStateText  ); STATE_TEXT_LIST.add( arcStateText  );
//        STATE_TEXT_LIST.add( catStateText  ); STATE_TEXT_LIST.add( proStateText  );
//        STATE_TEXT_LIST.add( hdrStateText  ); STATE_TEXT_LIST.add( aschStateText );
//        STATE_TEXT_LIST.add( atcsStateText );
        STATE_TEXT_LIST.add( accsStateText );
        STATE_TEXT_LIST.add( aarcStateText ); STATE_TEXT_LIST.add( ahdrStateText );
    }

    /**
          * Initializes the controller class. 
          * <p>
          * This method is automatically called  after the fxml file has been loaded.
          */
    @Override
    public void initialize( URL locationUrl, ResourceBundle resourceBundle ) {
        
        _exec = new Executive();
        
//        ExecutorService es = Executors.newCachedThreadPool();
//        _exec.cEventTask_SUMSTATE.forEach( es::submit );
        
        primaryExit.setOnAction( e -> {
            
            Platform.exit();
            
            System.exit( 0 );
        }); 
    }
    
    /**
          * Method is called by the main FX application to give a reference back to itself.
          * 
          * @param refExecFX class which holds {@code main()}
          */
    //public void setExecFXApp( ExecutiveFX refExecFX ) { this._exec = refExecFX; }

    /**
           * The {@code checkSummaryState()} method subscribes to the SummaryState topic
           * of a specific CSC on a background JavaFX thread.
           * <p>
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
                        
                        Thread.currentThread().setName( "GuiCheck SumState Service" );
                        //out.println( Thread.currentThread().getName() + ": " + Thread.currentThread().getId() );
                        
                        int ndx = _exec.getEntityList().indexOf( entity );
                        TextField stateText = STATE_TEXT_LIST.get( ndx );
                        Label stateLabel = STATE_LABEL_LIST.get( ndx );
                        Tooltip stateTooltip = STATE_TOOLTIP_LIST.get( ndx );
                        
                        int viewState =  entity._viewStateTransitionQ.take();
            
                        if ( viewState == entity.getNextStateValue() ) {

                            stateText.setText( CSC_STATE_CMD.valueOf( cmdString ).toValueString() );
                            stateText.setStyle( "-fx-text-fill: darkcyan;" );
                            stateText.setFont( Font.font( "System", FontWeight.BOLD, 11 ));

                            if ( cmdString.matches( "enterControl" )) {

                                stateLabel.setStyle( "-fx-text-fill: green;" );
                                stateLabel.setEffect( new Glow( 0.9 ));
                                stateLabel.setFont( Font.font( "System", FontWeight.BOLD, 14 ));
                                stateLabel.setBorder( new Border( 
                                                        new BorderStroke( Color.BLACK,
                                                        BorderStrokeStyle.SOLID,
                                                        CornerRadii.EMPTY,
                                                        new BorderWidths( 1, 1, 1, 0 )))
                                );
                                
                                stateTooltip.setText( stateLabel.getText() + " Online" );
                            }

                            if ( cmdString.matches( "exitControl" )) {

                                stateLabel.setStyle( "-fx-text-fill: gainsboro;" );
                                stateLabel.setEffect( new Glow() );
                                stateLabel.setFont( Font.font( "System", FontWeight.NORMAL, 11 ));
                                stateLabel.setBorder( new Border( 
                                                        new BorderStroke( Color.BLACK,
                                                        BorderStrokeStyle.SOLID,
                                                        CornerRadii.EMPTY,
                                                        new BorderWidths( 1, 1, 1, 0 )))
                                );
                                
                                stateTooltip.setText( stateLabel.getText() + " Offline" );
                            }
                        }
                        
                        return null;
                    } // end call()
                }; // end Task()
            } // end createTask()
        }; // end new Service()
        
       service.start();
    }

    void checkSettingsVersion( Entity entity, String cmdString ) throws Exception { }
    void checkAppliedSettings( Entity entity, String cmdString ) throws Exception { }
    void checkFilterChange   ( Entity entity, String cmdString ) throws Exception { }
    void checkTarget         ( Entity entity, String cmdString ) throws Exception { }

    // Generic "State Transition" EventHandler for any CSC State pull-down menu items
    @FXML private void cscState( ActionEvent event ) throws Exception {

        MenuItem mi = ( MenuItem ) event.getSource();
        
        String cmdString = mi.getText();

        // Grab the first 3 characters of the command string
        Entity entity = _exec.getEntityMap()     /* e.g. entitySCH */
                             .get( mi.getId().split( "_" )[0] ); // split by dash & grab 1st word
                             //.get( mi.getId().substring( 0, 3 )); 

        entity.setNextStateValue( CSC_STATE_CMD.valueOf( cmdString ).toValue() );
        
        // State Pattern: context.request() [e.g. entitySCH.enterControl()]
        ( new CmdTask( entity, cmdString ) ).call();
        
        checkSummaryState( entity, cmdString );
    }
    
    // Generic "Command Request" EventHandler for any CSC Command pull-down menu items
    @FXML private void cscCmd( ActionEvent event ) {
    
        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText();
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscELE
        
        // 2a. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        // 2b. Also, assign topic & topic arguments
        SalCmd salCmd = new SalCmd( _exec.getCscMap()   /* e.g. cscELE */
                                         .get( mi.getId().split( "_" )[0] )); // split by dash & grab 1st word
                                         //.get( mi.getId().substring( 0, 3 )));
        
        salCmd.setTopic( cmdString );

        // 3a. Define Invoker w/ # of threads
        // 3b. Set SalService request (a cmd in this case)
        SalConnect salConnect = new SalConnect( 1 );
        salConnect.setSalService( salCmd );
        
        // 4. Invoker indirectly calls cmd->execute()
        salConnect.connect();
    }
    
    @FXML private void createCSC( ActionEvent event ) throws Exception {

        out.println( Thread.currentThread()
                         .getStackTrace()[1]
                         .getMethodName() + "::" 
                                          + "Threadid: "
                                          + Thread.currentThread().getId() );
        
        // Grab the index & string of the selected CSC menu item
        int cscIndex = menuCSC.getItems().indexOf( event.getSource() );
        String cmdString = "enterControl";

        Entity entity = _exec.getEntityList().get( cscIndex /* e.g. entityMTCS */ );
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscMTCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        SalCmd salCmdCsc = new SalCmd( _exec.getCscList().get( cscIndex ));
        salCmdCsc.setTopic( cmdString );

        // 3. Define Invoker w/ # of threads & set SalService request
        SalConnect salConnectCsc = new SalConnect( 1 );
        salConnectCsc.setSalService( salCmdCsc );

        // 4. Invoker indirectly calls cmd->execute()
        salConnectCsc.connect();
        
        checkSummaryState( entity, cmdString );        
    }
    
    @FXML private void cscStateAll( ActionEvent event ) throws Exception {

        out.print( Thread.currentThread()
                         .getStackTrace()[1]
                         .getMethodName() + "::" 
                                          + "Threadid: "
                                          + Thread.currentThread().getId() + "\n" );
        
        MenuItem mi = ( MenuItem ) event.getSource();
        String cmdString = mi.getText()
                             .split( " " )[0]; // split by space & grab 1st word
        
        // 3a. Define Invoker w/ # of threads
        SalConnect salConnectCsc = new SalConnect( _exec.getCscList().size() );

        _exec.getCscList().forEach( csc -> {
            
            // 1. SalComponent (Receiver) previously defined: Executive.cscMTCS

            // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
            //    Also, assign topic & topic arguments
            SalCmd salCmdCsc = new SalCmd( csc );
            salCmdCsc.setTopic( cmdString );
            
            // 3b.Sset SalService request
            salConnectCsc.setSalService( salCmdCsc );
        });
        
        // 4. Invoker indirectly calls cmd->execute()
        salConnectCsc.connect();

        _exec.getEntityList().forEach( entity -> {
            
            try {
                checkSummaryState( entity, cmdString );
            } catch ( Exception ex ) {
                ex.printStackTrace( out.printf(
                    "InterruptedException from  PrimaryController.createAllCSC()" ));
                //Logger.getLogger( PrimaryController.class.getName() ).log( Level.SEVERE, null, ex );
            }
        });
    }
    
    @FXML private void handleTakeImage( ActionEvent event ) throws Exception {
        
        TakeImageFX.getInstance().startStage();
    }

    @FXML private void handleRunScript( ActionEvent event ) throws Exception {
        
        RunScriptFX.getInstance().startStage();
    }
}