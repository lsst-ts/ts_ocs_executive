/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lsst.ocs.executive;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author James Buffill <jbuffill@lsst.org>
 */
public class ExecutiveFXApp extends Application {
    
    /* start() method is the main entry point for all JavaFX applications */
    @Override public void start(Stage primaryStage) throws Exception { // Stage class is the top-level JavaFX container
  
        ///////////////////////////////////////////////       
        /// Load fxml configuration file
        ///////////////////////////////////////////////       
        
        // 1.
        BorderPane rootBorderPane = FXMLLoader.load(getClass().getResource("/fxml/primaryFXML.fxml"));        
        
        // OR
        // 2.
        //String fxmlFile = "/fxml/primaryFXML.fxml";
        //FXMLLoader loader = new FXMLLoader();
        //Parent rootBorderPane = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        ///////////////////////////////////////////////       
        
        Scene scene = new Scene(rootBorderPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("OCS Executive GUI");
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        
        launch(args);
    }

}
