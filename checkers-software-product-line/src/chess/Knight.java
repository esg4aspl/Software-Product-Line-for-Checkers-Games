package chess;

import core.*;

public class Knight extends AbstractPiece {

	private String name;
	
	public Knight(int id, String icon, IPlayer myPlayer, Direction goalDirection,
			IPieceMovePossibilities pieceMovePossibilities, 
			IPieceMoveConstraints pieceMoveConstraints) {
		super(id, icon, myPlayer, goalDirection, pieceMovePossibilities, pieceMoveConstraints);
		name = "Knight";
	}

	public String getName() {
		return name;
	}

}
