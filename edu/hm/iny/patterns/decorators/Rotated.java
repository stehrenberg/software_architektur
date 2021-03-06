/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 3: Decorator Pattern
 */

package edu.hm.iny.patterns.decorators;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * Decorator that rotates a given TextImage 90 degrees CCW.
 * @version 2015-04-25
 */
public class Rotated extends TextImageBaseDecorator{

	/**
	 * Ctor.
	 * @param image The underlying TextImage object.
	 */
	public Rotated(final TextImage image) {
		super(image);
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#getWidth()
	 */
	@Override public int getWidth() {
		return super.getHeight();
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#getHeight()
	 */
	@Override public int getHeight() {
		return super.getWidth();
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#charAt(int, int)
	 */
	@Override public char charAt(final int column, final int row) {

		final int newColumn = super.getWidth() - 1 - row;
		final int newRow = column;
		return super.charAt(newColumn, newRow);
	}
}
