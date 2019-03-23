package rules;

import core.AbstractBoard;
import core.AbstractPiece;
import core.AbstractReferee;
import core.CoordinatePieceMap;
import core.ICoordinate;
import core.IMoveCoordinate;
import core.IRule;

public class RuleEndOfGameNoPieceCapturedForFortyTurn implements IRule {
	private int uncapturedPieceTurnCounter;
	
	public RuleEndOfGameNoPieceCapturedForFortyTurn() {
		uncapturedPieceTurnCounter = 0;
	}
	@Override
	public boolean evaluate(AbstractReferee referee) {		
		IMoveCoordinate currentMoveCoordinate = referee.getCurrentMoveCoordinate();
		CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		AbstractBoard board = referee.getBoard();		
		
		if(isPieceJumping(board, coordinatePieceMap, currentMoveCoordinate))
			uncapturedPieceTurnCounter = 0;
		else 
			uncapturedPieceTurnCounter++;	

							// number of turn
		return isTurnEnd(referee, 40);
	}

	
	private boolean isTurnEnd(AbstractReferee referee, int numberOfEndTurn) {
		if(uncapturedPieceTurnCounter >= numberOfEndTurn)
			return true;
		else
			return false;	
	}
	
	private boolean isPieceJumping(AbstractBoard board, CoordinatePieceMap pieceMap, IMoveCoordinate moveCoordinate) {
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		System.out.println(sourceCoordinate);
		AbstractPiece piece = pieceMap.getPieceAtCoordinate(sourceCoordinate);
		//System.out.println(piece);
		if(board.getMBO().isJumpMove(piece, moveCoordinate))
			return true;
		return false;
	}	

}
