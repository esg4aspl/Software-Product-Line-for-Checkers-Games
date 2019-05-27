package chess;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import core.*;
import base.*;
import rules.*;

public class Referee extends AbstractReferee {
	protected ChessBoardConsoleView consoleView;

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
		IPieceMovePossibilities rookMovePossibilities = new RookMovePossibilities();
		IPieceMoveConstraints rookMoveConstraints =  new RookMoveConstraints();
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
			men = new Rook((i*7)+i+2, "R"+i, player, direction, rookMovePossibilities, rookMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new Knight((i*7)+i+3, "k"+i, player, direction, knightMovePossibilities, knightMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new Bishop((i*7)+i+4, "B"+i, player, direction, bishopMovePossibilities, bishopMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new King((i*7)+i+5, "K"+i, player, direction, kingMovePossibilities, kingMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new Queen((i*7)+i+6, "Q"+i, player, direction, queenMovePossibilities, queenMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new Bishop((i*7)+i+7, "B"+i, player, direction, bishopMovePossibilities, bishopMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());			

			men = new Knight((i*7)+i+8, "k"+i, player, direction, knightMovePossibilities, knightMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			men = new Rook((i*7)+i+9, "R"+i, player, direction, rookMovePossibilities, rookMoveConstraints);
			player.addPiece(men);
			coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());

			for (int j = 0; j < numberOfPiecesPerPlayer/2; j++) {
				men = new Pawn(i, icon, player, direction, menMovePossibilities, menMoveConstraints);
				player.addPiece(men);
				coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());
			}

			//-------------------------------
		}

		//coordinatePieceMap.printPieceMap();
		view.drawBoardView();
		//view.printMessage(playerList.getPlayerStatus());
	}

	public void conductGame() {		
		boolean endOfGame = false;	
		
		if (automaticGameOn) {
			conductAutomaticGame();
			endOfGame = (isSatisfied(new RuleEndOfGameChess(), this));
			view.printMessage("End Of Game? " + endOfGame);
		}
		if(!endOfGame) {
		//	view.printMessage(playerList.getPlayerStatus()+"\n Game begins ...");
			consoleView.drawBoardView();
		}
		while (!endOfGame) {
			currentMoveCoordinate = view.getNextMove(currentPlayer);
			while (!conductMove()) {
				currentMoveCoordinate = view.getNextMove(currentPlayer);				
			}
			consoleView.drawBoardView();

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
		consoleView.closeFile();
		System.exit(0);
	}

	private void conductAutomaticGame() {				
		view.printMessage("Automatic Game begins ...");
		automaticGameOn = true;
		int step = 0;
		consoleView.drawBoardView();
		while (step < consoleView.getSizeOfAutomaticMoveList()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentMove = consoleView.getNextAutomaticMove(step);
			currentMoveCoordinate = currentMove.getMoveCoordinate();
			currentPlayer = currentMove.getPlayer();
			currentPlayerID = currentPlayer.getId();
			conductMove();
			consoleView.drawBoardView();
			step++;
		}
		automaticGameOn = false;
		view.printMessage("Automatic Game ends ...");
	}

	protected boolean conductMove() {
		ICoordinate sourceCoordinate = currentMoveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = currentMoveCoordinate.getDestinationCoordinate();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		if (!checkMove()) return false;
		List<ICoordinate> path= new ArrayList<ICoordinate>();
		path=board.getCBO().findPath(piece, currentMoveCoordinate);
		coordinatePieceMap.removePieceFromCoordinate(piece, sourceCoordinate);

		MoveOpResult moveOpResult = moveInterimOperation(piece, currentMoveCoordinate, path);

		//if piece become king then terminate the move
		AbstractPiece  temp  = piece;
		piece = becomeAndOrPutOperation(piece, destinationCoordinate);
		if(!temp.equals(piece))
			moveOpResult = new MoveOpResult(true, false);
		view.printMessage("CurrentPlayerTurnAgain? " + moveOpResult.isCurrentPlayerTurnAgain());
		//if (moveOpResult.isCurrentPlayerTurnAgain() && !automaticGameOn) 
		//	conductCurrentPlayerTurnAgain(moveOpResult, piece);
		return true;
	}

	protected boolean conductCurrentPlayerTurnAgain(MoveOpResult moveOpResult, AbstractPiece piece) {
		AbstractPiece temp  = piece;
		while (moveOpResult.isCurrentPlayerTurnAgain()) {
			List<ICoordinate> secondJumpList = board.getCBO().findAllowedContinousJumpList(piece);
			board.getCBO().printCoordinateList(secondJumpList, "Second Jump List");
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
				&& isSatisfied(new RuleMoveMustMatchPieceMoveConstraints(), this)
				&& isSatisfied(new RulePawnOfChessCapturePieceInCrossMove(), this)
				&& isSatisfied(new RulePawnOfChessNoCapturePieceInStraightMove(), this)
				&& isSatisfied(new RuleIfPieceToBeCapturedThenItMustBeBelongsToOpponent(), this)
				&& isSatisfied(new RuleThereMustBeNoPieceOnPath(), this)
				&& isSatisfied(new RuleIsKingFree(), this);
	}

	protected MoveOpResult moveInterimOperation(AbstractPiece piece, IMoveCoordinate moveCoordinate, List<ICoordinate> path) {
		IPlayer player = piece.getPlayer();
		ICoordinate pathCoordinate = path.get(path.size()-1);
		AbstractPiece pieceAtPath = coordinatePieceMap.getPieceAtCoordinate(pathCoordinate);		
		if (pieceAtPath!=null && !pieceAtPath.getPlayer().equals(player)) {
			// capture piece at path
			coordinatePieceMap.capturePieceAtCoordinate(pieceAtPath, pathCoordinate);
			pieceAtPath.getPlayer().removePiece(pieceAtPath);
			return new MoveOpResult(true, true); // jumped over opponent team
		}
		return new MoveOpResult(true, false);
	}

	protected AbstractPiece becomeAndOrPutOperation(AbstractPiece piece, ICoordinate destinationCoordinate) {
		if(piece.getClass() == Pawn.class) {
			IPlayer player = piece.getPlayer();
			piece = becomeNewPiece(player, piece);				
		}else if(piece instanceof Pawn) {
			if ((piece.getGoalDirection() == Direction.N && destinationCoordinate.getYCoordinate() == 7)
					|| (piece.getGoalDirection() == Direction.S && destinationCoordinate.getYCoordinate() == 0)){
				IPlayer player = piece.getPlayer();
				piece = becomeNewPiece(player, piece);
			}			
		}

		coordinatePieceMap.putPieceToCoordinate(piece, destinationCoordinate);
		return piece;
	}

	protected AbstractPiece becomeNewPiece(IPlayer player, AbstractPiece piece) {
		Direction goalDirection = piece.getGoalDirection();		
		IPieceMovePossibilities pieceMovePossibilities;
		IPieceMoveConstraints pieceMoveConstraints;
		AbstractPiece newPiece = null;		
		
		if(piece.getClass() == Pawn.class) {
			pieceMoveConstraints = new LimitedPawnMoveConstraints();
			pieceMovePossibilities = new LimitedPawnMovePossibilities();
			newPiece = new LimitedPawn(piece.getId(), piece.getIcon(), player, goalDirection, pieceMovePossibilities, pieceMoveConstraints);
		}else {
			pieceMovePossibilities = new QueenMovePossibilities();
			pieceMoveConstraints =  new QueenMoveConstraints();
			String icon = "Q"+player.getId();
			newPiece = new Queen((player.getId()*7)+player.getId()+6, icon, player, goalDirection, pieceMovePossibilities , pieceMoveConstraints);			
		}
			
		player.removePiece(piece);
		player.addPiece(newPiece);
		
		return newPiece;
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
