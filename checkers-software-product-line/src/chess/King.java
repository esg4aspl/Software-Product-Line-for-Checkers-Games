package chess;

import core.*;

public class King extends AbstractPiece {

	private String name;
	
	public King(int id, String icon, IPlayer myPlayer, Direction goalDirection,
			IPieceMovePossibilities pieceMovePossibilities, 
			IPieceMoveConstraints pieceMoveConstraints) {
		super(id, icon, myPlayer, goalDirection, pieceMovePossibilities, pieceMoveConstraints);
		name = "King";
	}

	public String getName() {
		return name;
	}

}
