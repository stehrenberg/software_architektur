/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 4: Factory-Pattern
 */
package edu.hm.iny.patterns.factory;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.ClocktimeImage;
import edu.hm.iny.patterns.decorators.Distant;
import edu.hm.iny.patterns.decorators.Framed;
import edu.hm.iny.patterns.decorators.Modern;
import edu.hm.iny.patterns.decorators.Printable;
import edu.hm.iny.patterns.decorators.Rotated;
import edu.hm.iny.patterns.decorators.StringPicture;
import edu.hm.iny.patterns.decorators.Tiled;

/**
 * Factory that creates TextImage objects.
 * @version 2015-05-01
 */
public class SwitchedTextImageFactory extends TextImageFactory {

	/** Number of args needed by image type ClocktimeImage. */
	private static final int ARGS_NUMBER_CLOCKTIME = 2;
	/** Number of args needed by image type Modern. */
	private static final int ARGS_NUMBER_MODERN = 3;

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.factory.TextImageFactory#make(java.lang.String, java.lang.String[])
	 */
	@Override
	public TextImage make(final String typename, final String... args) throws ClassNotFoundException {

		checkArguments(typename, args);

		TextImage image;
		final int width;
		final int height;

		switch(typename) {
		case "Modern":
			final char pictureChar = args[0].charAt(0);
			width = parseDimension(args[1]);
			height = parseDimension(args[2]);
			image = new Modern(pictureChar, width, height);
			break;

		case "ClocktimeImage":
			width = parseDimension(args[0]);
			height = parseDimension(args[1]);
			image = new ClocktimeImage(width, height);
			break;

		default:
			image = new StringPicture(args);
			break;
		}

		return image;
	}

	/**
	 * Ensures that the given arguments are suitable for the desired TextImage type.
	 * @param imageType The desired TextImage type.
	 * @param args For TextImage creation, p.e. size or image char/strings.
	 * @throws ClassNotFoundException If stated imageType is not supported.
	 */
	private void checkArguments(final String imageType, final String... args) throws ClassNotFoundException {

		switch(imageType) {

		case "StringPicture":
			// all arguments are sufficient and will simply be taken as image strings
			break;

		case "ClocktimeImage":
			if(args.length > ARGS_NUMBER_CLOCKTIME) {
				throw new IllegalArgumentException("Arguments not suited for type " + imageType);
			}
			checkArgumentLength(args);
			break;

		case "Modern":
			if(args.length > ARGS_NUMBER_MODERN) {
				throw new IllegalArgumentException("Arguments not suited for type " + imageType);
			}
			checkArgumentLength(args);
			break;

		default:
			throw new ClassNotFoundException(imageType + " not supported.");
		}
	}

	/**
	 * Ensures that given arguments suit the TextImages Clocktime and Modern.
	 * @param args The arguments provided for TextImage creation.
	 */
	private void checkArgumentLength(final String... args) {

		for(final String string : args) {
			if(string.length() > 1)
				throw new IllegalArgumentException("Arguments not suited for desired type.");
		}
	}

	/**
	 * Parses width or height for TextImage construction.
	 * @param arg The strings provided as TextImage args.
	 * @return An int representing width or height.
	 */
	private int parseDimension(final String arg) {

		try {
			return Integer.parseInt(arg);
		}
		catch(final NumberFormatException nfExc) {
			throw new IllegalArgumentException("Argument must be an Integer!");
		}
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.factory.TextImageFactory#make(java.lang.String, edu.hm.cs.rs.arch.pattern.decorator.TextImage)
	 */
	@Override
	public TextImage make(final String typename, final TextImage image) throws ClassNotFoundException {

		TextImage decoratedImage;

		switch(typename) {

		case "Rotated":
			decoratedImage = new Rotated(image);
			break;

		case "Distant":
			decoratedImage = new Distant(image);
			break;

		case "Tiled":
			decoratedImage = new Tiled(image);
			break;

		case "Framed":
			decoratedImage = new Framed(image);
			break;

		case "Printable":
			decoratedImage = new Printable(image);
			break;

		default:
			throw new ClassNotFoundException("No Decorator matches type " + typename);
		}

		return decoratedImage;
	}
}
