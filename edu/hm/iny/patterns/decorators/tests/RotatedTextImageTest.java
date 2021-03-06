package edu.hm.iny.patterns.decorators.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.Rotated;
import edu.hm.iny.patterns.decorators.StringPicture;

public class RotatedTextImageTest {

	private static TextImage image;
	private static TextImage rotatedImage;
	private static TextImage fourTimesRotatedImage;

	@BeforeClass public static void setUp() {
		image = new StringPicture("bla", "gedoens", "piep", "lalala");
		rotatedImage = new Rotated(image);
		fourTimesRotatedImage = new Rotated(new Rotated(new Rotated(new Rotated(image))));
	}

	@Test
	public void testSimpleRotatedImage() {

		assertEquals(image.getHeight(), rotatedImage.getWidth());
		assertEquals(image.getWidth(), rotatedImage.getHeight());

		assertEquals(image.charAt(2, 0), rotatedImage.charAt(0, rotatedImage.getHeight()-1 - 2));
		assertEquals(image.charAt(0, 0), rotatedImage.charAt(0, rotatedImage.getHeight()-1));
	}

	@Test
	public void testFourTimesRotatedImage() {
		for(int row = 0; row < fourTimesRotatedImage.getHeight(); row++) {
			for (int col = 0; col < fourTimesRotatedImage.getWidth(); col++) {
				assertEquals(image.charAt(col, row), fourTimesRotatedImage.charAt(col, row));
			}
		}
	}
}