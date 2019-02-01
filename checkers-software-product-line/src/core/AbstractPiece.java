package core;

public abstract class AbstractPiece {
	
	protected int id;
	protected String icon;
	protected IPieceMovePossibilities pieceMovePossibilities;
	protected IPieceMoveConstraints pieceMoveConstraints;
	protected IPlayer myPlayer;
	protected Direction goalDirection;
	protected ICoordinate currentCoordinate;
	protected Zone currentZone;
	
	public AbstractPiece(int id, String icon, IPlayer myPlayer, Direction goalDirection,
			IPieceMovePossibilities pieceMovePossibilities, 
			IPieceMoveConstraints pieceMoveConstraints) {
		this.id = id;
		this.icon = icon;
		this.myPlayer = myPlayer;
		this.goalDirection = goalDirection;
		this.pieceMovePossibilities = pieceMovePossibilities;
		this.pieceMoveConstraints = pieceMoveConstraints;
		currentZone = Zone.ONBOARD;
	}

	public int getId() {
		return id;
	}

	public String getIcon() {
		return icon;
	}

	public IPieceMovePossibilities getPieceMovePossibilities() {
		return pieceMovePossibilities;
	}

	public IPieceMoveConstraints getPieceMoveConstraints() {
		return pieceMoveConstraints;
	}

	public IPlayer getPlayer() {
		return myPlayer;
	}

	public Direction getGoalDirection() {
		return goalDirection;
	}

	public ICoordinate getCurrentCoordinate() {
		return currentCoordinate;
	}

	public void setCurrentCoordinate(ICoordinate currentCoordinate) {
		this.currentCoordinate = currentCoordinate;
	}

	public Zone getCurrentZone() {
		return currentZone;
	}

	public void setCurrentZone(Zone currentZone) {
		this.currentZone = currentZone;
	}

	@Override
	public String toString() {
		return "AbstractPiece [id=" + id + ", Player=" + myPlayer + "]";
	}

}
