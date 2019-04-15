package checkersspanish;

import base.StartCoordinates;


import core.*;

public class SpanishStartCoordinates extends StartCoordinates{
	
	public SpanishStartCoordinates() {
		super();
		super.numberOfPieces = 12;
	}

	public void init() {
		//player 1
		startCoordinates.add(new Coordinate(1,0));
		startCoordinates.add(new Coordinate(3,0));
		startCoordinates.add(new Coordinate(5,0));
		startCoordinates.add(new Coordinate(7,0));
		startCoordinates.add(new Coordinate(0,1));
		startCoordinates.add(new Coordinate(2,1));
		startCoordinates.add(new Coordinate(4,1));
		startCoordinates.add(new Coordinate(6,1));
		startCoordinates.add(new Coordinate(1,2));
		startCoordinates.add(new Coordinate(3,2));
		startCoordinates.add(new Coordinate(5,2));
		startCoordinates.add(new Coordinate(7,2));
		
		//player 2
		startCoordinates.add(new Coordinate(0,5));
		startCoordinates.add(new Coordinate(2,5));
		startCoordinates.add(new Coordinate(4,5));
		startCoordinates.add(new Coordinate(6,5));
		startCoordinates.add(new Coordinate(1,6));
		startCoordinates.add(new Coordinate(3,6));
		startCoordinates.add(new Coordinate(5,6));
		startCoordinates.add(new Coordinate(7,6));
		startCoordinates.add(new Coordinate(0,7));
		startCoordinates.add(new Coordinate(2,7));
		startCoordinates.add(new Coordinate(4,7));
		startCoordinates.add(new Coordinate(6,7));
	}
}
