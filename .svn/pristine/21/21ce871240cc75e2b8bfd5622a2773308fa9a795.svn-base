package edu.hm.iny.patterns.factory.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.iny.patterns.decorators.ClocktimeImage;
import edu.hm.iny.patterns.decorators.Modern;
import edu.hm.iny.patterns.decorators.StringPicture;
import edu.hm.iny.patterns.factory.SwitchedTextImageFactory;

public class TestSwitchedTextImageFactory {

	private static SwitchedTextImageFactory swFactory;
	private static String[] pictureStrings;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		swFactory = new SwitchedTextImageFactory();
		pictureStrings = new String[] {
				"Balkon",
				"im",
				"Grünen"
		};
	}

	@Test
	public void simpleTest() {
		assertEquals(StringPicture.class,
				swFactory.make("StringPicture", pictureStrings).getClass());
		assertEquals(Modern.class,
				swFactory.make("Modern", "&", "3", "5").getClass());
		assertEquals(ClocktimeImage.class,
				swFactory.make("ClocktimeImage", "3", "5").getClass());
	}
}
