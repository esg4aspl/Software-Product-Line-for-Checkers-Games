package checkerschinese;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.*;
import base.*;
import rules.*;

public class Referee extends AbstractReferee {
	protected ChineseCheckersBoardConsoleView consoleView;
	private String[] icons; 
	private Direction[] directions;
	IView view;

	public Referee(IGameConfiguration checkersGameConfiguration) {
		super(checkersGameConfiguration);

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
		for(int i=0; i<numberOfPlayers; i++) {
			playerList.addPlayer(new Player(i, getRandomColor()));
		}
		currentPlayerID = 0;
		currentPlayer = playerList.getPlayer(0);
	}
	
	private Color getRandomColor() {
		Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
	}

	private void setupBoardMVC() {
		board = new ChineseCheckersBoard();
		coordinatePieceMap = board.getCoordinatePieceMap();
		consoleView = new ChineseCheckersBoardConsoleView(this);
		view = new ChineseCheckersBoardConsoleView(this);
	}

	private void setupPiecesOnBoard() {
		icons = new String[]{"#","$","%","?","=","@"};
		directions = createDirections(numberOfPlayers);
		// create pieces for players and put them on board
		StartCoordinateFactory startCoordinateFactory = new StartCoordinateFactory();
		IPlayer player;
		AbstractPiece men;
		StartCoordinates startCoordinates = startCoordinateFactory.getStartCoordinates(numberOfPlayers,numberOfPiecesPerPlayer);
		IPieceMovePossibilities menMovePossibilities = new ChinesePawnMovePossibilities();
		IPieceMoveConstraints menMoveConstraints =  new ChinesePawnMoveConstraints();

		for (int i = 0; i < numberOfPlayers; i++) {
			player = playerList.getPlayer(i);
			String icon;
			Direction direction;
			icon = icons[i];
			view.printMessage("Player "+i+" "+icon);
			direction = directions[i];
			for (int j = 0; j < numberOfPiecesPerPlayer; j++) {
				men = new ChinesePawn(j, icon, player, direction, menMovePossibilities, menMoveConstraints);
				player.addPiece(men);
				coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());
			}
		}
		
	    //coordinatePieceMap.printPieceMap();

	}
	
	private Direction[] createDirections(int numberOfPlayers) {
		if(numberOfPlayers==2)
			return new Direction[] {Direction.N, Direction.S};
		else if(numberOfPlayers==3)
			return new Direction[] {Direction.S, Direction.NE, Direction.NW};
		else if(numberOfPlayers==4)
			return new Direction[] {Direction.NE, Direction.NW, Direction.SE, Direction.SW};
		else if(numberOfPlayers==6)
			return new Direction[] {Direction.NE, Direction.N, Direction.NW, Direction.SW, Direction.S, Direction.SE};
		return null;
	}
	
	public void conductGame() {		
		boolean endOfGame = false;
		if (automaticGameOn) {
			conductAutomaticGame();
			endOfGame = (isSatisfied(new RuleEndOfGamePiecesOfPlayerOnFinishCoordinates(), this));
			view.printMessage("End Of Game? " + endOfGame);
		}
		if(!endOfGame) {
			view.printMessage(playerList.getPlayerStatus());
			view.printMessage("Game begins ...");
			consoleView.drawBoardView();
		}
		while (!endOfGame) {
			currentMoveCoordinate = consoleView.getNextMove(currentPlayer);
			while (!conductMove()) {
				currentMoveCoordinate = consoleView.getNextMove(currentPlayer);				
			}
			consoleView.drawBoardView();

			endOfGame = (isSatisfied(endRule, this));
			
			view.printMessage("End Of Game? " + endOfGame);
			if (endOfGame) break;
			
			currentPlayerID++;
			if (currentPlayerID >= numberOfPlayers) currentPlayerID = 0;
			currentPlayer = getPlayerbyID(currentPlayerID);
		} 
		//consoleView.drawBoardView();
		
		view.printMessage("WINNER " + announceWinner());
		
		consoleView.closeFile();
		System.exit(0);
	}

	private void conductAutomaticGame() {				
		view.printMessage("Automatic Game begins ...");
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
		view.printMessage("Automatic Game ends ...");
	}
	
	protected boolean conductMove() {
		ICoordinate sourceCoordinate = currentMoveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = currentMoveCoordinate.getDestinationCoordinate();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		if (!checkMove()) return false;
		view.printMessage(currentMoveCoordinate+" current move corrd");
		List<ICoordinate> path = board.getCBO().findPath(piece, currentMoveCoordinate);
		coordinatePieceMap.removePieceFromCoordinate(piece, sourceCoordinate);
		MoveOpResult moveOpResult = moveInterimOperation(piece, currentMoveCoordinate, path);
		
		piece = becomeAndOrPutOperation(piece, destinationCoordinate);
		view.printMessage("CurrentPlayerTurnAgain? " + moveOpResult.isCurrentPlayerTurnAgain());
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

			board.getCBO().printCoordinateList(secondJumpList, "Second Jump List");
			currentMoveCoordinate = consoleView.getNextMove(currentPlayer, currentMoveCoordinate.getDestinationCoordinate());
			ICoordinate sourceCoordinate = currentMoveCoordinate.getSourceCoordinate();
			ICoordinate destinationCoordinate = currentMoveCoordinate.getDestinationCoordinate();

			// if player select same coordinate, turn will be terminated
			if(sourceCoordinate.equals(destinationCoordinate)) 
				moveOpResult = new MoveOpResult(false, false);
			else{
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
			ICoordinate pathCoordinate = path.get(1);
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
