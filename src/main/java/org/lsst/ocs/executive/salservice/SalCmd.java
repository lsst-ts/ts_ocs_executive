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

package org.lsst.ocs.executive.salservice;

import static java.lang.System.out;
import org.lsst.ocs.executive.DomainObject;
import org.lsst.ocs.executive.salcomponent.CommandableSalComponent;

/**
 *
 * SalCmd is a Concrete Command class in the command pattern
 *
 */

public class SalCmd extends SalService implements DomainObject {

    // Receiver (e.g. SalCamera)
    CommandableSalComponent _salComponent;
    
    public SalCmd(CommandableSalComponent salComponent) { this._salComponent = salComponent; }
    
//    public void setCSC(CommandableSalComponent salComponent) {
//        this._salComponent = salComponent;
//    }
            
    @Override public void execute() { 
        
        // receiver.action() (e.g. SalCamera.enterControl())
        try {
            _salComponent.getClass()
                         .getMethod(super._topic, new Class[]{}) // method w/ null args
                         .invoke(_salComponent, super._topicArgs);
                         //.invoke(_salComponent, new Object[]{}); // invoke w/ null args
        }
        catch (Exception e) {
            e.printStackTrace(out.printf(this.getName() + "interrupted"));
        }
    }
}

//public class SalCmd implements SalService {
//
//    // Receiver (e.g. SalCamera)
//    CommandableSalComponent _salComponent;
//    
//    // Cmd (e.g. enterControl)
//    public String _topic;
//    
//    // Topic Arg list
//    public List<String> _topicArgs = null;
//    
//    public SalCmd(CommandableSalComponent salComponent) { this._salComponent = salComponent; }
//    
//    @Override public void execute() { 
//        
//        // receiver.action() (e.g. SalCamera.enterControl())
//        try {
//            _salComponent.getClass()
//                         .getMethod(this._topic, new Class[]{}) // method w/ null args
//                         .invoke(_salComponent, _topicArgs);
//                         //.invoke(_salComponent, new Object[]{}); // invoke w/ null args
//        }
//        catch (Exception e) {
//            e.printStackTrace(out.printf(this.getName() + "interrupted"));
//        }
//    }
//}


