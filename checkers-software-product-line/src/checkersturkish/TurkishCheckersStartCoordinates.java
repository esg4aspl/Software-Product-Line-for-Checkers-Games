package checkersturkish;

import base.StartCoordinates;
import core.Coordinate;

public class TurkishCheckersStartCoordinates extends StartCoordinates{
	
	
	public TurkishCheckersStartCoordinates() {
		init();
		super.numberOfPieces = 16;
	}

	public void init() {
		startCoordinates.add(new Coordinate(0,1));
		startCoordinates.add(new Coordinate(1,1));
		startCoordinates.add(new Coordinate(2,1));
		startCoordinates.add(new Coordinate(3,1));
		startCoordinates.add(new Coordinate(4,1));
		startCoordinates.add(new Coordinate(5,1));
		startCoordinates.add(new Coordinate(6,1));
		startCoordinates.add(new Coordinate(7,1));
		startCoordinates.add(new Coordinate(0,2));
		startCoordinates.add(new Coordinate(1,2));
		startCoordinates.add(new Coordinate(2,2));
		startCoordinates.add(new Coordinate(3,2));
		startCoordinates.add(new Coordinate(4,2));
		startCoordinates.add(new Coordinate(5,2));
		startCoordinates.add(new Coordinate(6,2));
		startCoordinates.add(new Coordinate(7,2));
	
		startCoordinates.add(new Coordinate(0,6));
		startCoordinates.add(new Coordinate(1,6));
		startCoordinates.add(new Coordinate(2,6));
		startCoordinates.add(new Coordinate(3,6));
		startCoordinates.add(new Coordinate(4,6));
		startCoordinates.add(new Coordinate(5,6));
		startCoordinates.add(new Coordinate(6,6));
		startCoordinates.add(new Coordinate(7,6));
		startCoordinates.add(new Coordinate(0,5));
		startCoordinates.add(new Coordinate(1,5));
		startCoordinates.add(new Coordinate(2,5));
		startCoordinates.add(new Coordinate(3,5));
		startCoordinates.add(new Coordinate(4,5));
		startCoordinates.add(new Coordinate(5,5));
		startCoordinates.add(new Coordinate(6,5));
		startCoordinates.add(new Coordinate(7,5));
	}
}	
