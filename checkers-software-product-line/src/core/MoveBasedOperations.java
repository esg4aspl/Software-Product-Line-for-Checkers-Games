package core;

import java.util.List;

public class MoveBasedOperations {

	protected AbstractBoard board;
	protected CoordinateBasedOperations cbo;

	public MoveBasedOperations(AbstractBoard board, CoordinateBasedOperations cbo) {
		this.board = board;
		this.cbo = cbo;
	}
	
	public boolean isJumpMove(AbstractPiece piece, IMoveCoordinate moveCoordinate) {
		
		List<ICoordinate> path  = cbo.findPath(piece, moveCoordinate);
		//number of playable coordinate doesn't count destination and source coordinate 
		int numberOfPlayableCoordinate = 0;
		for(int i=1; i<path.size()-1;i++) {
			if(board.isPlayableCoordinate(path.get(i))) {
				numberOfPlayableCoordinate++;
			}
				
		}
		
		if(numberOfPlayableCoordinate>=1)
			return true;
		return false;
		
	}
	
	
}
