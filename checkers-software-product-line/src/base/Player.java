package base;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import core.*;

public class Player implements IPlayer {

	protected int id;
	protected Color color;
	protected List<AbstractPiece> pieceList;

	public Player(int id, Color color) {
		this.id = id;
		this.color = color;
		pieceList = new ArrayList<AbstractPiece>();
	}

	public int getId() {
		return id;
	}

	public Color getColor() {
		return color;
	}
	
	public void addPiece(AbstractPiece piece) {
		pieceList.add(piece);
	}
	
	public void removePiece(AbstractPiece piece) {
		pieceList.remove(piece);
	}

	public int getNumberOfPieces() {
		return pieceList.size();
	}
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", color=" + color + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public List<AbstractPiece> getPieceList(){
		return Collections.unmodifiableList(pieceList);
	}

}
