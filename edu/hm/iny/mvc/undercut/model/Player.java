package edu.hm.iny.mvc.undercut.model;

import edu.hm.iny.mvc.undercut.model.readonly.ReadOnlyPlayer;

public class Player extends ReadOnlyPlayer {

	/** The points scored by this player. Never negative. */
	private int score;
	/** The die score chosen in this round. */
	private int currentChoice;
	/** Die score chosen in the previous round. */
	private int lastChoice;
	/** Indicates that this player has chosen a die score for the current round. */
	private boolean isChoiceMade;


	/** Ctor. */
	public Player() {
		score = 0;
		currentChoice = 0;
		lastChoice = 0;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public int getCurrentChoice() {
		return currentChoice;
	}

	/**
	 * @param currentChoice the currentChoice to set
	 */
	public void setCurrentChoice(final int currentChoice) {
		this.currentChoice = currentChoice;
	}

	@Override
	public int getLastChoice() {
		return lastChoice;
	}

	/**
	 * @param lastChoice the lastChoice to set
	 */
	public void setLastChoice(final int lastChoice) {
		this.lastChoice = lastChoice;
	}

	@Override
	public boolean isChoiceMade() {
		return isChoiceMade;
	}

	/**
	 * Sets the flag if a choice has been made in the current round.
	 */
	public void setIsChoiceMade(final boolean value) {
		isChoiceMade = value;
	}

	/**
	 * Increments the player's score by adding the given points.
	 * @param points The points to add to the player's score.
	 */
	public void addPoints(final int points) {
		score += points;
	}
}
