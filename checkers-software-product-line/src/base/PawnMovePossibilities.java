package base;

import java.util.ArrayList;
import java.util.List;

import core.*;

public class PawnMovePossibilities implements IPieceMovePossibilities {

	public List<ICoordinate> getPossibleRelativeDestinationList(ICoordinate sourceCoordinate, Direction direction) {
		List<ICoordinate> possibleDestinationList = new ArrayList<>();

		// single step possible moves
		if (direction == Direction.N ) {
			possibleDestinationList.add(new Coordinate(1,1));
			possibleDestinationList.add(new Coordinate(-1,1));			
		}
		else {
			possibleDestinationList.add(new Coordinate(-1,-1));
			possibleDestinationList.add(new Coordinate(1,-1));			
		}

		// jump over possible moves
		if (direction == Direction.N ) {
			possibleDestinationList.add(new Coordinate(2,2));
			possibleDestinationList.add(new Coordinate(-2,2));
		}
		else {		
			possibleDestinationList.add(new Coordinate(-2,-2));
			possibleDestinationList.add(new Coordinate(2,-2));
		}
		return possibleDestinationList;
	}

}
