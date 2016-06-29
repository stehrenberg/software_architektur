package edu.hm.iny.patterns.factory.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.Framed;
import edu.hm.iny.patterns.decorators.StringPicture;
import edu.hm.iny.patterns.factory.CachedTextImageFactory;
import edu.hm.iny.patterns.factory.TextImageFactory;

public class TestCachedTextImageFactory {

	private static TextImageFactory cachingFactory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("factorytype", "CachedTextImageFactory");
	}

	@Test
	public void setupTest() {
		assertEquals(CachedTextImageFactory.class, cachingFactory.getClass());
	}

	@Test
	public void simplePositiveTest() throws ClassNotFoundException {

		System.setProperty("cachecapacity", "2");
		cachingFactory = TextImageFactory.locate();

		final TextImage stringImg = cachingFactory.make("StringPicture", "bla");
		assertTrue(
				stringImg == cachingFactory.make("StringPicture", "bla"));

		final TextImage modernImg = cachingFactory.make("Modern", "$", "2", "2");
		assertTrue(
				modernImg == cachingFactory.make("Modern", "$", "2", "2"));

		final TextImage clocktimeImg = cachingFactory.make("ClocktimeImage", "3", "3");
		assertTrue(
				clocktimeImg == cachingFactory.make("ClocktimeImage", "3", "3"));
	}

	// TODO Refactoring Vorschlag: Expected und Actual Variablen einfuehren!
	@Test
	public void cacheLimitTest() throws ClassNotFoundException {

		System.setProperty("cachecapacity", "2");
		cachingFactory = TextImageFactory.locate();

		final TextImage stringImg = cachingFactory.make("StringPicture", "bla");
		assertTrue(
				stringImg == cachingFactory.make("StringPicture", "bla"));

		final TextImage modernImg = cachingFactory.make("Modern", "$", "2", "2");
		assertTrue(
				modernImg ==cachingFactory.make("Modern", "$", "2", "2"));

		final TextImage clocktimeImg = cachingFactory.make("ClocktimeImage", "3", "3");
		assertTrue(
				clocktimeImg == cachingFactory.make("ClocktimeImage", "3", "3"));

		// sollte jetzt aus dem Cache mit Limit 2 rausgefallen sein, also muss neues
		// StringPicture erzeugt werden!
		assertTrue(stringImg != cachingFactory.make("StringPicture", "bla"));
	}

	@Test
	public void makeDecoratedImagesTest() throws ClassNotFoundException {

		System.setProperty("cachecapacity", "2");
		cachingFactory = TextImageFactory.locate();

		final TextImage stringPic = new StringPicture("bla");
		final TextImage someFramedStringImg = new Framed(stringPic);
		final TextImage framedImgString = cachingFactory.make("Framed", stringPic);
		final TextImage anotherFramedStringImg = cachingFactory.make("Framed", stringPic);
		assertTrue(anotherFramedStringImg == framedImgString);
		assertEquals(someFramedStringImg, framedImgString);
	}

	@Test
	public void defaultCapacityTest() throws ClassNotFoundException {

		cachingFactory = TextImageFactory.locate();
		final String randomChars = "!§$%&/()*+~'#";

		final TextImage firstImg = cachingFactory.make("Modern", "<", "1", "1");

		// flooding the cache with 10 images
		for(int index = 0; index < 10; index++) {
			cachingFactory.make("Modern", "" + randomChars.charAt(index), "1", "1");

			// cache should be full now, next iteration will kick "firstImg" out of the cache
			if(index == 8)
				assertTrue(firstImg == cachingFactory.make("Modern", "<", "1", "1"));
			// cache should not contain "firstImg" anymore
			if(index == 9)
				assertTrue(firstImg != cachingFactory.make("Modern", "<", "1", "1"));
		}

	}
}
