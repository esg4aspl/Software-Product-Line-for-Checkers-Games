package chess;


import base.StartCoordinates;


import core.*;

public class ChessStartCoordinates extends StartCoordinates {
	
	public ChessStartCoordinates() {
		super();
		super.numberOfPieces = 16;
	}

	public void init() {
		startCoordinates.add(new Coordinate(0,0));
		startCoordinates.add(new Coordinate(1,0));
		startCoordinates.add(new Coordinate(2,0));
		startCoordinates.add(new Coordinate(3,0));
		startCoordinates.add(new Coordinate(4,0));
		startCoordinates.add(new Coordinate(5,0));
		startCoordinates.add(new Coordinate(6,0));
		startCoordinates.add(new Coordinate(7,0));
		
		startCoordinates.add(new Coordinate(0,1));
		startCoordinates.add(new Coordinate(1,1));
		startCoordinates.add(new Coordinate(2,1));
		startCoordinates.add(new Coordinate(3,1));
		startCoordinates.add(new Coordinate(4,1));
		startCoordinates.add(new Coordinate(5,1));
		startCoordinates.add(new Coordinate(6,1));
		startCoordinates.add(new Coordinate(7,1));


	
		startCoordinates.add(new Coordinate(0,7));
		startCoordinates.add(new Coordinate(1,7));
		startCoordinates.add(new Coordinate(2,7));
		startCoordinates.add(new Coordinate(3,7));
		startCoordinates.add(new Coordinate(4,7));
		startCoordinates.add(new Coordinate(5,7));
		startCoordinates.add(new Coordinate(6,7));
		startCoordinates.add(new Coordinate(7,7));
		
		startCoordinates.add(new Coordinate(0,6));
		startCoordinates.add(new Coordinate(1,6));
		startCoordinates.add(new Coordinate(2,6));
		startCoordinates.add(new Coordinate(3,6));
		startCoordinates.add(new Coordinate(4,6));
		startCoordinates.add(new Coordinate(5,6));
		startCoordinates.add(new Coordinate(6,6));
		startCoordinates.add(new Coordinate(7,6));
		

	}
}
