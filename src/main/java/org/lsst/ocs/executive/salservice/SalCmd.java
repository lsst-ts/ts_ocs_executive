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
 * <h2>SAL Command</h2>
 * <p>
 * {@code SalCmd} is a Concrete Command class in the command pattern
 *
 */

public class SalCmd extends SalService implements DomainObject {

    @Override public String getName() { return "SalCmd"; }
    
    // Receiver (e.g. SalCamera)
    CommandableSalComponent _salComponent;
    
    public SalCmd( CommandableSalComponent salComponent ) { 
    
        this._salComponent = salComponent;
        //super._topicArgs = new Object[]{};
    }
    
    @Override public void execute() { 

        out.print( this.getName() + "::" + 
                   super._topic + "::" +
                   this._salComponent + "::" +
                   Thread.currentThread().getStackTrace()[1].getMethodName() + "::" +
                   "Threadid: " + 
                   Thread.currentThread().getId() + "\n" );
        
        // receiver.action() (e.g. cscTcs.enterControl())
        try {
            _salComponent.getClass()
                         .getMethod( super._topic, new Class[]{} ) // method w/ null args
                         .invoke( _salComponent, new Object[]{} ); // invoke w/ null args
        }
        catch ( Exception e ) {
            e.printStackTrace( out.printf( this.getName() + "interrupted" ) );
        }
    }
}