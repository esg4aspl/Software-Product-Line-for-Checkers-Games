package checkerschinese;

import base.*;
import core.AbstractReferee;

public class ChineseCheckersApp {

	public static void main(String[] args) {
		ChineseGameConfiguration gameConfiguration = new ChineseGameConfiguration();
		AbstractReferee referee = new Referee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
