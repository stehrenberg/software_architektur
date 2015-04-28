package edu.hm.iny.patterns.decorators.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.StringPicture;
import edu.hm.iny.patterns.decorators.Tiled;

public class TiledTextImageTest {

	private static TextImage tiledImage;
	private static TextImage stringPicture;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		final String[] imageStrings = new String[] {
				"balkon",
				"im",
				"Grünen"
		};

		stringPicture = new StringPicture(imageStrings);
		tiledImage = new Tiled(new StringPicture(imageStrings));
	}

	@Test
	public void simpleTest() {
		assertEquals(stringPicture.charAt(0, 0), tiledImage.charAt(stringPicture.getWidth(), stringPicture.getHeight()));
	}

}
