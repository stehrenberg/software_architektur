/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 3: Decorator Pattern
 */
package edu.hm.iny.patterns.decorators;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * Decorator that makes a TextImage printable.
 * @version 2015-04-25
 */
public class Printable extends TextImageBaseDecorator {

	/**
	 * Ctor.
	 * @param image The underlying TextImage object.
	 */
	public Printable(final TextImage image){
		super(image);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() {

		final StringBuilder strBuilder = new StringBuilder();

		for(int row = 0; row < getHeight(); row++) {

			final boolean isNotLastRow = row < getHeight() - 1;

			for(int col = 0; col < getWidth(); col++) {
				strBuilder.append(charAt(col, row));
			}

			if(isNotLastRow)
				strBuilder.append('\n');
		}

		return strBuilder.toString();
	}

}
