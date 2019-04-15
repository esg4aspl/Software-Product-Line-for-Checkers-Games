package checkerschinese;

import base.StartCoordinates;
import core.Coordinate;

public class ChineseStartCoordinatesFor4Players extends StartCoordinates {
	
	public ChineseStartCoordinatesFor4Players(int numberOfpieces) {
		super();
		super.numberOfPieces = numberOfpieces;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		//first player
		startCoordinates.add(new Coordinate(0,4));
		startCoordinates.add(new Coordinate(2,4));
		startCoordinates.add(new Coordinate(4,4));
		startCoordinates.add(new Coordinate(6,4));
		startCoordinates.add(new Coordinate(1,5));
		startCoordinates.add(new Coordinate(2,6));
		startCoordinates.add(new Coordinate(3,7));
		startCoordinates.add(new Coordinate(3,5));
		startCoordinates.add(new Coordinate(4,6));
		startCoordinates.add(new Coordinate(5,5));
		
		//second player
		startCoordinates.add(new Coordinate(18,4));
		startCoordinates.add(new Coordinate(20,4));
		startCoordinates.add(new Coordinate(22,4));
		startCoordinates.add(new Coordinate(24,4));
		startCoordinates.add(new Coordinate(23,5));
		startCoordinates.add(new Coordinate(22,6));
		startCoordinates.add(new Coordinate(21,7));
		startCoordinates.add(new Coordinate(19,5));
		startCoordinates.add(new Coordinate(21,5));
		startCoordinates.add(new Coordinate(20,6));
		
		//thirdPlayer
		startCoordinates.add(new Coordinate(0,12));
		startCoordinates.add(new Coordinate(1,11));
		startCoordinates.add(new Coordinate(2,12));
		startCoordinates.add(new Coordinate(4,12));
		startCoordinates.add(new Coordinate(6,12));
		startCoordinates.add(new Coordinate(2,10));
		startCoordinates.add(new Coordinate(3,9));
		startCoordinates.add(new Coordinate(3,11));
		startCoordinates.add(new Coordinate(4,10));
		startCoordinates.add(new Coordinate(5,11));
		
		//forth player
		startCoordinates.add(new Coordinate(18,12));
		startCoordinates.add(new Coordinate(20,12));
		startCoordinates.add(new Coordinate(22,12));
		startCoordinates.add(new Coordinate(24,12));
		startCoordinates.add(new Coordinate(19,11));
		startCoordinates.add(new Coordinate(21,11));
		startCoordinates.add(new Coordinate(23,11));
		startCoordinates.add(new Coordinate(20,10));
		startCoordinates.add(new Coordinate(22,10));
		startCoordinates.add(new Coordinate(21,9));
	}

}
