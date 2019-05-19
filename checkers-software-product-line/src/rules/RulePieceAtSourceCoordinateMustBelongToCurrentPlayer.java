package rules;

import core.*;

public class RulePieceAtSourceCoordinateMustBelongToCurrentPlayer implements IRule {

    public boolean evaluate(AbstractReferee referee) {
    	IPlayer player = referee.getCurrentPlayer();
    	IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
    	ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
    	CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
    	AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		if (!piece.getPlayer().equals(player)) {
			referee.printMessage("Piece does not belong to current player");
			return false;
		}
    	return true;
    }

}
