package core;

import java.util.List;

public abstract class AbstractBoard {
	
	protected int[][] boardMatrix;
	protected int maxOfDimensionX, maxOfDimensionY;
	protected CoordinatePieceMap coordinatePieceMap;
	
	protected CoordinateBasedOperations cbo;
	protected MoveBasedOperations mbo;
	
	public AbstractBoard(int maxX, int maxY) {
		maxOfDimensionX = maxX;
		maxOfDimensionY = maxY;
		coordinatePieceMap = new CoordinatePieceMap(maxX+1, maxY+1);
		cbo = new CoordinateBasedOperations(this);
		mbo = new MoveBasedOperations(this, cbo);
	}
	
	public boolean isMoveMatchPieceMoveConstraints(AbstractPiece piece, IMoveCoordinate moveCoordinate) {
		IPieceMoveConstraints pieceMoveConstraints = piece.getPieceMoveConstraints();

		if (isDestinationCoordinateValid(piece, moveCoordinate)) {
			// is it a march move
			Direction direction = cbo.findDirection(moveCoordinate);
			direction = cbo.correctPlayerOrientation(piece, direction);
			if (pieceMoveConstraints.isSingleSquareMarchMoveAllowed(direction)) 
				return true;
			// is it a single jump move
			else if (pieceMoveConstraints.isSingleSquareJumpMoveAllowed(direction)) 
				return true;
		}
		return false;
	}

	public boolean isDestinationCoordinateValid(AbstractPiece piece, IMoveCoordinate moveCoordinate) {
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = moveCoordinate.getDestinationCoordinate();
		IPieceMovePossibilities pieceMovePossibilities = piece.getPieceMovePossibilities();
		
		if (sourceCoordinate.getXCoordinate()==destinationCoordinate.getXCoordinate() && 
				sourceCoordinate.getYCoordinate()==destinationCoordinate.getYCoordinate()) return false;
		
		List<ICoordinate> possibleRelativeDestinationList = pieceMovePossibilities.getPossibleRelativeDestinationList(sourceCoordinate, piece.getGoalDirection());
		List<ICoordinate> allowedCorrectedDestinationList = cbo.findAllowedCorrectedDestinationList(sourceCoordinate, possibleRelativeDestinationList);
		return cbo.containsDestinationCoordinateInCorrectedDestinationList(destinationCoordinate, allowedCorrectedDestinationList);
	}

	public boolean isCoordinateOnBoard(ICoordinate coordinate) {
		int coordinateX = coordinate.getXCoordinate();
		int coordinateY = coordinate.getYCoordinate();
		if((maxOfDimensionX >= coordinateX) && (maxOfDimensionY >= coordinateY) && (coordinateX >= 0 && coordinateY >= 0))
			return true;
		else
			return false;
	}
	
	public CoordinatePieceMap getCoordinatePieceMap() {
		return coordinatePieceMap;
	}
	
	public CoordinateBasedOperations getCBO() {
		return cbo;
	}

	public MoveBasedOperations getMBO() {
		return mbo;
	}
	
	public boolean isPlayableCoordinate(ICoordinate coordinate) {
		int coordinateX = coordinate.getXCoordinate();
		int coordinateY = coordinate.getYCoordinate();
		if(boardMatrix[coordinateY][coordinateX]==1)
			return true;
		else
			return false;
	}
}
