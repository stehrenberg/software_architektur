package edu.hm.iny.idioms.equals;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestBuggyEquals {

	@Test
	public void testEqualsUsingInstanceof() {
		assertEquals(true, BuggyEquals.equalsHasBrokenTypecheck());
	}

	//@Test
	public void testEqualsWithWrongSignature() {
		assertEquals(true, BuggyEquals.equalsHasBadSignature());
	}

	//@Test
	public void testEqualsWithoutSuper() {
		assertEquals(true, BuggyEquals.equalsMissesSuper());
	}

}
