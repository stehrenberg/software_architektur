/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 6: MVC
 */
package edu.hm.iny.mvc.undercut.logic;

import edu.hm.iny.mvc.undercut.model.Game;
import edu.hm.iny.mvc.undercut.model.Player;

/**
 * Implements the gaming rules of the game "Undercut".
 * @version 2015-05-25
 *
 */
public class Logic {

	private static final int MAX_TURNS = 25;
	private static final int MIN_DIE_VALUE = 1;
	private static final int MAX_DIE_VALUE = 6;
	/** The game object.*/
	private final Game game;

	public Logic(final Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	/**
	 * Called from view layer. Receives the current player's choice and
	 * evaluates the round if both players have chosen a die value.
	 * @param isFirst Indicates whether choosing player is player#1.
	 * @param choice The die value chosen by the respective player.
	 */
	public void choose(final boolean isFirst, final int choice) {

		// game already over?
		if(game.isGameOver())
			throw new AssertionError("choose() cannot be called if game is over!");
		final Player currentPlayer = game.getPlayer(isFirst);

		// player has already chosen?
		if(currentPlayer.isChoiceMade())
			throw new AssertionError("Player has already chosen!");

		// choice valid?
		if(choice < MIN_DIE_VALUE || choice > MAX_DIE_VALUE)
			throw new IllegalArgumentException("Die value must be between 1 and 6!");

		currentPlayer.setCurrentChoice(choice);
		currentPlayer.setIsChoiceMade(true);


		// if second player called choose(), the round is over and needs to be evaluated
		if(!isFirst) {
			final Player playerOne = game.getPlayer(true);
			final int choicePlayerOne = playerOne.getCurrentChoice();
			final int storedPoints = game.getStoredPoints();
			final int diff = choicePlayerOne - choice;
			final int sum = choice + choicePlayerOne + storedPoints;
			boolean storedPointsAssigned = true;

			//TODO Baeh. Refactoren!
			if(diff == -1)
				playerOne.addPoints(sum);
			else if (diff == 1)
				currentPlayer.addPoints(sum);
			else if(diff > 1)
				playerOne.addPoints(sum);
			else if(diff < -1)
				currentPlayer.addPoints(sum);
			else if (diff == 0) {
				game.setStoredPoints(sum);
				storedPointsAssigned = false;
			}

			if(storedPointsAssigned)
				game.setStoredPoints(0);

			// game over?
			if(game.getTurns() == MAX_TURNS
					|| currentPlayer.getScore() >= 100
					|| playerOne.getScore() >= 100)
				game.setGameOver();
			else {
				game.setTurns(game.getTurns() + 1);
				currentPlayer.setLastChoice(choice);
				currentPlayer.setIsChoiceMade(false);
				playerOne.setLastChoice(choicePlayerOne);
				playerOne.setIsChoiceMade(false);
			}

			// notify view-instances that round is over
			game.notifyObservers();
		}

		// notify controller - next player's turn!
		synchronized (game) {
			game.notifyAll();
		}
	}
}
