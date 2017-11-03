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
import org.lsst.ocs.executive.salcomponent.*;

/**
 * <h2>[Runnable] Event Task</h2>
 * <p>
 * The {@code rEventTask} class implements the {@link Runnable} interface and 
 * overrides the {@code run()} method defined in it. The {@code rEventTask} 
 * class wraps a SAL event topic and is intended to be run in a 
 * separate {@link Thread}.
 * <p>
 * NOTE: The {@code rEventTask} class does not create a {@link Thread} object,
 * it only defines an entry point for threads. It allows you to pass the 
 * object to the {@link Thread}.
 */
public class rEventTask implements Runnable  {

    private final CommandableSalComponent _csc;
    private final String _event;
    private final String _name = "rEventTask";

    public rEventTask( CommandableSalComponent csc, String event ) {
        
        this._csc = csc;
        this._event = event;
    }

    public String getName() {
        
        return _name;
    }

    @Override
    public void run() {

        Thread.currentThread().setName( getName() );
        out.print( this.getName() + "::"
                                  + Thread.currentThread().getStackTrace()[1]
                                                          .getMethodName()
                                  + "::" 
                                  + this._csc.getName()
                                  + "::"
                                  + this._event 
                                  + "::"
                                  + "Threadid: " 
                                  + Thread.currentThread().getId() + "\n");

        try {
            _csc.getClass()
                 // invoke w/ null args
                .getMethod( this._event, new Class[] {} )
                 // invoke w/ null args
                .invoke( _csc, new Object[] {} );
        } catch ( Exception e ) {
            e.printStackTrace( out.printf( this.getName() + "interrupted" ) );
        }
    }
}
