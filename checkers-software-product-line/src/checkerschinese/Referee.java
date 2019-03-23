package checkerschinese;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import core.*;
import base.*;
import rules.*;

public class Referee extends AbstractReferee {

	protected ChineseCheckersBoardConsoleView consoleView;
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
		board = new ChineseCheckersBoard();
		coordinatePieceMap = board.getCoordinatePieceMap();
		consoleView = new ChineseCheckersBoardConsoleView(this);
	}

	private void setupPiecesOnBoard() {
		// create pieces for players and put them on board
		IPlayer player;
		AbstractPiece men;
		ChineseStartCoordinates startCoordinates = new ChineseStartCoordinates();
		IPieceMovePossibilities menMovePossibilities = new ChinesePawnMovePossibilities();
		IPieceMoveConstraints menMoveConstraints =  new ChinesePawnMoveConstraints();

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
				men = new ChinesePawn(j, icon, player, direction, menMovePossibilities, menMoveConstraints);
				player.addPiece(men);
				coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate(i));
			}
		}
		
	    coordinatePieceMap.printPieceMap();
		System.out.println(playerList.getPlayerStatus());
	}
	
	public void conductGame() {		
		boolean endOfGame = false;
		boolean endOfGameDraw = false;
		boolean startWithAutomaticGame = false;
		IRule noPromoteRule = new RuleDrawIfNoPromoteForFortyTurn();
		IRule noPieceCapturedForFortyTurn = new RuleEndOfGameNoPieceCapturedForFortyTurn();

		if (startWithAutomaticGame) {
			conductAutomaticGame();
			endOfGame = (isSatisfied(new RuleEndOfGameGeneral(), this) || isSatisfied(new RuleEndOfGameWhenOpponentBlocked(), this));
			//endOfGameDraw = (isSatisfied(noPromoteRule, this) || isSatisfied(noPieceCapturedForFortyTurn, this));
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
			endOfGameDraw = false;
			
			System.out.println("End Of Game? " + endOfGame);
			if (endOfGame || endOfGameDraw) break;
			
			currentPlayerID++;
			if (currentPlayerID >= numberOfPlayers) currentPlayerID = 0;
			currentPlayer = getPlayerbyID(currentPlayerID);
		} 
		//consoleView.drawBoardView();
		
		if(endOfGameDraw)
			System.out.println("DRAW\n" + announceDraw());
		else
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
		System.out.println(currentMoveCoordinate+" current move corrd");
		List<ICoordinate> path = board.getCBO().findPath(piece, currentMoveCoordinate);
		coordinatePieceMap.removePieceFromCoordinate(piece, sourceCoordinate);
		MoveOpResult moveOpResult = moveInterimOperation(piece, currentMoveCoordinate, path);
		
		piece = becomeAndOrPutOperation(piece, destinationCoordinate);
		System.out.println("CurrentPlayerTurnAgain? " + moveOpResult.isCurrentPlayerTurnAgain());
		if (moveOpResult.isCurrentPlayerTurnAgain() && !automaticGameOn) 
			conductCurrentPlayerTurnAgain(moveOpResult, piece);
		return true;
	}

	protected boolean conductCurrentPlayerTurnAgain(MoveOpResult moveOpResult, AbstractPiece piece) {
		AbstractPiece temp  = piece;
		
		while (moveOpResult.isCurrentPlayerTurnAgain()) {
			List<ICoordinate> secondJumpList = new ArrayList<ICoordinate>();		
			List<ICoordinate> jumpList = board.getCBO().findAllowedContinousJumpList(piece);
			
			Direction lastMoveDirection = board.getCBO().findDirection(currentMoveCoordinate.getSourceCoordinate(),currentMoveCoordinate.getDestinationCoordinate());
			
			for(ICoordinate destinationCoordinate : jumpList) {
				Direction newDirection = board.getCBO().findDirection(currentMoveCoordinate.getDestinationCoordinate(), destinationCoordinate);
				if(!lastMoveDirection.getOppositeDirection().equals(newDirection) )
					secondJumpList.add(destinationCoordinate);	
			}
			
			if (secondJumpList.size() == 0) {
				moveOpResult = new MoveOpResult(false, false);
				break;
			}
			
			board.getCBO().printPathList(secondJumpList, "Second Jump List");
			currentMoveCoordinate = consoleView.getNextMove(currentPlayer, currentMoveCoordinate.getDestinationCoordinate());
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
		return  isSatisfied(new RuleThereMustBePieceAtSourceCoordinate(), this)
				&& isSatisfied(new RuleThereMustNotBePieceAtDestinationCoordinate(), this)
				&& isSatisfied(new RulePieceAtSourceCoordinateMustBelongToCurrentPlayer(), this)
				&& isSatisfied(new RuleDestinationCoordinateMustBeValidForCurrentPiece(), this)
				&& isSatisfied(new RuleMoveMustMatchPieceMoveConstraints(), this)
				&& isSatisfied(new RuleIfJumpMoveThenJumpedPieceMustBe(), this);
	}

	protected MoveOpResult moveInterimOperation(AbstractPiece piece, IMoveCoordinate moveCoordinate, List<ICoordinate> path) {
		//IPlayer player = piece.getPlayer();
		if (board.getMBO().isJumpMove(piece, moveCoordinate)) {
			System.out.println("Jump Move");
			ICoordinate pathCoordinate = path.get(1);
			System.out.println("Path Coordinate " + pathCoordinate);
			AbstractPiece pieceAtPath = coordinatePieceMap.getPieceAtCoordinate(pathCoordinate);
			if (pieceAtPath != null) {
				return new MoveOpResult(true, true); // jumped over piece
			}
		}
		return new MoveOpResult(true, false);
	}

	protected AbstractPiece becomeAndOrPutOperation(AbstractPiece piece, ICoordinate destinationCoordinate) {
		coordinatePieceMap.putPieceToCoordinate(piece, destinationCoordinate);
		return piece;
	}
	
	protected AbstractPiece becomeNewPiece(IPlayer player, AbstractPiece piece) {
		return null;
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
