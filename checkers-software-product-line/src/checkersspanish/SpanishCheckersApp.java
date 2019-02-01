package checkersspanish;

import base.*;

public class SpanishCheckersApp {

	public static void main(String[] args) {
		
		AmericanGameConfiguration gameConfiguration = new AmericanGameConfiguration();
		Referee referee = new Referee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
