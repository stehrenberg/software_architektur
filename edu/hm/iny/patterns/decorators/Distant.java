/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 3: Decorator Pattern
 */
package edu.hm.iny.patterns.decorators;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * A decorator that displays a TextImage "from a distance", thus showing
 * only every second char in every second row.
 * @version 2015-04-25
 */
public class Distant extends TextImageBaseDecorator {

	/** The "zoom out" factor by which the image is shrinked. */
	private static final int ZOOM_OUT_FACTOR = 2;

	/**
	 * Ctor.
	 * @param image The underlying TextImage object.
	 */
	public Distant(final TextImage image) {
		super(image);
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#getWidth()
	 */
	@Override
	public int getWidth() {
		return getNewDimension(super.getWidth());
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#getHeight()
	 */
	@Override
	public int getHeight() {
		return getNewDimension(super.getHeight());
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#charAt(int, int)
	 */
	@Override
	public char charAt(final int column, final int row) {
		return super.charAt(column * ZOOM_OUT_FACTOR, row * ZOOM_OUT_FACTOR);
	}

	/**
	 * Used to calculate the new width/height of the decorated picture that
	 * is half the original size.
	 * Since the dimensions for images with an odd row/column number must
	 * be rounded up, but an int is required as width/height, the little voodoo
	 * with type casts was neccessary.
	 * @param oldDimension The old width or height of the picture.
	 * @return The new width/height of the picture.
	 */
	private int getNewDimension(final int oldDimension) {
		return (int)Math.round((double)oldDimension / ZOOM_OUT_FACTOR);
	}
}
