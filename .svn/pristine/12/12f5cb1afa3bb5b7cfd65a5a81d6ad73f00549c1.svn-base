package edu.hm.iny.patterns.factory;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.Modern;
import edu.hm.iny.patterns.decorators.StringPicture;

public class SwitchedTextImageFactory implements TextImageFactory {

	/**
	 *
	 * @param typename
	 * @param args
	 * @return
	 */
	public TextImage make(final String typename, final String... args) {

		checkArguments(typename, args);
		TextImage image;

		switch(typename) {
		case "Modern":
			final char pictureChar = args[0].charAt(0);
			final int width = Integer.parseInt(args[1]);
			final int height = Integer.parseInt(args[2]);
			image = new Modern(pictureChar, width, height);
			break;
		default:
			image = new StringPicture(args);
			break;
		}


		return image;
	}

	/**
	 *
	 * @param imageType
	 * @param args
	 */
	private void checkArguments(final String imageType, final String... args) {

		switch(imageType) {

		case("Modern"):
			for(final String string : args) {
				if(string.length() > 1)
					throw new IllegalArgumentException("Arguments not suited for type " + imageType);
			}
		break;
		}
	}

	/**
	 *
	 * @param typename
	 * @param other
	 * @return
	 */
	//	public TextImage make(final String typename, final TextImage other) {
	//
	//	}
}
