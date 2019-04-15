package checkersamerican;


import base.StartCoordinates;


import core.*;

public class AmericanStartCoordinates extends StartCoordinates {
	
	public AmericanStartCoordinates() {
		super();
		super.numberOfPieces = 12;
	}

	public void init() {
		startCoordinates.add(new Coordinate(0,0));
		startCoordinates.add(new Coordinate(2,0));
		startCoordinates.add(new Coordinate(4,0));
		startCoordinates.add(new Coordinate(6,0));
		startCoordinates.add(new Coordinate(1,1));
		startCoordinates.add(new Coordinate(3,1));
		startCoordinates.add(new Coordinate(5,1));
		startCoordinates.add(new Coordinate(7,1));
		startCoordinates.add(new Coordinate(0,2));
		startCoordinates.add(new Coordinate(2,2));
		startCoordinates.add(new Coordinate(4,2));
		startCoordinates.add(new Coordinate(6,2));
	
		startCoordinates.add(new Coordinate(1,5));
		startCoordinates.add(new Coordinate(3,5));
		startCoordinates.add(new Coordinate(5,5));
		startCoordinates.add(new Coordinate(7,5));
		startCoordinates.add(new Coordinate(0,6));
		startCoordinates.add(new Coordinate(2,6));
		startCoordinates.add(new Coordinate(4,6));
		startCoordinates.add(new Coordinate(6,6));
		startCoordinates.add(new Coordinate(1,7));
		startCoordinates.add(new Coordinate(3,7));
		startCoordinates.add(new Coordinate(5,7));
		startCoordinates.add(new Coordinate(7,7));
	}
}
