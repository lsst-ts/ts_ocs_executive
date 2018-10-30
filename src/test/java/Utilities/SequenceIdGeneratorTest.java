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

package Utilities;

import org.lsst.ocs.utilities.SequenceIdGenerator;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.lsst.ocs.executive.salcomponent.CSCArchiver;

//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * <h2>Sequence Id Generator Test</h2>
 * <p>
 * Tests {@code SequenceIdGenerator} utility/helper class
 */
class MySequenceIdGeneratorTest {

  @Mock
  SequenceIdGenerator sigInstance2;
}

@ExtendWith (MockitoExtension.class)
public class SequenceIdGeneratorTest {

//    @Mock private CSCArchiver archiver;
  @Mock
  private SequenceIdGenerator sigInstance2;

  static final SequenceIdGenerator sigInstance = SequenceIdGenerator.getInstance();

  static String sigInstanceId = sigInstance.getNextId();
  static int sigInstanceHash = sigInstance.hashCode();

  public String Str2IntIncr2Str( String idStr, int incr ) {

    Long idLong = Long.parseLong( idStr );
    AtomicLong atomicLong = new AtomicLong( idLong );

    sigInstanceId = Integer.toUnsignedString( atomicLong.intValue() + incr );

    return sigInstanceId;
  }

  @BeforeAll
  public static void initTest() {

    System.out.printf( "initialId: %s" + " from: " + Thread.currentThread().getName() + "\n", sigInstanceId );
  }

  @Test
  public void sigThread1() throws Exception {

    Executors.newSingleThreadExecutor().submit( () -> {

      SequenceIdGenerator sgenT1 = SequenceIdGenerator.getInstance();
      assertEquals( sigInstance, sgenT1 );
      assertEquals( sigInstanceHash, sgenT1.hashCode() );

      String id;
      String id2;

      assertEquals( Str2IntIncr2Str( sigInstanceId, 1 ), id = sgenT1.getNextId() );
      assertEquals( Str2IntIncr2Str( sigInstanceId, 1 ), id2 = sgenT1.getNextId() );

      System.out.printf( "t1Id: %s" + " from: " + Thread.currentThread().getName() + "\n", id );
      System.out.printf( "t1Id: %s" + " from: " + Thread.currentThread().getName() + "\n", id2 );

      assertEquals( Str2IntIncr2Str( sigInstanceId, 10 ), id = sgenT1.getNextId( 10 ) );
      assertEquals( Str2IntIncr2Str( sigInstanceId, 10 ), id2 = sgenT1.getNextId( 10 ) );

      System.out.printf( "t1Id10: %s" + " from: " + Thread.currentThread().getName() + "\n", id );
      System.out.printf( "t1Id10: %s" + " from: " + Thread.currentThread().getName() + "\n", id2 );

    } ).get(); // get() waits for thread to finish (i.e. no parallelism, thus sequential threading)

// Alternative Way
//----------------------------
//        Executors.newSingleThreadExecutor().execute( () -> {
//
//            SequenceIdGenerator sgenT1 = SequenceIdGenerator.getInstance();
//            assertEquals( sigInstance, sgenT1 );
//            assertEquals( sigInstanceHash, sgenT1.hashCode() );
//
//            String id;
//            String id2;
//
//            assertEquals( Str2IntIncr2Str( sigInstanceId, 1 ), id = sgenT1.getNextId() );
//            assertEquals( Str2IntIncr2Str( sigInstanceId, 1 ), id2 = sgenT1.getNextId() );
//
//            System.out.printf( "t1Id: %s" + " from: " + Thread.currentThread().getName() + "\n", id );
//            System.out.printf( "t1Id: %s" + " from: " + Thread.currentThread().getName() + "\n", id2 );
//
//            assertEquals( Str2IntIncr2Str( sigInstanceId, 10 ), id = sgenT1.getNextId(10) );
//            assertEquals( Str2IntIncr2Str( sigInstanceId, 10 ), id2 = sgenT1.getNextId(10) );
//
//            System.out.printf( "t1Id10: %s" + " from: " + Thread.currentThread().getName() + "\n", id );
//            System.out.printf( "t1Id10: %s" + " from: " + Thread.currentThread().getName() + "\n", id2 );
//        });
  }

  @Test
  public void sigThread2() throws Exception {

    Runnable t2 = () -> {

      SequenceIdGenerator sgenT2 = SequenceIdGenerator.getInstance();
      assertEquals( sigInstance, sgenT2 );
      assertEquals( sigInstanceHash, sgenT2.hashCode() );

      String id;
      String id2;

      assertEquals( Str2IntIncr2Str( sigInstanceId, 1 ), id = sgenT2.getNextId() );
      assertEquals( Str2IntIncr2Str( sigInstanceId, 1 ), id2 = sgenT2.getNextId() );

      System.out.printf( "t2Id: %s" + " from: " + Thread.currentThread().getName() + "\n", id );
      System.out.printf( "t2Id: %s" + " from: " + Thread.currentThread().getName() + "\n", id2 );
    };

    Executors.newSingleThreadExecutor().submit( t2 ).get();
  }
}
