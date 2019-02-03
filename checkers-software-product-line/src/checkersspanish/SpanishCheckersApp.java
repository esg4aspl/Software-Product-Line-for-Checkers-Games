package checkersspanish;

import base.*;

public class SpanishCheckersApp {

	public static void main(String[] args) {
		
		AmericanGameConfiguration gameConfiguration = new AmericanGameConfiguration();
		TurkishReferee referee = new TurkishReferee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
