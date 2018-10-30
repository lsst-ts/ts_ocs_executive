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

package org.lsst.ocs.utilities;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <h2>Sequence Id Generator</h2>
 * <p>
 * The non-instantiable {@code SequenceIdGenerator} utility/helper class
 * that implements a {@link Thread} safe version of a monotonic counter.
 * <p>
 * Example:
 * <pre>
 *  {@code string seqId = SequenceIdGenerator.getInstance().getNextId(); }
 * </pre>
 */
public final class SequenceIdGenerator {

  private static volatile SequenceIdGenerator _instance = null;

  private static AtomicInteger _atomicSranInt;

  // private Cstr so no direct instances can be made    
  private SequenceIdGenerator() {

    // Prevent forming new instance via reflection API
    if ( _instance != null ) {

      throw new RuntimeException( "Use getInstance () method to get the singleton instance of this class" );
    }
  }

  private static void createInstance() {

    // make local copy
    SequenceIdGenerator instance = SequenceIdGenerator._instance;

    // Double-check locking pattern
    if ( instance == null ) {   // 1st Check

      // Thread safe
      synchronized ( SequenceIdGenerator.class ) {

        instance = SequenceIdGenerator._instance;

        if ( instance == null ) {   // 2nd Check

          SequenceIdGenerator._instance = instance = new SequenceIdGenerator();

          _atomicSranInt = new AtomicInteger( ThreadLocalRandom.current().nextInt( 1 ) );
        }
      }
    }
  }

  public static SequenceIdGenerator getInstance() {

    createInstance();

    return SequenceIdGenerator._instance;
  }

  public static String getNextId() {

    _atomicSranInt.incrementAndGet();

    return Integer.toUnsignedString( _atomicSranInt.get() );
  }

  public static String getNextId( int offset ) {

    _atomicSranInt.getAndAdd( offset );

    return Integer.toUnsignedString( _atomicSranInt.get() );
  }

  public static String getNextBlockId() {

    if ( ( _atomicSranInt.intValue() % 10 ) > 0 ) {

      _atomicSranInt.getAndUpdate( x -> x + ( 10 - ( _atomicSranInt.intValue() % 10 ) ) );

    }

    return Integer.toUnsignedString( _atomicSranInt.get() );
  }
}
