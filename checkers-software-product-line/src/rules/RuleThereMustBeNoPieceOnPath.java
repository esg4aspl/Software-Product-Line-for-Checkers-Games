package rules;

import java.util.List;

import chess.Knight;
import core.AbstractPiece;
import core.AbstractReferee;
import core.CoordinateBasedOperations;
import core.CoordinatePieceMap;
import core.ICoordinate;
import core.IMoveCoordinate;
import core.IRule;

public class RuleThereMustBeNoPieceOnPath implements IRule {
	//you must add to tail of rule set
	//other rules must be checked until here
	@Override
	public boolean evaluate(AbstractReferee referee) {
		// TODO Auto-generated method stub
		IMoveCoordinate currentMoveCoordinate = referee.getCurrentMoveCoordinate();
		ICoordinate sourceCoordinate = currentMoveCoordinate.getSourceCoordinate();
		CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		CoordinateBasedOperations cbo = referee.getBoard().getCBO();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
	
		if(piece instanceof Knight) {
			return true;
		}
		List<ICoordinate> path = cbo.findPath(piece, currentMoveCoordinate);
		for(int i=1; i<path.size()-1; i++) {
			ICoordinate coordinateOnPath = path.get(i);
			AbstractPiece pieceAtCoord = coordinatePieceMap.getPieceAtCoordinate(coordinateOnPath);
			if(pieceAtCoord!=null) {
				referee.printMessage("There must be no piece on the move path!!!.");
				return false;
			}
		}
		return true;
	}

}
