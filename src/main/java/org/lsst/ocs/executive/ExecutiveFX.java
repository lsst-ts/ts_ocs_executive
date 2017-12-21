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

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.lsst.ocs.executive.gui.primary.PrimaryController;
import org.lsst.ocs.executive.salcomponent.CommandableSalComponent;

/**
 * <h2>Executive FX</h2>
 * <p>
 * The {@code ExecutiveFX} class is the entry point of this JavaFX application.
 */

public class ExecutiveFX extends Application {

    /**
     * The data as an observable list of Persons.
     */
    private final ObservableList<CommandableSalComponent> cscList = FXCollections.observableArrayList();
    
    private final ObservableList<Entity> entityList = FXCollections.observableArrayList();
    
    private final ObservableList<String> tcsCmdList = FXCollections.observableArrayList();
    
    private final ObservableList<String> cameraCmdList = FXCollections.observableArrayList();
    
    /**
     * Constructor
     * 
     * @throws InterruptedException A few of the methods throw exceptions
     */
    public ExecutiveFX() throws Exception {
        
        cscList.add( Executive.cscTCS );
        cscList.add( Executive.cscCCS );
        cscList.add( Executive.cscARC );
        cscList.add( Executive.cscCAT );
        cscList.add( Executive.cscPRO );
        cscList.add( Executive.cscHDR);
        
        entityList.add( Executive.entityTCS );
        entityList.add( Executive.entityCCS );
        entityList.add( Executive.entityARC );
        entityList.add( Executive.entityCAT );
        entityList.add( Executive.entityPRO );
        entityList.add( Executive.entityHDR );
        
        tcsCmdList.add( "filterChange" );
        tcsCmdList.add( "target" );

        cameraCmdList.add( "setFilter" );
        cameraCmdList.add( "takeImage" );
    }

    /**
     * Returns the observable list of non-active CSCs. 
     * @return List of pre-instantiated CSCs
     */
    public ObservableList<CommandableSalComponent> getCscList() {
        
        return cscList;
    }    
    
    public ObservableList<Entity> getEntityList() {
        
        return entityList;
    }    
    
    public ObservableList<String> getTcsCmdList() {
        
        return tcsCmdList;
    }    
    
    public ObservableList<String> getCameraCmdList() {
        
        return cameraCmdList;
    }    
    
    /**
     * Returns the observable list of active CSCs. 
     * @return
     */
    private PrimaryController controller;

    /**
     * The {@code start()} method is the main entry point for all JavaFX
     * applications
     */
    @Override
    public void start( Stage primaryStage ) throws Exception { // Stage class is the top-level JavaFX container
        
//        out.print( Thread.currentThread().getStackTrace()[1].getMethodName() + "::" +
//                   "Threadid: " + 
//                   Thread.currentThread().getId() + "\n");

        ///////////////////////////////////////////////       
        /// Load fxml configuration file
        ///////////////////////////////////////////////       
        // 1.
        //BorderPane rootBorderPane = FXMLLoader.load( getClass().getResource( "/fxml/primaryFXML.fxml" ) );

        String fxmlFile = "/fxml/primaryFXML.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootBorderPane = (Parent) loader.load( getClass().getResourceAsStream( fxmlFile ) );
        
        // Give the controller access to the main app.
        controller = loader.getController();
        controller.setExecFXApp( this );
        
        // OR
        // 2.
        //String fxmlFile = "/fxml/primaryFXML.fxml";
        //FXMLLoader loader = new FXMLLoader();
        //Parent rootBorderPane = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        ///////////////////////////////////////////////       
        // Create the Scene using the loaded Pane
        Scene scene = new Scene( rootBorderPane );

        // Set the Scene to the Stage, set the Title to the Stage & display the Stage
        primaryStage.setScene( scene );
        primaryStage.setTitle( "OCS Executive GUI" );
        primaryStage.show();
    }

    /**
     * Overriding {@code stop()} method to add {@code System.exit()} 
     * so ALL threads (JavaFX and non Java-FX threads) will be terminated
     * when clicking 'x' on the {@code primaryStage} window.
     */ 
    @Override
    public void stop() throws Exception 
    {
        super.stop();
        
        if(controller != null)
        {
            //controller.startHousekeeping(); 
        }

        // Terminates the JavaFX Application & Launcher threads
        Platform.exit();
        // Terminates the current JVM (basically killing non-JavaFX threads)
        System.exit(0);
    } 
    
    /**
     * This is the {@code main()} method which launches the application using
     * the {@code launch()} method.
     * <p>
     * The {@code launch()} method internally calls the {@code start()} method
     * of the {@link Application} class.
     * 
     * @param args n/a
     */
    public static void main( String[] args ) {

        launch( args );
    }

}
