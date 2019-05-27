package rules;

import java.util.List;
import core.*;

public class RuleIfJumpMoveThenJumpedPieceMustBeOpponentPiece implements IRule {
	
	public boolean evaluate(AbstractReferee referee) {
		IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
		IPlayer player = referee.getCurrentPlayer();
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = moveCoordinate.getDestinationCoordinate();
		CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		AbstractBoard board = referee.getBoard();
		if (board.getMBO().isJumpMove(piece, moveCoordinate)) {
			List<ICoordinate> path = board.getCBO().findPath(piece, moveCoordinate);

			int howManyPieceAreOnPath = 0;
			ICoordinate pathCoordinate = null;
			for(int i=1; i<path.size()-1; i++) {
				ICoordinate coordinateOnPath = path.get(i);
				AbstractPiece pieceAtCoord = board.getCoordinatePieceMap().getPieceAtCoordinate(coordinateOnPath);
				if(pieceAtCoord!=null) {
					howManyPieceAreOnPath+=1;
					pathCoordinate = coordinateOnPath;
				}
					
			}
			CoordinateBasedOperations cbo = board.getCBO();
			IPieceMoveConstraints moveConstraints = piece.getPieceMoveConstraints();
			Direction moveDirection = cbo.findDirection(sourceCoordinate, destinationCoordinate);
			if(howManyPieceAreOnPath==0) {
				if(moveConstraints.isMultipleSquareMarchMoveAllowed(moveDirection))
					return true;
				else {
					referee.printMessage("There must be one piece on jump path " + howManyPieceAreOnPath);
					return false;
				}
					
			}
						
			if(howManyPieceAreOnPath!=1) {
				referee.printMessage("There must be only one piece on jump path " + howManyPieceAreOnPath);
				return false;
			}
				
			AbstractPiece pieceAtPath = coordinatePieceMap.getPieceAtCoordinate(pathCoordinate);
			if (pieceAtPath==null || pieceAtPath.getPlayer().equals(player)) {
				referee.printMessage("Jumped Piece Must Be Opponent Piece");
				return false;
			}
		}
		return true;
	}
}
