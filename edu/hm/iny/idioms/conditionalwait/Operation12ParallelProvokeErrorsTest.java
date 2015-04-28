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

public class Operation12ParallelProvokeErrorsTest {

	@BeforeClass public static void setUp() {

		final String defaultOperation = "1000";
		final String longOperation = "5000";
		final String veryLongOperation = "10000";

		final String[] customDurations = new String[] {
				longOperation,		// a1
				veryLongOperation,	// a2
				defaultOperation,	// a3
				defaultOperation,	// b1
				veryLongOperation,	// b2
				defaultOperation,	// b3
				defaultOperation,	// c1
				veryLongOperation,	// c2
				defaultOperation,	// c3
				veryLongOperation,	// d1
				defaultOperation,	// d2
				defaultOperation	// d3
		};
		Operation12Parallel.main(customDurations);
	}

	@Test
	public void testOperationsA1B1D1beforeC3() {

		final long c3StartTime = c3.getStartedAt();
		final boolean a1BeforeC3 = c3StartTime >= a1.getEndedAt();
		final boolean b1BeforeC3 = c3StartTime >= b1.getEndedAt();
		final boolean d1BeforeC3 = c3StartTime >= d1.getEndedAt();

		new Thread(() -> System.out.println(a1.getEndedAt() + "/" + c3.getStartedAt())).run();
		assertTrue(a1BeforeC3 && b1BeforeC3 && d1BeforeC3);
	}

	@Test
	public void testOperationsA2orB2orC2beforeD3() {

		final long d3StartTime = d3.getStartedAt();
		final boolean a1BeforeD3 = d3StartTime >= a1.getEndedAt();
		final boolean b2BeforeD3 = d3StartTime >= b2.getEndedAt();
		final boolean c2BeforeD3 = d3StartTime >= c2.getEndedAt();
		assertTrue(a1BeforeD3 || b2BeforeD3 || c2BeforeD3);
	}

}
