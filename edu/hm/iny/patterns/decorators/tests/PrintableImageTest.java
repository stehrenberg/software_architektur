package edu.hm.iny.patterns.decorators.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.ClocktimeImage;
import edu.hm.iny.patterns.decorators.Distant;
import edu.hm.iny.patterns.decorators.Framed;
import edu.hm.iny.patterns.decorators.Modern;
import edu.hm.iny.patterns.decorators.Printable;
import edu.hm.iny.patterns.decorators.Rotated;
import edu.hm.iny.patterns.decorators.StringPicture;
import edu.hm.iny.patterns.decorators.Tiled;

public class PrintableImageTest {

	private static TextImage stringPicture;
	private static TextImage modernPicture;
	private static TextImage clocktimePicture;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		final String[] imageStrings = new String[] {
				"Balkon",
				"im",
				"Grünen"
		};

		stringPicture = new StringPicture(imageStrings);
		modernPicture = new Modern('$', 3, 5);
		clocktimePicture = new ClocktimeImage(10, 10);
	}

	@Test
	public void simplePrintTest() {

		System.out.println(new Printable(new Framed(stringPicture))	+ "\n");
		System.out.println(new Printable(new Framed(new Tiled(stringPicture))) + "\n");
		System.out.println(new Printable(new Framed(modernPicture)) + "\n");
		System.out.println(new Printable(new Framed(new Rotated(stringPicture))) + "\n");
		System.out.println(new Printable(new Rotated(new Framed(stringPicture))) + "\n");
		System.out.println(new Printable(new Rotated(new Framed(modernPicture))) + "\n");
		System.out.println(new Printable(new Framed(new Distant(stringPicture))) + "\n");
		System.out.println(new Printable(new Distant(stringPicture)) + "\n");
		System.out.println(new Printable(clocktimePicture));

		assertEquals("Blo\nGüe", new Printable(new Distant(stringPicture)).toString());
	}
}
