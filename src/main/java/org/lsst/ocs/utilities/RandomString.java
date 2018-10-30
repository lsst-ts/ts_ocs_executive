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

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * <h2>Random String </h2>
 * <p>
 * The {@code RandomString} utility/helper class
 */
public class RandomString {

  // Latin Capital Letters
  public static final String _upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  // Latin Capital Letters
  public static final String _lower = _upper.toLowerCase( Locale.ROOT );

  public static final String _digits = "0123456789";

  public static final String _alphanum = _upper + _lower + _digits;

  private final Random _random;

  private final char[] _symbols;

  private final char[] _buf;

  /**
   * Generate a random string.
   *
   * @return the calculated random String
   */
  public String nextString() {

    for ( int idx = 0; idx < _buf.length; ++idx ) {

      _buf[idx] = _symbols[_random.nextInt( _symbols.length )];
    }

    return new String( _buf );
  }

  public RandomString( int length, Random random, String symbols ) {

    if ( length < 1 ) {
      throw new IllegalArgumentException();
    }
    if ( symbols.length() < 2 ) {
      throw new IllegalArgumentException();
    }

    this._random = Objects.requireNonNull( random );
    this._symbols = symbols.toCharArray();
    this._buf = new char[length];
  }

  /**
   * Create an alphanumeric string generator.
   *
   * @param length length of String
   * @param random Random type
   */
  public RandomString( int length, Random random ) {

    this( length, random, _alphanum );
  }

  /**
   * Create an alphanumeric strings from a secure generator.
   *
   * @param length length of string
   */
  public RandomString( int length ) {

    this( length, new SecureRandom() );
  }

  /**
   * Create session identifiers.
   */
  public RandomString() {

    this( 21 );
  }
}
