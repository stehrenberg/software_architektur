package edu.hm.iny.patterns.decorators.tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;
import edu.hm.iny.patterns.decorators.ClocktimeImage;

public class ClocktimeImageTest {

	private char currentHourTens;
	private char currentHourOnes;
	private char currentMinutesTens;
	private char currentMinutesOnes;
	private char currentSecondsTens;
	private char currentSecondsOnes;

	@Before
	public void setUp() {
		currentHourTens = convertToChar(LocalTime.now().getHour()/10);
		currentHourOnes = convertToChar(LocalTime.now().getHour()%10);
		currentMinutesTens = convertToChar(LocalTime.now().getMinute()/10);
		currentMinutesOnes = convertToChar(LocalTime.now().getMinute()%10);
		currentSecondsTens = convertToChar(LocalTime.now().getSecond()/10);
		currentSecondsOnes = convertToChar(LocalTime.now().getSecond()%10);
	}

	@Test
	public void testSimpleClocktimeImage() {

		final int imageSize = 6;
		final TextImage clockPic = new ClocktimeImage(imageSize, imageSize);

		assertEquals(currentHourTens, clockPic.charAt(0, 0));

		assertEquals(currentHourOnes, clockPic.charAt(1, 0));
		assertEquals(currentHourOnes, clockPic.charAt(0, 1));

		assertEquals(currentMinutesTens, clockPic.charAt(2, 0));
		assertEquals(currentMinutesTens, clockPic.charAt(1, 1));
		assertEquals(currentMinutesTens, clockPic.charAt(0, 2));

		assertEquals(currentMinutesOnes, clockPic.charAt(3, 0));
		assertEquals(currentMinutesOnes, clockPic.charAt(2, 1));
		assertEquals(currentMinutesOnes, clockPic.charAt(1, 2));
		assertEquals(currentMinutesOnes, clockPic.charAt(0, 3));

		assertEquals(currentSecondsTens, clockPic.charAt(4, 0));
		assertEquals(currentSecondsTens, clockPic.charAt(3, 1));
		assertEquals(currentSecondsTens, clockPic.charAt(2, 2));
		assertEquals(currentSecondsTens, clockPic.charAt(1, 3));
		assertEquals(currentSecondsTens, clockPic.charAt(0, 4));

		assertEquals(currentSecondsOnes, clockPic.charAt(5, 0));
		assertEquals(currentSecondsOnes, clockPic.charAt(4, 1));
		assertEquals(currentSecondsOnes, clockPic.charAt(3, 2));
		assertEquals(currentSecondsOnes, clockPic.charAt(2, 3));
		assertEquals(currentSecondsOnes, clockPic.charAt(1, 4));
		assertEquals(currentSecondsOnes, clockPic.charAt(0, 5));
	}

	@Test
	public void testSmallClocktimeImage() {
		final int imageSize = 3;
		final TextImage clockPic = new ClocktimeImage(imageSize, imageSize);

		assertEquals(currentHourTens, clockPic.charAt(0, 0));
		assertEquals(currentHourOnes, clockPic.charAt(1, 0));
		assertEquals(currentHourOnes, clockPic.charAt(0, 1));
		assertEquals(currentMinutesTens, clockPic.charAt(2, 0));
		assertEquals(currentMinutesTens, clockPic.charAt(1, 1));
		assertEquals(currentMinutesTens, clockPic.charAt(0, 2));

	}

	private char convertToChar(final int digit) {
		return (char)(digit + 48);
	}
}
