package edu.hm.iny.idioms.equals;

public class X {

	private final int a;

	X(final int a) {
		this.a = a;
	}

	@Override
	public boolean equals(final Object anotherX) {

		final boolean isEqual = true;

		if(anotherX == null)
			return false;
		if(!(anotherX instanceof X))
			return false;
		if(((X)anotherX).a != a)
			return false;

		return isEqual;
	}

}
