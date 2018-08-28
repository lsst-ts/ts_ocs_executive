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
import org.lsst.ocs.executive.EntityType;
import org.lsst.ocs.executive.Executive;
import org.lsst.ocs.utilities.SequenceIdGenerator;
import org.lsst.sal.SAL_atcamera;

/**
 * <h2>Auxiliary Camera Control System (ACCS) CSC</h2>
 *
 * {@code CSCAuxCamera} is a (Concrete) Receiver class in the command pattern
 */
public class CSCAuxCamera implements CommandableSalComponent {
    
    static final SequenceIdGenerator seqIdGen = SequenceIdGenerator.getInstance();
    
    @Override public String getName() { return "CSCAuxCamera"; }

    @Override public void enterControl( Object [] args ) {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_enterControl" );

        publisher.setDebugLevel( 1 );
        
        atcamera.command_enterControl command = new atcamera.command_enterControl();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
        command.property = "state";
        command.action = "enterControl";
        command.state = true;

        int cmdId = publisher.issueCommand_enterControl( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_enterControl( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void start( Object [] args ) {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_start" );

        publisher.setDebugLevel( 1 );

        atcamera.command_start command = new atcamera.command_start();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
        command.property = "state";
        command.action = "start";

        int cmdId = publisher.issueCommand_start( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_start( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();

    }

    @Override public void enable( Object [] args ) {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_enable" );

        publisher.setDebugLevel( 1 );

        atcamera.command_enable command = new atcamera.command_enable();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
        command.property = "state";
        command.action = "enable";
        command.state = true;

        int cmdId = publisher.issueCommand_enable( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_enable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void disable( Object [] args ) {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_disable" );

        publisher.setDebugLevel( 1 );

        atcamera.command_disable command = new atcamera.command_disable();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
        command.property = "state";
        command.action = "disable";
        command.state = true;

        int cmdId = publisher.issueCommand_disable( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_disable( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void standby( Object [] args ) {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_standby" );

        publisher.setDebugLevel( 1 );

        atcamera.command_standby command = new atcamera.command_standby();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
        command.property = "state";
        command.action = "standby";
        command.state = true;

        int cmdId = publisher.issueCommand_standby( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_standby( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    @Override public void exitControl( Object [] args ) {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_exitControl" );

        publisher.setDebugLevel( 1 );

        atcamera.command_exitControl command = new atcamera.command_exitControl();
        command.private_revCode = "LSST AuxCamera enterControl COMMAND";
        command.device = "atcamera";
        command.property = "state";
        command.action = "exitControl";
        command.state = true;

        int cmdId = publisher.issueCommand_exitControl( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_exitControl( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }

    public void takeImage( Object [] args ) {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_takeImages" );

        publisher.setDebugLevel( 1 );

        atcamera.command_takeImages command  = new atcamera.command_takeImages();
        command.private_revCode = "LSST TEST COMMAND";
        command.device   = "accs";
        command.property = "takeImages";
        command.action   = "set";
        
        command.imageSequenceName = (String) args[0] + seqIdGen.getNextId();
        
        command.numImages = (int) args[1];
        command.expTime   = (double) args[2];
        
        command.shutter = (boolean) args[3];
        command.science = (boolean) args[4];
        command.guide   = (boolean) args[5];
        command.wfs     = (boolean) args[6];
            
//        out.println( "imageSequenceName: " + command.imageSequenceName );
//        out.println( "shutter: " + command.shutter  );
//        out.println( "science: " + command.science  );
            
        int cmdId = publisher.issueCommand_takeImages( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 20;
        publisher.waitForCompletion_takeImages( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }
   
    public void initImage( Object [] args ) {

        SAL_atcamera publisher = new SAL_atcamera();
        publisher.salCommand( "atcamera_command_takeImages" );

        publisher.setDebugLevel( 1 );

        atcamera.command_initImage command  = new atcamera.command_initImage();
        command.private_revCode = "LSST TEST COMMAND";
        command.device   = "accs";
        command.property = "initImage";
        command.action   = "apply";
        
        command.deltaT = (double) 5.0;

        int cmdId = publisher.issueCommand_initImage( command );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int timeout = 4;
        publisher.waitForCompletion_initImage( cmdId, timeout );

        /* Remove the DataWriters etc */
        publisher.salShutdown();
    }
   
    @Override public Integer summaryState() {

        // Initialize
        SAL_atcamera subscriber = new SAL_atcamera();
        subscriber.salEvent( "atcamera_logevent_summaryState" );
        subscriber.setDebugLevel( 1 );

        atcamera.logevent_summaryState event = new atcamera.logevent_summaryState();

        Integer status = CSC_STATUS.SAL__NO_UPDATES.getValue();
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_summaryState( event );
            if ( status == SAL_atcamera.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
                out.println( "=== Event Status : " + status );
                out.println( "=== Event SummaryState : " + event.summaryStateValue );
                
                try {
                    Executive.getEntityMap()
                             .get( EntityType.ACCS.toString() )
                             ._modelStateTransitionQ.put( event.summaryStateValue );
                    
                    Executive.getEntityMap()
                             .get( EntityType.ACCS.toString() )
                             ._viewStateTransitionQ.put( event.summaryStateValue );
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
        SAL_atcamera subscriber = new SAL_atcamera();
        subscriber.salEvent( "atcamera_logevent_SettingVersions" );

        subscriber.setDebugLevel( 1 );

        atcamera.logevent_settingVersions event = new atcamera.logevent_settingVersions();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_settingVersions( event );
            if ( status == SAL_atcamera.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
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
        SAL_atcamera subscriber = new SAL_atcamera();
        subscriber.salEvent( "atcamera_logevent_AppliedSettingsMatchStart" );

        subscriber.setDebugLevel( 1 );

        atcamera.logevent_appliedSettingsMatchStart event =
            new atcamera.logevent_appliedSettingsMatchStart();

        int status;
        while ( Boolean.TRUE ) {
            
            status = subscriber.getEvent_appliedSettingsMatchStart( event );
            if ( status == SAL_atcamera.SAL__OK ) {
                
                out.println( "=== Event Logged : " + event );
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
