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
import java.util.concurrent.Callable;
import org.lsst.ocs.executive.salcomponent.*;
import org.lsst.ocs.executive.salcomponent.CommandableSalComponent.*;

/**
 * <h2>[Callable] Event Task</h2>
 * <p>
 * The {@code cEventTask} class implements the {@link Callable} interface and 
 * overrides the {@code call()} method defined in it. The {@code cEventTask} 
 * class wraps a SAL event topic and is intended to be run in a 
 * separate {@link Thread}.
 * <p>
 * NOTE: The {@code cEventTask} class does not create a {@link Thread} object,
 * it only defines an entry point for threads. It allows you to pass the 
 * object to the {@link Thread}.
 */
//public class cEventTask implements Callable<CommandableSalComponent> {
public class cEventTask implements Callable<Integer> {

    private final CommandableSalComponent _csc;
    private final String _event;
    private final String _name;

    public cEventTask(CommandableSalComponent csc, String event) {

        this._csc = csc;
        this._event = event;
        this._name = "cEventTask";
    }

    public String getName() {
        
        return _name;
    }

    @Override
    //public CommandableSalComponent call() {
    public Integer call() {
        
        Thread.currentThread().setName("cEventTaskThread");
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
        
        int status = CSC_STATUS.SAL__OK.getValue();
        
        try {
            
            status = ( Integer ) _csc.getClass()
                .getMethod( this._event, new Class[] {} ) // invoke w/ null args
                .invoke( _csc, new Object[] {} ); // invoke w/ null args
        } catch ( Exception e ) {
            
            e.printStackTrace( out.printf( this.getName() + "interrupted" ) );
        }

        //return _csc;
        return status;
    }
}