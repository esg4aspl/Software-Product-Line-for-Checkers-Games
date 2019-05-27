package rules;

import java.util.ArrayList;
import java.util.List;

import base.Pawn;
import chess.King;
import chess.Knight;
import core.AbstractBoard;
import core.AbstractPiece;
import core.AbstractReferee;
import core.Coordinate;
import core.CoordinateBasedOperations;
import core.CoordinatePieceMap;
import core.ICoordinate;
import core.IMoveCoordinate;
import core.IPlayer;
import core.IPlayerList;
import core.IRule;
import core.MoveCoordinate;

public class RuleIsKingFree implements IRule {

	@Override
	public boolean evaluate(AbstractReferee referee) {
		AbstractBoard board = referee.getBoard();
		CoordinateBasedOperations cbo = board.getCBO();
		IPlayerList playerList = referee.getPlayers();
		IPlayer currentPlayer = referee.getCurrentPlayer();
		int currentPlayerID = currentPlayer.getId();
		currentPlayerID++;
		if (currentPlayerID >= referee.getNumberOfPlayers()) currentPlayerID = 0;
		IPlayer otherPlayer = playerList.getPlayer(currentPlayerID);
		List<AbstractPiece> otherPlayerPieces = otherPlayer.getPieceList();
		ICoordinate pieceDestinationCoordinate;

		//tehdit edebilecek source coordinatlarý tutmak için
		List<ICoordinate> checkSquares = new ArrayList<ICoordinate>();
		// if the piece is King then Other player pieces will check the King's destination coordinate
		if(board.getCoordinatePieceMap().getPieceAtCoordinate(referee.getCurrentMoveCoordinate().getSourceCoordinate()) instanceof King)
			pieceDestinationCoordinate = referee.getCurrentMoveCoordinate().getDestinationCoordinate();
		else	
			pieceDestinationCoordinate = getKingsCoordinate(referee);

		for(AbstractPiece piece: otherPlayerPieces) {
			boolean flag=true;
			List<ICoordinate> path = new ArrayList<ICoordinate>();
			ICoordinate pieceSourceCoordinate = new Coordinate(piece.getCurrentCoordinate().getXCoordinate(), piece.getCurrentCoordinate().getYCoordinate());

			// the piece moves like from its source coordinate to King's current coordinate
			IMoveCoordinate tempMoveCoordinate = new MoveCoordinate(pieceSourceCoordinate, pieceDestinationCoordinate);
			if(piece instanceof Knight) {
				// if the piece is Knight and also Knight can move to King's square then add Knight's coordinate to check squares
				if(moveMustMatchPieceMoveConstraints(tempMoveCoordinate, referee))			
					checkSquares.add(pieceSourceCoordinate);			
			}
			else {
				// if the piece is not Knight
				if(moveMustMatchPieceMoveConstraints(tempMoveCoordinate, referee)){
					path = cbo.findPath(piece, tempMoveCoordinate);
					for(int i=1; i<path.size()-1; i++) {
						ICoordinate coordinateOnPath = path.get(i);
						AbstractPiece pieceAtCoord = board.getCoordinatePieceMap().getPieceAtCoordinate(coordinateOnPath);
						// There is piece(s) on the path
						if(pieceAtCoord != null) {
							if(pieceAtCoord.equals(board.getCoordinatePieceMap().getPieceAtCoordinate(referee.getCurrentMoveCoordinate().getSourceCoordinate())) 
									&&!path.contains(referee.getCurrentMoveCoordinate().getDestinationCoordinate())) {
								continue;	
							}
							flag=false;
							break;
						}
					}
					if(flag)
						checkSquares.add(pieceSourceCoordinate);
				}
			}
		}

		ICoordinate temp = null;		
		for(ICoordinate coordinate: checkSquares) {
			// if moved piece removes the piece that checks, save that coordinate to remove from checks squares later
			if(coordinate.equals(referee.getCurrentMoveCoordinate().getDestinationCoordinate())) {
				temp = coordinate;
				break;
			}
			IMoveCoordinate tempMoveCoordinate = new MoveCoordinate(coordinate,pieceDestinationCoordinate);
			List<ICoordinate> pathFromCheckToKing = cbo.findPath(referee.getCoordinatePieceMap().getPieceAtCoordinate(coordinate), tempMoveCoordinate);
			if(!(board.getCoordinatePieceMap().getPieceAtCoordinate(referee.getCurrentMoveCoordinate().getSourceCoordinate()) instanceof King))
				if(isCoordinateInPath(pathFromCheckToKing, referee.getCurrentMoveCoordinate().getDestinationCoordinate())) {
					temp = coordinate;
					break;
				}
		}

		if(temp!=null) {	
			checkSquares.remove(temp);
		}
		// Still, there is threat the move is invalid
		if(checkSquares.size()>0) {
			referee.printMessage("invalid : King has to be free");
			return false;
		}
		//there is no threat
		return true;
	}
	private ICoordinate getKingsCoordinate(AbstractReferee referee) {
		IPlayer currentPlayer = referee.getCurrentPlayer();
		for(AbstractPiece piece:currentPlayer.getPieceList()) {
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
		if(piece instanceof Pawn) {
			isMoveLegal = isMoveLegal && checkPawn(moveCoordinate, referee);
		}

		if (!isMoveLegal) {
			return false;
		}
		return true;
	}

	private boolean checkPawn(IMoveCoordinate moveCoordinate, AbstractReferee referee) {
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = moveCoordinate.getDestinationCoordinate();
		if(sourceCoordinate.getXCoordinate()-destinationCoordinate.getXCoordinate() == 0) {
			return false;
		}


		return true;
	}

	private boolean isCoordinateInPath(List<ICoordinate> path,ICoordinate destinationCoord) {
		for(ICoordinate c: path) {
			if(c.equals(destinationCoord))
				return true;
		}
		return false;
	}

}
