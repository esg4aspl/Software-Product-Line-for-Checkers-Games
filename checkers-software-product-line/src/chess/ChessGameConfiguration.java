package chess;

import core.*;

public class ChessGameConfiguration extends AbstractGameConfiguration {

	public int getNumberOfPlayers() {
		return 2;
	}
	
	public int getNumberOfPiecesPerPlayer() {
		return 16;
	}

}
