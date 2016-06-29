package edu.hm.iny.patterns.observer.tests;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.iny.patterns.observer.AddingNextNumberSubscriber;
import edu.hm.iny.patterns.observer.AnyNumberSubscriber;
import edu.hm.iny.patterns.observer.NumberPublisher;
import edu.hm.iny.patterns.observer.NumberSubscriber;
import edu.hm.iny.patterns.observer.SetNumberPublisher;

public class AddingNextNumSubTest {

	private static NumberPublisher numPub;
	private static NumberSubscriber numSub;
	private static NumberSubscriber addNextNumSub;
	private static NumberSubscriber anotherAddNextNumSub;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		numPub = new SetNumberPublisher();
		numSub = new AnyNumberSubscriber(numPub, () -> System.out.println(numPub.getNumbers()));
		addNextNumSub = new AddingNextNumberSubscriber(numPub, numSub);
		anotherAddNextNumSub = new AddingNextNumberSubscriber(numPub, numSub);
	}

	@Test
	public void test() throws InterruptedException {
		numPub.addNumber(3);
		Thread.sleep(500);
		assertTrue(numPub.getNumbers().contains(4));
	}

}
