package core;

import java.awt.Color;
import java.util.List;

public interface IPlayer {

	public int getId();
	public Color getColor();
	public void addPiece(AbstractPiece piece);
	public void removePiece(AbstractPiece piece);
	public int getNumberOfPieces();
	public List<AbstractPiece> getPieceList();
}
