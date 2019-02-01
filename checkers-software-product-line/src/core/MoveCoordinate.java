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

}
