package core;

public interface IPieceMoveConstraints {

	public boolean isSingleSquareMarchMoveAllowed(Direction direction);
	public boolean isMultipleSquareMarchMoveAllowed(Direction direction);

	public boolean isSingleSquareJumpMoveAllowed(Direction direction);
	public boolean isMultipleSquareJumpMoveAllowed(Direction direction);

}
