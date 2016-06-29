/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 4: Factory Pattern
 */
package edu.hm.iny.patterns.factory;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * Pretty random factory class that behaves rather stupidly ^^
 * Its make-methods either return a 1x1 TextImage consisting of the letter "A", no matter
 * the method parameters, or return the unchanged given TextImage with no Decorator whatsoever.
 * @version 2015-04-28
 */
public class FakeTextImageFactory extends TextImageFactory {

	/** A default TextImage. */
	private final TextImage defaultTextImage;

	/**
	 * Ctor.
	 */
	public FakeTextImageFactory() {

		defaultTextImage = new TextImage() {

			@Override
			public int getWidth() {
				return 1;
			}

			@Override
			public int getHeight() {
				return 1;
			}

			@Override
			public char charAt(final int column, final int row) {
				return 'A';
			}

			@Override
			public String toString() {
				return "A";
			}
		};
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.factory.TextImageFactory#make(java.lang.String, java.lang.String[])
	 */
	@Override
	public TextImage make(final String typename, final String... args) {
		return defaultTextImage;
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.factory.TextImageFactory#make(java.lang.String, edu.hm.cs.rs.arch.pattern.decorator.TextImage)
	 */
	@Override
	public TextImage make(final String typename, final TextImage image) {
		return image;
	}
}
