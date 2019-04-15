package checkersturkish;

import core.Direction;
import core.IPieceMoveConstraints;

public class TurkishKingMoveConstraints implements IPieceMoveConstraints {

	@Override
	public boolean isSingleSquareMarchMoveAllowed(Direction direction) {
		switch(direction) {
		case NE: return false;
		case SE: return false;
		case NW: return false;
		case SW: return false;
		case N: return true;
		case S: return true;
		case E: return true;
		case W: return true;
		default: break;
		}
		return false;
	}

	@Override
	public boolean isMultipleSquareMarchMoveAllowed(Direction direction) {
		switch(direction) {
		case NE: return false;
		case SE: return false;
		case NW: return false;
		case SW: return false;
		case N: return true;
		case S: return true;
		case E: return true;
		case W: return true;
		default: break;
		}
		return false;
	}

	@Override
	public boolean isSingleSquareJumpMoveAllowed(Direction direction) {
		switch(direction) {
		case NE: return false;
		case SE: return false;
		case NW: return false;
		case SW: return false;
		case N: return true;
		case S: return true;
		case E: return true;
		case W: return true;
		default: break;
		}
		return false;
	}

	@Override
	public boolean isMultipleSquareJumpMoveAllowed(Direction direction) {
		switch(direction) {
		case NE: return false;
		case SE: return false;
		case NW: return false;
		case SW: return false;
		case N: return true;
		case S: return true;
		case E: return true;
		case W: return true;
		default: break;
		}
		return false;
	}

}
