package Chessproblem;

import core.*;

public class ChessGameConfiguration implements IGameConfiguration {

	public int getNumberOfPlayers() {
		return 1;
	}
	
	public int getNumberOfPiecesPerPlayer() {
		return 1;
	}

	@Override
	public boolean getAutomaticGameStatus() {
		return false;
	}

}
