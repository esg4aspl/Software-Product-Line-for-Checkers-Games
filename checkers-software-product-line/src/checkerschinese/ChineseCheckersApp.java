package checkerschinese;

import core.AbstractGameConfiguration;
import core.AbstractReferee;

public class ChineseCheckersApp {

	public static void main(String[] args) {
		AbstractGameConfiguration gameConfiguration = new ChineseGameConfiguration();
		AbstractReferee referee = new Referee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
