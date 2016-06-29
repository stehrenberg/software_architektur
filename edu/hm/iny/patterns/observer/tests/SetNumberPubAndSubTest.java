package edu.hm.iny.patterns.observer.tests;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.iny.patterns.observer.AnyNumberSubscriber;
import edu.hm.iny.patterns.observer.NumberPublisher;
import edu.hm.iny.patterns.observer.NumberSubscriber;
import edu.hm.iny.patterns.observer.SetNumberPublisher;
import edu.hm.iny.patterns.observer.SkippingNumberSubscriber;

public class SetNumberPubAndSubTest {

	private static NumberSubscriber numSub;
	private static NumberPublisher numPub;
	private static NumberSubscriber skipNumSub;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		numPub = new SetNumberPublisher();
		numSub = new AnyNumberSubscriber(numPub, () -> System.out.println("AnyNumSub was here!"));
		skipNumSub = new SkippingNumberSubscriber(numPub, 2, numSub);
	}

	@Test
	public void anyNumberSubOutputTest() throws InterruptedException {

		numPub.addNumber(1);		// Output
		Thread.sleep(500);
		numPub.addNumber(2);		// Output
		Thread.sleep(500);
		numPub.addNumber(1);		// no Output
		Thread.sleep(500);
		numPub.deleteNumber(1);		// Output
		Thread.sleep(500);
		numPub.deleteNumber(1);		// no Output
	}

	@Test
	public void skippingNumberSubOutputTest() throws InterruptedException {

		numPub.addNumber(1);		// Output
		Thread.sleep(500);
		numPub.addNumber(2);		// Output
		Thread.sleep(500);
		numPub.addNumber(1);		// no Output
		Thread.sleep(500);
		numPub.deleteNumber(1);		// Output
		Thread.sleep(500);
		numPub.deleteNumber(1);		// no Output
	}
}
