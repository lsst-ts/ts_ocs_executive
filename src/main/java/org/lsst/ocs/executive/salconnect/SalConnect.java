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

package org.lsst.ocs.executive.salconnect;

import java.util.concurrent.Executors;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.concurrent.Task;

import org.lsst.ocs.executive.salservice.SalService;

/**
 * <h2>SAL Connect</h2>
 * <p>
 * {@code SalConnect} is the Invoker class in the command pattern
 *
 */

public class SalConnect {
    
    private SalService _salService;  // command infc object
    BlockingQueue<SalService> _salServiceQ;
    Task<Void> [] _salServiceTasks;
    int _numTasks;

    public void setSalService( SalService salService ) {
        
        try {
            _salServiceQ.put( salService );
        } catch ( Exception e ) {
            // TODO
        }
    }
    
    public SalConnect( int numTasks ) {
        
        _salServiceQ = new LinkedBlockingQueue<>();
        _salServiceTasks = new Task[numTasks];
        _numTasks = numTasks;
    }
        
    public void connect() { 
        
        ExecutorService es = Executors.newFixedThreadPool( _numTasks );
        int i;
        //for ( int i = 0; i < _numTasks; i++ ) {
        for ( i = 0; i < _numTasks; i++ ) {
            
            _salServiceTasks[i] = new Task<Void>()  {
                
                @Override protected Void call() {
                    while ( !(_salServiceQ.isEmpty()) ) {
                        
                        _salService = _salServiceQ.poll();
                        _salService.execute();
                    }

                    return null;
                }
            };
            
            es.submit( _salServiceTasks[i] );
        }
        
        while ( _salServiceTasks[_numTasks-1].isRunning() ) {
                        
        }
        es.shutdown();
    }
}