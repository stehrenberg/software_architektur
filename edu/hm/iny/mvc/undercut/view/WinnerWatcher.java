/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 6: MVC
 */

package edu.hm.iny.mvc.undercut.view;

import java.util.Observable;

import edu.hm.iny.mvc.undercut.model.readonly.GameStatus;
import edu.hm.iny.mvc.undercut.model.readonly.ReadOnlyGame;

/**
 * A View class that prints a message to the console at game over stating the winner.
 * @version 2015-05-25
 */
public class WinnerWatcher extends View {

	/** Ctor. */
	public WinnerWatcher(final ReadOnlyGame game) {
		super(game);
		game.addObserver(this);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(final Observable game, final Object status) {

		final GameStatus gameStatus = (GameStatus) status;

		switch(gameStatus) {
		case Initializing:
			throw new AssertionError("WinnerWatcher should not get an update in init phase.");
		case Running:
			throw new AssertionError("WinnerWatcher should not get an update in running phase.");
		default:
			final String resultMsg = gameStatus.getStatusMsg();
			System.out.println(resultMsg);
			break;
		}
	}

}
