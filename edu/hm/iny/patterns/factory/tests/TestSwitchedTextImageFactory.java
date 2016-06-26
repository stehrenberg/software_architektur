package edu.hm.iny.patterns.factory.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.iny.patterns.decorators.ClocktimeImage;
import edu.hm.iny.patterns.decorators.Modern;
import edu.hm.iny.patterns.decorators.StringPicture;
import edu.hm.iny.patterns.factory.SwitchedTextImageFactory;
import edu.hm.iny.patterns.factory.TextImageFactory;

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
	public void getSwitchedTextImageFactoryFromLocate() throws ClassNotFoundException {
		System.setProperty("factorytype", "SwitchedTextImageFactory");
		assertEquals(SwitchedTextImageFactory.class, TextImageFactory.locate().getClass());
	}

	@Test
	public void simplePositiveTest() throws ClassNotFoundException {
		TextImageFactory.locate();

		assertEquals(StringPicture.class,
				swFactory.make("StringPicture", pictureStrings).getClass());
		assertEquals(Modern.class,
				swFactory.make("Modern", "&", "3", "5").getClass());
		assertEquals(ClocktimeImage.class,
				swFactory.make("ClocktimeImage", "3", "5").getClass());
	}


	@Test (expected = ClassNotFoundException.class)
	public void unsupportedImageTypeTest() throws ClassNotFoundException {
		swFactory.make("BlaPic", "&", "3", "1");
	}


	@Test(expected = IllegalArgumentException.class)
	public void tooManyArgumentsTest() throws ClassNotFoundException {

		swFactory.make("Modern", "&", "3", "1", "2");
		swFactory.make("ClocktimeImage", "3", "5", "3");
	}


	@Test(expected = IllegalArgumentException.class)
	public void wrongArgumentsTest() throws ClassNotFoundException {
		swFactory.make("Modern", "bla", "3", "1");
		swFactory.make("ClocktimeImage", "3", "b");
	}
}
