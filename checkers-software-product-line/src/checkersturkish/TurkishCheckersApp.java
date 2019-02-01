package checkersturkish;

import base.*;

public class TurkishCheckersApp {

	public static void main(String[] args) {
		
		TurkishGameConfiguration gameConfiguration = new TurkishGameConfiguration();
		Referee referee = new Referee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
