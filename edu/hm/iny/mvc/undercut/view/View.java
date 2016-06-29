package edu.hm.iny.mvc.undercut.view;

import java.util.Observer;

import edu.hm.iny.mvc.undercut.model.readonly.ReadOnlyGame;

public abstract class View implements Observer {

	private final ReadOnlyGame game;

	public View(final ReadOnlyGame game) {
		this.game = game;
	}

	public ReadOnlyGame getGame() {
		return game;
	}
}
