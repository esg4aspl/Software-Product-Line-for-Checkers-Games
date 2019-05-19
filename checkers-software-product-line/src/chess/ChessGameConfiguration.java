package chess;

import core.*;

public class ChessGameConfiguration implements IGameConfiguration {

	public int getNumberOfPlayers() {
		return 2;
	}
	
	public int getNumberOfPiecesPerPlayer() {
		return 16;
	}

	@Override
	public boolean getAutomaticGameStatus() {
		return false;
	}

}
