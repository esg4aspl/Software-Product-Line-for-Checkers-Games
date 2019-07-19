package Chessproblem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import core.*;
import base.*;

public class Referee extends AbstractReferee {
	

	public Referee(IGameConfiguration checkersGameConfiguration) {
		super(checkersGameConfiguration);
		view = new GUI("GUI", this);
	}

	public void setup() {
		setupPlayers();
		setupBoardMVC();
		setupPiecesOnBoard();
	}

	private void setupPlayers() {
		numberOfPlayers = gameConfiguration.getNumberOfPlayers();
		numberOfPiecesPerPlayer = gameConfiguration.getNumberOfPiecesPerPlayer();
		playerList = new PlayerList();
		IPlayer player0 = new Player(0, Color.WHITE);
		playerList.addPlayer(player0);
		currentPlayerID = 0;
		currentPlayer = player0;
	}

	private void setupBoardMVC() {
		board = new ChessBoard();
		coordinatePieceMap = board.getCoordinatePieceMap();
	
	}

	private void setupPiecesOnBoard() {
		// create pieces for players and put them on board
		IPlayer player;
		AbstractPiece men;
		StartCoordinates startCoordinates = new ChessStartCoordinates();
		IPieceMovePossibilities menMovePossibilities = new PawnMovePossibilities();
		IPieceMoveConstraints menMoveConstraints =  new PawnMoveConstraints();
		


			player = playerList.getPlayer(0);
			String icon;
			Direction direction;
			
				icon = "W0";
				direction = Direction.N;
			

			//------ should be changed ------
				
				men = new Pawn(0, icon, player, direction, menMovePossibilities, menMoveConstraints);
				player.addPiece(men);
				coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());


			//-------------------------------
		

		//coordinatePieceMap.printPieceMap();
		view.drawBoardView();
		//view.printMessage(playerList.getPlayerStatus());
	}

	public void conductGame() {		
		boolean endOfGame = false;	
		
		if (automaticGameOn) {

			endOfGame = (isSatisfied(new RuleEndOfGameChess(), this));
			view.printMessage("End Of Game? " + endOfGame);
		}
		if(!endOfGame) {
		//	view.printMessage(playerList.getPlayerStatus()+"\n Game begins ...");
		}
		while (!endOfGame) {
			currentMoveCoordinate = view.getNextMove(currentPlayer);
			while (!conductMove()) {
				currentMoveCoordinate = view.getNextMove(currentPlayer);				
			}
			

			endOfGame = (isSatisfied(new RuleEndOfGameChess(), this));

			view.printMessage("Turn : Player " + currentPlayer.getId());
			if (endOfGame) break;

			currentPlayerID++;
			if (currentPlayerID >= numberOfPlayers) currentPlayerID = 0;
			currentPlayer = getPlayerbyID(currentPlayerID);
		} 

		view.printMessage("WINNER " + announceWinner());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);
	}

	
	protected boolean conductMove() {
		ICoordinate sourceCoordinate = currentMoveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = currentMoveCoordinate.getDestinationCoordinate();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		if (!checkMove()) return false;
		List<ICoordinate> path= new ArrayList<ICoordinate>();
		path=board.getCBO().findPath(piece, currentMoveCoordinate);
		coordinatePieceMap.removePieceFromCoordinate(piece, sourceCoordinate);
		coordinatePieceMap.putPieceToCoordinate(piece, destinationCoordinate);
		view.printMessage("CurrentPlayerTurnAgain? " );
		return true;
	}


	protected boolean checkMove() {
		return isSatisfied(new RuleThereMustBePieceAtSourceCoordinate(), this)
				&& isSatisfied(new RulePieceAtSourceCoordinateMustBelongToCurrentPlayer(), this)
				&& isSatisfied(new RuleDestinationCoordinateMustBeValidForCurrentPiece(), this)
				&& isSatisfied(new RuleMoveMustMatchPieceMoveConstraints(), this)
				&& isSatisfied(new RuleThereMustBeNoPieceOnPath(), this);
	}

	public IPlayer announceWinner() {
		return playerList.getPlayer(currentPlayerID);
	}

	public IPlayerList announceDraw(){
		return playerList;
	}

	public IPlayer getCurrentPlayer() {
		return playerList.getPlayer(0);
	}

	public IPlayer getNextPlayer() {
		return playerList.getPlayer(0);
	}

}
