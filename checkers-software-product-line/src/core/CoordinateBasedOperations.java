package core;

import java.util.ArrayList;
import java.util.List;

public class CoordinateBasedOperations {
	
	protected AbstractBoard board;
	
	public CoordinateBasedOperations(AbstractBoard board) {
		this.board = board;
	}

	public List<ICoordinate> findPath(AbstractPiece piece, IMoveCoordinate moveCoordinate) {
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = moveCoordinate.getDestinationCoordinate();
		IPieceMovePossibilities pieceMovePossibilities = piece.getPieceMovePossibilities();
		Direction direction = findDirection(sourceCoordinate, destinationCoordinate);
		List<ICoordinate> possibleRelativeDestinationList = pieceMovePossibilities.getPossibleRelativeDestinationList(sourceCoordinate, piece.getGoalDirection());
		List<ICoordinate> allowedCorrectedDestinationList = findAllowedCorrectedDestinationList(sourceCoordinate, possibleRelativeDestinationList);
		//printCoordinateList(allowedCorrectedDestinationList, "Allowed Corrected Destination List");
		List<ICoordinate> path = new ArrayList<>();
		path.add(sourceCoordinate);
		while (!(sourceCoordinate.equals(destinationCoordinate))) {
			for(ICoordinate coordinate: allowedCorrectedDestinationList) {
				if (sourceCoordinate.getXCoordinate()+direction.getXDelta()==coordinate.getXCoordinate() && 
						sourceCoordinate.getYCoordinate()+direction.getYDelta()==coordinate.getYCoordinate()) {
					path.add(coordinate);
					//System.out.println("Adding to Path " + direction);
					//printPathList(path, direction + " Path");
					sourceCoordinate = coordinate;
					break;
				}
			}
		}
		//printPathList(path, direction + " Path");
		return path;
	}

	protected double findCoordinateDistance(ICoordinate sourceCoordinate, ICoordinate destinationCoordinate) {
		int sourceX = sourceCoordinate.getXCoordinate();
		int sourceY = sourceCoordinate.getYCoordinate();
		int destinationX = destinationCoordinate.getXCoordinate();
		int destinationY = destinationCoordinate.getYCoordinate();
		if (sourceX == destinationX) return (double) Math.abs(sourceY-destinationY);
		else if (sourceY == destinationY) return (double) Math.abs(sourceX-destinationX);
		else return Math.sqrt((Math.abs(sourceX-destinationX) + Math.abs(sourceY-destinationY)));
	}

	protected Direction findDirection(IMoveCoordinate moveCoordinate) {
		ICoordinate sourceCoordinate = moveCoordinate.getSourceCoordinate();
		ICoordinate destinationCoordinate = moveCoordinate.getDestinationCoordinate();
		return findDirection(sourceCoordinate, destinationCoordinate);
	}

	public Direction findDirection(ICoordinate sourceCoordinate, ICoordinate destinationCoordinate) {
		int sourceX = sourceCoordinate.getXCoordinate();
		int sourceY = sourceCoordinate.getYCoordinate();
		int destinationX = destinationCoordinate.getXCoordinate();
		int destinationY = destinationCoordinate.getYCoordinate();
		if (sourceX == destinationX && sourceY < destinationY) return Direction.N;
		if (sourceX == destinationX && sourceY > destinationY) return Direction.S;
		if (sourceY == destinationY && sourceX < destinationX) return Direction.E;
		if (sourceY == destinationY && sourceX > destinationX) return Direction.W;
		if (sourceX < destinationX && sourceY < destinationY) return Direction.NE;
		if (sourceX < destinationX && sourceY > destinationY) return Direction.SE;
		if (sourceX > destinationX && sourceY < destinationY) return Direction.NW;
		if (sourceX > destinationX && sourceY > destinationY) return Direction.SW;
		return null;
	}

	protected Direction correctPlayerOrientation(AbstractPiece piece, Direction direction) {
		if (piece.getGoalDirection() == Direction.S) {
			switch(direction) {
			case NE: return Direction.SW;
			case SE: return Direction.NW;
			case NW: return Direction.SE;
			case SW: return Direction.NE;
			case N: return Direction.S;
			case S: return Direction.N;
			case E: return Direction.W;
			case W: return Direction.E;
			default: return direction;
			}
		}
		else return direction;			
	}
	
	protected List<ICoordinate> correctDestinationList(ICoordinate sourceCoordinate, List<ICoordinate> possibleRelativeDestinationList) {
		List<ICoordinate> possibleCorrectedDestinationList = new ArrayList<>();
		int sourceX = sourceCoordinate.getXCoordinate();
		int sourceY = sourceCoordinate.getYCoordinate();

		for(ICoordinate coordinate : possibleRelativeDestinationList) {
			int x = coordinate.getXCoordinate();
			int y = coordinate.getYCoordinate();
			ICoordinate correctedCoordinate = new Coordinate(sourceX + x, sourceY + y);
			possibleCorrectedDestinationList.add(correctedCoordinate);
		}

		return possibleCorrectedDestinationList;
	}

	public List<ICoordinate> findAllowedCorrectedDestinationList(ICoordinate sourceCoordinate, 
			List<ICoordinate> possibleRelativeDestinationList) {
		List<ICoordinate> possibleCorrectedDestinationList = correctDestinationList(sourceCoordinate, possibleRelativeDestinationList);
		//printPathList(possibleCorrectedDestinationList, "Possible Corrected Destination List");
		List<ICoordinate> allowedCorrectedDestinationList = trimDestinationList(possibleCorrectedDestinationList);
		//printPathList(allowedCorrectedDestinationList, "Allowed Trimmed Destination List");
		return allowedCorrectedDestinationList;
	}

	protected List<ICoordinate> trimDestinationList(List<ICoordinate> possibleCorrectedDestinationList) {
		List<ICoordinate> allowedCorrectedDestinationList = new ArrayList<>();

		for(ICoordinate coordinate : possibleCorrectedDestinationList) {
			if (isCoordinateOnBoard(coordinate)) allowedCorrectedDestinationList.add(new Coordinate(coordinate));
		}

		return allowedCorrectedDestinationList;
	}

	protected boolean isCoordinateOnBoard(ICoordinate coordinate) {
		int x = coordinate.getXCoordinate();
		int y = coordinate.getYCoordinate();
		// first check the bounds 
		if ((x >= 0 && x <= board.maxOfDimensionX) && (y >= 0 && y <= board.maxOfDimensionY))
			// second check if cell is 1
			if (board.boardMatrix[x][y] == 1) return true;
			
			
		return false;
	}

	protected boolean containsDestinationCoordinateInCorrectedDestinationList(ICoordinate destinationCoordinate, List<ICoordinate> possibleCorrectedDestinationList) {
		boolean contains = false;
		for(ICoordinate coordinate : possibleCorrectedDestinationList) {
			if ((coordinate.getXCoordinate() == destinationCoordinate.getXCoordinate()) &&
					(coordinate.getYCoordinate() == destinationCoordinate.getYCoordinate())) {
				contains = true; 
				break;
			}
		}
		return contains;
	}

	public List<ICoordinate> findAllowedContinousJumpList(AbstractPiece piece) {
		ICoordinate sourceCoordinate = piece.getCurrentCoordinate();		
		IPieceMovePossibilities pieceMovePossibilities = piece.getPieceMovePossibilities();
		List<ICoordinate> possibleRelativeDestinationList = pieceMovePossibilities.getPossibleRelativeDestinationList(sourceCoordinate, piece.getGoalDirection());
		List<ICoordinate> allowedCorrectedDestinationList = findAllowedCorrectedDestinationList(sourceCoordinate, possibleRelativeDestinationList);
		//System.out.println("SOURCE: "+sourceCoordinate);
		//printCoordinateList(allowedCorrectedDestinationList, "Allowed Corrected Destination List");
		List<ICoordinate> secondJumpList = new ArrayList<>();
		for(ICoordinate destinationCoordinate : allowedCorrectedDestinationList) {
			IMoveCoordinate moveCoordinate = new MoveCoordinate(sourceCoordinate, destinationCoordinate);
	    	if (board.getMBO().isJumpMove(piece, moveCoordinate)
	    			&& !isPieceBlockedForJump(piece, destinationCoordinate)) {
	    		List<ICoordinate> path = board.getCBO().findPath(piece, moveCoordinate);
				//ICoordinate pathCoordinate = path.get(1);
				//System.out.println("Path Coordinate " + pathCoordinate);
				int howManyPiecesAreOnPath = 0;
				for(int i=1; i<path.size()-1;i++) {
					ICoordinate coordinateOnPath = path.get(i);
					AbstractPiece pieceAtCoord = board.getCoordinatePieceMap().getPieceAtCoordinate(coordinateOnPath);
					if(pieceAtCoord!=null) {
						howManyPiecesAreOnPath++;
					}
				}
				if(howManyPiecesAreOnPath==1) {
					ICoordinate destination = path.get(path.size()-1);
					secondJumpList.add(destination);
				}
	    	}
		}
		//printCoordinateList(secondJumpList,"SECOND JUMP LIST");
		return secondJumpList;
	}
	/*
	public List<ICoordinate> findAllowedContiniousJumpListForMultipleSquare(AbstractPiece piece){
		ICoordinate sourceCoordinate = piece.getCurrentCoordinate();
		IPieceMovePossibilities pieceMovePossibilities = piece.getPieceMovePossibilities();
		List<ICoordinate> possibleRelativeDestinationList = pieceMovePossibilities.getPossibleRelativeDestinationList(sourceCoordinate, piece.getGoalDirection());
		List<ICoordinate> allowedCorrectedDestinationList = findAllowedCorrectedDestinationList(sourceCoordinate, possibleRelativeDestinationList);
		List<ICoordinate> secondJumpList = new ArrayList<>();
		for(ICoordinate destinationCoordinate : allowedCorrectedDestinationList) {
			IMoveCoordinate moveCoordinate = new MoveCoordinate(sourceCoordinate,destinationCoordinate);
			if(board.getMBO().isJumpMove(piece, moveCoordinate) && 
					!isPieceBlockedForJump(piece, destinationCoordinate)) {
				List<ICoordinate> path = board.getCBO().findPath(piece, moveCoordinate);
				int howManyPiecesAreOnPath = 0;
				for(int i=1; i<path.size()-1;i++) {
					ICoordinate coordinateOnPath = path.get(i);
					AbstractPiece pieceAtCoord = board.getCoordinatePieceMap().getPieceAtCoordinate(coordinateOnPath);
					if(pieceAtCoord!=null) {
						howManyPiecesAreOnPath++;
					}
				}
				if(howManyPiecesAreOnPath==1) {
					secondJumpList.add(path.get(path.size()-1));
				}
			}
		}
		return secondJumpList;
	}*/

	public boolean isPieceBlockedForJump(AbstractPiece piece, ICoordinate coordinate) {
    	AbstractPiece pieceAtDestination = board.getCoordinatePieceMap().getPieceAtCoordinate(coordinate);
		if (pieceAtDestination != null) return true;
		else return false;
	}

	protected void printCoordinateList(List<ICoordinate> coordinateList, String header) {
		System.out.println(header);
		for(ICoordinate coordinate : coordinateList)
			System.out.println(coordinate);
	}

	public void printPathList(List<ICoordinate> coordinateList, String header) {
		System.out.println(header);
		for(ICoordinate coordinate : coordinateList)
			System.out.println(coordinate);
	}

}
