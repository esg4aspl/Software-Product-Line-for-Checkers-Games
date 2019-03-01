package checkersspanish;

import java.util.List;
import java.util.ArrayList;

import core.*;

public class SpanishStartCoordinates {

	int currentIndex1, currentIndex2;
	protected List<ICoordinate> startCoordinatesPlayer1;
	protected List<ICoordinate> startCoordinatesPlayer2;
	
	public SpanishStartCoordinates() {
		startCoordinatesPlayer1 = new ArrayList<ICoordinate>();
		startCoordinatesPlayer2 = new ArrayList<ICoordinate>();
		currentIndex1 = 0; 
		currentIndex2 = 0;
		init();
	}

	private void init() {
		startCoordinatesPlayer1.add(new Coordinate(1,0));
		startCoordinatesPlayer1.add(new Coordinate(3,0));
		startCoordinatesPlayer1.add(new Coordinate(5,0));
		startCoordinatesPlayer1.add(new Coordinate(7,0));
		startCoordinatesPlayer1.add(new Coordinate(0,1));
		startCoordinatesPlayer1.add(new Coordinate(2,1));
		startCoordinatesPlayer1.add(new Coordinate(4,1));
		startCoordinatesPlayer1.add(new Coordinate(6,1));
		startCoordinatesPlayer1.add(new Coordinate(1,2));
		startCoordinatesPlayer1.add(new Coordinate(3,2));
		startCoordinatesPlayer1.add(new Coordinate(5,2));
		startCoordinatesPlayer1.add(new Coordinate(7,2));
	
		startCoordinatesPlayer2.add(new Coordinate(0,5));
		startCoordinatesPlayer2.add(new Coordinate(2,5));
		startCoordinatesPlayer2.add(new Coordinate(4,5));
		startCoordinatesPlayer2.add(new Coordinate(6,5));
		startCoordinatesPlayer2.add(new Coordinate(1,6));
		startCoordinatesPlayer2.add(new Coordinate(3,6));
		startCoordinatesPlayer2.add(new Coordinate(5,6));
		startCoordinatesPlayer2.add(new Coordinate(7,6));
		startCoordinatesPlayer2.add(new Coordinate(0,7));
		startCoordinatesPlayer2.add(new Coordinate(2,7));
		startCoordinatesPlayer2.add(new Coordinate(4,7));
		startCoordinatesPlayer2.add(new Coordinate(6,7));
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
