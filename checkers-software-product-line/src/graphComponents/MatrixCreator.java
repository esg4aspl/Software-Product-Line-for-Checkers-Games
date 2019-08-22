package graphComponents;

import graphInterfaces.AreaInterface;
import graphInterfaces.AreaWithObjectInterface;
import graphInterfaces.ConnectionInterface;

public class MatrixCreator {


	private AreaWithObjectInterface point00;
	private int numberOfRows;
	private int numberOfColumns;
	private Object fillObject;
	
	//+y is downward
	public MatrixCreator(int numberOfRows, int numberOfColumns,Object fillObject) {
		checkValid(numberOfRows,numberOfColumns);
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		this.fillObject = fillObject;
		createMatrix();
	}	
	
	public MatrixCreator(int numberOfRows, int numberOfColumns) {
		this(numberOfRows,numberOfColumns,null);
	}

	public AreaWithObjectInterface getPoint00() {
		return point00;
	}
	
	private Object getFillObject() {
		return fillObject;
	}
	
	private int getNumberOfRows() {
		return numberOfRows;
	}

	private int getNumberOfColumns() {
		return numberOfColumns;
	}

	private void checkValid(int numberOfRows, int numberOfColumns) {
		if(numberOfRows<=0||numberOfColumns<=0)
			throw new IllegalArgumentException();
	}

	private void createMatrix() {
		AreaWithObjectInterface[] tempRow = null;
		for(int r=0;r<getNumberOfRows();r++) {
			AreaWithObjectInterface[] currentRow = new AreaWithObject[getNumberOfColumns()];
			for(int c=0;c<numberOfColumns;c++) {
				currentRow[c] = new AreaWithObject(new GameObject(getFillObject()));
			}
			connectColumn(currentRow);
			if(r == 0){
				this.point00 = currentRow[0];
			}else {
				connectRows(tempRow,currentRow);
			}
			tempRow = currentRow;
		}

		
	}

	private void connectColumn(AreaWithObjectInterface[] currentRow) {
		for(int i=0;i<getNumberOfColumns()-1;i++) {
			ConnectionInterface<AreaInterface, AreaInterface> connection = new Connection<AreaInterface, AreaInterface>(currentRow[i],currentRow[i+1],0);
			currentRow[i].addArea(connection);
			currentRow[i+1].addArea(connection.createReverseConnection());
		}		
	}

	private void connectRows(AreaWithObjectInterface[] tempRow, AreaWithObjectInterface[] currentRow) {
		for(int i=0;i<getNumberOfColumns();i++) {
			ConnectionInterface<AreaInterface, AreaInterface> connection = new Connection<AreaInterface, AreaInterface>(tempRow[i],currentRow[i],270);
			tempRow[i].addArea(connection);
			currentRow[i].addArea(connection.createReverseConnection());
		}
	}
}
