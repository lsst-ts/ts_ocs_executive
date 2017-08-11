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

import org.lsst.ocs.executive.salservice.SalService;

/**
 *
 * SalConnect is the Invoker class in the command pattern
 *
 */

public class SalConnect {
    
    private SalService _salService;  // command infc object
    
    public void setSalService(SalService salService) {
        
        this._salService = salService;
    }
    
    public void connect() { this._salService.execute(); }
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
