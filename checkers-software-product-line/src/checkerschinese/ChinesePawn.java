package checkerschinese;

import core.*;

public class ChinesePawn extends AbstractPiece {

	private String name;
	
	public ChinesePawn(int id, String icon, IPlayer myPlayer, Direction goalDirection,
			IPieceMovePossibilities pieceMovePossibilities, 
			IPieceMoveConstraints pieceMoveConstraints) {
		super(id, icon, myPlayer, goalDirection, pieceMovePossibilities, pieceMoveConstraints);
		name = "Men";
	}

	public String getName() {
		return name;
	}

}
