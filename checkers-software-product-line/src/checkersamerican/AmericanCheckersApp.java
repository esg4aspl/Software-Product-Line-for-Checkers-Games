package checkersamerican;

import base.*;
import core.AbstractReferee;

public class AmericanCheckersApp {

	public static void main(String[] args) {
		AmericanGameConfiguration gameConfiguration = new AmericanGameConfiguration();
		AbstractReferee referee = new Referee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
