package core;

public enum Direction {

	// delta between previous and next coordinate
	N (0,1), 
	NE (1,1), 
	E (1,0), 
	SE (1,-1), 
	S (0,-1), 
	SW (-1,-1), 
	W (-1,0), 
	NW (-1,1);

	private final int xDelta;
	private final int yDelta;
	
	Direction(int xDelta, int yDelta) {
		this.xDelta = xDelta;
		this.yDelta = yDelta;
	}
	
	public int getXDelta() {
		return xDelta;
	}
	
	public int getYDelta() {
		return yDelta;
	}
	
	public Direction getOppositeDirection() {
		switch(this) {
		case N:
			return S;
		case E:
			return W;
		case NE:
			return SW;
		case NW:
			return SE;
		case S:
			return N;
		case SE:
			return NW;
		case SW:
			return NE;
		case W:
			return E;
		default:
			return this;
		}
		
	}

}