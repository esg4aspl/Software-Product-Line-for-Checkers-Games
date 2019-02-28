package rules;

import java.util.List;

import core.AbstractBoard;
import core.AbstractPiece;
import core.AbstractReferee;
import core.CoordinateBasedOperations;
import core.CoordinatePieceMap;
import core.ICoordinate;
import core.IMoveCoordinate;
import core.IPieceMovePossibilities;
import core.IPlayer;
import core.IPlayerList;
import core.IRule;
import core.MoveCoordinate;

public class RuleEndOfGameWhenOpponentBlocked implements IRule {
	
	@Override
	public boolean evaluate(AbstractReferee referee) {
		// TODO Auto-generated method stub
		return isOpponentMoveBlocked(referee);
	}
	
	private boolean isOpponentMoveBlocked(AbstractReferee referee) {
		AbstractBoard board = referee.getBoard();
		IPlayerList playerList = referee.getPlayers();
		IPlayer currentPlayer = referee.getCurrentPlayer();
		int currentPlayerID = currentPlayer.getId();
		currentPlayerID++;
		if (currentPlayerID >= referee.getNumberOfPlayers()) currentPlayerID = 0;
		IPlayer otherPlayer = playerList.getPlayer(currentPlayerID);
		List<AbstractPiece> otherPlayerPieces = otherPlayer.getPieceList();
		for(AbstractPiece piece : otherPlayerPieces) {
			//check is there any jump move
			//System.out.println(piece+" BLOCKEDDDD!!!");
			ICoordinate sourceCoordinateOfCurrentPiece = piece.getCurrentCoordinate();
			CoordinateBasedOperations cbo = board.getCBO();
			IPieceMovePossibilities piecePossibilities = piece.getPieceMovePossibilities();
			List<ICoordinate> relativePossibleDestinationList = piecePossibilities.getPossibleRelativeDestinationList(piece.getCurrentCoordinate(), piece.getGoalDirection());
			List<ICoordinate> allowedList = cbo.findAllowedCorrectedDestinationList(piece.getCurrentCoordinate(), relativePossibleDestinationList);
			for(ICoordinate possibleDestinationCoordinate :  allowedList) {
				IMoveCoordinate possibleMoveCoordinate = new MoveCoordinate(sourceCoordinateOfCurrentPiece,possibleDestinationCoordinate);
				//if possible move is jump move then consider correct jump control also
				if(board.getMBO().isJumpMove(piece, possibleMoveCoordinate)) {
					//System.out.println(piece+" - "+possibleMoveCoordinate+" ***********");
					if(isDestinationCoodinateValidAndNull(possibleMoveCoordinate, referee, piece, board) && 
							isJumpedPieceExistsAndBelongsToOpponent(referee, possibleMoveCoordinate, currentPlayer, piece, cbo)) {
						return false;
					}
				}else {
					if(isDestinationCoodinateValidAndNull(possibleMoveCoordinate, referee, piece, board))
						return false;
				}
			}
		}
		System.out.println("OPPONENT'S MOVE IS BLOCKED!!!!!!");
		return true;
		
	}
	
	private boolean isJumpedPieceExistsAndBelongsToOpponent(AbstractReferee referee, IMoveCoordinate possibleMoveCoordinate,
		IPlayer currentPlayer,AbstractPiece currentPiece,CoordinateBasedOperations cbo) {
		CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		List<ICoordinate> path = cbo.findPath(currentPiece, possibleMoveCoordinate);
		ICoordinate pathCoordinate = path.get(1);
		AbstractPiece pieceAtPath = coordinatePieceMap.getPieceAtCoordinate(pathCoordinate);
		if (pieceAtPath==null || pieceAtPath.getPlayer().equals(currentPlayer)) {
			return false;
		}
		return true;
	}
	private boolean isDestinationCoodinateValidAndNull(IMoveCoordinate possibleMoveCoordinate,AbstractReferee referee,
			AbstractPiece currentPiece,AbstractBoard board) {		
		CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		ICoordinate destinationCoordinate = possibleMoveCoordinate.getDestinationCoordinate();
		AbstractPiece pieceAtDestination = coordinatePieceMap.getPieceAtCoordinate(destinationCoordinate);
		if (pieceAtDestination != null || !board.isDestinationCoordinateValid(currentPiece, possibleMoveCoordinate) )
			return false;
		return true;
	}

}

