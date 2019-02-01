package core;

public class MoveBasedOperations {

	protected AbstractBoard board;
	protected CoordinateBasedOperations cbo;

	public MoveBasedOperations(AbstractBoard board, CoordinateBasedOperations cbo) {
		this.board = board;
		this.cbo = cbo;
	}

	public boolean isJumpMove(AbstractPiece piece, IMoveCoordinate moveCoordinate) {
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = moveCoordinate.getDestinationCoordinate();
		if (cbo.findCoordinateDistance(sourceCoordinate, destinationCoordinate) >= 2.0)
			return true;
		else return false;
	}
}
