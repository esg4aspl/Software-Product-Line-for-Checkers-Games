package checkerschinese;


import base.StartCoordinates;

import core.*;

public class ChineseStartCoordinatesFor2Players extends StartCoordinates {

	public ChineseStartCoordinatesFor2Players(int numberOfPieces) {
		super();
		super.numberOfPieces=numberOfPieces;
	}

	public void init() {
		startCoordinates.add(new Coordinate(12,0));
		startCoordinates.add(new Coordinate(11,1));
		startCoordinates.add(new Coordinate(10,2));
		startCoordinates.add(new Coordinate(9,3));
		startCoordinates.add(new Coordinate(13,1));
		startCoordinates.add(new Coordinate(12,2));
		startCoordinates.add(new Coordinate(11,3));
		startCoordinates.add(new Coordinate(14,2));
		startCoordinates.add(new Coordinate(13,3));
		startCoordinates.add(new Coordinate(15,3));

	
		startCoordinates.add(new Coordinate(12,16));
		startCoordinates.add(new Coordinate(11,15));
		startCoordinates.add(new Coordinate(10,14));
		startCoordinates.add(new Coordinate(9,13));
		startCoordinates.add(new Coordinate(13,15));
		startCoordinates.add(new Coordinate(14,14));
		startCoordinates.add(new Coordinate(15,13));
		startCoordinates.add(new Coordinate(12,14));
		startCoordinates.add(new Coordinate(11,13));
		startCoordinates.add(new Coordinate(13,13));

	}
}
