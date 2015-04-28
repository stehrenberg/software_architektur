package edu.hm.iny.idioms.conditionalwait;

import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.a1;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.a2;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.a3;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.b1;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.b2;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.b3;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.c1;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.c2;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.c3;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.d1;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.d2;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.d3;
import static edu.hm.cs.rs.arch.lab.conditionalwait.Operation12.init;

public class Operation12Sequential {

	public static void main(final String... args) {
		init(args);
		new Operation12Sequential().runOperations();
	}

	void runOperations() {
		a1.exec();
		a2.exec();
		a3.exec();
		b1.exec();
		b2.exec();
		b3.exec();
		c1.exec();
		c2.exec();
		c3.exec();
		d1.exec();
		d2.exec();
		d3.exec();
		System.out.println("complete");
	}
}