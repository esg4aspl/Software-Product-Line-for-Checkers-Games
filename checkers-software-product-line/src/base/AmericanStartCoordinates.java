package base;

import java.util.List;
import java.util.ArrayList;

import core.*;

public class AmericanStartCoordinates {

	int currentIndex1, currentIndex2;
	protected List<ICoordinate> startCoordinatesPlayer1;
	protected List<ICoordinate> startCoordinatesPlayer2;
	
	public AmericanStartCoordinates() {
		startCoordinatesPlayer1 = new ArrayList<ICoordinate>();
		startCoordinatesPlayer2 = new ArrayList<ICoordinate>();
		currentIndex1 = 0; 
		currentIndex2 = 0;
		init();
	}

	private void init() {
		startCoordinatesPlayer1.add(new Coordinate(0,0));
		startCoordinatesPlayer1.add(new Coordinate(2,0));
		startCoordinatesPlayer1.add(new Coordinate(4,0));
		startCoordinatesPlayer1.add(new Coordinate(6,0));
		startCoordinatesPlayer1.add(new Coordinate(1,1));
		startCoordinatesPlayer1.add(new Coordinate(3,1));
		startCoordinatesPlayer1.add(new Coordinate(5,1));
		startCoordinatesPlayer1.add(new Coordinate(7,1));
		startCoordinatesPlayer1.add(new Coordinate(0,2));
		startCoordinatesPlayer1.add(new Coordinate(2,2));
		startCoordinatesPlayer1.add(new Coordinate(4,2));
		startCoordinatesPlayer1.add(new Coordinate(6,2));
	
		startCoordinatesPlayer2.add(new Coordinate(1,5));
		startCoordinatesPlayer2.add(new Coordinate(3,5));
		startCoordinatesPlayer2.add(new Coordinate(5,5));
		startCoordinatesPlayer2.add(new Coordinate(7,5));
		startCoordinatesPlayer2.add(new Coordinate(0,6));
		startCoordinatesPlayer2.add(new Coordinate(2,6));
		startCoordinatesPlayer2.add(new Coordinate(4,6));
		startCoordinatesPlayer2.add(new Coordinate(6,6));
		startCoordinatesPlayer2.add(new Coordinate(1,7));
		startCoordinatesPlayer2.add(new Coordinate(3,7));
		startCoordinatesPlayer2.add(new Coordinate(5,7));
		startCoordinatesPlayer2.add(new Coordinate(7,7));
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
