package checkersspanish;

import core.*;

public class Queen extends AbstractPiece {

	private String name;
	
	public Queen(int id, String icon, IPlayer myPlayer, Direction goalDirection,
			IPieceMovePossibilities pieceMovePossibilities, 
			IPieceMoveConstraints pieceMoveConstraints) {
		super(id, icon, myPlayer, goalDirection, pieceMovePossibilities, pieceMoveConstraints);
		name = "Queen";
	}

	public String getName() {
		return name;
	}


}
