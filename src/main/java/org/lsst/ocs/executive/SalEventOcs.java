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

import org.lsst.sal.SAL_ocs;

/**
 *
 * SalEventOcs is OCS Executive's interface to SAL middle-ware for OCS events
 *
 */

public class SalEventOcs implements SalEvent {
    
    @Override public String getName() { return "SalEventOcs"; }    
    
    @Override public void summaryState() { 
    
        SAL_ocs evt = new SAL_ocs();
        evt.salEvent("ocs_logevent_SummaryState");

        ocs.logevent_SummaryState event = new ocs.logevent_SummaryState();
        event.private_revCode = "LSST TEST EVENT";
        event.SummaryStateValue = 1;
        event.priority = 1;

        // Issue Event
        int priority = 1;
        //int status = evt.logEvent_SummaryState(event, priority);
        evt.logEvent_SummaryState(event, priority);

        try { Thread.sleep(250); } catch (InterruptedException e) { e.printStackTrace(); }

        /* Remove the DataWriters etc */
        evt.salShutdown();
    }
    
    @Override public void settingsVersion() {

        SAL_ocs mgr = new SAL_ocs();
        mgr.salEvent("ocs_logevent_SettingVersions");

        ocs.logevent_SettingVersions event = new ocs.logevent_SettingVersions();
        event.private_revCode = "LSST TEST EVENT";
        event.recommendedSettingVersion = "testing";
        event.priority = 1;

        // Issue Event
        int priority = 1;
        //int status = mgr.logEvent_SettingVersions(event, priority);
        mgr.logEvent_SettingVersions(event, priority);

	try { Thread.sleep(250); } catch (InterruptedException e) { e.printStackTrace(); }

        /* Remove the DataWriters etc */
        mgr.salShutdown();
    }
    
    @Override public void appliedSettingsMatchStartTest() {
    
        SAL_ocs mgr = new SAL_ocs();
        mgr.salEvent("ocs_logevent_AppliedSettingsMatchStart");

        ocs.logevent_AppliedSettingsMatchStart event = new ocs.logevent_AppliedSettingsMatchStart();
        event.private_revCode = "LSST TEST EVENT";
        event.appliedSettingsMatchStartIsTrue = true;
        event.priority = 1;

        // Issue Event
        int priority = 1;
        //int status = mgr.logEvent_AppliedSettingsMatchStart(event, priority);
        mgr.logEvent_AppliedSettingsMatchStart(event, priority);

	try {Thread.sleep(250);} catch (InterruptedException e) { e.printStackTrace(); }

        /* Remove the DataWriters etc */
        mgr.salShutdown();
    }
    
    @Override public void errorCode() {
    
        SAL_ocs mgr = new SAL_ocs();
        mgr.salEvent("ocs_logevent_ErrorCode");

        ocs.logevent_ErrorCode event = new ocs.logevent_ErrorCode();
        event.private_revCode = "LSST TEST EVENT";
        event.errorCode = 1;
        event.priority = 1;

        // Issue Event
        int priority = 1;
        //int status = mgr.logEvent_ErrorCode(event, priority);
        mgr.logEvent_ErrorCode(event, priority);

	try {Thread.sleep(250);} catch (InterruptedException e) { e.printStackTrace(); }

        /* Remove the DataWriters etc */
        mgr.salShutdown();
    }
    
}
