package base;

import core.*;
import graphComponents.MatrixBoard;

//model class
public class AmericanCheckersBoard extends AbstractBoard {

	public AmericanCheckersBoard() {
		super(7, 7);
		init();
	}

	private void init() {
		// board lower left has coordinate (0,0)
		/*boardMatrix = new int[][]
				{ 
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1}
				};*/
		boardMatrix = new MatrixBoard(8,8,0);
		for(int x=0; x<8; x++)
			for(int y=0; y<8; y++)
				if((x+y)%2==0)
					boardMatrix.setPlace(x, y, 1);
	}

}
