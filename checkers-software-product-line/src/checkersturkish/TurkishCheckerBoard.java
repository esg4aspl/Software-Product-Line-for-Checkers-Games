package checkersturkish;

import core.AbstractBoard;

public class TurkishCheckerBoard extends AbstractBoard{

	public TurkishCheckerBoard() {
		super(7, 7);
		init();
	}
	
	private void init() {
		boardMatrix = new int[][]
				{ 
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1}
				};
	}

}
