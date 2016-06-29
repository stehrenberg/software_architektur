package edu.hm.iny.mvc.undercut.model.readonly;

public abstract class ReadOnlyPlayer {

	public abstract int getScore();

	public abstract int getCurrentChoice();

	public abstract boolean isChoiceMade();

	public abstract int getLastChoice();

}
