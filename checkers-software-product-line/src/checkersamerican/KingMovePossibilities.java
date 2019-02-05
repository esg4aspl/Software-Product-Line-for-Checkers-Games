package checkersamerican;

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

		// jump over possible moves
		
		possibleDestinationList.add(new Coordinate(2,2));
		possibleDestinationList.add(new Coordinate(-2,2));
	
		possibleDestinationList.add(new Coordinate(-2,-2));
		possibleDestinationList.add(new Coordinate(2,-2));
	
/*		// jump over possible moves
		possibleDestinationList.add(new Coordinate(2,2));
		possibleDestinationList.add(new Coordinate(-2,2));
		possibleDestinationList.add(new Coordinate(-2,-2));
		possibleDestinationList.add(new Coordinate(2,-2));
*/
		return possibleDestinationList;
	}

}

