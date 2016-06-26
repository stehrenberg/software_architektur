/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 4: Factory Pattern
 */
package edu.hm.iny.patterns.factory;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * ABC for TextImageFactories that construct TextImages.
 * @version 2015-05-01
 */
public abstract class TextImageFactory {

	/**
	 * Reads the value for system property "factorytype" and chooses
	 * the appropriate TextImageFactory.
	 * System property is set as VM Argument with "-Dfactorytype=[imageFactory]"
	 * @return A suitable TextImage factory.
	 * @throws ClassNotFoundException If no suitable Factory could be located.
	 */
	public static TextImageFactory locate() throws ClassNotFoundException {

		final String factoryType = System.getProperty("factorytype");
		TextImageFactory factory;

		switch(factoryType) {
		case "SwitchedTextImageFactory":
			factory = new SwitchedTextImageFactory();
			break;
		case "FakeTextImageFactory":
			factory = new FakeTextImageFactory();
			break;
		case "CachedTextImageFactory":
			factory = new CachedTextImageFactory();
			break;
		default:
			throw new ClassNotFoundException("No suitable factory could be found.");
		}

		return factory;
	}

	/**
	 * Constructs a TextImage specified by typename with the given arguments.
	 * @param typename The desired TextImage type.
	 * @param args The arguments for the TextImage, such as size, image chars etc.
	 * @return A TextImage object.
	 * @throws ClassNotFoundException If the given typename could not be matched to a TextImage class.
	 */
	public abstract TextImage make(final String typename, final String... args) throws ClassNotFoundException;

	/**
	 * Constructs a decorated TextImage.
	 * @param typename The desired decorator.
	 * @param textImage The TextImage to be decorated.
	 * @return A decorated TextImage.
	 * @throws ClassNotFoundException If the given typename does not fit a Decorator.
	 */
	public abstract TextImage make(final String typename, final TextImage textImage) throws ClassNotFoundException;

}
