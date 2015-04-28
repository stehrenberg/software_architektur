/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 3: Decorator-Pattern
 */

package edu.hm.iny.patterns.decorators;

import java.util.NoSuchElementException;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * A abstract basic TextImage.
 * Derived classes must implement the method returning a char at a given
 * image position.
 * @version 2015-04-25
 */
public abstract class BaseTextImage implements TextImage {

	/** The image's width. */
	private final int imageWidth;
	/** The image's height. */
	private final int imageHeight;

	/**
	 * Ctor.
	 * @param width The image width.
	 * @param height The image height.
	 */
	public BaseTextImage(final int width, final int height) {
		checkIfSizeParamsAreValid(width, height);
		imageWidth = width;
		imageHeight = height;
	}


	/* (non-Javadoc)
	 * @see edu.hm.cs.rs.arch.pattern.decorator.TextImage#getWidth()
	 */
	@Override public int getWidth() {
		return imageWidth;
	}

	/* (non-Javadoc)
	 * @see edu.hm.cs.rs.arch.pattern.decorator.TextImage#getHeight()
	 */
	@Override public int getHeight() {
		return imageHeight;
	}


	/**
	 * Returns a char of the picture at a specified index.
	 * @param column The image's column.
	 * @param row The image's row.
	 * @return The char at the given index.
	 */
	@Override public abstract char charAt(int column, int row);

	/**
	 * Checks if the params for width and height are non-negative.
	 * @param width The image width.
	 * @param height The image height.
	 */
	void checkIfSizeParamsAreValid(final int width, final int height) {
		if(width < 0 || height < 0)
			throw new IllegalArgumentException("Size must be positive.");
	}

	/**
	 * Checks if the stated coordinates lie within the picture.
	 * Throws an Exception if the coordinates are invalid.
	 * @param column The image's column.
	 * @param row The image's row.
	 * @return True, if coordinates are valid.
	 * @throws NoSuchElementException If coordinates are outside the picture.
	 */
	boolean checkIfWithinPicture(final int column, final int row) {

		final boolean columnWithinPicture = column < getWidth() && column > -1;
		final boolean rowWithinPicture = row < getHeight() && row > -1;
		final boolean withinPicture = columnWithinPicture && rowWithinPicture;

		if(!withinPicture)
			throw new NoSuchElementException(
					"Invalid coordinates. No element at "
							+ row
							+ "/"
							+ column
							+ " (row/column)");

		return withinPicture;
	}


}