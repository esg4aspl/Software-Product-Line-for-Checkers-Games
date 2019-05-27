package chess;

import java.util.ArrayList;
import java.util.List;

import core.*;

public class KingMovePossibilities implements IPieceMovePossibilities {

	public List<ICoordinate> getPossibleRelativeDestinationList(ICoordinate sourceCoordinate, Direction direction) {
		List<ICoordinate> possibleDestinationList = new ArrayList<>();
		
		possibleDestinationList.add(new Coordinate(1,1));
		possibleDestinationList.add(new Coordinate(-1,1));			
	
		possibleDestinationList.add(new Coordinate(-1,-1));
		possibleDestinationList.add(new Coordinate(1,-1));
		
		possibleDestinationList.add(new Coordinate(1,0));
		possibleDestinationList.add(new Coordinate(0,1));
		
		possibleDestinationList.add(new Coordinate(0,-1));
		possibleDestinationList.add(new Coordinate(-1,0));

		return possibleDestinationList;
	}

}

