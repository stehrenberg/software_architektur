package edu.hm.iny.idioms.conditionalwait;

import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.a1;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.b1;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.b2;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.c2;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.c3;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.d1;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.d3;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class Operation12ParallelSimpleTest {

	@BeforeClass public static void setUp() {
		Operation12Parallel.main();
	}

	@Test public void testOperationsA1B1D1beforeC3() {

		final boolean a1BeforeC3 = c3.getStartedAt() >= a1.getEndedAt();
		final boolean b1BeforeC3 = c3.getStartedAt() >= b1.getEndedAt();
		final boolean d1BeforeC3 = c3.getStartedAt() >= d1.getEndedAt();

		assertTrue(a1BeforeC3&&b1BeforeC3&&d1BeforeC3);
	}

	@Test
	public void testOperationsA2orB2orC2beforeD3() {

		final boolean a1BeforeD3 = d3.getStartedAt() >= a1.getEndedAt();
		final boolean b2BeforeD3 = d3.getStartedAt() >= b2.getEndedAt();
		final boolean c2BeforeD3 = d3.getStartedAt() >= c2.getEndedAt();
		assertTrue(a1BeforeD3 || b2BeforeD3 || c2BeforeD3);
	}

}
