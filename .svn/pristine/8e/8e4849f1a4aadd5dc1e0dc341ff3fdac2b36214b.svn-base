package edu.hm.iny.patterns.decorators.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.Framed;
import edu.hm.iny.patterns.decorators.Modern;
import edu.hm.iny.patterns.decorators.StringPicture;

public class FramedTextImageTest {

	@Test
	public void testSimpleFramedTextImage() {

		final TextImage stringImage = new StringPicture("bla", "bli", "blu");
		final TextImage framedImage = new Framed(stringImage);

		assertEquals(framedImage.getWidth(), stringImage.getWidth() + 2);
		assertEquals(framedImage.getHeight(), stringImage.getHeight() + 2);

		// test frame corners
		assertEquals(framedImage.charAt(0, 0), '+');
		assertEquals(framedImage.charAt(4, 0), '+');
		assertEquals(framedImage.charAt(4, 4), '+');
		assertEquals(framedImage.charAt(0, 4), '+');
		// test vertical frame
		assertEquals(framedImage.charAt(0, 3), '|');
		assertEquals(framedImage.charAt(4, 3), '|');
		// test horizontal frame
		assertEquals(framedImage.charAt(3, 0), '-');
		assertEquals(framedImage.charAt(3, 4), '-');

		// test actual picture
		assertEquals(framedImage.charAt(3, 3), stringImage.charAt(2, 2));
		assertEquals(framedImage.charAt(1, 1), stringImage.charAt(0, 0));
	}

	@Test
	public void testEmptyFramedTextImage() {

		final TextImage modern = new Modern(' ', 0, 0);
		final TextImage framedModern = new Framed(modern);

		assertEquals(framedModern.getWidth(), modern.getWidth() + 2);
		assertEquals(framedModern.getHeight(), modern.getHeight() + 2);

		assertEquals(framedModern.charAt(0, 0), '+');
		assertEquals(framedModern.charAt(1,0), '+');
		assertEquals(framedModern.charAt(0,1), '+');
		assertEquals(framedModern.charAt(1,1), '+');
	}
}
