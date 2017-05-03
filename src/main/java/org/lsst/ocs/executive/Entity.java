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
 *
 * You should have received a copy of the LSST License Statement and 
 * the GNU General Public License along with this program.  If not, 
 * see <http://www.lsstcorp.org/LegalNotices/>.
 * 
 */

package org.lsst.ocs.executive;

/**
 *
 * Entity is a Context class implementation
 *
 */

//public class Entity extends DomainObject {
public class Entity implements DomainObject {
    
    @Override public String getName() {return "Entity->" + this.etype_.toString(); }
    
    public Entity Entity() { return this; }
    
    /** The state/etype of Commandable Entity **/
    protected EntityType etype_;
    private EntityState state_;
    public EntityState EntityState() { return this.state_; }
    public void EntityState(EntityState state) { this.state_ = state; }
    
    protected ObservingMode observingMode_;
    private Mode mode_;
    public Mode Mode() { return this.mode_; }
    public void Mode(Mode mode) { this.mode_ = mode; }
    
    public Entity(EntityType etype) { 

        // Starting up & initial transition to OfflineState
        this.etype_ = etype;
        this.state_ = new OfflineState();
        
        //this.mode_ = new Mode(this); // mode_.modeState_ set in Mode Cstr
        /* this.Name_ = "Entity->" + this.etype_.toString(); */

        // Can start here or in Main
        // this.state_.start();
        
        // Publish SummaryState of 'StandbyState' if OCS
    }

    public Entity(EntityType etype, ObservingMode observingMode) { 

        // Starting up & initial transition to OfflineState
        this(etype);

        this.observingMode_ = observingMode;
        this.mode_ = new Mode(this);
        this.mode_.ModeState(new StartNightMode());
        
        /* this.Name_ = "Entity->" + this.etype_.toString(); */

        // Publish SummaryState of 'StandbyState' if OCS
    }

    // The initial configuration setting for the device. 
    //_configurationState = new ProductionConfigurationState(this);

    public String getEntityType() { return etype_.toString(); }
    public EntityState getState() { return this.state_; }
    public void setState(EntityState state) { this.state_ = state; }

    // Delegate to the entity state object & pass the this ptr
    public void enterControl()  { this.state_.enterControl(this); }
    public void exitControl()   { this.state_.exitControl(this); }
    public void start()         { this.state_.start(this); }
    public void standby()       { this.state_.standby(this); }
    public void enable()        { this.state_.enable(this); }
    public void disable()       { this.state_.disable(this); }

    public String getObservingMode() { return observingMode_.toString(); }
    public Mode getMode() { return this.mode_; }
    public void setMode(Mode mode) { this.mode_ = mode; }

    // Delegate to the mode state object & pass the this ptr
    public void startNight() { this.mode_.startNight(); }
    public void endNight()   { this.mode_.endNight(); }

    //public void startDay()   { out.println("error"); }
    //public void endDay()     { out.println("error"); }
    
}
