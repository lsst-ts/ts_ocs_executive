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

import static java.lang.System.out;

/**
 *
 * OfflineState is a Concrete State class implementation
 *
 */

public class OfflineState implements EntityState {

    @Override public String getName() { return "OfflineState"; }
    
    @Override public void enterControl(Entity entity) {
        
        // Cmd Sequencer, TCS, CCS or DMCS via SAL
        // Send msg
        String salactor = entity.etype_.toString();
        out.println(salactor + "." + this.getName() + ".enterControl");
        SalCmd salCmd = new SalCmd(salactor);
        salCmd.enterControl();

        if ( EntityType.OCS.toString().equalsIgnoreCase(salactor) ) {
            
            // Publish SummaryState->OfflineState if not previously pub'd
        }

        // Cmd local entity state from OfflineState[AvailableState] to StandbyState
        entity.setState(new StandbyState());
    }

}
