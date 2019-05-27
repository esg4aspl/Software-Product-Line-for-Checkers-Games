package checkersspanish;

import core.IGameConfiguration;
import core.AbstractReferee;

public class SpanishCheckersApp {

	public static void main(String[] args) {
		
		IGameConfiguration gameConfiguration = new SpanishGameConfiguration();
		AbstractReferee referee = new SpanishReferee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
