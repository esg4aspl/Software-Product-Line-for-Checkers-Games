package chess;

import java.util.ArrayList;
import java.util.List;

import core.*;

public class KnightMovePossibilities implements IPieceMovePossibilities {

	public List<ICoordinate> getPossibleRelativeDestinationList(ICoordinate sourceCoordinate, Direction direction) {
		List<ICoordinate> possibleDestinationList = new ArrayList<>();
		
		possibleDestinationList.add(new Coordinate(-1,2));
		possibleDestinationList.add(new Coordinate(-2,1));			
	
		possibleDestinationList.add(new Coordinate(-2,-1));
		possibleDestinationList.add(new Coordinate(-1,-2));
		
		possibleDestinationList.add(new Coordinate(1,-2));
		possibleDestinationList.add(new Coordinate(2,-1));
		
		possibleDestinationList.add(new Coordinate(2,1));
		possibleDestinationList.add(new Coordinate(1,2));

		return possibleDestinationList;
	}

}

