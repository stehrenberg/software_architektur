package edu.hm.iny.mvc.undercut.model.readonly;

public enum GameStatus {

	Initializing("Game has not started yet."),
	Running("Game is still running!"),
	PlayerOneWins("Player#1 wins."),
	PlayerTwoWins("Player#2 wins. "),
	Draw("It's a draw!");

	private final String statusMsg;

	private GameStatus(final String msg) {
		statusMsg = msg;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

}
