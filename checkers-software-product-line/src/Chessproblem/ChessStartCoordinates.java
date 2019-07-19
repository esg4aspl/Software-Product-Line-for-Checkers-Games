package Chessproblem;


import base.StartCoordinates;


import core.*;

public class ChessStartCoordinates extends StartCoordinates {
	
	public ChessStartCoordinates() {
		super();
		super.numberOfPieces = 1;
	}

	public void init() {
		startCoordinates.add(new Coordinate(0,2));
	}
}
