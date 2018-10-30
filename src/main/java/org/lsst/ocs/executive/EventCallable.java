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

import org.lsst.ocs.executive.salcomponent.CommandableSalComponent;

import static java.lang.System.out;
import java.util.concurrent.Callable;

/**
 * <h2>Event Callable</h2>
 * <p>
 * The {@code EventCallable} class implements the {@link Callable} interface and
 * overrides the {@code call()} method defined in it. The {@code EventCallable}
 * class wraps a SAL event topic and is intended to be run in a
 * separate {@link Thread}.
 * <p>
 * NOTE: The {@code EventCallable} class does not create a {@link Thread} object,
 * it only defines an entry point for threads. It allows you to pass the
 * object to the {@link Thread}.
 */
public class EventCallable implements Callable<Integer> {

  /*
   * Command Pattern: Receiver (e.g. SalCamera)
   */
  private final CommandableSalComponent _csc;

  private final String _event;

  public EventCallable( CommandableSalComponent csc, String event ) {

    this._csc = csc;
    this._event = event;
  }

  public String getName() {

    return "EventCallable" + "::" + this._csc.getName() + "::" + this._event;
  }

  @Override
  public Integer call() {

    Thread.currentThread().setName( getName() );
    out.println( this.getName() + "::"
                 + Thread.currentThread().getStackTrace()[1].getMethodName()
                 + "::"
                 + "Threadid: "
                 + Thread.currentThread().getId() );

    int status = CommandableSalComponent.CSC_STATUS.SAL__OK.getValue();

    try {
      /*
       * Command Pattern: receiver.action() [e.g. cscTcs.enterControl()]
       */
      status = (Integer) _csc.getClass()
          /*
           * specify method & that it takes no (i.e. null) args
           */
          .getMethod( this._event, new Class<?>[]{} )
          /*
           * invoke w/ null args
           */
          .invoke( _csc, new Object[]{} );
    } catch ( Exception e ) {
      e.printStackTrace(
          out.printf( this.getName() + "interrupted from EventCallable.call()" ) );
    }

    return status;
  }
}
