package edu.hm.iny.mvc.undercut.model.readonly;

import java.util.Observable;

public abstract class ReadOnlyGame extends Observable {


	public abstract int getTurns();

	public abstract int getStoredPoints();

	public abstract boolean isGameOver();

	/**
	 * Returns the player indicated by the parameter flag.
	 * @param firstDesired Indicates whether the first player should be returned (true)
	 * 			or the second player (false).
	 * @return The desired player.
	 */
	public abstract ReadOnlyPlayer getPlayer(final boolean firstDesired);

}
