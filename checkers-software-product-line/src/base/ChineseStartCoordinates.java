package base;

import java.util.List;
import java.util.ArrayList;

import core.*;

public class ChineseStartCoordinates {

	int currentIndex1, currentIndex2;
	protected List<ICoordinate> startCoordinatesPlayer1;
	protected List<ICoordinate> startCoordinatesPlayer2;
	
	public ChineseStartCoordinates() {
		startCoordinatesPlayer1 = new ArrayList<ICoordinate>();
		startCoordinatesPlayer2 = new ArrayList<ICoordinate>();
		currentIndex1 = 0; 
		currentIndex2 = 0;
		init();
	}

	private void init() {
		startCoordinatesPlayer1.add(new Coordinate(12,0));
		startCoordinatesPlayer1.add(new Coordinate(11,1));
		startCoordinatesPlayer1.add(new Coordinate(10,2));
		startCoordinatesPlayer1.add(new Coordinate(9,3));
		startCoordinatesPlayer1.add(new Coordinate(13,1));
		startCoordinatesPlayer1.add(new Coordinate(12,2));
		startCoordinatesPlayer1.add(new Coordinate(11,3));
		startCoordinatesPlayer1.add(new Coordinate(14,2));
		startCoordinatesPlayer1.add(new Coordinate(13,3));
		startCoordinatesPlayer1.add(new Coordinate(15,3));

	
		startCoordinatesPlayer2.add(new Coordinate(12,16));
		startCoordinatesPlayer2.add(new Coordinate(11,15));
		startCoordinatesPlayer2.add(new Coordinate(10,14));
		startCoordinatesPlayer2.add(new Coordinate(9,13));
		startCoordinatesPlayer2.add(new Coordinate(13,15));
		startCoordinatesPlayer2.add(new Coordinate(14,14));
		startCoordinatesPlayer2.add(new Coordinate(15,13));
		startCoordinatesPlayer2.add(new Coordinate(12,14));
		startCoordinatesPlayer2.add(new Coordinate(11,13));
		startCoordinatesPlayer2.add(new Coordinate(13,13));

	}

	public ICoordinate getNextCoordinate(int i) {
		if (i == 0) {
			currentIndex1++;
			return startCoordinatesPlayer1.get(currentIndex1-1);
		}
		else {
			currentIndex2++;
			return startCoordinatesPlayer2.get(currentIndex2-1);
		}
	}
}
