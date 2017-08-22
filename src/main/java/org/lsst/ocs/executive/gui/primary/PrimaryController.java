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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import org.lsst.ocs.executive.ExecutiveFX;
import org.lsst.ocs.executive.salconnect.SalConnect;
import org.lsst.ocs.executive.salservice.SalCmd;

/**
 * <h2>FXML Primary Controller</h2>
 * <p>
 * The controller class with <i>primaryFXML.fxml</i> document (via
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

    private ExecutiveFX execFX;
    
    @FXML
    private BorderPane guiPrimaryScene;
    
    @FXML
    private MenuItem menuitemCreateTCS;

    @FXML
    private void createTCS(ActionEvent event) {
        
        execFX.getCscAList().add( 
            execFX.getCscList().get( 0 ) );
        
        execFX.getCscAList().get( 0 ).enterControl();
        
        // 1. SalComponent (Receiver) previously defined: Executive.cscTCS
        // 2. Define Concrete SalService (Cmd) for specific SalComponent (Rcr)
        //    Also, assign topic & topic arguments
        SalCmd salCmdTcs = new SalCmd( execFX.getCscAList().get( 0 ) );
        salCmdTcs.setTopic( "enterControl" );
        // 3. Define Invoker & set SalService request
        SalConnect salConnectTcs = new SalConnect();
        salConnectTcs.setSalService( salCmdTcs );
        //salConnectCamera.setSalCmd(new SalCmd(rCCS), "enterControl");
        // 4. Invoker indirectly calls cmd->execute()
        salConnectTcs.connect();
            
    }
    
    /**
     * Is called by the main FX application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setExecFXApp(ExecutiveFX refExecFX) {

        this.execFX = refExecFX;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize( URL url, ResourceBundle rb ) {
        // TODO
    }

}
