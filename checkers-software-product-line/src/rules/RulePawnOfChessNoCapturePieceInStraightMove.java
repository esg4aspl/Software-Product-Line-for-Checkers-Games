package rules;


import base.Pawn;
import core.AbstractBoard;
import core.AbstractPiece;
import core.AbstractReferee;
import core.CoordinateBasedOperations;
import core.CoordinatePieceMap;
import core.Direction;
import core.ICoordinate;
import core.IMoveCoordinate;
import core.IRule;

public class RulePawnOfChessNoCapturePieceInStraightMove implements IRule {

	@Override
	public boolean evaluate(AbstractReferee referee) {
		IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
    	ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
    	ICoordinate destinationCoordinate = moveCoordinate.getDestinationCoordinate();
    	CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		AbstractBoard board = referee.getBoard();
		CoordinateBasedOperations cbo = board.getCBO();
		
		if(piece instanceof Pawn) {
			Direction direction = cbo.findDirection(sourceCoordinate, destinationCoordinate);
			if(direction == Direction.N || direction == Direction.S) {
				AbstractPiece pieceAtDestination = coordinatePieceMap.getPieceAtCoordinate(destinationCoordinate);
				if(pieceAtDestination != null) {
					referee.printMessage("Pawn cannot move to straight if there is pawn on destination coordinate");
					return false;	
				}							
			}
		}
		return true;	
	}
	
}
