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
 * <h2>[Runnable] Command Task</h2>
 * <p>
 * The {@code rCmdTask} class implements the {@link Runnable} interface and 
 * overrides the {@code run()} method defined in it. The {@code rCmdTask} 
 * class wraps a SAL command topic and is intended to be run in a 
 * separate {@link Thread}.
 * <p>
 * NOTE: The {@code rCmdTask} class does not create a {@link Thread} object,
 * it only defines an entry point for threads. It allows you to pass the 
 * object to the {@link Thread}.
 */
public class rCmdTask implements Runnable {

    private final CommandableSalComponent _csc;
    private final String _cmd;
    private final String _name;

    public rCmdTask( CommandableSalComponent csc, String cmd ) {
        
        this._csc = csc;
        this._cmd = cmd;
        this._name = "rCmdTask";
    }

    public String getName() {
        
        return _name;
    }

    @Override
    public void run() {

        Thread.currentThread().setName("rCmdTaskThread");
        out.print( this.getName() + "::"
                                  + Thread.currentThread().getStackTrace()[1]
                                                          .getMethodName()
                                  + "::" 
                                  + this._csc.getName()
                                  + "::"
                                  + this._cmd 
                                  + "::"
                                  + "Threadid: " 
                                  + Thread.currentThread().getId() + "\n");

        try {
            _csc.getClass()
                .getMethod( this._cmd, new Class[] {} ) // invoke w/ null args
                .invoke( this._csc, new Object[] {} ); // invoke w/ null args
        } catch ( Exception e ) {
            e.printStackTrace( out.printf( this.getName() + "interrupted" ) );
        }
    }
}