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

/**
 *
 * EntityType is a type-safe constant type
 *
 */

public final class EntityType { // final class
    
    private final String etype_;
    
    // private constructor
    private EntityType(final String etype) { this.etype_ = etype; }
    
    @Override public String toString() { return this.etype_; }
    
    public static final EntityType Ocs  = new EntityType("Ocs");
    public static final EntityType OCS  = new EntityType("OCS");
    
    public static final EntityType Sequencer  = new EntityType("Sequencer");
    public static final EntityType SEQUENCER  = new EntityType("SEQUENCER");

    public static final EntityType CCS  = new EntityType("CCS");
    public static final EntityType Camera  = new EntityType("Camera");
    public static final EntityType CAMERA  = new EntityType("CAMERA");

    public static final EntityType Tcs  = new EntityType("Tcs");
    public static final EntityType TCS  = new EntityType("TCS");
    
    public static final EntityType Archiver = new EntityType("Archiver");
    public static final EntityType ARCHIVER = new EntityType("ARCHIVER");

    public static final EntityType Catchuparchiver = new EntityType("Catchuparchiver");
    public static final EntityType CatchupArchiver = new EntityType("CatchupArchiver");
    public static final EntityType CATCHUPARCHIVER = new EntityType("CATCHUPARCHIVER");

    public static final EntityType Processingcluster = new EntityType("Processingcluster");
    public static final EntityType ProcessingCluster = new EntityType("ProcessingCluster");
    public static final EntityType PROCESSINGCLUSTER = new EntityType("PROCESSINGCLUSTER");
}


//////////////////////////////////////////////////////////////
// OR
/**
 * 
public final class EntityType { 

    private EntityType() {}

    public static final EntityType OCS  = new EntityType();
    public static final EntityType TCS  = new EntityType();
    public static final EntityType CCS  = new EntityType();
    public static final EntityType DMCS = new EntityType();
}
 * 
 */


//////////////////////////////////////////////////////////////
// OR
/**
 *
public enum EntityType {

    OCS, TCS, CCS, DMCS
}
 *
 */
//////////////////////////////////////////////////////////////


