/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 5: Observer Pattern
 */
package edu.hm.iny.patterns.observer;

import java.util.Collection;
import java.util.Observable;

/**
 * With every change from the publisher, this class adds the increment of
 * the recently added number to the publisher's set and ignores the resulting update.
 * @version 2015-05-09
 */
public class AddingNextNumberSubscriber extends NumberSubscriberDecorator {

	/** To indicate wether to ignore an update or perform it. */
	private boolean suppressUpdate;
	/** To determine which number was added with the last update. */
	private Collection<Integer> numSetFromLastUpdate;

	/**
	 * Ctor.
	 * @param pub The publisher to subscribe to.
	 * @param sub The subscriber to be decorated.
	 */
	public AddingNextNumberSubscriber(final NumberPublisher pub, final NumberSubscriber sub) {
		super(pub, sub);
		suppressUpdate = false;
		numSetFromLastUpdate = getCurrentNumberSetFromPub(pub);
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.observer.NumberSubscriberDecorator#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(final Observable observable, final Object arg) {

		super.update(observable, arg);

		if(!suppressUpdate) {
			final NumberPublisher pub = super.getPublisher();
			final Collection<Integer> numberSet = getCurrentNumberSetFromPub(pub);
			final boolean addNumbercausedUpdate = numberSet.size() > numSetFromLastUpdate.size();
			if(addNumbercausedUpdate) {
				int addedNumber = getNewlyAddedNumber(numberSet);
				suppressUpdate = true;
				pub.addNumber(++addedNumber);
				numSetFromLastUpdate = getCurrentNumberSetFromPub(pub);
			}
		}
		suppressUpdate = false;
	}

	/**
	 * Retrieves a copy of the publisher's current number set.
	 * @param pub The publisher.
	 * @return A Integer Collection containing the current number set.
	 */
	private Collection<Integer> getCurrentNumberSetFromPub(final NumberPublisher pub) {
		return pub.getNumbers();
	}

	/** Determines and returns the element from the publisher's set whose addition
	 * caused the update.
	 * @param numberSet The publisher's number set
	 * @return The Integer element that was newly added to the set.
	 */
	private Integer getNewlyAddedNumber(final Collection<Integer> numberSet) {

		numberSet.removeAll(numSetFromLastUpdate);
		if(numberSet.size() != 1)
			throw new AssertionError("Pub's set should not contain more than one new element!");
		final int addedNumber = numberSet.iterator().next();

		return addedNumber;
	}
}
