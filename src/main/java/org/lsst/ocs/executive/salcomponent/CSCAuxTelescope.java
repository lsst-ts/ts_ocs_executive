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
import org.lsst.ocs.executive.Executive;
import org.lsst.sal.SAL_atcs;

import org.lsst.sal.SAL_scheduler;

/**
 * <h2>Auxiliary Telescope Control System (ATCS) CSC</h2>
 *
 * {@code CSCAuxTelescope} is a (Concrete) Receiver class in the command pattern
 *
 */
public class CSCAuxTelescope implements CommandableSalComponent {
    
    @Override public String getName() { return "CSCAuxTelescope"; }
    
    @Override public void enterControl() { 

//        out.print( this.getName() + "::" + 
//                   Thread.currentThread().getStackTrace()[1].getMethodName() + "::" +
//                   "Threadid: " + 
//                   Thread.currentThread().getId() + "\n" );
        
        SAL_atcs publisher = new SAL_atcs();
        publisher.salCommand( "atcs_command_enterControl" );

        publisher.setDebugLevel( 1 );
        
        atcs.command_enterControl command = new atcs.command_enterControl();
        command.private_revCode = "LSST AuxTelescope enterControl COMMAND";
        command.device = "atcs";
        command.property = "state";
        command.action = "enterControl";
        command.enterControl = true;

        int cmdId = publisher.issueCommand_enterControl( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 5;
        publisher.waitForCompletion_enterControl( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void start() { 
        
        SAL_atcs publisher = new SAL_atcs();
        publisher.salCommand( "atcs_command_start" );

        publisher.setDebugLevel( 1 );
        
        atcs.command_start command = new atcs.command_start();
        command.private_revCode = "LSST AuxTelescope enterControl COMMAND";
        command.device = "atcs";
        command.property = "state";
        command.action = "start";

        int cmdId = publisher.issueCommand_start( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 5;
        publisher.waitForCompletion_start( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    
    }

    @Override public void enable() {

        SAL_atcs publisher = new SAL_atcs();
        publisher.salCommand( "atcs_command_enable" );

        publisher.setDebugLevel( 1 );
        
        atcs.command_enable command = new atcs.command_enable();
        command.private_revCode = "LSST AuxTelescope enterControl COMMAND";
        command.device = "atcs";
        command.property = "state";
        command.action = "enable";

        int cmdId = publisher.issueCommand_enable( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 5;
        publisher.waitForCompletion_enable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void disable() {

        SAL_atcs publisher = new SAL_atcs();
        publisher.salCommand( "atcs_command_disable" );

        publisher.setDebugLevel( 1 );
        
        atcs.command_disable command = new atcs.command_disable();
        command.private_revCode = "LSST AuxTelescope enterControl COMMAND";
        command.device = "atcs";
        command.property = "state";
        command.action = "disable";

        int cmdId = publisher.issueCommand_disable( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 5;
        publisher.waitForCompletion_disable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void standby() {

        SAL_atcs publisher = new SAL_atcs();
        publisher.salCommand( "atcs_command_standby" );

        publisher.setDebugLevel( 1 );
        
        atcs.command_standby command = new atcs.command_standby();
        command.private_revCode = "LSST AuxTelescope enterControl COMMAND";
        command.device = "atcs";
        command.property = "state";
        command.action = "standby";

        int cmdId = publisher.issueCommand_standby( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 5;
        publisher.waitForCompletion_standby( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void exitControl() {

        SAL_atcs publisher = new SAL_atcs();
        publisher.salCommand( "atcs_command_exitControl" );

        publisher.setDebugLevel( 1 );
        
        atcs.command_exitControl command = new atcs.command_exitControl();
        command.private_revCode = "LSST AuxTelescope enterControl COMMAND";
        command.device = "atcs";
        command.property = "state";
        command.action = "exitControl";

        int cmdId = publisher.issueCommand_exitControl( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 5;
        publisher.waitForCompletion_exitControl( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    public void spectrographSetup() {

        SAL_atcs publisher = new SAL_atcs();
        publisher.salCommand( "atcs_command_SpectrographSetup" );

        publisher.setDebugLevel( 1 );
        
        atcs.command_SpectrographSetup command = new atcs.command_SpectrographSetup();
        command.grating = (int) 0;
        command.filter = (int) 0;
        command.stagePosition = (double) 0.0;

        int cmdId = publisher.issueCommand_SpectrographSetup(command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 5;
        publisher.waitForCompletion_SpectrographSetup(cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    public void offset() {

        SAL_atcs publisher = new SAL_atcs();
        publisher.salCommand( "atcs_command_Offset" );

        publisher.setDebugLevel( 1 );
        
        atcs.command_Offset command = new atcs.command_Offset();
        command.offsetX = (double) 0.0;
        command.offsetY = (double) 0.0;

        int cmdId = publisher.issueCommand_Offset(command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 5;
        publisher.waitForCompletion_Offset(cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    public void target() {

        SAL_atcs publisher = new SAL_atcs();
        publisher.salCommand( "atcs_command_Target" );

        publisher.setDebugLevel( 1 );
        
        atcs.command_Target command = new atcs.command_Target();

        command.targetId = (int) 2;
        command.ra = (double) 289.583;
        command.decl = (double) 0.187;
        command.skyPositionAngle = (double) 156.887;
        command.numOfExposures = (int) 2;
        command.exposureTime = (int) 15;
            
        int cmdId = publisher.issueCommand_Target( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_Target( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }
    
    @Override public Integer summaryState() {
    
        // Initialize
        SAL_atcs subscriber = new SAL_atcs();
        subscriber.salEvent( "atcs_logevent_SummaryState" );

        subscriber.setDebugLevel( 1 );
        
        atcs.logevent_SummaryState event = new atcs.logevent_SummaryState();

        Integer status = CommandableSalComponent.CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SummaryState( event );
            if ( status == SAL_scheduler.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
                out.println( "=== Event Status : " + status );
                out.println( "=== Event SummaryState : " + event.summaryState );
                
                try {
                    Executive.getEntityMap().get( "atc" )._stateTransitionQ.put( event.summaryState );
                    Executive.getEntityMap().get( "atc" )._guiStateTransitionQ.put( event.summaryState );
                } catch ( InterruptedException ie ) {
                    ie.printStackTrace( out.printf( "GOOD SummaryState" ));
                }
            }

            try {
                Thread.sleep( 100 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        /* Remove the DataWriters etc */
        subscriber.salShutdown();

        return status;
    }
    
    @Override public void settingsVersion() {
    
        // Initialize
        SAL_atcs subscriber = new SAL_atcs();
        subscriber.salEvent( "atcs_logevent_SettingVersions" );
        
        subscriber.setDebugLevel( 1 );
        
        atcs.logevent_SettingVersions event = new atcs.logevent_SettingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SettingVersions( event );
            if ( status == SAL_atcs.SAL__OK ) {
                
                out.println("=== Event Logged : " + event);
            }
            
            try {
                Thread.sleep( 100 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        /* Remove the DataWriters etc */
        subscriber.salShutdown();
    }
    
    @Override public void appliedSettingsMatchStart() {
    
        // Initialize
        SAL_atcs subscriber = new SAL_atcs();
        subscriber.salEvent( "atcs_logevent_AppliedSettingsMatchStart" );
        
        subscriber.setDebugLevel( 1 );
        
        atcs.logevent_AppliedSettingsMatchStart event =
            new atcs.logevent_AppliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_AppliedSettingsMatchStart( event );
            if ( status == SAL_atcs.SAL__OK ) {
                
                out.println("=== Event Logged : " + event);
            }
            
            try {
                Thread.sleep( 100 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        /* Remove the DataWriters etc */
	subscriber.salShutdown();
    }
    
    public void spectrographInPosition() {
    
        // Initialize
        SAL_atcs subscriber = new SAL_atcs();
        subscriber.salEvent( "atcs_logevent_SpectrographInPosition" );
        
        subscriber.setDebugLevel( 1 );
        
        atcs.logevent_SpectrographInPosition event =
            new atcs.logevent_SpectrographInPosition();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_SpectrographInPosition( event );
            if ( status == SAL_atcs.SAL__OK ) {
                
                out.println("=== Event Logged : " + event);
                out.println("=== inPosition value: " + event.inPosition);
            }
            
            try {
                Thread.sleep( 100 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        /* Remove the DataWriters etc */
	subscriber.salShutdown();
    }
    
    public void telescopeInPosition() {
    
        // Initialize
        SAL_atcs subscriber = new SAL_atcs();
        subscriber.salEvent( "atcs_logevent_TelescopeInPosition" );
        
        subscriber.setDebugLevel( 1 );
        
        atcs.logevent_TelescopeInPosition event = new atcs.logevent_TelescopeInPosition();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_TelescopeInPosition( event );
            if ( status == SAL_atcs.SAL__OK ) {
                
                out.println("=== Event Logged: " + event);
                out.println("=== inPosition value: " + event.inPosition);
            }

            try {
                Thread.sleep( 100 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        /* Remove the DataWriters etc */
	subscriber.salShutdown();
    }
}


