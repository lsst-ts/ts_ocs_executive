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
 *
 * Mode is a Context class implementation
 *
 */

//public class Mode extends DomainObject {
public class Mode implements DomainObject {
    
    @Override public String getName() {
        return "Entity->" + entity_.etype_.toString() +
                     "->" + entity_.observingMode_.toString();
    }

    private Entity entity_;
    public Entity Entity() { return this.entity_; }
    public void Entity(Entity entity) { this.entity_  = entity; }
    
    private ModeState modeState_; //package-private member
    public ModeState ModeState() { return this.modeState_; }
    public void ModeState(ModeState modeState) { this.modeState_ = modeState; }
    
    public Mode(Entity entity) { 

        entity_ = entity;
        modeState_ = new StartNightMode();
        //modeState_ = new StartNightMode(this);

        /*
        this.Name_ = entity.toString() + "." +
                entity.observingMode_.toString();
        */

    }

    public void startNight() { entity_.Mode().ModeState().startNight(entity_); }
    //public void startNight() { modeState_.startNight(entity_); }
    //public void startNight() { modeState_.startNight(); }
    public void endNight() { entity_.Mode().ModeState().endNight(entity_); }
    //public void endNight() { modeState_.endNight(entity_); }
    //public void endNight() { modeState_.endNight(); }

    public void scienceNight() { modeState_.scienceNight(); }

}
