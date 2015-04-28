package edu.hm.iny.idioms.equals;

public class Y extends X {
	private final int b;

	Y(final int arbitraryValue, final int arbitraryValue2) {
		super(arbitraryValue);
		b = arbitraryValue2;
	}

	int getB() { return b; }

	/**
	 * Incorrectly implemented equals() that misses the call to super.equals().
	 * super.equals() is obligatory for every class that is not derived directly from java.lang.Object.
	 */
	@Override public boolean equals(final Object object) {
		if(object == null)
			return false;
		if(getClass() != object.getClass())
			return false;
		if(getB() != ((Y)object).getB())
			return false;

		return true;
	}
}