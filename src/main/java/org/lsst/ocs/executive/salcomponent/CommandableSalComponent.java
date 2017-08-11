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

package org.lsst.ocs.executive.salcomponent;

import static java.lang.System.out;
import org.lsst.ocs.executive.DomainObject;

/**
 *
 * CommandableSalComponent is the Receiver base class in the command pattern
 *
 */

public abstract class CommandableSalComponent  implements DomainObject {
    
    // SAL middle-ware default Commands
    public abstract void enterControl();
    public abstract void start();
    public abstract void enable();
    public abstract void disable();
    public abstract void standby();
    public abstract void exitControl();
    
    // SAL middle-ware default Events
    public abstract void settingsVersion();
    public abstract void appliedSettingsMatchStartTest();
    public void summaryState() { out.println("SalEvent summaryState() error"); };
    public void summaryState(int summaryStateValue) { out.println("SalEvent summaryState(int) error"); }
    public void errorCode() { out.println("SalEvent errorCode() error"); }
}
