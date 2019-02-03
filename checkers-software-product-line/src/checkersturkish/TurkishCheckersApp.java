package checkersturkish;

import base.*;
import checkersamerican.Referee;

public class TurkishCheckersApp {

	public static void main(String[] args) {
		
		TurkishGameConfiguration gameConfiguration = new TurkishGameConfiguration();
		Referee referee = new TurkishReferee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
