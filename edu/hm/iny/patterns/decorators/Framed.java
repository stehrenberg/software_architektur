/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 3: Decorator Pattern
 */
package edu.hm.iny.patterns.decorators;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * A decorator that provides a frame around a given TextImage.
 * @version 2015-04-25
 */
public class Framed extends TextImageBaseDecorator {

	/** This is the size of the frame that adds twice to the height/width of an image. */
	private static final int FRAME = 1;
	/** Corners of the frame consist of this char. */
	private static final char CORNER_FRAME_ELEMENT = '+';
	/** Vertical borders of the frame consist of this char. */
	private static final char VERTICAL_FRAME_ELEMENT = '|';
	/** Horizontal borders of the frame consist of this char. */
	private static final char HORIZONTAL_FRAME_ELEMENT = '-';

	/**
	 * Ctor.
	 * @param image The underlying TextImage object.
	 */
	public Framed(final TextImage image) {
		super(image);
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#getWidth()
	 */
	@Override public int getWidth() {
		return super.getWidth() + FRAME + FRAME;
	};

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#getHeight()
	 */
	@Override public int getHeight() {
		return super.getHeight() + FRAME + FRAME;
	};

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.decorators.TextImageBaseDecorator#charAt(int, int)
	 */
	@Override public char charAt(final int column, final int row) {

		char returnChar;

		if (isCornerElement(column, row))
			returnChar = CORNER_FRAME_ELEMENT;
		else if(isVerticalBorder(column))
			returnChar = VERTICAL_FRAME_ELEMENT;
		else if (isHorizontalBorder(row))
			returnChar = HORIZONTAL_FRAME_ELEMENT;
		else
			returnChar = super.charAt(column - FRAME, row - FRAME);

		return returnChar;
	}


	/**
	 * Determines if the given position is an image corner.
	 * @param column The column to inspect.
	 * @param row The row to inspect.
	 * @return True, if position indicates a corner, otherwise false.
	 */
	private boolean isCornerElement(final int column, final int row) {
		return isHorizontalBorder(row) && isVerticalBorder(column);
	}

	/**
	 * Determines if the row index is a horizontal image border.
	 * @param row The row to inspect.
	 * @return True, if the row is a horizontal border, otherwise false.
	 */
	private boolean isHorizontalBorder(final int row) {
		return row == 0 || row == getHeight() -1;
	}

	/**
	 * Determines if the column index is a vertical image border.
	 * @param column The colum to inspect.
	 * @return True, if the colum is a vertical border, otherwise false.
	 */
	private boolean isVerticalBorder(final int column) {
		return column == 0 || column == getWidth()-1;
	}
}
