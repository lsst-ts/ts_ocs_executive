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
import org.lsst.ocs.executive.salservice.SalService;
import javafx.concurrent.Task;

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

    public void setSalService(SalService salService) {
        
        try {
            _salServiceQ.put(salService);
        } catch (Exception e) {
            // TODO
        }
    }
    
    public SalConnect(int n) {
        
        _salServiceQ = new LinkedBlockingQueue<>();
        _salServiceTasks = new Task[n];
        _numTasks = n;
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
            //Executors.newFixedThreadPool( 1 )
            //         .submit( _salServiceTasks[i] );
        }
        
        while ( _salServiceTasks[_numTasks-1].isRunning() ) {
                        
        }
        es.shutdown();
    }
}



////////////////////////////////////////////////////////////////////////////
//    
//    private SalCmd _salCmd;  // command infc object
//    private SalEvent _salEvent;  // command infc object
//    private SalTelemetry _salTelemetry;  // command infc object
    

//    //public void setSalCmd(SalCmd salCmd, String topicString) { 
//    public void setSalCmd(SalService salService, String topicString) { 
//
//        //this._salCmd = salCmd;
//        //this._salCmd._cmdTopic = topicString;
//        SalCmd salCmd = (SalCmd) salService;
//        salCmd._topic = topicString;
//    }
    
//    //public void setSalEvent(SalEvent salEvent, String topicString) {
//    public void setSalEvent(SalService salService, String topicString) { 
//
//        //this._salEvent = salEvent;
//        //this._salEvent._eventTopic = topicString;
//        SalEvent salEvent = (SalEvent) salService;
//        salEvent._topic = topicString;
//    }

//    //public void setSalTelemetry(SalTelemetry salTelemetry, String topicString) {
//    public void setSalTelemetry(SalService salService, String topicString) { 
//
//        //this._salTelemetry = salTelemetry;
//        //this._salTelemetry._telemetryTopic = topicString;
//        SalTelemetry salTelemetry = (SalTelemetry) salService;
//        salTelemetry._topic = topicString;
//    }

    
//    public void connectCmd() { this._salCmd.execute(); }
//    public void connectEvent() { this._salEvent.listen(); }
//    public void connectTelemetry() { this._salTelemetry.issue(); }

//    public void connectCmd() { this._salService.execute(); }
//    public void connectEvent() { this._salService.listen();}
//    public void connectlEvent() { this._salService.listen();}
//    public void connectTelemetry() { this._salService.trigger();}
