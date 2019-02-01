package base;

import core.*;

//model class
public class AmericanCheckersBoard extends AbstractBoard {

	public AmericanCheckersBoard() {
		super(7, 7);
		init();
	}

	private void init() {
		// board lower left has coordinate (0,0)
		boardMatrix = new int[][]
				{ 
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1}
				};
	}

}
