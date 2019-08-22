package chess;

import core.*;
import graphComponents.MatrixBoard;

//model class
public class ChessBoard extends AbstractBoard {

	public ChessBoard() {
		super(7, 7);
		init();
	}

	private void init() {
		// board lower left has coordinate (0,0)
		/*boardMatrix = new int[][]
				{ 
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1}
				};*/
		boardMatrix = new MatrixBoard(8,8,1);

	}

}
