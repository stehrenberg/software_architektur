package edu.hm.iny.patterns.decorators.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.Modern;

public class ModernTest {

	@Test
	public void testSimpleModernImage() {
		final int imageSize = 3;
		final char imageChar = '&';

		final TextImage modernImage = new Modern(imageChar, imageSize, imageSize);
		assertEquals(imageSize, modernImage.getHeight());
		assertEquals(imageChar, modernImage.charAt(0, 0));
		assertEquals(imageChar, modernImage.charAt(2, 2));
	}

	@Test
	public void testInvalidCoordinates() {
		final int imageSize = 3;
		final char imageChar = '&';
		final TextImage modernImage = new Modern(imageChar, imageSize, imageSize);

		try {
			modernImage.charAt(3, 3);
		}
		catch(final NoSuchElementException nseExc) {
			assertTrue(true);
		}
	}

	//@Test
	//public void test___() {
	//}
}
