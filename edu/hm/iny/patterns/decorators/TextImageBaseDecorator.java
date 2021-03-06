/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 3: Decorator Pattern
 */

package edu.hm.iny.patterns.decorators;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * Represents the abstract base class for TextImage decorators.
 * @version 2015-04-25
 */
public abstract class TextImageBaseDecorator implements TextImage {

	/** The underlying TextImage object. */
	private final TextImage textImage;

	/**
	 * Ctor.
	 * @param image The underlying TextImage object.
	 */
	public TextImageBaseDecorator(final TextImage image) {
		if (image == null)
			throw new NullPointerException("TextImage missing!");
		textImage = image;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object object) {

		if (this == object)
			return true;
		if (object == null)
			return false;
		if (!(object instanceof TextImageBaseDecorator))
			return false;
		final TextImageBaseDecorator other = (TextImageBaseDecorator) object;
		if (textImage == null) {
			if (other.textImage != null)
				return false;
		} else if (!textImage.equals(other.textImage))
			return false;
		return true;
	}



	/* (non-Javadoc)
	 * @see edu.hm.cs.rs.arch.pattern.decorator.TextImage#getWidth()
	 */
	@Override
	public int getWidth() {
		return textImage.getWidth();
	}

	/* (non-Javadoc)
	 * @see edu.hm.cs.rs.arch.pattern.decorator.TextImage#getHeight()
	 */
	@Override
	public int getHeight() {
		return textImage.getHeight();
	}

	/* (non-Javadoc)
	 * @see edu.hm.cs.rs.arch.pattern.decorator.TextImage#charAt(int, int)
	 */
	@Override
	public char charAt(final int column, final int row) {
		return textImage.charAt(column, row);
	}
}
