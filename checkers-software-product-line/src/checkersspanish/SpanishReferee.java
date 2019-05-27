package checkersspanish;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import base.AmericanCheckersBoardConsoleView;
import base.GUI;
import base.Pawn;
import base.PawnMoveConstraints;
import base.PawnMovePossibilities;
import base.Player;
import base.PlayerList;
import core.IGameConfiguration;
import core.AbstractPiece;
import core.AbstractReferee;
import core.Direction;
import core.ICoordinate;
import core.IMoveCoordinate;
import core.IPieceMoveConstraints;
import core.IPieceMovePossibilities;
import core.IPlayer;
import core.IPlayerList;
import core.IRule;
import core.MoveCoordinate;
import core.MoveOpResult;
import rules.RuleDestinationCoordinateMustBeValidForCurrentPiece;
import rules.RuleDrawIfNoPromoteForFortyTurn;
import rules.RuleEndOfGameGeneral;
import rules.RuleEndOfGameWhenOpponentBlocked;
import rules.RuleIfAnyPieceCanBeCapturedThenMoveMustBeThat;
import rules.RuleIfJumpMoveThenJumpedPieceMustBeOpponentPiece;
import rules.RuleMoveMustMatchPieceMoveConstraints;
import rules.RulePieceAtSourceCoordinateMustBelongToCurrentPlayer;
import rules.RuleThereMustBePieceAtSourceCoordinate;
import rules.RuleThereMustNotBePieceAtDestinationCoordinate;

public class SpanishReferee extends AbstractReferee{
	protected AmericanCheckersBoardConsoleView consoleView;
	public SpanishReferee(IGameConfiguration gameConfiguration) {
		super(gameConfiguration);
		view = new GUI("Spanish Checkers", this);
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
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
		board = new SpanishCheckersBoard();
		coordinatePieceMap = board.getCoordinatePieceMap();
		consoleView = new AmericanCheckersBoardConsoleView(this);
		
	}

	private void setupPiecesOnBoard() {
		// create pieces for players and put them on board
		IPlayer player;
		AbstractPiece men;
		SpanishStartCoordinates startCoordinates = new SpanishStartCoordinates();
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
				men = new Pawn(1000+i, icon, player, direction, menMovePossibilities, menMoveConstraints);
				player.addPiece(men);
				coordinatePieceMap.putPieceToCoordinate(men, startCoordinates.getNextCoordinate());
			}
		}
		 //coordinatePieceMap.printPieceMap();
		view.printMessage(playerList.getPlayerStatus());
	}
	
	public void conductGame() {		
		boolean endOfGame = false;
		boolean endOfGameDraw = false;
		IRule noPromoteRule = new RuleDrawIfNoPromoteForFortyTurn();
		if (automaticGameOn) {
			conductAutomaticGame();
			endOfGame = (isSatisfied(new RuleEndOfGameGeneral(), this) || isSatisfied(new RuleEndOfGameWhenOpponentBlocked(), this));
			endOfGameDraw = (isSatisfied(noPromoteRule, this));
			view.printMessage("End Of Game? " + endOfGame);
		}
		if(!endOfGame) {
			view.printMessage("Game begins ...");
			consoleView.drawBoardView();
		}
		while (!endOfGame) {
			currentMoveCoordinate = view.getNextMove(currentPlayer);
			while (!conductMove()) {
				currentMoveCoordinate = view.getNextMove(currentPlayer);				
			}
			consoleView.drawBoardView();

			endOfGame = (isSatisfied(new RuleEndOfGameGeneral(), this) || isSatisfied(new RuleEndOfGameWhenOpponentBlocked(), this));
			endOfGameDraw = (isSatisfied(noPromoteRule, this) );
			
			view.printMessage("End Of Game? " + endOfGame);
			if (endOfGame || endOfGameDraw) break;
			
			currentPlayerID++;
			if (currentPlayerID >= numberOfPlayers) currentPlayerID = 0;
			currentPlayer = getPlayerbyID(currentPlayerID);
		} 
		consoleView.drawBoardView();
		
		if(endOfGameDraw)
			view.printMessage("DRAW\n" + announceDraw());
		else
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
	
	protected boolean checkMove() {
		return isSatisfied(new RuleIfAnyPieceCanBeCapturedThenMoveMustBeThat(), this)
				&& isSatisfied(new RuleThereMustBePieceAtSourceCoordinate(), this)
				&& isSatisfied(new RuleThereMustNotBePieceAtDestinationCoordinate(), this)
				&& isSatisfied(new RulePieceAtSourceCoordinateMustBelongToCurrentPlayer(), this)
				&& isSatisfied(new RuleDestinationCoordinateMustBeValidForCurrentPiece(), this)
				&& isSatisfied(new RuleMoveMustMatchPieceMoveConstraints(), this)
				&& isSatisfied(new RuleIfJumpMoveThenJumpedPieceMustBeOpponentPiece(), this);
	}
	
	private List<ICoordinate> piecesOnPath(List<ICoordinate> path){
		List<ICoordinate> coordinatesThatHasAnyPiece = new ArrayList<ICoordinate>();
		for (ICoordinate c: path) {
			if(coordinatePieceMap.getPieceAtCoordinate(c)!=null)
				coordinatesThatHasAnyPiece.add(c);
		}
		return coordinatesThatHasAnyPiece;
	}
	
	protected boolean conductMove() {
		ICoordinate sourceCoordinate = currentMoveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = currentMoveCoordinate.getDestinationCoordinate();
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(sourceCoordinate);
		if (!checkMove()) return false;
		List<ICoordinate> path = board.getCBO().findPath(piece, currentMoveCoordinate);

		coordinatePieceMap.removePieceFromCoordinate(piece, sourceCoordinate);
		MoveOpResult moveOpResult = moveInterimOperation(piece, currentMoveCoordinate, path);
		piece = becomeAndOrPutOperation(piece, destinationCoordinate);
		view.printMessage("CurrentPlayerTurnAgain? " + moveOpResult.isCurrentPlayerTurnAgain());
		if (moveOpResult.isCurrentPlayerTurnAgain() && !automaticGameOn) 
			conductCurrentPlayerTurnAgain(moveOpResult, piece);
		return true;
	}
	
	protected MoveOpResult moveInterimOperation(AbstractPiece piece, IMoveCoordinate moveCoordinate, List<ICoordinate> path) {
		IPlayer player = piece.getPlayer();
		if (board.getMBO().isJumpMove(piece, moveCoordinate) && piecesOnPath(path).size()==1) {
			ICoordinate pathCoordinate = piecesOnPath(path).get(0);
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
	
	protected AbstractPiece becomeNewPiece(IPlayer player, AbstractPiece piece) {
		int pieceID = piece.getId();
		Direction goalDirection = piece.getGoalDirection();
		player.removePiece(piece);
		IPieceMovePossibilities queenMovePossibilities = new QueenMovePossibilities();
		IPieceMoveConstraints queenMoveConstraints =  new QueenMoveConstraints();
		String icon;
		if (player.getId() == 0) icon = "A";
		else icon = "Z";
		AbstractPiece king = new Queen(pieceID+2, icon, player, goalDirection, queenMovePossibilities, queenMoveConstraints);
		player.addPiece(king);
		return king;
	}
	
	
	protected boolean conductCurrentPlayerTurnAgain(MoveOpResult moveOpResult, AbstractPiece piece) {
		AbstractPiece temp  = piece;
		boolean flag = false;		
		IMoveCoordinate rollbackTemp;
		while (moveOpResult.isCurrentPlayerTurnAgain()) {
			List<ICoordinate> jumpList = board.getCBO().findAllowedContinousJumpList(piece);
			List<ICoordinate> secondJumpList = new ArrayList<ICoordinate>();
			rollbackTemp = currentMoveCoordinate;
			Direction lastMoveDirection = board.getCBO().findDirection(currentMoveCoordinate.getSourceCoordinate(),currentMoveCoordinate.getDestinationCoordinate());
			
			for(ICoordinate destinationCoordinate : jumpList) {
				IMoveCoordinate moveCoordinate = new MoveCoordinate(piece.getCurrentCoordinate(), destinationCoordinate);
				List<ICoordinate> path = board.getCBO().findPath(piece, moveCoordinate);
				Direction newDirection = board.getCBO().findDirection(currentMoveCoordinate.getDestinationCoordinate(), destinationCoordinate);
				if(!isCurrentPlayersPieceOnPath(currentPlayer, path) && !lastMoveDirection.getOppositeDirection().equals(newDirection))
					secondJumpList.add(destinationCoordinate);	
			}
				
			if (secondJumpList.size() == 0) {
				moveOpResult = new MoveOpResult(false, false);
				break;
			}
			board.getCBO().printCoordinateList(secondJumpList, "Second Jump List");
			currentMoveCoordinate = view.getNextMove(currentPlayer, currentMoveCoordinate.getDestinationCoordinate());
			ICoordinate sourceCoordinate = currentMoveCoordinate.getSourceCoordinate();
			ICoordinate destinationCoordinate = currentMoveCoordinate.getDestinationCoordinate();
			
			
			for(ICoordinate coord : secondJumpList) {
				if(coord.equals(destinationCoordinate))
					flag=true;
			}
			if(!flag) {
				currentMoveCoordinate = rollbackTemp;
				continue;
			}
			
			if (!checkMove()) { 
				currentMoveCoordinate = rollbackTemp;
				continue;
			}
			
			
			moveOpResult = new MoveOpResult(false, false);
			for(ICoordinate coordinate : secondJumpList) {
				if (coordinate.equals(destinationCoordinate)) {
					List<ICoordinate> path = board.getCBO().findPath(piece, currentMoveCoordinate);
					coordinatePieceMap.removePieceFromCoordinate(piece, sourceCoordinate);
					moveOpResult = moveInterimOperation(piece, currentMoveCoordinate, path);
					piece = becomeAndOrPutOperation(piece, destinationCoordinate);
					if(!temp.equals(piece)) {
						moveOpResult = new MoveOpResult(true, false);
					}
						
				}
			}
		}
		return true;
	}
	
	private boolean isCurrentPlayersPieceOnPath(IPlayer player,List<ICoordinate> path) {
		path.remove(0);
		path.remove(path.size()-1);
		for(ICoordinate coordinate:path) {
			AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(coordinate);
			if(piece!=null && piece.getPlayer().equals(player))
				return true;
		}
		return false;
	}
	
	public IPlayer announceWinner() {
		return playerList.getPlayer(currentPlayerID);
	}
	
	protected AbstractPiece becomeAndOrPutOperation(AbstractPiece piece, ICoordinate destinationCoordinate) {
		//check the piece is already queen or not
		if(!(piece instanceof Queen))
			if ((piece.getGoalDirection() == Direction.N && destinationCoordinate.getYCoordinate() == 7)
					|| (piece.getGoalDirection() == Direction.S && destinationCoordinate.getYCoordinate() == 0)){
				IPlayer player = piece.getPlayer();
				piece = becomeNewPiece(player, piece);
			}
		coordinatePieceMap.putPieceToCoordinate(piece, destinationCoordinate);
		return piece;
	}
	
	public IPlayerList announceDraw(){
		return playerList;
	}
}
