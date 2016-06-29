/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 5: Observer Pattern
 */
package edu.hm.iny.patterns.observer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * A NumberPublisher implementation that is observable and stores numbers in a set.
 * @version 2015-05-04
 */
public class SetNumberPublisher extends NumberPublisher {

	/** Internal data structure for some numbers. */
	private final Set<Integer> numbers;

	/**
	 * Ctor.
	 */
	public SetNumberPublisher() {
		numbers = new HashSet<>();
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.observer.NumberPublisher#getNumbers()
	 */
	@Override
	public Collection<Integer> getNumbers() {
		return new HashSet<Integer>(numbers);
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.observer.NumberPublisher#addNumber(int)
	 */
	@Override
	public void addNumber(final int number) {
		if(number >= 0) {
			final boolean wasAddSuccessful = numbers.add(number);
			if(wasAddSuccessful) {
				setChanged();
				notifyObservers();
			}
		}
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.observer.NumberPublisher#deleteNumber(int)
	 */
	@Override
	public void deleteNumber(final int number) {
		final boolean setHasChanged = numbers.remove(number);
		if(setHasChanged) {
			setChanged();
			notifyObservers();
		}
	}
}
