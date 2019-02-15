package checkersamerican;

import java.awt.Color;
import java.util.List;

import core.*;
import base.*;
import rules.*;

public class Referee extends AbstractReferee {

	protected AmericanCheckersBoardConsoleView consoleView;
	protected boolean automaticGameOn;

	public Referee(AbstractGameConfiguration checkersGameConfiguration) {
		super(checkersGameConfiguration);
		automaticGameOn = false;
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
		IPlayer player0 = new Player(0, Color.BLACK);
		playerList.addPlayer(player0);
		IPlayer player1 = new Player(1, Color.WHITE);
		playerList.addPlayer(player1);
		currentPlayerID = 0;
		currentPlayer = player0;
	}

	private void setupBoardMVC() {
		board = new AmericanCheckersBoard();
		coordinatePieceMap = board.getCoordinatePieceMap();
		consoleView = new AmericanCheckersBoardConsoleView(this);
	}

	private void setupPiecesOnBoard() {
		// create pieces for players and put them on board
		IPlayer player;
		AbstractPiece men;
		AmericanStartCoordinates startCoordinates = new AmericanStartCoordinates();
		IPieceMovePossibilities menMovePossibilities = new PawnMovePossibilities();
		IPieceMoveConstraints menMoveConstraints =  new PawnMoveConstraints();

		for (int i = 0; i < numberOfPlayers; i++) {
			player = playerList.getPlayer(i);
			String icon;
			Direction direction;
			if (i == 0) {
				icon = "B";
				direction = Direction.N;
			}
			else {
				icon = "W";
				direction = Direction.S;
			}
			for (int j = 0; j < numberOfPiecesPerPlayer; j++) {
				men = new Pawn(j, icon, player, direction, menMovePossibilities, menMoveConstraints);
				player.addPiece(men);
				coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate(i));
			}
		}
		
		// coordinatePieceMap.printPieceMap();
		System.out.println(playerList.getPlayerStatus());
	}
	
	public void conductGame() {		
		boolean endOfGame = false;
		boolean endOfGameDraw = false;
		boolean startWithAutomaticGame = false;
		IRule noPromoteRule = new RuleDrawIfNoPromoteForFortyTurn();
		if (startWithAutomaticGame) {
			conductAutomaticGame();
			endOfGame = (isSatisfied(new RuleEndOfGameGeneral(), this) || isSatisfied(new RuleEndOfGameWhenOpponentBlocked(), this));
			//endOfGame = isSatisfied(new RuleEndOfGameGeneral(), this);
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
			endOfGameDraw = isSatisfied(noPromoteRule, this);
			if (endOfGameDraw) {
				System.out.println("Draw "+announceDraw());
				consoleView.closeFile();
				System.exit(0);
			}
			endOfGame = (isSatisfied(new RuleEndOfGameGeneral(), this) || isSatisfied(new RuleEndOfGameWhenOpponentBlocked(), this));
			//endOfGame = isSatisfied(new RuleEndOfGameGeneral(), this);
			System.out.println("End Of Game? " + endOfGame);
			if (endOfGame) break;
			
			currentPlayerID++;
			if (currentPlayerID >= numberOfPlayers) currentPlayerID = 0;
			currentPlayer = getPlayerbyID(currentPlayerID);
		} 
		//consoleView.drawBoardView();
		System.out.println("WINNER "+announceWinner());
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
		List<ICoordinate> path = board.getCBO().findPath(piece, currentMoveCoordinate);
		coordinatePieceMap.removePieceFromCoordinate(piece, sourceCoordinate);
		MoveOpResult moveOpResult = moveInterimOperation(piece, currentMoveCoordinate, path);
		//if piece become king then terminate the move
		AbstractPiece  temp  = piece;
		piece = becomeAndOrPutOperation(piece, destinationCoordinate);
		if(!temp.equals(piece))
			moveOpResult = new MoveOpResult(true, false);
		System.out.println("CurrentPlayerTurnAgain? " + moveOpResult.isCurrentPlayerTurnAgain());
		if (moveOpResult.isCurrentPlayerTurnAgain() && !automaticGameOn) 
			conductCurrentPlayerTurnAgain(moveOpResult, piece);
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
		return isSatisfied(new RuleIfAnyPieceCanBeCapturedThenMoveMustBeThat(), this)
				&& isSatisfied(new RuleThereMustBePieceAtSourceCoordinate(), this)
				&& isSatisfied(new RuleThereMustNotBePieceAtDestinationCoordinate(), this)
				&& isSatisfied(new RulePieceAtSourceCoordinateMustBelongToCurrentPlayer(), this)
				&& isSatisfied(new RuleDestinationCoordinateMustBeValidForCurrentPiece(), this)
				&& isSatisfied(new RuleMoveMustMatchPieceMoveConstraints(), this)
				&& isSatisfied(new RuleIfJumpMoveThenJumpedPieceMustBeOpponentPiece(), this);
	}

	protected MoveOpResult moveInterimOperation(AbstractPiece piece, IMoveCoordinate moveCoordinate, List<ICoordinate> path) {
		IPlayer player = piece.getPlayer();
		if (board.getMBO().isJumpMove(piece, moveCoordinate)) {
			System.out.println("Jump Move");
			ICoordinate pathCoordinate = path.get(1);
			System.out.println("Path Coordinate " + pathCoordinate);
			AbstractPiece pieceAtPath = coordinatePieceMap.getPieceAtCoordinate(pathCoordinate);
			if (!pieceAtPath.getPlayer().equals(player)) {
				// capture piece at path
				coordinatePieceMap.capturePieceAtCoordinate(pieceAtPath, pathCoordinate);
				pieceAtPath.getPlayer().removePiece(pieceAtPath);
				return new MoveOpResult(true, true); // jumped over opponent team
			}
		}
		return new MoveOpResult(true, false);
	}

	protected AbstractPiece becomeAndOrPutOperation(AbstractPiece piece, ICoordinate destinationCoordinate) {
		if ((piece.getGoalDirection() == Direction.N && destinationCoordinate.getYCoordinate() == 7 && piece.getIcon().equals("B"))
				|| (piece.getGoalDirection() == Direction.S && destinationCoordinate.getYCoordinate() == 0 && piece.getIcon().equals("W"))){
			IPlayer player = piece.getPlayer();
			piece = becomeNewPiece(player, piece);
		}
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
	
	public List<IPlayer> announceDraw(){
		return playerList.getPlayers();
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
