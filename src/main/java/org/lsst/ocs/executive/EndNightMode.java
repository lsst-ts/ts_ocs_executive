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
 * <h2>End Night Mode</h2>
 *
 * {@code EndNightMode} is a Concrete State class implementation
 */
public class EndNightMode implements ModeState {
    
    @Override public String getName() { return "EndNightMode"; }
    //private void Initialize() { super._Name = "EndNightMode"; }
    
    //public EndNightMode() { Initialize(); }

    /*
    public EndNightMode( ModeState modeState ) {
        
        Initialize();
        //this._mode = modeState._mode;
        this.Mode( modeState.Mode() );
    }
    */

    /*
    public EndNightMode( Mode mode ) {
        
        Initialize();
        //this._mode = mode;
        this.Mode( mode );
    }
    */

    //@Override public void startNight() {
    @Override public void startNight( Entity entity ) {
        
        out.println( super.toString() + ".startNight" );

        // 1. Sequencer comms...
        // 2. 
    }
    
    //@Override public void endNight() {
    @Override public void endNight( Entity entity ) {
        
        out.println( super.toString() + ".endNight" );

        // 1. Sequencer comms...
        // 2. 
    }
}
