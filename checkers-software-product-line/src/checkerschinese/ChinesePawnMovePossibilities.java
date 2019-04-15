package checkerschinese;

import java.util.ArrayList;
import java.util.List;

import core.*;

public class ChinesePawnMovePossibilities implements IPieceMovePossibilities {

	public List<ICoordinate> getPossibleRelativeDestinationList(ICoordinate sourceCoordinate, Direction direction) {
		List<ICoordinate> possibleDestinationList = new ArrayList<>();

		// single step possible moves
			possibleDestinationList.add(new Coordinate(1,1));
			possibleDestinationList.add(new Coordinate(-1,1));			
			possibleDestinationList.add(new Coordinate(-1,-1));
			possibleDestinationList.add(new Coordinate(1,-1));			
			possibleDestinationList.add(new Coordinate(-2,0));
			possibleDestinationList.add(new Coordinate(2,0));

		// jump over possible moves
			possibleDestinationList.add(new Coordinate(2,2));
			possibleDestinationList.add(new Coordinate(-2,2));	
			possibleDestinationList.add(new Coordinate(-2,-2));
			possibleDestinationList.add(new Coordinate(2,-2));
			possibleDestinationList.add(new Coordinate(4,0));
			possibleDestinationList.add(new Coordinate(-4,0));
		return possibleDestinationList;
	}

}
