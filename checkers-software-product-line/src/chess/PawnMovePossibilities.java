package chess;

import java.util.ArrayList;
import java.util.List;

import core.*;

public class PawnMovePossibilities implements IPieceMovePossibilities {

	public List<ICoordinate> getPossibleRelativeDestinationList(ICoordinate sourceCoordinate, Direction direction) {
		List<ICoordinate> possibleDestinationList = new ArrayList<>();
		
// single step possible moves
		if (direction == Direction.N ) {
			possibleDestinationList.add(new Coordinate(0,1));			
		}
		else {
			possibleDestinationList.add(new Coordinate(1,0));
		}

		return possibleDestinationList;
	}

}

