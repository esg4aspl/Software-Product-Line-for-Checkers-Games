package base;

import java.util.ArrayList;
import java.util.List;

import core.ICoordinate;

public abstract class StartCoordinates {
	protected int numberOfPieces;
	protected int currentIndex;
	protected List<ICoordinate> startCoordinates;
	
	public StartCoordinates() {
		startCoordinates = new ArrayList<ICoordinate>();
		currentIndex=0;
		init();
	}
	
	public abstract void init();
	
	public ICoordinate getNextCoordinate() {
		ICoordinate coordinate = startCoordinates.get(currentIndex);
		currentIndex++;
		return coordinate;
	}
}
