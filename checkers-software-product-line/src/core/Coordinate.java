package core;

public class Coordinate implements ICoordinate {

	private final int xCoordinate;
	private final int yCoordinate;
	
	public Coordinate(int x, int y) {
		xCoordinate = x;
		yCoordinate = y;
	}

	public Coordinate(ICoordinate coordinate) {
		xCoordinate = coordinate.getXCoordinate();
		yCoordinate = coordinate.getYCoordinate();
	}

	public int getXCoordinate() {
		return xCoordinate;
	}
	
	public int getYCoordinate() {
		return yCoordinate;
	}
	
	public boolean equals(ICoordinate otherCoordinate) {
		return (xCoordinate==otherCoordinate.getXCoordinate()) && 
				(yCoordinate==otherCoordinate.getYCoordinate());
	}
	
	@Override
	public String toString() {
		return "Coordinate [x=" + xCoordinate + ", y=" + yCoordinate + "]";
	}

}
