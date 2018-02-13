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
    
    private Entity _entity;
    public Entity Entity() { return this._entity; }
    public void Entity(Entity entity) { this._entity  = entity; }
    
    private ModeState _modeState; //package-private member
    public ModeState ModeState() { return this._modeState; }
    public void ModeState( ModeState modeState ) { this._modeState = modeState; }
    
    public Mode( Entity entity ) { 

        _entity = entity;
        _modeState = new StartNightMode();
        //_modeState = new StartNightMode(this);

        /*
        this.Name_ = entity.toString() + "." +
                entity.observingMode_.toString();
        */

    }

    @Override public String getName() {
        return "Entity->" + _entity.getEntityType() 
                          + "->" 
                          + _entity.getObservingMode();
    }

    public void startNight() { _entity.getMode().ModeState().startNight(_entity); }
    //public void startNight() { _entity.Mode().ModeState().startNight(_entity); }
    //public void startNight() { _modeState.startNight(_entity); }
    //public void startNight() { _modeState.startNight(); }
    
    public void endNight() { _entity.getMode().ModeState().endNight(_entity); }
    //public void endNight() { _entity.Mode().ModeState().endNight(_entity); }
    //public void endNight() { _modeState.endNight(_entity); }
    //public void endNight() { _modeState.endNight(); }

    public void scienceNight() { _modeState.scienceNight(); }

}
