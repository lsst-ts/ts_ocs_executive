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
 * FaultState is a Concrete State class implementation
 *
 */

public class FaultState implements EntityState {

    @Override public String getName() { return "FaultState"; }
    
    @Override public void exitControl(Entity entity) {
        
        out.println(entity.etype_.toString() + "." + this.getName() + ".fault");

        if ( EntityType.OCS.equals(entity.etype_) ) {
            
            // 1. Publish SummaryState->FaultState if not previously pub'd
            //    a. Publish Topic->ErrorCode
            // 2. May read sensor data but control features disallowed
            // 3. Cmd entity from FaultState to OfflineState
            entity.setState(new OfflineState());
        }
    }
    
}
