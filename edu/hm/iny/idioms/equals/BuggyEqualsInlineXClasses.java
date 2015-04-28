/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik
 * Praktikum Software-Architektur, Sommersemester 2015
 * Windows 7 SP1; Java-Version: 1.8.0_05
 * Aufgabe 1.1: Objektvergleich mit equals
 */

package edu.hm.iny.idioms.equals;

/**
 * Determines if an object's equals-Method is sloppily implemented, meaning...
 * ...usage of instanceof to determine if two objects are of the same class
 * ...specifying the wrong method signature, not using Object as parameter type
 * ...
 * @author Stephanie Ehrenberg
 * @version 2015-03-18
 */
public class BuggyEqualsInlineXClasses {

	private static final int ARBITRARY_VALUE = 42;

	/**
	 * Determines whether an object's equals-Method uses instanceof
	 * to determine if two objects are of the same class.
	 * @return true, if object's equals() uses instanceof - otherwise false
	 */
	public static boolean equalsHasBrokenTypecheck() {

		/**
		 * Some class with an equals()-Method that uses instanceof to compare some objects' classes.
		 */
		class ClassX {
			private final int valueA;

			ClassX(final int valueA) { this.valueA = valueA; }
			int getValueA() { return valueA; }

			@Override
			public boolean equals(final Object anotherX) {

				if(anotherX == null)
					return false;
				if(!(anotherX instanceof ClassX))
					return false;
				if(getValueA() != ((ClassX)anotherX).getValueA())
					return false;

				return true;
			}
		}

		final ClassX someX = new ClassX(ARBITRARY_VALUE);
		final ClassX subX = new ClassX(ARBITRARY_VALUE) {};
		final boolean xEqualsY = someX.equals(subX);
		final boolean yEqualsX = subX.equals(someX);
		final boolean equalsUsesInstanceof = xEqualsY == yEqualsX;

		return equalsUsesInstanceof;
	}

	/**
	 * Determines whether class X's equals-method uses "X" as parameter type.
	 * @return 	true, if equals' parameter type is "X"
	 * 			false, if equals' parameter type is "Object"
	 */
	public static boolean equalsHasBadSignature() {

		class ClassX {
			private final int valueA;

			ClassX(final int valueA) { this.valueA = valueA; }

			private Object getValueA() { return valueA; }
			public boolean equals(final ClassX anotherX) {

				if(anotherX == null)
					return false;
				if(!(anotherX.getClass() == this.getClass()))
					return false;
				if(anotherX.getValueA() != getValueA())
					return false;

				return true;
			}

		}

		final ClassX someX = new ClassX(ARBITRARY_VALUE);
		final Object anotherX = new ClassX(ARBITRARY_VALUE);
		final boolean equalsHasBadSignature = !someX.equals(anotherX);

		return equalsHasBadSignature;
	}

	/**
	 *
	 * @return 	true, if
	 * 			false otherwise.
	 */
	public static boolean equalsMissesSuper() {

		class ClassX {
			private final int valueA;

			ClassX(final int valueA) { this.valueA = valueA; }
			int getValueA() { return valueA; }

			@Override
			public boolean equals(final Object object) {

				if(object == null)
					return false;
				if(!(object.getClass() == this.getClass()))
					return false;
				if(((ClassX)object).getValueA() != getValueA())
					return false;

				return true;
			}
		}

		class ClassY extends ClassX {
			private final int valueB;

			ClassY(final int arbitraryValue, final int arbitraryValue2) {
				super(arbitraryValue);
				valueB = arbitraryValue2;
			}

			int getValueB() { return valueB; }

			/**
			 * Incorrectly implemented equals() that misses the call to super.equals().
			 * super.equals() is obligatory for every class that is not derived directly from java.lang.Object.
			 */
			@Override public boolean equals(final Object object) {

				if(object == null)
					return false;
				if(getClass() != object.getClass())
					return false;
				if(getValueB() != ((ClassY)object).getValueB())
					return false;

				return true;
			}
		};

		final ClassY someY = new ClassY(ARBITRARY_VALUE, ARBITRARY_VALUE);
		final ClassY anotherY = new ClassY(ARBITRARY_VALUE+1, ARBITRARY_VALUE);
		final boolean equalsMissesSuper = someY.equals(anotherY);

		return equalsMissesSuper;
	}
}
