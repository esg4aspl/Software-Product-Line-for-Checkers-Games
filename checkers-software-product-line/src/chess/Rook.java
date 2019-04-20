package chess;

import core.*;

public class Rook extends AbstractPiece {

	private String name;
	
	public Rook(int id, String icon, IPlayer myPlayer, Direction goalDirection,
			IPieceMovePossibilities pieceMovePossibilities, 
			IPieceMoveConstraints pieceMoveConstraints) {
		super(id, icon, myPlayer, goalDirection, pieceMovePossibilities, pieceMoveConstraints);
		name = "Rook";
	}

	public String getName() {
		return name;
	}

}
