package edu.hm.iny.mvc.undercut.control;

import java.util.function.BiFunction;

import edu.hm.iny.mvc.undercut.logic.Logic;
import edu.hm.iny.mvc.undercut.model.readonly.ReadOnlyGame;
import edu.hm.iny.mvc.undercut.model.readonly.ReadOnlyPlayer;

public class Robot extends Controller {

	BiFunction<ReadOnlyGame, ReadOnlyPlayer, Integer> strategy;

	/**
	 * Ctor.
	 * @param isFirstPlayer Determines it this robot is player#1.
	 * @param logic A reference to the game logic object.
	 * @param strategy Is used to make the robot choose a die value.
	 */
	public Robot(final boolean isFirstPlayer, final Logic logic, final BiFunction<
			ReadOnlyGame,
			ReadOnlyPlayer,
			Integer> strategy) {

		super(isFirstPlayer, logic);
		this.strategy = strategy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override public void run() {

		final ReadOnlyGame game = super.getLogic().getGame();

		while(!game.isGameOver()) {

			if(!super.isFirst()) {
				synchronized(game) {
					try {
						game.wait();
					} catch (final InterruptedException e) {
						throw new AssertionError("No interrupts shall occur!");
					}
				}
			}

			final Integer choice = strategy.apply(game, game.getPlayer(super.isFirst()));
			super.getLogic().choose(super.isFirst(), choice);

			final String player = super.isFirst()? "Player#1" : "Player#2";
			System.out.println(player + "'s choice: " + choice);
			try {
				Thread.sleep(500);
			} catch (final InterruptedException e) {
				throw new AssertionError("No interrupt, other thread should wait!");
			}

			if(super.isFirst()) {
				synchronized(game) {
					try {
						game.wait();
					} catch (final InterruptedException e) {
						throw new AssertionError("No interrupts shall occur!");
					}
				}
			}
		}
	}
}
