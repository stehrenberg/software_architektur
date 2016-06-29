/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 5: Observer Pattern
 */
package edu.hm.iny.patterns.observer;

import java.util.Observable;

/**
 * A subscriber decorator that only reacts to every nth update from the publisher
 * it subscribed to.
 * @version 2015-05-08
 */
public class SkippingNumberSubscriber extends NumberSubscriberDecorator {


	/** Indicates the intervall of changes from the pub that triggers an update().*/
	private final int nthCall;
	/** Keeps track of the total changes from subscriber. */
	private int totalNumberOfCalls;

	/**
	 * Ctor.
	 * @param pub The publisher to subscribe to.
	 * @param nth Only every nth change from the pub will trigger update().
	 * @param sub The decorated subscriber.
	 */
	public SkippingNumberSubscriber(final NumberPublisher pub, final int nth,
			final NumberSubscriber sub) {

		super(pub, sub);
		if(nth < 1)
			throw new IllegalArgumentException("nth must be positive!");
		nthCall = nth;
		totalNumberOfCalls = 0;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(final Observable observable, final Object arg) {
		totalNumberOfCalls++;
		if(totalNumberOfCalls % nthCall == 0)
			super.update(observable, null);
	}
}
