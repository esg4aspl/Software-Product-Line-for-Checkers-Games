package checkerschildren;

import base.*;

public class ChildrenCheckersApp {

	public static void main(String[] args) {
		
		AmericanGameConfiguration gameConfiguration = new AmericanGameConfiguration();
		Referee referee = new Referee(gameConfiguration);
		referee.setup();
		referee.conductGame();
	}

}
