package checkerschinese;
import base.StartCoordinates;
import core.Coordinate;

public class ChineseStartCoordinatesFor3Players extends StartCoordinates {
	
	public ChineseStartCoordinatesFor3Players(int numberOfPieces) {
		// TODO Auto-generated constructor stub
		super();
		super.numberOfPieces = numberOfPieces;
	}
	
	public void init() {
		//first player
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
		
		//second player
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
	}

}
