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

package org.lsst.ocs.executive.gui.fx;

import org.lsst.ocs.executive.gui.controllers.PrimaryController;

import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * <h2>Executive FX</h2>
 *
 * The {@code ExecutiveFX} class is the entry point of this JavaFX application.
 */
public class ExecutiveFX extends Application {

    private PrimaryController controller;
    private Alert confirmQuitAlert;

    /**
          * The {@code start()} method is the main entry point for all JavaFX  applications
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
        Parent rootBorderPane = (Parent) loader.load(
            getClass().getResourceAsStream( fxmlFile ));

        // Give the controller access to the main app.
        //controller = loader.getController();
        //controller.setExecFXApp( this );

        // OR
        // 2.
        //String fxmlFile = "/fxml/primaryFXML.fxml";
        //FXMLLoader loader = new FXMLLoader();
        //Parent rootBorderPane = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        ///////////////////////////////////////////////       
        // Create the Scene using the loaded Pane
        Scene scene = new Scene( rootBorderPane );

        /* Set the Scene to the Stage, set the Stage Title, disable window resizing  & display the Stage */
        primaryStage.setScene( scene );
        primaryStage.setTitle( "OCS Executive EUI" );
        primaryStage.setResizable( false );
        primaryStage.show();
        
        /* exit all secondary windows when primary window close for the 'X' close button*/
        primaryStage.setOnCloseRequest( ( WindowEvent we ) -> {
            
            confirmQuitAlert = new Alert( Alert.AlertType.CONFIRMATION );
            confirmQuitAlert.setTitle( "Quit Application" );
            confirmQuitAlert.setHeaderText( "Quit?" );
            confirmQuitAlert.setContentText( "Do you really want to quit?" );

            ButtonType quitButton   = new ButtonType( "Quit" );
            ButtonType cancelButton = new ButtonType( "Cancel", 
                                                      ButtonBar.ButtonData.CANCEL_CLOSE );

            confirmQuitAlert.initModality( Modality.APPLICATION_MODAL );
            confirmQuitAlert.getButtonTypes().setAll( quitButton, cancelButton );

            Optional<ButtonType> result = confirmQuitAlert.showAndWait();

            if ( result.get() == quitButton ) {
                
                Platform.exit();
                
                System.exit( 0 );
            } else {
                // cancel the close request by consuming the event
                we.consume();
            }            
        });
    }

    /**
          * Overriding {@code stop()} method to add {@code System.exit()} so ALL
          * threads (JavaFX and non Java-FX threads) will be terminated when clicking
          * 'x' on the {@code primaryStage} window.
          */
    @Override
    public void stop() throws Exception {
        
        super.stop();

        if ( controller != null ) {
            //controller.startHousekeeping(); 
        }

        // Terminates the JavaFX Application & Launcher threads
        Platform.exit();
        
        // Terminates the current JVM (basically killing non-JavaFX threads)
        System.exit( 0 );
    }

    /**
          * This is the {@code main()} method which launches the application using the {@code launch()} method.
          * <p>
          * The {@code launch()} method internally calls the {@code start()} method of the {@link Application} class.
          *
          * @param args n/a
          */
    public static void main( String[] args ) {

        launch( args );
    }
}