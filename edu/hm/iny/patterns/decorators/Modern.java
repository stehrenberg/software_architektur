/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 3: Decorator-Pattern
 */

package edu.hm.iny.patterns.decorators;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * A picture that consists of one single char in the given dimensions.
 * @version 2015-04-25
 */
public class Modern extends BaseTextImage implements TextImage{

	/** The char that forms the picture. */
	private final char character;

	/**
	 * Ctor.
	 * @param character The character displayed in the image.
	 * @param width The image width.
	 * @param height The image height.
	 */
	public Modern(final char character, final int width, final int height) {
		super(width, height);
		this.character = character;
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.BaseTextImage#charAt(int, int)
	 */
	@Override
	public char charAt(final int column, final int row) {
		checkIfWithinPicture(column, row);
		return character;
	}
}
