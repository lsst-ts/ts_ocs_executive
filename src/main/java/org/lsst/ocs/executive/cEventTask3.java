/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lsst.ocs.executive;

import static java.lang.System.out;
import org.lsst.ocs.executive.salcomponent.CommandableSalComponent;
import javafx.concurrent.Task;


public class cEventTask3 extends Task<Integer> {

    private final CommandableSalComponent _csc;
    private final String _event;
    private final String _name = "cEventTask3";

    public cEventTask3 ( CommandableSalComponent csc, String event ) {

        this._csc = csc;
        this._event = event;
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
                                  + this._csc.getName()
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
                // specify method & that it takes no (i.e. null) args
                .getMethod( this._event, new Class[] {} )
                // invoke w/ specific Integer arg
                //.invoke( _csc, i ); 
                // invoke w/ null args
                .invoke( _csc, new Object[] {} );
        } catch ( Exception e ) {

            e.printStackTrace( out.printf( this.getName() + "interrupted from cEventTask3:call()" ) );
        }

        return status;
    }
}
