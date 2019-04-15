package checkersspanish;

import core.AbstractGameConfiguration;
import core.AbstractReferee;

public class SpanishCheckersApp {

	public static void main(String[] args) {
		
		AbstractGameConfiguration gameConfiguration = new SpanishGameConfiguration();
		AbstractReferee referee = new SpanishReferee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
