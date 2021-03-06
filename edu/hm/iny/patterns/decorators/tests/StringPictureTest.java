package edu.hm.iny.patterns.decorators.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.StringPicture;

public class StringPictureTest {

	@Test
	public void testSimpleStringImage() {

		final String testString = "Hello";
		final TextImage image = new StringPicture(testString);
		assertEquals(testString.length(), image.getWidth());
		assertEquals(1, image.getHeight());
		assertEquals(testString.charAt(0), image.charAt(0, 0));
	}

	@Test
	public void testWrongCoordinates() {

		final String testString = "Hello";
		final TextImage image = new StringPicture(testString);
		try {
			image.charAt(0, 3);
		} catch(final NoSuchElementException nseExc) {
			assertTrue(true);
		}
	}

	@Test
	public void testEmptyStringPicture() {
		final String emptyString = "";
		final TextImage emptyImage = new StringPicture(emptyString);
		assertEquals(0, emptyImage.getWidth());
		assertEquals(1, emptyImage.getHeight());
		try {
			assertEquals('\0', emptyImage.charAt(0,0));
		} catch (final NoSuchElementException nseExc) {
			assertTrue(true);
		}
	}

	@Test
	public void testEmptyPicture() {
		final String nullString = null;
		try {
			final TextImage emptyImage = new StringPicture(nullString);
		} catch(final NullPointerException nseExc) {
			assertTrue(true);
		}
	}

	@Test
	public void testLargerImage() {

		final String[] imageStrings = new String[]{"Hallo", "du", "da!"};
		final TextImage threeCrossFiveImage = new StringPicture(imageStrings);
		assertEquals(imageStrings.length, threeCrossFiveImage.getHeight());
		assertEquals(imageStrings[0].length(), threeCrossFiveImage.getWidth());
		assertEquals(imageStrings[2].charAt(1), threeCrossFiveImage.charAt(1, 2));
		assertEquals(' ', threeCrossFiveImage.charAt(4, 2));
		assertEquals(' ', threeCrossFiveImage.charAt(3, 1));

		// Auslagern in eigene Testmethode
		try {
			threeCrossFiveImage.charAt(imageStrings.length + 1, 0);
		} catch (final NoSuchElementException nseExc) {
			assertTrue(true);
		}


	}
}
