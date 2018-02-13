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
 * EntityState is an abstract state base class
 *
 */

public interface EntityState extends DomainObject {

    // cmd entity from [OfflineState,OfflineState[AvailableState]] to StandbyState
    // external entity usage
    default public void enterControl( Entity entity ) { out.println("state transition error"); } 
    
    // cmd entity from StandbyState to [OfflineState,OfflineState[PublishOnlyState]]
    // external entity usage
    default public void exitControl( Entity entity ) { out.println("state transition error"); }
    
    // Entity is in StandbyState & ready for start trigger & transitions to DisabledState
    // external entity usage
    default public void start( Entity entity ) { out.println("state transition error"); };
    
    // cmd entity from DisabledState to StandbyState
    default public void standby( Entity entity ) { out.println("state transition error"); }
    
    // cmd entity from DisabledState to EnabledState
    // external entity usage??
    default public void enable( Entity entity ) { out.println("state transition error"); }
    
    // cmd entity from EnabledState to DisabledState
    // external entity usage??
    default public void disable( Entity entity ) { out.println("state transition error"); }
    
    // cmd OCS entity from [StandbyState,EnabledState,DisabledState] to FaultState
    default public void fault( Entity entity ) { out.println("state transition error"); }
}
