package chess;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import core.*;
import base.*;
import rules.*;

public class Referee extends AbstractReferee {

	protected ChessBoardConsoleView consoleView;
	protected boolean automaticGameOn;

	public Referee(AbstractGameConfiguration checkersGameConfiguration) {
		super(checkersGameConfiguration);
		automaticGameOn = true;
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
		IPlayer player1 = new Player(1, Color.BLACK);
		playerList.addPlayer(player1);
		currentPlayerID = 0;
		currentPlayer = player0;
	}

	private void setupBoardMVC() {
		board = new ChessBoard();
		coordinatePieceMap = board.getCoordinatePieceMap();
		consoleView = new ChessBoardConsoleView(this);
	}

	private void setupPiecesOnBoard() {
		// create pieces for players and put them on board
		IPlayer player;
		AbstractPiece men;
		StartCoordinates startCoordinates = new ChessStartCoordinates();
		IPieceMovePossibilities menMovePossibilities = new PawnMovePossibilities();
		IPieceMoveConstraints menMoveConstraints =  new PawnMoveConstraints();
		IPieceMovePossibilities rookMovePossibilities = new PawnMovePossibilities();
		IPieceMoveConstraints rookMoveConstraints =  new PawnMoveConstraints();
		IPieceMovePossibilities knightMovePossibilities = new KnightMovePossibilities();
		IPieceMoveConstraints knightMoveConstraints =  new KnightMoveConstraints();
		IPieceMovePossibilities bishopMovePossibilities = new BishopMovePossibilities();
		IPieceMoveConstraints bishopMoveConstraints =  new BishopMoveConstraints();
		IPieceMovePossibilities kingMovePossibilities = new KingMovePossibilities();
		IPieceMoveConstraints kingMoveConstraints =  new KingMoveConstraints();
		IPieceMovePossibilities queenMovePossibilities = new QueenMovePossibilities();
		IPieceMoveConstraints queenMoveConstraints =  new QueenMoveConstraints();

		for (int i = 0; i < numberOfPlayers; i++) {
			player = playerList.getPlayer(i);
			String icon;
			Direction direction;
			if (i == 0) {
				icon = "W0";
				direction = Direction.N;
			}
			else {
				icon = "B1";
				direction = Direction.S;
			}

			//------ should be changed ------
			men = new Rook(0, "R"+i, player, direction, rookMovePossibilities, rookMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new Knight(0, "k"+i, player, direction, knightMovePossibilities, knightMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new Bishop(0, "B"+i, player, direction, bishopMovePossibilities, bishopMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new King(0, "K"+i, player, direction, kingMovePossibilities, kingMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new Queen(0, "Q"+i, player, direction, queenMovePossibilities, queenMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new Bishop(0, "B"+i, player, direction, bishopMovePossibilities, bishopMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());			

			men = new Knight(0, "k"+i, player, direction, knightMovePossibilities, knightMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new Rook(0, "R"+i, player, direction, rookMovePossibilities, rookMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			for (int j = 0; j < numberOfPiecesPerPlayer/2; j++) {
				men = new Pawn(j, icon, player, direction, menMovePossibilities, menMoveConstraints);
				player.addPiece(men);
				coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());
			}

			//-------------------------------
		}

		coordinatePieceMap.printPieceMap();
		System.out.println(playerList.getPlayerStatus());
	}

	public void conductGame() {		
		boolean endOfGame = false;
		boolean startWithAutomaticGame = false;

		if (startWithAutomaticGame) {
			conductAutomaticGame();
			endOfGame = (isSatisfied(new RuleEndOfGameGeneral(), this));
			System.out.println("End Of Game? " + endOfGame);
		}
		if(!endOfGame) {
			System.out.println(playerList.getPlayerStatus());
			System.out.println("Game begins ...");
			consoleView.drawBoardView();
		}
		while (!endOfGame) {
			currentMoveCoordinate = consoleView.getNextMove(currentPlayer);
			while (!conductMove()) {
				currentMoveCoordinate = consoleView.getNextMove(currentPlayer);				
			}
			consoleView.drawBoardView();

			endOfGame = (isSatisfied(new RuleEndOfGameGeneral(), this));

			System.out.println("End Of Game? " + endOfGame);
			if (endOfGame) break;

			currentPlayerID++;
			if (currentPlayerID >= numberOfPlayers) currentPlayerID = 0;
			currentPlayer = getPlayerbyID(currentPlayerID);
		} 
		//consoleView.drawBoardView();

		System.out.println("WINNER " + announceWinner());

		consoleView.closeFile();
		System.exit(0);
	}

	private void conductAutomaticGame() {				
		System.out.println("Automatic Game begins ...");
		automaticGameOn = true;
		int step = 0;
		consoleView.drawBoardView();
		while (step < consoleView.getSizeOfAutomaticMoveList()) {
			currentMove = consoleView.getNextAutomaticMove(step);
			currentMoveCoordinate = currentMove.getMoveCoordinate();
			currentPlayer = currentMove.getPlayer();
			currentPlayerID = currentPlayer.getId();
			conductMove();
			consoleView.drawBoardView();
			step++;
		}
		automaticGameOn = false;
		System.out.println("Automatic Game ends ...");
	}

	protected boolean conductMove() {
		ICoordinate sourceCoordinate = currentMoveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = currentMoveCoordinate.getDestinationCoordinate();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		if (!checkMove()) return false;
		List<ICoordinate> path= new ArrayList<ICoordinate>();
		if(piece instanceof Knight) {
			path.add(sourceCoordinate);
			path.add(destinationCoordinate);
		}
		else
			path=board.getCBO().findPath(piece, currentMoveCoordinate);

		coordinatePieceMap.removePieceFromCoordinate(piece, sourceCoordinate);

		MoveOpResult moveOpResult = moveInterimOperation(piece, currentMoveCoordinate, path);
		System.out.println(path);
		//if piece become king then terminate the move
		AbstractPiece  temp  = piece;
		piece = becomeAndOrPutOperation(piece, destinationCoordinate);
		if(!temp.equals(piece))
			moveOpResult = new MoveOpResult(true, false);
		System.out.println("CurrentPlayerTurnAgain? " + moveOpResult.isCurrentPlayerTurnAgain());
		//if (moveOpResult.isCurrentPlayerTurnAgain() && !automaticGameOn) 
		//	conductCurrentPlayerTurnAgain(moveOpResult, piece);
		return true;
	}

	protected boolean conductCurrentPlayerTurnAgain(MoveOpResult moveOpResult, AbstractPiece piece) {
		AbstractPiece temp  = piece;
		while (moveOpResult.isCurrentPlayerTurnAgain()) {
			List<ICoordinate> secondJumpList = board.getCBO().findAllowedContinousJumpList(piece);
			board.getCBO().printPathList(secondJumpList, "Second Jump List");
			if (secondJumpList.size() == 0) {
				moveOpResult = new MoveOpResult(false, false);
				break;
			}
			currentMoveCoordinate = consoleView.getNextMove(currentPlayer);
			ICoordinate sourceCoordinate = currentMoveCoordinate.getSourceCoordinate();
			ICoordinate destinationCoordinate = currentMoveCoordinate.getDestinationCoordinate();
			if (!checkMove()) continue;
			moveOpResult = new MoveOpResult(false, false);
			for(ICoordinate coordinate : secondJumpList) {
				if (coordinate.equals(destinationCoordinate)) {
					List<ICoordinate> path = board.getCBO().findPath(piece, currentMoveCoordinate);
					coordinatePieceMap.removePieceFromCoordinate(piece, sourceCoordinate);
					moveOpResult = moveInterimOperation(piece, currentMoveCoordinate, path);
					piece = becomeAndOrPutOperation(piece, destinationCoordinate);
					if(!temp.equals(piece)) 
						moveOpResult = new MoveOpResult(true, false);
				}
			}
		}
		return true;
	}

	protected boolean checkMove() {
		return isSatisfied(new RuleThereMustBePieceAtSourceCoordinate(), this)
				&& isSatisfied(new RulePieceAtSourceCoordinateMustBelongToCurrentPlayer(), this)
				&& isSatisfied(new RuleDestinationCoordinateMustBeValidForCurrentPiece(), this)
				&& isSatisfied(new RuleMoveMustMatchPieceMoveConstraints(), this);
	}

	protected MoveOpResult moveInterimOperation(AbstractPiece piece, IMoveCoordinate moveCoordinate, List<ICoordinate> path) {
		IPlayer player = piece.getPlayer();
		ICoordinate pathCoordinate = path.get(1);
		System.out.println("Path Coordinate " + pathCoordinate);
		AbstractPiece pieceAtPath = coordinatePieceMap.getPieceAtCoordinate(pathCoordinate);		
		if (pieceAtPath!=null && !pieceAtPath.getPlayer().equals(player)) {
			// capture piece at path
			coordinatePieceMap.capturePieceAtCoordinate(pieceAtPath, pathCoordinate);
			pieceAtPath.getPlayer().removePiece(pieceAtPath);
			System.out.println(pieceAtPath.getPlayer().getPieceList().size());
			return new MoveOpResult(true, true); // jumped over opponent team
		}
		return new MoveOpResult(true, false);
	}

	protected AbstractPiece becomeAndOrPutOperation(AbstractPiece piece, ICoordinate destinationCoordinate) {
		/*	//check the piece is already king or not
		if(!(piece instanceof King))
			if ((piece.getGoalDirection() == Direction.N && destinationCoordinate.getYCoordinate() == 7 && piece.getIcon().equals("B"))
					|| (piece.getGoalDirection() == Direction.S && destinationCoordinate.getYCoordinate() == 0 && piece.getIcon().equals("W"))){
				IPlayer player = piece.getPlayer();
				piece = becomeNewPiece(player, piece);
			}
		 */
		coordinatePieceMap.putPieceToCoordinate(piece, destinationCoordinate);
		return piece;
	}

	protected AbstractPiece becomeNewPiece(IPlayer player, AbstractPiece piece) {
		int pieceID = piece.getId();
		Direction goalDirection = piece.getGoalDirection();
		player.removePiece(piece);
		IPieceMovePossibilities kingMovePossibilities = new KingMovePossibilities();
		IPieceMoveConstraints kingMoveConstraints =  new KingMoveConstraints();
		String icon;
		if (player.getId() == 0) icon = "A";
		else icon = "Z";
		AbstractPiece king = new King(pieceID, icon, player, goalDirection, kingMovePossibilities, kingMoveConstraints);
		player.addPiece(king);
		return king;
	}

	public IPlayer announceWinner() {
		return playerList.getPlayer(currentPlayerID);
	}

	public IPlayerList announceDraw(){
		return playerList;
	}

	public IPlayer getCurrentPlayer() {
		if (currentPlayerID >= numberOfPlayers) currentPlayerID = 0;
		return playerList.getPlayer(currentPlayerID);
	}

	public IPlayer getNextPlayer() {
		int nextPlayerID = currentPlayerID+1;
		if (nextPlayerID >= numberOfPlayers) nextPlayerID = 0;
		return playerList.getPlayer(nextPlayerID);
	}

}
