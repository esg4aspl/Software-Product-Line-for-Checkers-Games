package core;

public class CoordinatePieceMap {

	protected AbstractPiece[][] map;
	int maxOfDimensionX, maxOfDimensionY;

	public CoordinatePieceMap (int sizeX, int sizeY) {
		map = new AbstractPiece[sizeX][sizeY];
		maxOfDimensionX = sizeX - 1;
		maxOfDimensionY = sizeY - 1;
	}

	public void putPieceToCoordinate(AbstractPiece piece, ICoordinate coordinate) {
		map[coordinate.getXCoordinate()][coordinate.getYCoordinate()] = piece;
		piece.setCurrentCoordinate(coordinate);
	}

	public void removePieceFromCoordinate(AbstractPiece piece, ICoordinate coordinate) {
		map[coordinate.getXCoordinate()][coordinate.getYCoordinate()] = null;
		piece.setCurrentCoordinate(null);
	}

	public AbstractPiece getPieceAtCoordinate(ICoordinate coordinate) {
		return map[coordinate.getXCoordinate()][coordinate.getYCoordinate()];
	}

	public void capturePieceAtCoordinate(AbstractPiece piece, ICoordinate coordinate) {
		map[coordinate.getXCoordinate()][coordinate.getYCoordinate()] = null;
		piece.setCurrentCoordinate(null);
		piece.setCurrentZone(Zone.ONSIDE);
	}

	public void printPieceMap() {
		int i = 1;
		for (int y = 0; y <= maxOfDimensionY; y++ )
			for (int x = 0; x <= maxOfDimensionX; x++ ) {
				AbstractPiece piece = map[x][y];
				if (piece != null) {
					System.out.println(i + " " + x + "," + y + " " + " ==> " + piece);
					i++;
				}
			}
	}

}
