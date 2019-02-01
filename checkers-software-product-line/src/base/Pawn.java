package base;

import core.*;

public class Pawn extends AbstractPiece {

	private String name;
	
	public Pawn(int id, String icon, IPlayer myPlayer, Direction goalDirection,
			IPieceMovePossibilities pieceMovePossibilities, 
			IPieceMoveConstraints pieceMoveConstraints) {
		super(id, icon, myPlayer, goalDirection, pieceMovePossibilities, pieceMoveConstraints);
		name = "Men";
	}

	public String getName() {
		return name;
	}

}
