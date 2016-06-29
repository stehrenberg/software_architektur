package edu.hm.iny.mvc.undercut.view;

import java.util.Observable;

import edu.hm.iny.mvc.undercut.model.readonly.ReadOnlyGame;
import edu.hm.iny.mvc.undercut.model.readonly.ReadOnlyPlayer;

/**
 * A view class that prints a status msg to the console after every round,
 * containing information on the players' current score, their last choice,
 * the number of played rounds and the stored points that have not been assigned
 * to a player yet.
 * @version 2015-05-25
 */
public class Spectator extends View {

	/**
	 * Ctor.
	 * @param game The observable readonly game object.
	 */
	public Spectator(final ReadOnlyGame game) {
		super(game);
		game.addObserver(this);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(final Observable o, final Object arg) {

		final ReadOnlyGame game = super.getGame();
		final ReadOnlyPlayer playerOne = game.getPlayer(true);
		final ReadOnlyPlayer playerTwo = game.getPlayer(false);

		System.out.printf("Turn=%d\n"
				+ "Player#1: Score=%d, last choice=%d\n"
				+ "Player#2: Score=%d, last choice=%d\n"
				+ "Saved points=%d",
				game.getTurns(),
				playerOne.getScore(), playerOne.getLastChoice(),
				playerTwo.getScore(), playerOne.getLastChoice(),
				game.getStoredPoints()
				);
	}

}
