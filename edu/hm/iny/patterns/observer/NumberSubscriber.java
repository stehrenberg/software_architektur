/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 5: Observer Pattern
 */
package edu.hm.iny.patterns.observer;

import java.util.Observer;

/**
 * Basic interface for a observer class that subscribes to a NumberPublisher.
 * @version 2015-05-04
 */
public interface NumberSubscriber extends Observer {

	/**
	 * Returns the corresponding publisher.
	 * @return The publisher the subscriber's been subscribed to.
	 */
	NumberPublisher getPublisher();
}