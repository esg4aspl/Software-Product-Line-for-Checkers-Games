package checkerschinese;

import core.IGameConfiguration;
import core.AbstractReferee;

public class ChineseCheckersApp {

	public static void main(String[] args) {
		IGameConfiguration gameConfiguration = new ChineseGameConfiguration();
		AbstractReferee referee = new Referee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
