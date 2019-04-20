package chess;

import core.AbstractGameConfiguration;
import core.AbstractReferee;

public class ChessApp {

	public static void main(String[] args) {
		AbstractGameConfiguration gameConfiguration = new ChessGameConfiguration();
		AbstractReferee referee = new Referee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
