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

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * <h2>Take Image FX</h2>
 *
 * The {@code TakeImageFX} class is the entry point for the Take Image Setup window.
 */
public final class TakeImageFX {
    
    private static volatile TakeImageFX _instance = null;

    public static TakeImageFX getInstance() throws Exception {
        
        // make local copy
        TakeImageFX instance = TakeImageFX._instance;
        
    	// Double-check locking pattern
    	if ( instance == null ) {   // 1st Check

            // Thread safe
            synchronized( TakeImageFX.class ) {
                
                instance = TakeImageFX._instance;
                
                if ( instance == null ) {   // 2nd Check

                    TakeImageFX._instance = instance = new TakeImageFX();
                }
            }
    	}
        
        return TakeImageFX._instance;
    }

    private static FXMLLoader _loader;
    private static Parent     _root;
    private static Stage      _stage;
    private static String     _fxmlFile;

    // private Cstr so no direct instances can be made    
    private TakeImageFX() throws Exception {

    	// Prevent forming new instance via reflection API
    	if ( _instance != null ) {
            
            throw new RuntimeException(
                "Use getInstance () method to get the singleton instance of this class" );
    	}
    
        _fxmlFile = "/fxml/takeImageFXML.fxml";
        _loader = new FXMLLoader();
        _root = (Parent) _loader.load( getClass().getResourceAsStream( _fxmlFile ));

        _stage = new Stage();
        _stage.initModality( Modality.WINDOW_MODAL );
        _stage.setScene( new Scene( _root ));
        _stage.setTitle( "Take Image Setup" );
        _stage.setResizable( false );
    }
    
    public static void startStage() { _stage.show(); }
    
    public static void closeStage() { _stage.close(); }
}
