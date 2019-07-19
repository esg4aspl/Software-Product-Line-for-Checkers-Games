package Chessproblem;

import core.*;

public class RuleThereMustBePieceAtSourceCoordinate implements IRule {

    public boolean evaluate(AbstractReferee referee) {
    	IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
    	ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
    	CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		if (piece == null) {
			// TODO improve with notification pattern
			referee.printMessage("No piece at source coordinate");
			return false;
		}
    	return true;
    }

}
