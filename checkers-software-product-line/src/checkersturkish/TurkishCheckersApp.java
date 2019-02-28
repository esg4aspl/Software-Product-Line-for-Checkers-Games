package checkersturkish;

import base.*;
import core.AbstractReferee;

public class TurkishCheckersApp {

	public static void main(String[] args) {
		
		TurkishGameConfiguration gameConfiguration = new TurkishGameConfiguration();
		AbstractReferee referee = new TurkishReferee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
