package checkerschinese;

import core.*;

public class ChineseGameConfiguration implements IGameConfiguration {

	public int getNumberOfPlayers() {
		return 3;
	}
	
	public int getNumberOfPiecesPerPlayer() {
		return 10;
	}

	@Override
	public boolean getAutomaticGameStatus() {
		// TODO Auto-generated method stub
		return false;
	}

}
