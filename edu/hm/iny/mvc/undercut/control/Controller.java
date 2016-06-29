package edu.hm.iny.mvc.undercut.control;

import edu.hm.iny.mvc.undercut.logic.Logic;

public abstract class Controller extends Thread {

	/** Indicates whether this controller is associated with player#1.*/
	private final boolean isFirst;
	/** The game logic object. */
	private final Logic logic;

	Controller(final boolean isFirst, final Logic logic) {
		this.isFirst = isFirst;
		this.logic = logic;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public Logic getLogic() {
		return logic;
	}


}
