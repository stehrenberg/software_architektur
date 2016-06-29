package edu.hm.iny.patterns.factory.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.StringPicture;
import edu.hm.iny.patterns.factory.FakeTextImageFactory;
import edu.hm.iny.patterns.factory.TextImageFactory;

public class TestFakeTextImageFactory {

	private static TextImageFactory factory;
	private static TextImage image;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = new FakeTextImageFactory();
		image = new StringPicture("bla");
	}

	@Test
	public void getFakeTextImageFactoryFromLocate() throws ClassNotFoundException {
		System.setProperty("factorytype", "FakeTextImageFactory");
		assertEquals(FakeTextImageFactory.class, TextImageFactory.locate().getClass());
	}

	@Test
	public void simplePositiveTest() throws ClassNotFoundException {

		assertEquals(1, factory.make("ClocktimeImage", "3", "5").getWidth());
		assertEquals(1, factory.make("ClocktimeImage", "3", "5").getHeight());
		assertEquals('A', factory.make("ClocktimeImage", "3", "5").charAt(2, 4));
		assertEquals("A", factory.make("ClocktimeImage", "3", "5").toString());
	}

	@Test
	public void makeDecoratedImageTest() throws ClassNotFoundException {
		// Ueberpruefung auf Identitaet!
		assertTrue(image == factory.make("Tiled", image));
	}

}
