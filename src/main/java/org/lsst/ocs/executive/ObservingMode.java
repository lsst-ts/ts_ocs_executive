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

/**
 * <h2>Observing Mode</h2>
 *
 * {@code ObservingMode} is a is a type-safe constant type class
 */
public final class ObservingMode {
    
    private final String _obsMode;
    
    // private constructor
    private ObservingMode( final String obsMode ) { this._obsMode = obsMode; }
    
    @Override public String toString() { return this._obsMode; }
    
    public static final ObservingMode SCI         = new ObservingMode( "SCI"     );
    public static final ObservingMode Science     = new ObservingMode( "Science" );
    public static final ObservingMode SCIENCE     = new ObservingMode( "SCIENCE" );

    public static final ObservingMode ENG         = new ObservingMode( "ENG"         );
    public static final ObservingMode Engineering = new ObservingMode( "Engineering" );
    public static final ObservingMode ENGINEERING = new ObservingMode( "ENGINEERING" );

    public static final ObservingMode MNT         = new ObservingMode( "MNT"         );
    public static final ObservingMode Maintenance = new ObservingMode( "Maintenance" );
    public static final ObservingMode MAINTENANCE = new ObservingMode( "MAINTENANCE" );

    public static final ObservingMode CAL         = new ObservingMode( "CAL"         );
    public static final ObservingMode Calibration = new ObservingMode( "Calibration" );
    public static final ObservingMode CALIBRATION = new ObservingMode( "CALIBRATION" );
}

