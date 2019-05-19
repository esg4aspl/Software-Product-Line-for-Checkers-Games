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

public class RulePawnOfChessCapturePieceInCrossMove implements IRule {

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
			if(direction == Direction.NW || direction == Direction.NE || 
					direction == Direction.SW || direction == Direction.SE) {
				AbstractPiece pieceAtDestination = coordinatePieceMap.getPieceAtCoordinate(destinationCoordinate);
				if(pieceAtDestination == null) {
					referee.printMessage("Pawn cannot move to cross if there is no piece on destination coordinate!");
					return false;
				}else {
					if(pieceAtDestination.getPlayer() == referee.getCurrentPlayer()) {
						referee.printMessage("Pawn cannot move to cross if there is your piece on destination coordinate!");
						return false;
					}
						
				}
			}					
		}
		return true;	
	}
	
}
