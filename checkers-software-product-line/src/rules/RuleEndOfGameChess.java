package rules;

import java.util.ArrayList;
import java.util.List;

import base.Pawn;
import chess.King;
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

public class RuleEndOfGameChess implements IRule {

	@Override
	public boolean evaluate(AbstractReferee referee) {
		List<ICoordinate> path = null;
		AbstractBoard board = referee.getBoard();
		CoordinateBasedOperations cbo = board.getCBO();
		ICoordinate kingCoordinate = getKingsCoordinate(referee);
		List<ICoordinate> checkCoordinates = findCheckCoordinates(referee,kingCoordinate);
		
		if(checkCoordinates.size()>1) {
			return canKingEscape(referee, kingCoordinate);

		}
		else if(checkCoordinates.size() == 1) {
			IPlayerList playerList = referee.getPlayers();
			IPlayer currentPlayer = referee.getCurrentPlayer();
			int currentPlayerID = currentPlayer.getId();
			currentPlayerID++;
			if (currentPlayerID >= referee.getNumberOfPlayers()) currentPlayerID = 0;
			IPlayer otherPlayer = playerList.getPlayer(currentPlayerID);
			List<AbstractPiece> otherPlayerPieces = otherPlayer.getPieceList();
			if(!canKingEscape(referee, kingCoordinate)) {
				return false;
			}
				
			IMoveCoordinate tempMoveCoordinate = new MoveCoordinate(checkCoordinates.get(0), kingCoordinate);
			List<ICoordinate> pathFromCheckToKing = cbo.findPath(referee.getCoordinatePieceMap().getPieceAtCoordinate(checkCoordinates.get(0)), tempMoveCoordinate);

			for(AbstractPiece otherPlayerPiece : otherPlayerPieces) {
				for(ICoordinate pathCoordinate : pathFromCheckToKing) {
					boolean flag = true;
					tempMoveCoordinate = new MoveCoordinate(otherPlayerPiece.getCurrentCoordinate(), pathCoordinate);
					if(moveMustMatchPieceMoveConstraints(tempMoveCoordinate, referee)){
						path = cbo.findPath(otherPlayerPiece, tempMoveCoordinate);
						for(int i=1; i<path.size()-1; i++) {
							ICoordinate coordinateOnPath = path.get(i);
							AbstractPiece pieceAtCoord = board.getCoordinatePieceMap().getPieceAtCoordinate(coordinateOnPath);
							//pathte arada taþ var
							if(pieceAtCoord != null) {
								flag = false;
								break;
							}
						}
						if(flag) {
							ICoordinate tempPieceCoordinate = otherPlayerPiece.getCurrentCoordinate();
							board.getCoordinatePieceMap().removePieceFromCoordinate(otherPlayerPiece, tempPieceCoordinate);
							
							//
							AbstractPiece rollBackPiece = board.getCoordinatePieceMap().getPieceAtCoordinate(pathCoordinate);
							board.getCoordinatePieceMap().putPieceToCoordinate(otherPlayerPiece, pathCoordinate);
							if(tempPieceCoordinate.equals(kingCoordinate))
								kingCoordinate = pathCoordinate;
							//kingCoordinate = getKingsCoordinate(referee);
							if(findCheckCoordinates(referee,kingCoordinate).size() < 1) {
								board.getCoordinatePieceMap().removePieceFromCoordinate(otherPlayerPiece, pathCoordinate);
								board.getCoordinatePieceMap().putPieceToCoordinate(otherPlayerPiece, tempPieceCoordinate);
								if(rollBackPiece!=null)
									board.getCoordinatePieceMap().putPieceToCoordinate(rollBackPiece, pathCoordinate);
								return false;
							}
							board.getCoordinatePieceMap().removePieceFromCoordinate(otherPlayerPiece, pathCoordinate);
							board.getCoordinatePieceMap().putPieceToCoordinate(otherPlayerPiece, tempPieceCoordinate);
							if(rollBackPiece!=null)
								board.getCoordinatePieceMap().putPieceToCoordinate(rollBackPiece, pathCoordinate);
						}
					}
				}
				
			}
			
			return true;

		}else {
			return false;
		}
		
	}
	
	private boolean canKingEscape(AbstractReferee referee,ICoordinate kingCoordinate) {
		CoordinateBasedOperations cbo = referee.getBoard().getCBO();
		AbstractPiece king = referee.getCoordinatePieceMap().getPieceAtCoordinate(kingCoordinate);
		List<ICoordinate> possibleMovesOfKing = cbo.findAllowedCorrectedDestinationList(kingCoordinate,
				king.getPieceMovePossibilities().getPossibleRelativeDestinationList(kingCoordinate, king.getGoalDirection()));
		AbstractPiece temp;
		for(ICoordinate possibleCoordinate : possibleMovesOfKing) {
			temp = referee.getCoordinatePieceMap().getPieceAtCoordinate(possibleCoordinate);
			if(temp!=null && temp.getPlayer().equals(king.getPlayer()))
				continue;
			if(findCheckCoordinates(referee,possibleCoordinate).size()==0) {
				return false;
			}
		}
		return true;
	}
	
	private List<ICoordinate> findCheckCoordinates(AbstractReferee referee, ICoordinate kingCoordinate){

		List<ICoordinate> checkCoordinates = new ArrayList<ICoordinate>();
		AbstractBoard board = referee.getBoard();
		CoordinateBasedOperations cbo = board.getCBO();

		for(AbstractPiece piece : referee.getCurrentPlayer().getPieceList()) {
			boolean flag = true;
			List<ICoordinate> path = new ArrayList<ICoordinate>();
			IMoveCoordinate tempMoveCoordinate = new MoveCoordinate(piece.getCurrentCoordinate(), kingCoordinate);

			if(moveMustMatchPieceMoveConstraints(tempMoveCoordinate, referee)){
				path = cbo.findPath(piece, tempMoveCoordinate);
				for(int i=1; i<path.size()-1; i++) {
					ICoordinate coordinateOnPath = path.get(i);
					AbstractPiece pieceAtCoord = board.getCoordinatePieceMap().getPieceAtCoordinate(coordinateOnPath);
					//pathte arada taþ var
					if(pieceAtCoord != null && !(pieceAtCoord instanceof King)) {
						flag = false;
						break;
					}
				}
				if(flag)
					checkCoordinates.add(piece.getCurrentCoordinate());
			}

		}
		return checkCoordinates;
	}

	private ICoordinate getKingsCoordinate(AbstractReferee referee) {
		IPlayerList playerList = referee.getPlayers();
		IPlayer currentPlayer = referee.getCurrentPlayer();
		int currentPlayerID = currentPlayer.getId();
		currentPlayerID++;
		if (currentPlayerID >= referee.getNumberOfPlayers()) currentPlayerID = 0;
		IPlayer otherPlayer = playerList.getPlayer(currentPlayerID);
		
		
		for(AbstractPiece piece:otherPlayer.getPieceList()) {
			if(piece instanceof King) {
				return piece.getCurrentCoordinate();
			}
		}
		return null;
	}
	private boolean moveMustMatchPieceMoveConstraints(IMoveCoordinate moveCoordinate, AbstractReferee referee) {
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		AbstractBoard board = referee.getBoard();
		boolean isMoveLegal = board.isMoveMatchPieceMoveConstraints(piece, moveCoordinate);
		if(piece instanceof Pawn)
			isMoveLegal = isMoveLegal && checkPawn(moveCoordinate, referee);
		if (!isMoveLegal) {
			return false;
		}
		return true;
	}
	
	private boolean checkPawn(IMoveCoordinate moveCoordinate, AbstractReferee referee) {
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = moveCoordinate.getDestinationCoordinate();
		CoordinatePieceMap coordinatePieceMap = referee.getCoordinatePieceMap();
		if(coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate)!=null && sourceCoordinate.getXCoordinate()-destinationCoordinate.getXCoordinate()==0)
			return false;
		
		if(coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate)==null)
			return false;
		return true;
	}

}
