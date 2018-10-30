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

import org.lsst.ocs.utilities.RandomString;

import java.util.concurrent.ThreadLocalRandom;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.DisplayName;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * <h2>Random String Generator Test</h2>
 * <p>
 * Tests the {@code RandomString} utility/helper class.
 * <p>
 * <p>
 * <br>
 * <u>NOTE</u>:<br>
 * <p>
 * The memory capacity of a String calculation:<br>
 * 8 * ( (int) (((String.length()) * 2) + 45) / 8)<br>
 * can be broken down as follows: <br>
 * <ul>
 * <li> char array reference = 24 bytes
 * <p>
 * <li> header for char array = 12 bytes
 * <p>
 * <li> String bytes = n*2 bytes where n is the actual number of chars in the String
 * </ul>
 * <p>
 * In total, the chars in the String require: <br>
 * header for char array + String bytes = 12 bytes of header + n*2 bytes for the n chars
 * <p>
 * <p>
 * If:<br>
 * header for char array + String bytes != multiple of 8 (for 64-bit machine)
 * <p>
 * Then:<br>
 * header for char array + String bytes => rounds up to next multiple of 8
 * <p>
 * <p>
 * So overall, our n-character String will use up: <br>
 * RoundedUpToMultOf8( header for char array + String bytes ) + 24 bytes of char array reference
 * <p>
 * <p>
 * <u>e.g.</u>:
 * <p>
 * <p>
 * String str => contains 17 characters
 * <p>
 * <ol>
 * <li> String bytes = 17*2 = 34 bytes
 * <p>
 * <li> header for char array + String bytes = 12 bytes + 34 bytes = 46 bytes
 * <p>
 * <p>
 * <li> RoundedUpToMultOf8( 46 ) = 48 bytes
 * <p>
 * <p>
 * <li> Total String byte usage = 48 bytes + 24 bytes of char array reference
 * = 72 bytes total
 * </ol>
 */
//@DisplayName( "Now Running Utilities.RandomStringTest" )
@RunWith (JUnitPlatform.class)
public class RandomStringTest {

  @Test
  @DisplayName ("Now Running RandomStringCstrTwoParams -> 16chars long()")
  public void RandomStringCstrTwoParams16( TestInfo testInfo ) {

    SecureRandom rnd = new SecureRandom();
    long seed = ThreadLocalRandom.current().nextLong();
    rnd.setSeed( seed );

    RandomString gen = new RandomString( 16, rnd );
    String rs = gen.nextString();
    RandomString gen2 = new RandomString( 16, ThreadLocalRandom.current() );
    String rs2 = gen2.nextString();

    System.out.println( "\nRandom String of character length: 16" );
    System.out.println( "gen.nextString: " + rs + " + " + gen.hashCode() );
    System.out.println( "gen2.nextString: " + rs2 + " + " + gen2.hashCode()
                        + " total_byte_size_of_memory: "
                        + ( 8 * (int) ( ( ( ( gen2.nextString().length() ) * 2 ) + 45 ) / 8 ) ) + "\n" );

//        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        long freeMemory = Runtime.getRuntime().maxMemory() - usedMemory;
//        String s;
//        s = gen2.nextString();
//        long usedMemory1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        long freeMemory1 = Runtime.getRuntime().maxMemory() - usedMemory1;
//        System.out.println( "freeMemory: " + ( freeMemory - freeMemory1 ) + "\n" );
    assertEquals( 16, gen2.nextString().length() );
    assertNotEquals( rs, rs2, "NOT PASSED" );
    assertEquals( "Now Running RandomStringCstrTwoParams -> 16chars long()", testInfo.getDisplayName(),
                  () -> "TestInfo is injected correctly" );
  }

  @Test
  @DisplayName ("Now Running RandomStringCstrTwoParams -> 32chars long()")
  public void RandomStringCstrTwoParams32() {

    RandomString gen21 = new RandomString( 32, ThreadLocalRandom.current() );
    String rs21 = gen21.nextString();
    RandomString gen22 = new RandomString( 32, ThreadLocalRandom.current() );
    String rs22 = gen22.nextString();

    System.out.println( "Random String of character length: 32" );
    System.out.println( "gen21.nextString: " + rs21 + " + " + gen21.hashCode() );
    System.out.println( "gen22.nextString: " + rs22 + " + " + gen22.hashCode()
                        + " total_byte_size_of_memory: "
                        + ( 8 * (int) ( ( ( ( gen22.nextString().length() ) * 2 ) + 45 ) / 8 ) ) + "\n" );

    assertEquals( 32, gen22.nextString().length() );
    assertNotEquals( rs21, rs22 );
  }

  @Test
  @DisplayName ("Now Running RandomStringCstrTwoParams -> 64chars long()")
  public void RandomStringCstrTwoParams64() {

    SecureRandom rnd = new SecureRandom();
    long seed = ThreadLocalRandom.current().nextLong();
    rnd.setSeed( seed );

    RandomString gen3 = new RandomString( 64, rnd );
    RandomString gen4 = new RandomString( 64, ThreadLocalRandom.current() );
    RandomString gen5 = new RandomString( 64, ThreadLocalRandom.current() );
    RandomString gen6 = new RandomString( 64, ThreadLocalRandom.current() );

    System.out.println( "Random String of character length: 64" );
    System.out.println( "gen3.nextString: " + gen3.nextString() + " + " + gen3.hashCode() );
    System.out.println( "gen4.nextString: " + gen4.nextString() + " + " + gen4.hashCode() );
    System.out.println( "gen5.nextString: " + gen5.nextString() + " + " + gen5.hashCode() );
    System.out.println( "gen6.nextString: " + gen6.nextString() + " + " + gen6.hashCode()
                        + " total_byte_size_of_memory: "
                        + ( 8 * (int) ( ( ( ( gen6.nextString().length() ) * 2 ) + 45 ) / 8 ) ) + "\n" );

    assertEquals( 64, gen6.nextString().length() );
  }
}
