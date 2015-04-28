package edu.hm.iny.patterns.decorators.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.Distant;
import edu.hm.iny.patterns.decorators.StringPicture;

public class DistantTextImageTest {

	private static TextImage oddRowNumberStringPicture;
	private static TextImage evenRowNumberStringPic;
	private static TextImage distantTextImageOddRows;
	private static TextImage distantTextImageEvenRows;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		final String[] imgStringsOddNumber = new String[] {
				"Balkon",
				"im",
				"Grünen"
		};

		final String[] imgStringsEvenNumber = new String[] {
				"Balkon",
				"im",
				"Grünen",
				"mit raschelnden Palmen"
		};

		oddRowNumberStringPicture = new StringPicture(imgStringsOddNumber);
		evenRowNumberStringPic = new StringPicture(imgStringsEvenNumber);
		distantTextImageOddRows = new Distant(oddRowNumberStringPicture);
		distantTextImageEvenRows = new Distant(evenRowNumberStringPic);
	}

	@Test
	public void oddRowNumberTest() {

		assertEquals(3, distantTextImageOddRows.getWidth());
		assertEquals(2, distantTextImageOddRows.getHeight());

		assertEquals(oddRowNumberStringPicture.charAt(0,0), distantTextImageOddRows.charAt(0,0));
		assertEquals(oddRowNumberStringPicture.charAt(4,0), distantTextImageOddRows.charAt(2,0));
		assertEquals(oddRowNumberStringPicture.charAt(2,0), distantTextImageOddRows.charAt(1,0));
		assertEquals(oddRowNumberStringPicture.charAt(2,2), distantTextImageOddRows.charAt(1,1));
	}

	@Test
	public void evenRowNumberTest() {
		assertEquals(11, distantTextImageEvenRows.getWidth());
		assertEquals(2, distantTextImageEvenRows.getHeight());
	}
}
