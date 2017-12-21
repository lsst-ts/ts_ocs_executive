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
import org.lsst.ocs.executive.salcomponent.CommandableSalComponent;
import javafx.concurrent.Task;

/**
 * <h2>[Callable] Event Task</h2>
 * <p>
 * The {@code EventTask} class implements the {@link Task} interface and 
 * overrides the {@code call()} method defined in it. The {@code EventTask} 
 * class wraps a SAL event topic and is intended to be run in a 
 * separate {@link Thread}.
 * <p>
 * NOTE: The {@code EventTask} class does not create a {@link Thread} object,
 * it only defines an entry point for threads. It allows you to pass the 
 * object to the {@link Thread}.
 */

public class EventTask extends Task<Integer> {

    private final CommandableSalComponent _csc;
    private final String _event;
    private String _name;

    public EventTask ( CommandableSalComponent csc, String event ) {

        this._csc = csc;
        this._event = event;
        this._name = "EventTask" +"::" + this._csc.getName();
    }

    public String getName() {
        
        return _name;
    }

    @Override public Integer call() throws Exception {
        
        Thread.currentThread().setName( getName() );
        out.print( this.getName() + "::"
                                  + Thread.currentThread().getStackTrace()[1]
                                                          .getMethodName()
                                  + "::"
                                  + this._event 
                                  + "::"
                                  + "Threadid: " 
                                  + Thread.currentThread().getId() + "\n");

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();

        try {

            status = (Integer) _csc.getClass()
                                   // specify method & that it takes 1 Integer type arg
                                   //.getMethod( this._event, Integer.class ) 
                                   // invoke w/ specific Integer arg
                                   //.invoke( _csc, i ); 

                                   // specify method & that it takes no (i.e. null) args
                                   .getMethod( this._event, new Class[] {} )
                                   // invoke w/ null args
                                   .invoke( _csc, new Object[] {} );
        } catch ( Exception e ) {

            e.printStackTrace( out.printf( this.getName() + "interrupted from cEventTask3:call()" ) );
        }

        return status;
    }
}
