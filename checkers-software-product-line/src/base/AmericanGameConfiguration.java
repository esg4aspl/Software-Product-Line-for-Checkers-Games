package base;

import core.*;

public class AmericanGameConfiguration implements IGameConfiguration {

	public int getNumberOfPlayers() {
		return 2;
	}
	
	public int getNumberOfPiecesPerPlayer() {
		return 12;
	}

	@Override
	public boolean getAutomaticGameStatus() {
		// TODO Auto-generated method stub
		return false;
	}

}
