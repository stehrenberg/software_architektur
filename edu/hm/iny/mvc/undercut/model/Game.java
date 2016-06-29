package edu.hm.iny.mvc.undercut.model;

import edu.hm.iny.mvc.undercut.model.readonly.ReadOnlyGame;

public class Game extends ReadOnlyGame {
	/** Number of played rounds. */
	private int turns;
	/** Points not yet assigned to a player. */
	private int storedPoints;
	/** Flag to indiates the end of the game. */
	boolean gameOver;
	/** Holds the two players. */
	Player[] players;

	/** Ctor. */
	public Game() {
		turns = 0;
		storedPoints = 0;
		players = new Player[] {new Player(), new Player()};
	}

	@Override
	public int getTurns() {
		return turns;
	}

	/**
	 * @param turns the turns to set
	 */
	public void setTurns(final int turns) {
		this.turns = turns;
		setChanged();
		notifyObservers();
	}

	@Override
	public int getStoredPoints() {
		return storedPoints;
	}

	/**
	 * @param storedPoints the storedPoints to set
	 */
	public void setStoredPoints(final int storedPoints) {
		this.storedPoints = storedPoints;
	}

	@Override
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver() {
		gameOver = true;
		setChanged();
		notifyObservers();
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.mvc.undercut.model.readonly.ReadOnlyGame#getPlayer(boolean)
	 */
	@Override
	public Player getPlayer(final boolean firstDesired) {
		final int playerNo = firstDesired? 0 : 1;
		return players[playerNo];
	}
}
