package rules;

import java.util.List;
import core.*;

public class RuleIfJumpMoveThenJumpedPieceMustBe implements IRule {
	
	public boolean evaluate(AbstractReferee referee) {
		IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		AbstractBoard board = referee.getBoard();
		if (board.getMBO().isJumpMove(piece, moveCoordinate)) {
			System.out.println(" SEYIN ICI " + piece+ " Move: "+moveCoordinate);
			List<ICoordinate> path = board.getCBO().findPath(piece, moveCoordinate);
			int howManyPieceAreOnPath = 0;
			for(int i=1; i<path.size()-1; i++) {
				ICoordinate coordinateOnPath = path.get(i);
				AbstractPiece pieceAtCoord = board.getCoordinatePieceMap().getPieceAtCoordinate(coordinateOnPath);
				if(pieceAtCoord!=null) {
					howManyPieceAreOnPath+=1;
				}
					
			}
			
			if(howManyPieceAreOnPath!=1) {
				System.out.println("There must be only one piece on jump path " + howManyPieceAreOnPath);
				return false;
			}
		}
		return true;
	}
}
