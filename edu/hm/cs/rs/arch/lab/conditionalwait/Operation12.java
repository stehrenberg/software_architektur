package edu.hm.cs.rs.arch.lab.conditionalwait;

public enum Operation12 {
	a1("a1"),
	a2("a2"),
	a3("a3"),
	b1("b1"),
	b2("b2"),
	b3("b3"),
	c1("c1"),
	c2("c2"),
	c3("c3"),
	d1("d1"),
	d2("d2"),
	d3("d3");

	private int duration = 1_000;
	private long startedAt;
	private long endedAt;
	private String name;

	private Operation12(final String name) {
		this.name = name;
	}

	public static void init(final String... args) {
		int arg = 0;

		for(final Operation12 operation: values())
			if(arg < args.length)
				operation.duration = Integer.parseInt(args[arg++]);
	}

	public long getStartedAt() {
		return startedAt;
	}

	public long getEndedAt() {
		return endedAt;
	}

	public void exec() {
		startedAt = System.currentTimeMillis();
		try {
			Thread.sleep(duration);
			// Nur 1 Aufruf erlaubt; naechster Aufruf unzulaessiges Argument fuer sleep!
			duration = -1;
		}
		catch(final InterruptedException interruptedException) {
			throw new AssertionError("interrupt from nowhere!");
		}

		endedAt = System.currentTimeMillis();
		System.out.println(name + " done!");
	}
}