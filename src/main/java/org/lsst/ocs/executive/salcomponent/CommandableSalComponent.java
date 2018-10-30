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
package org.lsst.ocs.executive.salcomponent;

import org.lsst.ocs.executive.DomainObject;

import static java.lang.System.out;
import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Commandable Software Access Layer (SAL) Component [CSC]</h2>
 * <p>
 * {@code CommandableSalComponent} is the Receiver base class in the command pattern
 */
public interface CommandableSalComponent extends DomainObject {

  //////////////////////////////////////////////////////
  // SAL middle-ware default Commands
  //////////////////////////////////////////////////////
  default public void enterControl( Object[] args ) {
    out.println( "CSC cmd error: enterControl" );
  }

  default public void start( Object[] args ) {
    out.println( "CSC cmd error: start" );
  }

  default public void enable( Object[] args ) {
    out.println( "CSC cmd error: enable" );
  }

  default public void disable( Object[] args ) {
    out.println( "CSC cmd error: disable" );
  }

  default public void standby( Object[] args ) {
    out.println( "CSC cmd error: standby" );
  }

  default public void exitControl( Object[] args ) {
    out.println( "CSC cmd error: exitControl" );
  }

  //////////////////////////////////////////////////////
  // SAL middle-ware default Events
  //////////////////////////////////////////////////////
  default public void settingsVersion() {
    out.println( "CSC event error: settingsVersion" );
  }

  default public void appliedSettingsMatchStart() {
    out.println( "CSC event error: appliedSettingsMatchStart" );
  }

  default public Integer summaryState() {
    out.println( "CSC event error: summaryState" );
    return -1;
  }
  //default public void summaryState() { out.println("CSC event error: summaryState"); }
  //public void errorCode() { out.println("SalEvent errorCode() error"); }

  /**
   * **************************************************
   */
  public enum CSC_STATE {

    DISABLED( "DISABLED", 1 ),
    ENABLED( "ENABLED", 2 ),
    FAULT( "FAULT", 3 ),
    OFFLINE( "OFFLINE", 4 ),
    STANDBY( "STANDBY", 5 );

    private final String _cscStateString;
    private final int _cscStateValue;

    /*
     * private constructor
     */
    private CSC_STATE( String cscStateString, int cscStateValue ) {

      this._cscStateString = cscStateString;
      this._cscStateValue = cscStateValue;
    }

    @Override
    public String toString() {
      return this._cscStateString;
    }

    public int toValue() {
      return this._cscStateValue;
    }
  }

  public enum CSC_STATE_CMD {

    enterControl( "enterControl", CSC_STATE.STANDBY ), // 5 
    start( "start", CSC_STATE.DISABLED ), // 1
    enable( "enable", CSC_STATE.ENABLED ), // 2
    disable( "disable", CSC_STATE.DISABLED ), // 1
    standby( "standby", CSC_STATE.STANDBY ), // 5
    exitControl( "exitControl", CSC_STATE.OFFLINE ); // 4

    private final String _cscStateCmd;
    private final CSC_STATE _cscState;

    /*
     * private constructor
     */
    private CSC_STATE_CMD( String cscStateCmd, CSC_STATE cscState ) {

      this._cscStateCmd = cscStateCmd;
      this._cscState = cscState;
    }

    @Override
    public String toString() {
      return this._cscStateCmd;
    }

    public int toValue() {
      return this._cscState.toValue();
    }

    public String toValueString() {
      return this._cscState.toString();
    }
  }

  public enum CSC_STATUS {

    SAL__SLOWPOLL( 1 ),
    SAL__LOG_ROUTINES( 1 ),
    SAL__OK( 0 ),
    SAL__ERR( -1 ),
    SAL__ERROR( -1 ),
    SAL__NO_UPDATES( -100 ),
    SAL__CMD_ACK( 300 ),
    SAL__CMD_INPROGRESS( 301 ),
    SAL__CMD_STALLED( 302 ),
    SAL__CMD_COMPLETE( 303 ),
    SAL__CMD_NOPERM( -300 ),
    SAL__CMD_NOACK( -301 ),
    SAL__CMD_FAILED( -302 ),
    SAL__CMD_ABORTED( -303 ),
    SAL__CMD_TIMEOUT( -304 ),
    SAL__DATA_AVAIL( 400 ),
    SAL__DEADLINE_MISS( 401 ),
    SAL__INCOMPAT_QOS( 402 ),
    SAL__SAMPLE_REJ( 403 ),
    SAL__LIVELINESS_CHG( 404 ),
    SAL__SAMPLELOST( 405 ),
    SAL__SUBSCR_MATCH( 406 ),
    SAL__STANDBYSTATE( 500 ),
    SAL__DISABLEDSTATE( 510 ),
    SAL__ENABLEDSTATE( 520 ),
    SAL__OFFLINESTATE( 530 ),
    SAL__PUBLISHONLYSTATE( 531 ),
    SAL__AVAILABLESTATE( 532 ),
    SAL__FAULTSTATE( 540 );

    ////////////////////////////////////////////////
    // ENUM -> VALUE CONVERSION
    ////////////////////////////////////////////////
    private final Integer statusValue;

    private CSC_STATUS( Integer value ) {
      this.statusValue = value;
    }

    public int getValue() {
      return statusValue;
    }

    ////////////////////////////////////////////////
    // VALUE -> ENUM CONVERSION
    ////////////////////////////////////////////////
    private static final Map<Integer, CSC_STATUS> statusValues = new HashMap<>();

    static {

      for ( CSC_STATUS status : CSC_STATUS.values() ) {
        statusValues.put( status.statusValue, status );
      }
    }

    public static CSC_STATUS valueOf( Integer status ) {

      return statusValues.get( status );
    }
  }

}
