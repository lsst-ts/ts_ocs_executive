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
    private ObservableList<CommandableSalComponent> cscAList = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public ExecutiveFX() {
        // Add some sample data
        cscList.add( Executive.cscTCS );
        cscList.add( Executive.cscCCS );
        cscList.add( Executive.cscARC );
        cscList.add( Executive.cscCAT );
        cscList.add( Executive.cscPRO );
    }

    /**
     * Returns the observable list of non-active CSCs. 
     * @return
     */
    public ObservableList<CommandableSalComponent> getCscList() {
        return cscList;
    }    
    
    /**
     * Returns the observable list of active CSCs. 
     * @return
     */
    public ObservableList<CommandableSalComponent> getCscAList() {
        return cscAList;
    }    
    
    /**
     * The {@code start()} method is the main entry point for all JavaFX
     * applications
     */
    @Override
    public void start( Stage primaryStage ) throws Exception { // Stage class is the top-level JavaFX container

        ///////////////////////////////////////////////       
        /// Load fxml configuration file
        ///////////////////////////////////////////////       
        // 1.
        //BorderPane rootBorderPane = FXMLLoader.load( getClass().getResource( "/fxml/primaryFXML.fxml" ) );

        String fxmlFile = "/fxml/primaryFXML.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootBorderPane = (Parent) loader.load( getClass().getResourceAsStream( fxmlFile ) );

        // Give the controller access to the main app.
        PrimaryController controller = loader.getController();
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
     * This is the {@code main()} method which launches the application using
     * the {@code launch()} method.
     * <p>
     * The {@code launch()} method internally calls the {@code start()} method
     * of the {@link Application} class.
     */
    public static void main( String[] args ) {

        launch( args );
    }

}
