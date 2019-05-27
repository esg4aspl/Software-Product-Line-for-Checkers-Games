package chess;

import core.*;

public class Bishop extends AbstractPiece {

	private String name;
	
	public Bishop(int id, String icon, IPlayer myPlayer, Direction goalDirection,
			IPieceMovePossibilities pieceMovePossibilities, 
			IPieceMoveConstraints pieceMoveConstraints) {
		super(id, icon, myPlayer, goalDirection, pieceMovePossibilities, pieceMoveConstraints);
		name = "Bishop";
	}

	public String getName() {
		return name;
	}

}
