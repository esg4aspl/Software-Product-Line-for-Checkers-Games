package graphComponents;

import graphComponents.MatrixCreator;
import graphInterfaces.AreaWithObjectInterface;

public class MatrixBoard{

private AreaWithObjectInterface area00;
private int rowNumber;
private int columnNumber;

	public MatrixBoard(int row,int column,Object value) {
		area00 = new MatrixCreator(row, column,value).getPoint00();
		rowNumber = row;
		columnNumber = column;
	}
	
	
	private AreaWithObjectInterface getPoint00() {
		return area00;
	}

	
	private AreaWithObjectInterface getArea(int x, int y) {
		AreaWithObjectInterface returnArea = null;
		if(checkXYValid(x, y)) {
			AreaWithObjectInterface currentArea = getPoint00();
			for(int r =0; r<y;r++)
				currentArea = (AreaWithObjectInterface) currentArea.getAreaConnection(270).getOther(currentArea);
			for(int c =0; c<x;c++)
				currentArea = (AreaWithObjectInterface) currentArea.getAreaConnection(0).getOther(currentArea);
			returnArea = currentArea;
		}
		return returnArea;
	}



	private boolean checkXYValid(int x, int y) {
		boolean condX = x>=0&&x<rowNumber;
		boolean condY = y>=0&&y<columnNumber;
		return condX&&condY;
	}


	public Object getPlace(int x, int y) {
		if(!checkXYValid(x,y)) {return null;}	//for validation
		return  getArea(x, y).getGameObject().getObject();
	}

	public boolean setPlace(int x, int y, Object value) {
		if(!checkXYValid(x,y)) {return false;}	//for validation
		getArea(x, y).getGameObject().setObject(value);
		return true;
	}

}
