/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 3: Decorator-Pattern
 */

package edu.hm.iny.patterns.decorators;

import java.time.LocalTime;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * Represents a picture with the given size, showing the current time (HHMMSS).
 * The six digits repeat themselves to the right and to the bottom for the
 * full picture's size.
 * @version 2015-04-17
 */
public class ClocktimeImage extends BaseTextImage implements TextImage {

	/** Used to convert the digits 0 to 9 to their ASCII representation. */
	private static final int INT_TO_CHAR_SHIFT = 48;
	/** Used to get the tens and ones of hours/minutes/seconds. */
	private static final int TIME_CONVERSION_DIVISOR = 10;

	/**
	 * Ctor.
	 * @param width The image width.
	 * @param height The image height.
	 */
	public ClocktimeImage(final int width, final int height) {
		super(width, height);
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.BaseTextImage#charAt(int, int)
	 */
	@Override public char charAt(final int column, final int row) {

		checkIfWithinPicture(column, row);

		final LocalTime time = LocalTime.now();
		final char[] timeAsArray = new char[] {
				convertToChar(time.getHour()/TIME_CONVERSION_DIVISOR),
				convertToChar(time.getHour()%TIME_CONVERSION_DIVISOR),
				convertToChar(time.getMinute()/TIME_CONVERSION_DIVISOR),
				convertToChar(time.getMinute()%TIME_CONVERSION_DIVISOR),
				convertToChar(time.getSecond()/TIME_CONVERSION_DIVISOR),
				convertToChar(time.getSecond()%TIME_CONVERSION_DIVISOR)
		};

		return timeAsArray[(column+row) % timeAsArray.length];
	}

	/**
	 * Used to convert the digits from the current time to chars, based on the
	 * fact that the ASCII codes for digits 0 - 9 go from 48 to 58.
	 * @param digit The digit.
	 * @return The corresponding char.
	 */
	private char convertToChar(final int digit) {
		return (char)(digit + INT_TO_CHAR_SHIFT);
	}


}
