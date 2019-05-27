package chess;

import core.*;

public class QueenMoveConstraints implements IPieceMoveConstraints {

	public boolean isSingleSquareMarchMoveAllowed(Direction direction) {
		switch(direction) {
		case NE: return true;
		case SE: return true;
		case NW: return true;
		case SW: return true;
		case N: return true;
		case S: return true;
		case E: return true;
		case W: return true;
		default: break;
		}
		return false;
	}
	
	public boolean isMultipleSquareMarchMoveAllowed(Direction direction) {
		switch(direction) {
		case NE: return true;
		case SE: return true;
		case NW: return true;
		case SW: return true;
		case N: return true;
		case S: return true;
		case E: return true;
		case W: return true;
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
		case N: return false;
		case S: return false;
		case E: return false;
		case W: return false;
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
