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
 * <h2>Fault Entity State</h2>
 * <p>
 * {@code FaultState} is a Concrete State class implementation.
 */
public class FaultState implements EntityState {

  @Override
  public String getName() {
    return "FaultState";
  }

  @Override
  public void exitControl( Entity entity ) {

    String salactor = entity.getClass()
        .getSimpleName() + "."
                      + entity.getCSC().getClass().getSimpleName()
                      + "."
                      + this.getName()
                      + ".exitControl";

    out.println( salactor + ": " + Thread.currentThread().getId() );

    if ( EntityType.OCS.toString().equalsIgnoreCase( salactor ) ) {

      // 1. Publish SummaryState->FaultState if not previously pub'd
      //    a. Publish Topic->ErrorCode
      // 2. May read sensor data but control features disallowed
      // 3. Cmd entity from FaultState to OfflineState
      entity.setState( new OfflineState() );
    }
  }
}
