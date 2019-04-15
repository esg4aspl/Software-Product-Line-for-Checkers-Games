package core;

public class MoveCoordinate implements IMoveCoordinate {
	
	ICoordinate sourceCoordinate;
	ICoordinate destinationCoordinate;
	
	public MoveCoordinate(ICoordinate source, ICoordinate destination) {
		sourceCoordinate = source;
		destinationCoordinate = destination;
	}

	public ICoordinate getSourceCoordinate() {
		return sourceCoordinate;
	}
	
	public ICoordinate getDestinationCoordinate() {
		return destinationCoordinate;
	}

	@Override
	public String toString() {
		String sc= String.valueOf(sourceCoordinate.getXCoordinate()) + "," +
				String.valueOf(sourceCoordinate.getYCoordinate());
		String dc= String.valueOf(destinationCoordinate.getXCoordinate()) + "," +
				String.valueOf(destinationCoordinate.getYCoordinate());
		return "(" + sc + ")->("+ dc + ")";
	}
	
	/*
	 * This method is used by contains method of List interface 
	 * so it is overridden in order to run properly when contains method is used.
	 * */
	@Override
	public boolean equals(Object obj) {
		//if two moves has same source and destination coordinate then they are equal.
		IMoveCoordinate moveCoordinate = (IMoveCoordinate)obj;
		return moveCoordinate.getDestinationCoordinate().equals(getDestinationCoordinate()) &&
				moveCoordinate.getSourceCoordinate().equals(getSourceCoordinate());
	}
	
	
}
