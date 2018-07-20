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
 * <h2>Mode State</h2>
 *
 * {@code ModeState} is an abstract state base class
 */
public interface ModeState extends DomainObject {
    
    default public void startNight( Entity entity ) { out.println("error"); }
    default public void endNight( Entity entity )   { out.println("error"); }
    //default public void startNight() { out.println("error"); }
    //default public void endNight()   { out.println("error"); }
    
    default public void scienceNight()     { out.println("error"); }
    default public void engineeringNight() { out.println("error"); }
    default public void maintenanceNight() { out.println("error"); }
    default public void calibrationNight() { out.println("error"); }

/*
    public void scienceDay()     { out.println("error"); }
    public void engineeringDay() { out.println("error"); }
    public void maintenanceDay() { out.println("error"); }
    public void calibrationDay() { out.println("error"); }
 */
    
}
