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
 * <h2>Start Night Mode</h2>
 *
 * {@code StartNightMode} is a Concrete State class implementation
 */
//public class StartNightMode extends ModeState {
public class StartNightMode implements ModeState {
    
    @Override public String getName() { return "StartNightMode"; }
    //private void Initialize() { super.Name_ = "StartNightMode"; }

    //public StartNightMode() { Initialize(); }

    /*
    public StartNightMode(ModeState modeState) {
        
        Initialize();
        //this.mode_ = modeState.mode_;
        //this.Mode(modeState.Mode());
    }
    */

    /*
    public StartNightMode(Mode mode) {
        
        Initialize();
        //this.mode_ = mode;
        //this.Mode(mode);
    }
    */

    //@Override public void startNight() {
    @Override public void startNight(Entity entity) {
        
        out.println(super.toString() + ".startNight");

        // 1. Sequencer comms...
        // 2. 
    }
    
    //@Override public void endNight() {
    @Override public void endNight(Entity entity) {
        
        out.println(super.toString() + ".endNight");

        // 1. Sequencer comms...
        // 2. 
    }
}
