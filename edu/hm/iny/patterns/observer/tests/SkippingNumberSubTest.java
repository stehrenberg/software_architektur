package edu.hm.iny.patterns.observer.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.iny.patterns.observer.AnyNumberSubscriber;
import edu.hm.iny.patterns.observer.NumberPublisher;
import edu.hm.iny.patterns.observer.NumberSubscriber;
import edu.hm.iny.patterns.observer.SetNumberPublisher;
import edu.hm.iny.patterns.observer.SkippingNumberSubscriber;

public class SkippingNumberSubTest {

	private static NumberPublisher numPub;
	private static AnyNumberSubscriber numSub;
	private static MockSkippingNumSubDecorator secondCallNumSub;
	private static MockSkippingNumSubDecorator thirdCallNumSub;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		numPub = new SetNumberPublisher();
	}

	@Test
	public void simpleTest() throws InterruptedException {
		//final y
		numSub = new AnyNumberSubscriber(numPub, ()-> System.out.println(numPub.getNumbers()));
		//		secondCallNumSub = new SkippingNumSubDecorator(numPub, 2, numSub);
		//		thirdCallNumSub = new kippingNumSubDecorator(numPub, 3, numSub);

		numPub.addNumber(3);
		Thread.sleep(500);
		numPub.addNumber(5);
		Thread.sleep(500);
		numPub.addNumber(5);	// nüscht
		Thread.sleep(500);
		numPub.addNumber(7);	// 2x Output - normal + 3er
		Thread.sleep(500);
		numPub.addNumber(9);	// 2x Output - normal + 2er
		Thread.sleep(500);
		numPub.addNumber(11);	// 1x Output
		Thread.sleep(500);
		numPub.addNumber(42);	// 3x Output - alle

	}

	private class MockBasicNumberSubscriber extends AnyNumberSubscriber {

		private final List<NumberSubscriber> callingSubs;

		MockBasicNumberSubscriber(final NumberPublisher pub, final Runnable doStuff) {
			super(pub, doStuff);
			callingSubs = new ArrayList<>();
		}

		void update(final Observable pub, final NumberSubscriber callingSub) {
			callingSubs.add(callingSub);
			super.update(pub, null);
		}

		List<NumberSubscriber> getCallingSubs() {
			return callingSubs;
		}

		void clearSubCalls() {
			callingSubs.clear();
		}
	}

	private class MockSkippingNumSubDecorator extends SkippingNumberSubscriber {

		private final MockBasicNumberSubscriber sub;

		public MockSkippingNumSubDecorator(final NumberPublisher pub, final int nth,
				final MockBasicNumberSubscriber sub) {
			super(pub, nth, sub);
			this.sub = sub;
		}

		@Override public void update(final Observable pub, final Object arg){
			super.update(pub, this);
		}
	}
}
