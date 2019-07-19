package Chessproblem;

import core.IGameConfiguration;
import core.AbstractReferee;

public class ChessApp {

	public static void main(String[] args) {
		IGameConfiguration gameConfiguration = new ChessGameConfiguration();
		AbstractReferee referee = new Referee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
