/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lsst.ocs.executive;

import static java.lang.System.out;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.lsst.ocs.executive.salcomponent.CommandableSalComponent;
import javafx.concurrent.Task;

public class cEventTask2 extends Task<Future> {

    private final CommandableSalComponent _csc;
    private final String _event;
    private final String _name = "cEventTask2";

    public cEventTask2 ( CommandableSalComponent csc, String event ) {

        this._csc = csc;
        this._event = event;
    }

    public String getName() {
        
        return _name;
    }

    @Override
    public Future call() throws Exception {
        
        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();

        CompletableFuture<Integer> completableFuture;
        
        completableFuture = CompletableFuture.supplyAsync( () -> {
            
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
                    // specify method & that it takes 1 Integer type arg
                    .getMethod( this._event, Integer.class ) 
                    // invoke w/ specific Integer arg
                    .invoke( _csc, status ); 
            } catch ( Exception e ) {

                e.printStackTrace( out.printf( this.getName() + "interrupted" ) );
            }
          
            return status;
        }); //end supplyAsync

        completableFuture.get();

        return completableFuture;
    } //end call
}
