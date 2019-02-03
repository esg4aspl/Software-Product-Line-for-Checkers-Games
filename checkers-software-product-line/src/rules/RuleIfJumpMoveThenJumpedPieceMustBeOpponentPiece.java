package rules;

import java.util.List;
import core.*;

public class RuleIfJumpMoveThenJumpedPieceMustBeOpponentPiece implements IRule {
	
	public boolean evaluate(AbstractReferee referee) {
		IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
		IPlayer player = referee.getCurrentPlayer();
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		AbstractBoard board = referee.getBoard();
		if (board.getMBO().isJumpMove(piece, moveCoordinate)) {
			List<ICoordinate> path = board.getCBO().findPath(piece, moveCoordinate);
			System.out.println("Jump Move");
			ICoordinate pathCoordinate = path.get(1);
			System.out.println("Path Coordinate " + pathCoordinate);
			AbstractPiece pieceAtPath = coordinatePieceMap.getPieceAtCoordinate(pathCoordinate);
			if (pieceAtPath.getPlayer().equals(player)) {
				System.out.println("Jumped Piece Must Be Opponent Piece");
				return false;
			}
		}
		return true;
	}

}
