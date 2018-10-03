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

import org.lsst.ocs.executive.Executive;
import org.lsst.ocs.executive.gui.fx.RunScriptFX;

import static java.lang.System.out;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import jep.Jep;
import jep.JepConfig;
import jep.JepException;
import jep.SharedInterpreter;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * <h2>FXML RunScript Controller</h2>
 * 
 * The controller class for <i>runScriptFXML.fxml</i> document (via 
 * {@code fx:controller} attribute).
 */

public class RunScriptController implements Initializable {
    
    // Reference to ExecutiveFX, the main Application class
    private Executive _exec;
    
    FileChooser _fileChooser;
    
    File _selectedFile;
    
    @FXML private TextField scriptText;
    
    @FXML private TextArea alertText;
    
    @FXML private Button chooseButton, runButton, exitButton;
    
    /**
     * Initializes the controller class. 
     * 
     * This method is automatically called after the fxml file has been loaded.
     */
    @Override
    public void initialize( URL locationUrl, ResourceBundle resourceBundle ) {
        
        _exec = new Executive();
        
        _fileChooser = new FileChooser();
        _fileChooser.setTitle( "Select Script File");
        _fileChooser.setInitialDirectory( new File( "." ));
        
        _fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter( "All Files" , "*.*" ),
            new FileChooser.ExtensionFilter( "Python Files" , "*.py" ));
    }
    
    @FXML private void handleChoose( ActionEvent event ) throws FileNotFoundException {
        
        _selectedFile = _fileChooser.showOpenDialog( null );
        
        if ( _selectedFile != null ) {
            try {
                scriptText.setText( _selectedFile.getAbsolutePath() );
            } catch ( Exception e ) {
                Alert alert = new Alert( Alert.AlertType.ERROR );
                alert.setTitle( "Select File Error" );
                ButtonType okButton = new ButtonType( "OK", 
                                                      ButtonBar.ButtonData.OK_DONE );
                alert.getButtonTypes().setAll( okButton );
                alert.showAndWait();

                alertText.setText( "Exception in selecting script file: " + e.getMessage() );
            }
        }
    }

    @FXML private void handleRun( ActionEvent event ) {

        Service service = new Service() {

            @Override protected Task createTask () {
                
                return new Task<Void>() {
                    
                    @Override protected Void call() throws Exception {

                        Jep jep = new SharedInterpreter();

                        try {

                            jep.eval( "import os" );
                            jep.eval( "import imp" );
                            jep.eval( "import inspect" );
                            
                            // Separate file name from file extension
                            jep.set( "fname", _selectedFile.getAbsolutePath() );
                            jep.eval( "fname, ext = os.path.splitext(fname)" );
                            
                            // Import file as a module
                            jep.eval( "fp, pathname, details = imp.find_module(fname)" );
                            jep.eval( "module = imp.load_module(fname, fp, pathname, details)" );
                            
                            // Aggregate classes from module into a class dictionary
                            jep.eval( "valid_sequences = {}; script = []" );
                            jep.eval( "for [name,obj] in inspect.getmembers(module):" +
                                      "\n\tif inspect.isclass(obj):" +
                                      "\n\t\tvalid_sequences[name] = obj" +
                                      "\n\t\tscript = name if name is not 'BaseSequence' else script" );
                            
                            jep.eval( "sequence = valid_sequences[script]()" );
                            jep.eval( "sequence.execute()" );
                            
                        } catch ( JepException e ) {
                            
                            out.println( "A Jep Python Exception occured in running script file" );
                            e.printStackTrace( out.printf( "\n" ));
                        } catch ( Exception e ) {
                            
                            out.println( "A Java Exception occurred in running script file" );
                            out.println( e.getMessage() );
                        } finally {
                            
                            try {
                                jep.close();
                                out.println( "\nDONE\n" );
                            } catch ( JepException e ) {
                                out.println( e.getMessage() );
                            }
                        }

                        return null;
                }};
            }};
        
        //out.println( "service: " + service );
        service.start();
    }
    
//    @FXML private void handleRun( ActionEvent event ) {
//
//        Service service = new Service() {
//
//            @Override protected Task createTask () {
//                
//                return new Task<Void>() {
//                    
//                    @Override protected Void call() throws Exception {
//
//                        //Jep jep = new Jep( new JepConfig().setInteractive( true ));        
//                        //jep = new Jep();   
//                        //jep = new JepConfig().setInteractive( true ).createJep();
//                        Jep jep = null;
//                        jep = new SharedInterpreter();
//
//                        try {
//                            
//                            jep.eval( "import os" );
//                            jep.eval( "import imp" );
//                            jep.eval( "import inspect" );
//                            
//                            // Separate file name from file extension
//                            jep.set( "name", _selectedFile.getAbsolutePath() );
//                            jep.eval( "name, ext = os.path.splitext(name)" );
//                            
//                            // Import file as a module
//                            jep.eval( "fp, pathname, details = imp.find_module(name)" );
//                            jep.eval( "module = imp.load_module(name, fp, pathname, details)" );
//                            
//                            jep.eval( "classname = module.__all__.copy().pop()" );
//                            jep.eval( "sequence = getattr(module, classname)" );
//
//                            jep.eval( "sequence.execute(sequence)" );
//                            
//                            // Aggregate classes from module into class dictionary
////                            jep.eval( "valid_sequences = {}" );
////                            jep.eval( "members = inspect.getmembers(module)" );
////                            jep.eval( "for member in members:" +
////                                      "\n\tif inspect.isclass(member[1]) and issubclass(member[1], module.BaseSequence):" +
////                                      "\n\t\tvalid_sequences[member[0]] = member[1]" );
////                            
////                            jep.eval( "from java.lang import System" );
////                            jep.eval( "System.out.println(members)" );
////                            jep.eval( "System.out.println(valid_sequences)" );
////
//                            // Extract subclass from classes
////                            jep.eval( "scripts = []" );
////                            jep.eval( "for script in valid_sequences:" +
////                                      "\n\tscripts.append(script)" );
////                            jep.eval( "script = scripts[0] if scripts[0] is not 'BaseSequence' else scripts[1]" );
////                            jep.eval( "script = scripts[0] if scripts[0] is not 'object' else scripts[1]" );
//                                    
//                            //jep.eval( "from java.lang import System" );
//                            //jep.eval( "System.out.println(script)" );
//
////                            jep.eval( "sequence = valid_sequences[script]()" );
////                            jep.eval( "sequence.execute()" );
//                             
//                            
//                            
//                            
//                            
//                            //jep.eval( "sequence = module.ATRaiseException()" );
//                            //jep.eval( "sequence.execute()" );
//
//                            //jep.runScript( "/home/tcs/swdev/ts_sequence/scripts/run_sequence.py -s WavelengthCalibrationSequence" );
//                            //jep.runScript( "/home/tcs/swdev/ts_sequence/python/lsst/ts/sequence/atcs/at_calibration_illumination_system.py" );
//                            //jep.runScript( _selectedFile.getName() );
//
////                                jep.eval( "from lsst.ts.sequence import ATRaiseException" );
////                                jep.eval( "sequence=ATRaiseException()" );
//
////                            if ( _ran == 0 ) {
////                                
////                                jep.eval( "from lsst.ts.sequence import ATRaiseException" );
////                                jep.eval( "sequence=ATRaiseException()" );
////                                
////                                _ran++;
////                            } else {
////                                
////                                jep.eval( "from lsst.ts.sequence import WavelengthCalibrationSequence" );
////                                jep.eval( "sequence=WavelengthCalibrationSequence()" );
////                            }
////
////                            jep.eval( "sequence.execute()" );
//                            
//                        } catch ( JepException e ) {
//                            
//                            out.println( "A Jep Python Exception occured in running script file" );
//                            //out.println( e.getMessage() + "\n" );
//                            e.printStackTrace( out.printf( "\n" ));
//                        } catch ( Exception e ) {
//                            
//                            out.println( "A Java Exception occurred in running script file" );
//                            out.println( e.getMessage() );
//                        } finally {
//                            
//                            try {
//                                jep.close();
//                                out.println( "\nDONE\n" );
//                            } catch ( JepException e ) {
//                                out.println( e.getMessage() );
//                            }
//                        }
//
//                        return null;
//                }};
//            }};
//        
//        //out.println( "service: " + service );
//        service.start();
//    }

//    @FXML private void handleRun( ActionEvent event ) {
//        
//        try ( Jep jep = new Jep( new JepConfig().setInteractive( true ))) {
//            jep.eval( "s = 'Hello World Test'" );
//            jep.eval( "from java.lang import System" );
//            jep.eval( "System.out.println(s)" );
//            jep.eval( "globals()" );
//            jep.eval( "print(s)" );
//            jep.eval( "print(s[1:-1])" );
//            jep.eval( "print(\n)" );
//
//            jep.runScript( "/home/tcs/swdev/python34/example.py" );
//            
//            //jep.eval( "from java.lang import System" );
//            jep.eval( "import sys" );
//            jep.eval( "sys.path.append('/home/tcs/swdev/python34')");
//            jep.eval( "import fib" );
//
//            jep.eval( "x = fib.fib(2)" );
//            Object result = jep.getValue( "x" );                
//            jep.eval( "print('x is: '); print(x)" );
//            System.out.println( "result: " +  result );
//            //jep.eval( "System.out.println( "result: " +  result )" );
//            jep.eval( "print(\n)" );
//
//            jep.eval( "pyfib = fib.fib" );
//            Object result1 = jep.invoke( "pyfib", 4 );
//            System.out.println( "result1: " +  result1 );
//            jep.eval( "print(\n)" );
//            
//            //jep.invoke( "fib.fib", 16 );
//            Object result2 = jep.invoke( "fib.fib", 16 );
//            jep.set( "pyresult2", result2 );
//            jep.eval( "print(\n)" );
//            System.out.println( "result2: " +  result2 );
//            System.out.println( "pyresult2: " +  jep.getValue( "pyresult2" ));
//            
//        } catch ( Exception e ) {
//            out.println( "Exception in selecting script file" );
//        }
//    }
    
    @FXML private void handleExit( ActionEvent event ) throws Exception {
        
        RunScriptFX.getInstance().closeStage();
    }
}
