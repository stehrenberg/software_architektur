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
 * Basic observable class that defines methods for a publisher that stores numbers.
 * @version 2015-05-04
 */
public abstract class NumberPublisher extends Observable {

	/**
	 * Returns a collection containing the publisher's number set.
	 * @return A collection containing the publisher's number set.
	 */
	public abstract Collection<Integer> getNumbers();

	/**
	 * Deletes a number from the collection. Effectless when the number is missing
	 * from the collection.
	 * @param number The number to be deleted.
	 */
	public abstract void deleteNumber(int number);

	/**
	 * Adds a number to the collection. Ignores duplicate and negative entries.
	 * @param number Some number to add to the collection.
	 */
	public abstract void addNumber(int number);
}
