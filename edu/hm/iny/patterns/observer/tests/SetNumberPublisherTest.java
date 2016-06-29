package edu.hm.iny.patterns.observer.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.iny.patterns.observer.NumberPublisher;
import edu.hm.iny.patterns.observer.SetNumberPublisher;

public class SetNumberPublisherTest {

	private static NumberPublisher numPub;

	@BeforeClass public static void setUpBeforeClass() throws Exception {
		numPub = new SetNumberPublisher();
	}

	@After public void tearDown() {
		final Collection<Integer> numbers = new HashSet<>(numPub.getNumbers());
		for(final Integer number : numbers)
			numPub.deleteNumber(number);
		assertTrue(numPub.getNumbers().size() == 0);
	}

	@Test public void testSimpleSetNumPub() {
		numPub.addNumber(1);
		assertTrue(numPub.getNumbers().contains(1));
		numPub.deleteNumber(1);
		assertFalse(numPub.getNumbers().contains(1));
	}

	@Test public void testNumberSetViewBehavior() {
		numPub.addNumber(0);
		final Collection<Integer> afterFirstInsert = new HashSet<Integer>(numPub.getNumbers());
		numPub.addNumber(3);
		final Collection<Integer> afterSecondInsert = new HashSet<Integer>(numPub.getNumbers());
		System.out.println("afterFirst: " + afterFirstInsert);
		System.out.println("afterSecond: " + afterSecondInsert);
	}

	@Test public void testDeletingNonexistentInt() {
		numPub.addNumber(0);
		numPub.addNumber(2);
		numPub.deleteNumber(1);
		assertEquals(2, numPub.getNumbers().size());
	}

	@Test public void testIntegerEquals() {
		assertFalse(new Integer(1_333_337) == new Integer(1_333_337));
		numPub.addNumber(new Integer(1_333_337));
		assertTrue(numPub.getNumbers().contains(new Integer(1_333_337)));
	}

	@Test public void testInvalidAddition() {
		numPub.addNumber(-2);
		assertFalse(numPub.getNumbers().contains(-2));
	}

	@Test public void testMultipleAddition() {
		numPub.addNumber(1);
		numPub.addNumber(1);
		numPub.addNumber(1);

		final Collection<Integer> numbers = numPub.getNumbers();
		int elementCount = 0;
		for(final Integer number : numbers)
			if(number.equals(1))
				elementCount++;
		assertEquals(1, elementCount);
	}
}

