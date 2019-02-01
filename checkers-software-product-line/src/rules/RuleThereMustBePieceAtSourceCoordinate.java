package rules;

import core.*;

public class RuleThereMustBePieceAtSourceCoordinate implements IRule {

    public boolean evaluate(AbstractReferee referee) {
    	IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
    	ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
    	CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		if (piece == null) {
			// TODO improve with notification pattern
			System.out.println("No piece at source coordinate");
			return false;
		}
    	return true;
    }

}
