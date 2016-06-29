/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 5: Observer Pattern
 */
package edu.hm.iny.patterns.observer;

import java.util.Observable;

/**
 * Basic Decorator class for a NumberSubscriber.
 * @version 2015-04-08
 */
abstract class NumberSubscriberDecorator implements NumberSubscriber {

	/** The publisher to subscribe to. */
	private final NumberPublisher publisher;
	/** The decorated subscriber object. */
	private final NumberSubscriber subscriber;

	/**
	 * Ctor.
	 * @param pub The publisher to subscribe to.
	 * @param sub The decorated subscriber.
	 */
	NumberSubscriberDecorator(final NumberPublisher pub, final NumberSubscriber sub) {
		publisher = pub;
		subscriber = sub;
		subscriber.getPublisher().deleteObserver(subscriber);
		publisher.addObserver(this);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(final Observable observable, final Object arg) {
		subscriber.update(observable, arg);
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.observer.NumberSubscriber#getPublisher()
	 */
	@Override
	public NumberPublisher getPublisher() {
		return publisher;
	}

}
