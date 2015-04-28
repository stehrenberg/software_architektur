/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik
 * Praktikum Software-Architektur, Sommersemester 2015
 * OS: Windows 7 Professional SP1 (64 Bit); Java-Version: 1.8.0_05
 * CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 1 - Java Idiome: Objektvergleich mit equals
 */

package edu.hm.iny.idioms.equals;

/**
 * Determines if an object's equals-Method is sloppily implemented, meaning...
 * ...usage of instanceof to determine if two objects are of the same class
 * ...specifying the wrong method signature, not using Object as parameter type
 * ...missing the call to super.equals() if a class extends another class
 * @author Stephanie Ehrenberg
 * @version 2015-03-26
 */
public class BuggyEquals {

	/* Some value that can be used as arguments for classes X and Y. The actual value is not important. */
	private static final int ARBITRARY_VALUE = 42;

	/**
	 * Determines whether an object's equals-Method uses instanceof
	 * to determine if two objects are of the same class.
	 * @return true, if object's equals() uses instanceof
	 * 			false otherwise
	 */
	public static boolean equalsHasBrokenTypecheck() {

		final X someX = new X(ARBITRARY_VALUE);
		// Y is derived from X!
		final X someY = new Y(ARBITRARY_VALUE, ARBITRARY_VALUE);
		final boolean xEqualsY = someX.equals(someY);
		final boolean yEqualsX = someY.equals(someX);

		System.out.println(xEqualsY);
		System.out.println(yEqualsX);

		final boolean equalsUsesInstanceof = xEqualsY != yEqualsX;

		return equalsUsesInstanceof;
	}

	/**
	 * Determines whether class X's equals-method uses "X" as parameter type.
	 * @return 	true, if equals' parameter type is "X"
	 * 			false, if equals' parameter type is "Object"
	 */
	public static boolean equalsHasBadSignature() {

		final X someX = new X(ARBITRARY_VALUE);
		final Object anotherX = new X(ARBITRARY_VALUE);
		final boolean equalsHasBadSignature = !someX.equals(anotherX);

		return equalsHasBadSignature;
	}

	/**
	 * Determines whether a class Y's (that extends X) equals-method misses call to its' super classes' equals().
	 * @return 	true, if it misses the call to super.equals(),
	 * 			false otherwise.
	 */
	public static boolean equalsMissesSuper() {

		final Y someY = new Y(ARBITRARY_VALUE, ARBITRARY_VALUE);
		final Y anotherY = new Y(ARBITRARY_VALUE+1, ARBITRARY_VALUE);
		final boolean equalsMissesSuper = someY.equals(anotherY);

		return equalsMissesSuper;
	}
}
