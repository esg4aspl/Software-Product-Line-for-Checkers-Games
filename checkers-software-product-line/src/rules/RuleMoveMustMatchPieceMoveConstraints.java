package rules;

import core.*;

public class RuleMoveMustMatchPieceMoveConstraints implements IRule {

    public boolean evaluate(AbstractReferee referee) {
    	IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
    	ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
    	CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		AbstractBoard board = referee.getBoard();
		boolean isMoveLegal = board.isMoveMatchPieceMoveConstraints(piece, moveCoordinate);
		if (!isMoveLegal) {
			referee.printMessage("Move Match Piece Move Constraints? " + isMoveLegal);
			return false;
		}
		return true;
    }

}
