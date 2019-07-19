package Chessproblem;

import base.Pawn;
import core.Direction;
import core.IPieceMoveConstraints;
import core.IPieceMovePossibilities;
import core.IPlayer;

public class LimitedPawn extends Pawn {

	public LimitedPawn(int id, String icon, IPlayer myPlayer, Direction goalDirection,
			IPieceMovePossibilities pieceMovePossibilities, IPieceMoveConstraints pieceMoveConstraints) {
		super(id, icon, myPlayer, goalDirection, pieceMovePossibilities, pieceMoveConstraints);
		// TODO Auto-generated constructor stub
	}

}
