package rules;

import core.*;

public class RuleThereMustNotBePieceAtDestinationCoordinate implements IRule {

    public boolean evaluate(AbstractReferee referee) {
    	IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
    	ICoordinate destinationCoordinate = moveCoordinate.getDestinationCoordinate();
    	CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
    	AbstractPiece pieceAtDestination = coordinatePieceMap.getPieceAtCoordinate(destinationCoordinate);
		if (pieceAtDestination != null) {
			referee.printMessage("A piece at destination coordinate");
			return false;
		}
    	return true;
    }

}
