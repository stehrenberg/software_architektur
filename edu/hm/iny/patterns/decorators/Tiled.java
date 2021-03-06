/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 3: Decorator Pattern
 */
package edu.hm.iny.patterns.decorators;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * Decorator that creates an image composed of 2x2 arrangements of the
 * original, i.e. underlying TextImage.
 * @version 2015-04-25
 */
public class Tiled extends TextImageBaseDecorator {

	/**
	 * Determines how many instances of the image are to be arranged
	 * vertically as well as horizontally.
	 */
	private static final int TILING_FACTOR = 2;

	/**
	 * Ctor.
	 * @param image The underlying TextImage object.
	 */
	public Tiled(final TextImage image) {
		super(image);
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#getWidth()
	 */
	@Override
	public int getWidth() {
		return super.getWidth() * TILING_FACTOR;
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#getHeight()
	 */
	@Override
	public int getHeight() {
		return super.getHeight() * TILING_FACTOR;
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#charAt(int, int)
	 */
	@Override
	public char charAt(final int column, final int row) {

		final int singleImageCol = column % super.getWidth();
		final int singleImageRow = row % super.getHeight();
		return super.charAt(singleImageCol, singleImageRow);
	}
}
