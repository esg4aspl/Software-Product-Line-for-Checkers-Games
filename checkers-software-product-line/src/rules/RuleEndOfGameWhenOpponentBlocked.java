package rules;

import java.util.ArrayList;
import java.util.List;

import base.Player;
import core.AbstractBoard;
import core.AbstractPiece;
import core.AbstractReferee;
import core.CoordinateBasedOperations;
import core.CoordinatePieceMap;
import core.ICoordinate;
import core.IMoveCoordinate;
import core.IPlayer;
import core.IPlayerList;
import core.IRule;
import core.MoveCoordinate;

public class RuleEndOfGameWhenOpponentBlocked implements IRule {
	private class CBOInner extends CoordinateBasedOperations {

		public CBOInner(AbstractBoard board) {
			super(board);
			// TODO Auto-generated constructor stub
		}
		
		public List<ICoordinate> possibleMoveCoordinate(AbstractPiece piece){
			List<ICoordinate> relativePossibleDestinationList = piece.getPieceMovePossibilities().getPossibleRelativeDestinationList(piece.getCurrentCoordinate(), piece.getGoalDirection());
			return findAllowedCorrectedDestinationList(piece.getCurrentCoordinate(), relativePossibleDestinationList);
		}
	}
	
	@Override
	public boolean evaluate(AbstractReferee referee) {
		// TODO Auto-generated method stub
		AbstractBoard board = referee.getBoard();
		CBOInner cboInner = new CBOInner(board);
		return isOpponentMoveBlocked(referee,cboInner,board);
	}
	
	private boolean isOpponentMoveBlocked(AbstractReferee referee,CBOInner cboInner,AbstractBoard board) {
		IPlayerList playerList = referee.getPlayers();
		IPlayer currentPlayer = referee.getCurrentPlayer();
		int currentPlayerID = currentPlayer.getId();
		currentPlayerID++;
		if (currentPlayerID >= referee.getNumberOfPlayers()) currentPlayerID = 0;
		Player otherPlayer = (Player)playerList.getPlayer(currentPlayerID);
		List<AbstractPiece> otherPlayerPieces = otherPlayer.getPieceList();
		List<IMoveCoordinate> possibleMoves = new ArrayList<IMoveCoordinate>();
		for(AbstractPiece piece : otherPlayerPieces) {
			//check is there any jump move
			//System.out.println(piece+" BLOCKEDDDD!!!");
			ICoordinate sourceCoordinateOfCurrentPiece = piece.getCurrentCoordinate();
			List<ICoordinate> allowedList = cboInner.possibleMoveCoordinate(piece);
			for(ICoordinate possibleDestinationCoordinate :  allowedList) {
				IMoveCoordinate possibleMoveCoordinate = new MoveCoordinate(sourceCoordinateOfCurrentPiece,possibleDestinationCoordinate);
				//if possible move is jump move then consider correct jump control also
				if(board.getMBO().isJumpMove(piece, possibleMoveCoordinate)) {
					//System.out.println(piece+" - "+possibleMoveCoordinate+" ***********");
					if(isDestinationCoodinateValidAndNull(possibleMoveCoordinate, referee, piece, board) && 
							isJumpedPieceExistsAndBelongsToOpponent(referee, possibleMoveCoordinate, currentPlayer, piece, board,cboInner)) {
						possibleMoves.add(possibleMoveCoordinate);
					}
				}else {
					if(isDestinationCoodinateValidAndNull(possibleMoveCoordinate, referee, piece, board))
						possibleMoves.add(possibleMoveCoordinate);
				}
			}
		}
		if(possibleMoves.isEmpty()) {
			System.out.println("OPPONENT'S MOVE IS BLOCKED!!!!!!");
			return true;
		}	
		return false;
	}
	
	private boolean isJumpedPieceExistsAndBelongsToOpponent(AbstractReferee referee, IMoveCoordinate possibleMoveCoordinate,
		IPlayer currentPlayer,AbstractPiece currentPiece,AbstractBoard board,CBOInner cboInner) {
		CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		List<ICoordinate> path = cboInner.findPath(currentPiece, possibleMoveCoordinate);
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

