/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 3: Decorator-Pattern
 */

package edu.hm.iny.patterns.decorators;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * Represents a TextImage that consists of strings, each string representing
 * one picture row. The image width is determined by the longest string,
 * all other strings are extendend to the image width by blanks.
 * @version 2015-04-25
 */
public class StringPicture extends BaseTextImage implements TextImage {

	/** The strings that form the picture. */
	private final String[] imageStrings;

	/**
	 * Ctor.
	 * @param strings The strings forming the TextImage.
	 */
	public StringPicture(final String... strings ) {

		super(getLengthOfLongestString(strings), strings.length);
		imageStrings = new String[strings.length];
		for(int index = 0; index < strings.length; index++)
			imageStrings[index] = strings[index];
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.BaseTextImage#charAt(int, int)
	 */
	@Override public char charAt(final int column, final int row) {

		checkIfWithinPicture(column, row);
		char imageChar = ' ';
		final boolean indexWithinString = column < imageStrings[row].length();
		if (indexWithinString)
			imageChar = imageStrings[row].charAt(column);

		return imageChar;
	}

	/**
	 * Returns the longest string from the vararg-parameter.
	 * Needed to determine the total image width within the Ctor, thus static.
	 * @param strings The vararg containing the strings for the text image.
	 * @return The longest string within the vararg.
	 */
	private static int getLengthOfLongestString(final String... strings) {

		int longestStringLength = 0;
		int stringLength;

		for(final String string : strings) {
			stringLength = string.length();

			if(stringLength > longestStringLength)
				longestStringLength = string.length();
		}

		return longestStringLength;
	}
}
