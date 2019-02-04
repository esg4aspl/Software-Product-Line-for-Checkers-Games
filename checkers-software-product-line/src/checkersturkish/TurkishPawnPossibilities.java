package checkersturkish;

import java.util.ArrayList;
import java.util.List;

import core.Coordinate;
import core.Direction;
import core.ICoordinate;
import core.IPieceMovePossibilities;

public class TurkishPawnPossibilities implements IPieceMovePossibilities{

	@Override
	public List<ICoordinate> getPossibleRelativeDestinationList(ICoordinate sourceCoordinate, Direction direction) {
		List<ICoordinate> possibleDestinationList = new ArrayList<>();

		// single step possible moves
		if (direction == Direction.N ) {
			possibleDestinationList.add(new Coordinate(0,1));
			possibleDestinationList.add(new Coordinate(1,0));
			possibleDestinationList.add(new Coordinate(-1,0));			
		}
		else {
			possibleDestinationList.add(new Coordinate(0,-1));
			possibleDestinationList.add(new Coordinate(1,0));
			possibleDestinationList.add(new Coordinate(-1,0));
		}

		// jump over possible moves
		if (direction == Direction.N ) {
			possibleDestinationList.add(new Coordinate(0,2));
			possibleDestinationList.add(new Coordinate(2,0));
			possibleDestinationList.add(new Coordinate(-2,0));
		}
		else {		
			possibleDestinationList.add(new Coordinate(0,-2));
			possibleDestinationList.add(new Coordinate(2,0));
			possibleDestinationList.add(new Coordinate(-2,0));
		}
		return possibleDestinationList;
	}


}
