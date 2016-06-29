/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 5: Observer Pattern
 */
package edu.hm.iny.patterns.observer;

import java.util.Observable;

/**
 * A subscriber that stores some random Runnable that is executed with every call
 * to the Subsriber's update().
 * @version 2015-05-04
 */
public class AnyNumberSubscriber implements NumberSubscriber {

	/** A publisher instance to subscribe to. */
	private final NumberPublisher publisher;
	/** Method that will be executed at every update(). */
	private final Runnable doStuffAtUpdate;

	/**
	 * Ctor.
	 * @param publisher Some publisher instance that can be subscribed to.
	 * @param runnable A method that is executed with every call to update().
	 */
	public AnyNumberSubscriber(final NumberPublisher publisher, final Runnable runnable) {
		this.publisher = publisher;
		doStuffAtUpdate = runnable;
		this.publisher.addObserver(this);
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.observer.NumberSubscriber#getPublisher()
	 */
	@Override
	public NumberPublisher getPublisher() {
		return publisher;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(final Observable observable, final Object arg) {
		new Thread(doStuffAtUpdate).start();
	}

}
