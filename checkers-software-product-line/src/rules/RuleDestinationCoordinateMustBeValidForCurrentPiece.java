package rules;

import core.*;

public class RuleDestinationCoordinateMustBeValidForCurrentPiece implements IRule {

    public boolean evaluate(AbstractReferee referee) {
    	IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
    	ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
    	CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		AbstractBoard board = referee.getBoard();
		boolean isDestinationValid = board.isDestinationCoordinateValid(piece, moveCoordinate);
		if (!isDestinationValid) {
			System.out.println("Destination Valid? " + isDestinationValid);
			return false;
		}
    	return true;
    }

}