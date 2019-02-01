package core;

import java.awt.Color;

public interface IPlayer {

	public int getId();
	public Color getColor();
	public void addPiece(AbstractPiece piece);
	public void removePiece(AbstractPiece piece);
	public int getNumberOfPieces();
	
}
