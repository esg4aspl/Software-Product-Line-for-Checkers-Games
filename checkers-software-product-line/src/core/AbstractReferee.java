package core;


public abstract class AbstractReferee {
	
	protected IGameConfiguration gameConfiguration;
	protected int currentPlayerID, numberOfPlayers, numberOfPiecesPerPlayer;
	protected IPlayerList playerList;
	protected IPlayer currentPlayer;
	protected AbstractMove currentMove;
	protected IMoveCoordinate currentMoveCoordinate;
	protected AbstractBoard board;
	protected CoordinatePieceMap coordinatePieceMap;
	protected IView view;
	protected boolean automaticGameOn;
	public AbstractReferee(IGameConfiguration gameConfiguration) {
		this.gameConfiguration = gameConfiguration;	
		automaticGameOn = gameConfiguration.getAutomaticGameStatus();
	}

	// TODO template pattern
	public abstract void setup();
	public abstract void conductGame();
	public abstract IPlayer announceWinner();
		
	// is Rule satisfied in the Context
	public boolean isSatisfied(IRule rule, AbstractReferee referee) {
    	return rule.evaluate(referee);
    }

	public IPlayerList getPlayers() {
		return playerList;
	}

	public IGameConfiguration getGameConfiguration() {
		return gameConfiguration;
	}

	public int getCurrentPlayerID() {
		return currentPlayerID;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public int getNumberOfPiecesPerPlayer() {
		return numberOfPiecesPerPlayer;
	}

	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}

	public IMoveCoordinate getCurrentMoveCoordinate() {
		return currentMoveCoordinate;
	}

	public AbstractBoard getBoard() {
		return board;
	}

	public AbstractMove getCurrentMove() {
		return currentMove;
	}

	public void setCurrentMove(AbstractMove currentMove) {
		this.currentMove = currentMove;
	}

	public CoordinatePieceMap getCoordinatePieceMap() {
		return coordinatePieceMap;
	}
	
	public IPlayer getPlayerbyID(int id) {
		return playerList.getPlayer(id);
	}
	
	public void printMessage(String message) {
		view.printMessage(message);
	}
}
