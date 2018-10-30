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

import org.lsst.ocs.executive.EntityType;
import org.lsst.ocs.executive.Executive;
import org.lsst.ocs.executive.gui.fx.TakeImageFX;
import org.lsst.ocs.executive.salconnect.SalConnect;
import org.lsst.ocs.executive.salservice.SalCmd;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

/**
 * <h2>FXML TakeImage Controller</h2>
 * <p>
 * The controller class for <i>takeImageFXML.fxml</i> document (via
 * {@code fx:controller} attribute).
 */
public class TakeImageController implements Initializable {

  // Reference to ExecutiveFX, the main Application class
  private Executive _exec;

  @FXML
  private TextField imageNameText;

  @FXML
  private SplitMenuButton numImagesMenub, exposeTimeMenub;

  @FXML
  private CheckBox shutterCheck, scienceCheck, guideCheck, wfsCheck;

  @FXML
  private Button applyButton, sendButton, exitButton;

  /**
   * Initializes the controller class.
   * <p>
   * This method is automatically called after the fxml file has been loaded.
   */
  @Override
  public void initialize( URL locationUrl, ResourceBundle resourceBundle ) {

    _exec = new Executive();

    numImagesMenub.getItems().forEach( mi -> {
      mi.addEventHandler( EventType.ROOT, event
                          -> {
                        MenuItem menui = (MenuItem) event.getSource();
                        numImagesMenub.setText( menui.getText() );
                      } );
    } );

    exposeTimeMenub.getItems().forEach( mi -> {
      mi.addEventHandler( EventType.ROOT, event
                          -> {
                        MenuItem menui = (MenuItem) event.getSource();
                        exposeTimeMenub.setText( menui.getText() );
                      } );
    } );
  }

  @FXML
  private void handleApply( ActionEvent event ) {

    Map<String /*
         * key
         */, Object /*
         * value
         */> argsMap = new TreeMap<>();

    argsMap.put( "1imageName", imageNameText.getText() /*
     * String
     */ );
    argsMap.put( "2numImages", Integer.parseInt( numImagesMenub.getText() /*
              * integer
              */ ) );
    argsMap.put( "3exposureTime", Double.parseDouble( exposeTimeMenub.getText() /*
              * double
              */ ) );
    argsMap.put( "4shutter", shutterCheck.isSelected() /*
     * boolean
     */ );
    argsMap.put( "5science", scienceCheck.isSelected() /*
     * boolean
     */ );
    argsMap.put( "6guide", guideCheck.isSelected() /*
     * boolean
     */ );
    argsMap.put( "7wfs", wfsCheck.isSelected() /*
     * boolean
     */ );

    System.out.println( "" );
    argsMap.forEach( ( key, value ) -> System.out.println( key + ": " + value ) );
  }

  @FXML
  private void handleSend( ActionEvent event ) {

    // Create a sorted map (sorted by key)
    Map<String /*
         * key
         */, Object /*
         * value
         */> argsMap = new TreeMap<>();

    argsMap.put( "1imageName", imageNameText.getText() /*
     * String
     */ );
    argsMap.put( "2numImages", Integer.parseInt( numImagesMenub.getText() /*
              * integer
              */ ) );
    argsMap.put( "3exposureTime", Double.parseDouble( exposeTimeMenub.getText() /*
              * double
              */ ) );
    argsMap.put( "4shutter", shutterCheck.isSelected() /*
     * boolean
     */ );
    argsMap.put( "5science", scienceCheck.isSelected() /*
     * boolean
     */ );
    argsMap.put( "6guide", guideCheck.isSelected() /*
     * boolean
     */ );
    argsMap.put( "7wfs", wfsCheck.isSelected() /*
     * boolean
     */ );

    System.out.println( "" );
    argsMap.forEach( ( key, value ) -> System.out.println( key + ": " + value ) );

    // 1. SalComponent (Receiver) previously defined: Executive.cscELE
    // 2a. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
    // 2b. Also, assign topic & topic arguments
    SalCmd salCmd = new SalCmd( _exec.getCscMap().get( EntityType.ATCAMERA.toString() ) );
    salCmd.setTopic( "takeImage" );
    salCmd.setTopicArgs( argsMap.values().toArray() );

    // 3a. Define Invoker w/ # of threads
    // 3b. Set SalService request (a cmd in this case)
    SalConnect salConnect = new SalConnect( 1 );
    salConnect.setSalService( salCmd );

    // 4. Invoker indirectly calls cmd->execute()
    salConnect.connect();
  }

  @FXML
  private void handleExit( ActionEvent event ) throws Exception {

    TakeImageFX.getInstance().closeStage();
  }
}
