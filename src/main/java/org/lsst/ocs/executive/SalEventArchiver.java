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
import org.lsst.sal.SAL_archiver;

/**
 *
 * SalEventArchiver is OCS Executive's interface to SAL middle-ware 
 * for DM Archiver events
 *
 */

public class SalEventArchiver implements SalEvent {
    
    @Override public String getName() { return "SalEventArchiver"; }    
    
    @Override public void summaryState() {
    
        // Initialize
        SAL_archiver evt = new SAL_archiver();
        evt.salEvent("archiver_logevent_SummaryState");

        archiver.logevent_SummaryState event = new archiver.logevent_SummaryState();
        out.println("Archiver Event SummaryState logger ready ");

        int status;
        //boolean finished = false;
        //while (!finished) {
        while (Boolean.TRUE) {
            status = evt.getEvent_SummaryState(event);
            if (status == SAL_archiver.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }

            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        evt.salShutdown();
    }
    
    @Override public void settingsVersion() {
    
        // Initialize
        SAL_archiver evt = new SAL_archiver();
        evt.salEvent("archiver_logevent_SettingVersions");
        
        archiver.logevent_SettingVersions event = new archiver.logevent_SettingVersions();
        out.println("Archiver Event SettingVersions logger ready ");

        int status;
        //boolean finished = false;
        //while (!finished) {
        while (Boolean.TRUE) {
            status = evt.getEvent_SettingVersions(event);
            if (status == SAL_archiver.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
        evt.salShutdown();
    }
    
    @Override public void appliedSettingsMatchStartTest() {
    
        // Initialize
        SAL_archiver evt = new SAL_archiver();
        evt.salEvent("archiver_logevent_AppliedSettingsMatchStart");
        
        archiver.logevent_AppliedSettingsMatchStart event = new archiver.logevent_AppliedSettingsMatchStart();
        out.println("Archiver Event AppliedSettingsMatchStart logger ready ");

        int status;
        //boolean finished = false;
        //while (!finished) {
        while (Boolean.TRUE) {
            status = evt.getEvent_AppliedSettingsMatchStart(event);
            if (status == SAL_archiver.SAL__OK) {
                out.println("=== Event Logged : " + event);
            }
            
            try {Thread.sleep(100);} catch (InterruptedException e) { e.printStackTrace(); }
        }

        /* Remove the DataWriters etc */
	  evt.salShutdown();
    }
        
}
