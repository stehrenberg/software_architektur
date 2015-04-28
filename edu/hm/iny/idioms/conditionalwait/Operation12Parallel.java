package edu.hm.iny.idioms.conditionalwait;

/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik
 * Praktikum Software-Architektur, Sommersemester 2015
 * OS: Windows 7 Professional SP1 (64 Bit); Java-Version: 1.8.0_05
 * CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 2 - Java Idiome: Bedingtes Warten mit wait/notify
 */

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

import java.util.ArrayList;
import java.util.List;

/**
 * Program for simulating parallelization of operations using conditional wait with wait()/notify().
 * Rules:
 * Operations a1, a2, a3 are executed sequential, same goes for b1−b3, c1−c3 and d1−d3.
 * c3 starts after a1, b1 and d1 are done.
 * d3 starts after one of the operations a2, b2 or c2 are done.
 * The program prints "complete" after all operations are finished.
 *
 * @author Stephanie Ehrenberg (stephanie.ehrenberg@hm.edu)
 * @version 2015-04-010
 */
public class Operation12Parallel {

	/** Error message for the assertion error that is thrown
	 * if there ever shall be an interrupt. */
	private static final String NO_INTERRUPTS = "No interrupts shall occur!";
	/** To indicate when operation a1 is finished, a prerequisite for op c3.*/
	private boolean a1done;
	/** To indicate when operation b1 is finished, a prerequisite for op c3.*/
	private boolean b1done;
	/** To indicate when operation d1 is finished, a prerequisite for op c3.*/
	private boolean d1done;
	/** To indicate when one of the operations a2, b2, c2 is finished.*/
	private boolean d3mayStart;

	/**
	 * Execution of the program.
	 * @param args The duration of the different operations. If none are provided,
	 * 				the default value of 1_000 ms will be used.
	 */
	public static void main(final String... args) {
		init(args);
		new Operation12Parallel().runOperations();
	}

	/**
	 * Runs the actual operations.
	 */
	private void runOperations() {

		/** Monitor object for synchronization and wait/notify purposes. */
		final Object monitor = void.class;

		/** Some structure to bundle the different threads. */
		final List<Thread> threads = new ArrayList<>();

		runAoperations(monitor, threads);
		runBoperations(monitor, threads);
		runCoperations(monitor, threads);
		runDoperations(monitor, threads);
		startAllThreads(threads);

		for(final Thread thread : threads)
			try {
				thread.join();
			} catch (final InterruptedException exception) {
				throw new AssertionError(NO_INTERRUPTS);
			}

		System.out.println("complete");
	}

	/**
	 * Creates a new thread that executes all the A operations sequentially.
	 * @param monitor A monitor object for synchronization and wait/notify mechanism.
	 * @param threads A List data struture to store the new thread in.
	 */
	private void runAoperations(final Object monitor, final List<Thread> threads) {
		threads.add(new Thread(() -> {
			a1.exec();
			synchronized(monitor) {
				a1done = true;
				monitor.notifyAll();
			}
			a2.exec();
			synchronized(monitor) {
				d3mayStart = true;
				monitor.notifyAll();
			}
			a3.exec();
		}));
	}

	/**
	 * Creates a new thread that executes all the B operations sequentially.
	 * @param monitor A monitor object for synchronization and wait/notify mechanism.
	 * @param threads A List data struture to store the new thread in.
	 */
	private void runBoperations(final Object monitor, final List<Thread> threads) {

		threads.add(new Thread(() -> {
			b1.exec();
			synchronized(monitor) {
				b1done = true;
				monitor.notifyAll();
			}
			b2.exec();
			synchronized(monitor) {
				d3mayStart = true;
				monitor.notifyAll();
			}
			b3.exec();
		}));
	}

	/**
	 * Creates a new thread that executes all the c operations sequentially.
	 * @param monitor A monitor object for synchronization and wait/notify mechanism.
	 * @param threads A List data struture to store the new thread in.
	 */
	private void runCoperations(final Object monitor, final List<Thread> threads) {
		threads.add(new Thread(() -> {
			c1.exec();
			c2.exec();
			synchronized(monitor) {
				d3mayStart = true;
				monitor.notifyAll();
			}

			synchronized(monitor) {
				try {
					while(!c3CanRun()) {
						System.out.println("c3 waiting...");
						monitor.wait();
					}
					System.out.println("got monitor!");
				} catch (final InterruptedException exception) {
					throw new AssertionError(NO_INTERRUPTS);
				}
			}
			c3.exec();
		}));
	}

	/**
	 * Creates a new thread that executes all the d operations sequentially.
	 * @param monitor A monitor object for synchronization and wait/notify mechanism.
	 * @param threads A List data struture to store the new thread in.
	 */
	private void runDoperations(final Object monitor, final List<Thread> threads) {
		threads.add(new Thread(() -> {
			d1.exec();
			synchronized(monitor) {
				d1done = true;
				monitor.notifyAll();
			}
			d2.exec();
			synchronized(monitor) {
				if(!d3mayStart) {
					try {
						monitor.wait();
					} catch (final InterruptedException exception) {
						throw new AssertionError(NO_INTERRUPTS);
					}
				}
			}
			System.out.println("d3 can start!");
			d3.exec();
		}));
	}

	/**
	 * Starts all threads within the List-structure.
	 * @param threads The data structure containing the threads.
	 */
	private void startAllThreads(final List<Thread> threads) {
		for(final Thread thread : threads) {
			thread.start();
		}
	}


	/**
	 * Tests if the requirements for c3 to run are met.
	 * @return True, if operations a1, b1 and d1 are finished.
	 * 			False, if at least one of the operations has not finished yet.
	 */
	private boolean c3CanRun() {
		return a1done && b1done  && d1done;
	}
}
