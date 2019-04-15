package checkerschinese;

import base.StartCoordinates;
import core.Coordinate;

public class ChineseStartCoordinatesFor6Players extends StartCoordinates {
	
	public ChineseStartCoordinatesFor6Players(int numberOfPieces) {
		// TODO Auto-generated constructor stub
		super();
		super.numberOfPieces = numberOfPieces;
	}
	@Override
	public void init() {
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
		
		//third player
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
		
		
		//fifth player
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
		
	
		//sixth player
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
	}

}
