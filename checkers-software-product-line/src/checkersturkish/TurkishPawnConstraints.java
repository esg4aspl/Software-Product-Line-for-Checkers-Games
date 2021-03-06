package checkersturkish;

import core.Direction;
import core.IPieceMoveConstraints;

public class TurkishPawnConstraints implements IPieceMoveConstraints {

	public boolean isSingleSquareMarchMoveAllowed(Direction direction) {
		switch(direction) {
		case NE: return false;
		case SE: return false;
		case NW: return false;
		case SW: return false;
		case N: return true;
		case S: return false;
		case E: return true;
		case W: return true;
		default: break;
		}
		return false;
	}
	
	public boolean isMultipleSquareMarchMoveAllowed(Direction direction) {
		switch(direction) {
		case NE: return false;
		case SE: return false;
		case NW: return false;
		case SW: return false;
		case N: return false;
		case S: return false;
		case E: return false;
		case W: return false;
		default: break;
		}
		return false;
	}

	public boolean isSingleSquareJumpMoveAllowed(Direction direction) {
		switch(direction) {
		case NE: return false;
		case SE: return false;
		case NW: return false;
		case SW: return false;
		case N: return true;
		case S: return false;
		case E: return true;
		case W: return true;
		default: break;
		}
		return false;		
	}
	
	public boolean isMultipleSquareJumpMoveAllowed(Direction direction) {
		switch(direction) {
		case NE: return false;
		case SE: return false;
		case NW: return false;
		case SW: return false;
		case N: return false;
		case S: return false;
		case E: return false;
		case W: return false;
		default: break;
		}
		return false;
	}

}
