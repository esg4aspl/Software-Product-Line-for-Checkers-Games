package rules;

import core.AbstractPiece;
import core.AbstractReferee;
import core.CoordinatePieceMap;
import core.ICoordinate;
import core.IMoveCoordinate;
import core.IRule;

public class RuleIfPieceToBeCapturedThenItMustBeBelongsToOpponent implements IRule{

	@Override
	public boolean evaluate(AbstractReferee referee) {
		// TODO Auto-generated method stub
		IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = moveCoordinate.getDestinationCoordinate();
		CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		AbstractPiece destPiece = coordinatePieceMap.getPieceAtCoordinate(destinationCoordinate);
		if(destPiece==null)
			return true;
		if(piece.getPlayer().equals(destPiece.getPlayer())) {
			referee.printMessage("You can only capture opponent piece!!!!");
			return false;
		}
		return true;
	}

}
