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
 * DomainObject is an interface type
 *
 */

//public class DomainObject {
public interface DomainObject {

    //protected String Name_;
    //String Name_ = null;

    //public DomainObject(String name) { Name_ = name; }
    
    //@Override public String toString() { return Name_; }
    
    //default String getName() { return null; }
    default String getName() { return "NONAME"; }

}
