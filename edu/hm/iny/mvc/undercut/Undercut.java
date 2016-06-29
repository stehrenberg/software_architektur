package edu.hm.iny.mvc.undercut;

import edu.hm.iny.mvc.undercut.control.Robot;
import edu.hm.iny.mvc.undercut.logic.Logic;
import edu.hm.iny.mvc.undercut.model.Game;
import edu.hm.iny.mvc.undercut.view.Spectator;
import edu.hm.iny.mvc.undercut.view.WinnerWatcher;

public class Undercut {

	public static void main(final String... ignored) {
		final Game game = new Game();
		final Logic logic = new Logic(game);
		new Spectator(game);
		new WinnerWatcher(game);
		new Robot(true, logic, (rogame, player) -> 5).start();
		new Robot(false, logic, (rogame, player) -> (int)(Math.random()*6 + 1)).start();
	}
}
