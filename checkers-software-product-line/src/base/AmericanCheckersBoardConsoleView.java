package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.*;
import utils.*;

public class AmericanCheckersBoardConsoleView {

	protected AbstractReferee referee;
	protected AbstractBoard checkersBoard;
	protected CoordinatePieceMap coordinatePieceMap;
	protected String[][] boardMatrixView;
	protected List<AbstractMove> automaticMoveList;
	protected Scanner scanner;
	protected MoveFileWriter moveFileWriter;
	protected MoveFileReader moveFileReader;

	public AmericanCheckersBoardConsoleView(AbstractReferee referee) {
		this.referee = referee;
		checkersBoard = referee.getBoard();
		coordinatePieceMap = checkersBoard.getCoordinatePieceMap();
		scanner = new Scanner(System.in);
		moveFileWriter = new MoveFileWriter();
		moveFileReader = new MoveFileReader();
		readAutomaticMoveList();
		initView();
	}

	private void readAutomaticMoveList() {
		automaticMoveList = new ArrayList<>();
		moveFileReader.readMoveListFromFile(referee, automaticMoveList);
	}

	public int getSizeOfAutomaticMoveList() {
		return automaticMoveList.size();
	}
	
	private void initView() {
		// board lower left has coordinate (0,0)
		boardMatrixView = new String[][]
				{ 
			{" "," "," "," "," "," "," "," "},
			{" "," "," "," "," "," "," "," "},
			{" "," "," "," "," "," "," "," "},
			{" "," "," "," "," "," "," "," "},
			{" "," "," "," "," "," "," "," "},
			{" "," "," "," "," "," "," "," "},
			{" "," "," "," "," "," "," "," "},
			{" "," "," "," "," "," "," "," "},
				};
	}

	public void drawBoardView() {
		String lineDraw       = "   --- --- --- --- --- --- --- --- ";
		String coordinateDraw = "    0   1   2   3   4   5   6   7  ";
		System.out.println(coordinateDraw);
		System.out.println(lineDraw);
		for (int i = 7; i >= 0; i--) {
			System.out.println(lineBuilder(i));
			System.out.println(lineDraw);
		}
		System.out.println(coordinateDraw);
	}

	private String lineBuilder(int lineNumber) {
		String breaker = " | ";
		String line = lineNumber + " | ";
		for (int i = 0; i < 8; i++)
			line = line + getIcon(i, lineNumber) + breaker;
		line = line + lineNumber;
		return line;
	}

	private String getIcon(int x, int y) {
		ICoordinate coordinate = new Coordinate(x, y);
		AbstractPiece piece = coordinatePieceMap.getPieceAtCoordinate(coordinate);
		if (piece == null) return " ";
		return piece.getIcon();
	}
	
	public IMoveCoordinate getNextMove(IPlayer currentPlayer) {
		System.out.println("Enter Source Coordinate x,y for Player " + currentPlayer.getId());
		String sc = scanner.nextLine(); 
		System.out.println("Enter Destination Coordinate x,y for Player " + currentPlayer.getId());
		String dc = scanner.nextLine(); 
		
		int scx = Integer.parseInt(sc.substring(0, 1));
		int scy = Integer.parseInt(sc.substring(2, 3));
		ICoordinate sourceCoordinate = new Coordinate(scx, scy);

		int dcx = Integer.parseInt(dc.substring(0, 1));
		int dcy = Integer.parseInt(dc.substring(2, 3));
		ICoordinate destinationCoordinate = new Coordinate(dcx, dcy);

		IMoveCoordinate moveCoordinate = new MoveCoordinate(sourceCoordinate, destinationCoordinate);
		AbstractMove move = new Move(currentPlayer, moveCoordinate);
		if (scx==9 && scy==9 && dcx==9 && dcy==9) {
			moveFileWriter.closeFile();
			System.exit(0);
		}
		else moveFileWriter.writeMoveToFile(move);
		return moveCoordinate;
	}

	public AbstractMove getNextAutomaticMove(int step) {
		AbstractMove move = automaticMoveList.get(step);
		moveFileWriter.writeMoveToFile(move);
		return move;
	}

	public void closeFile() {
		moveFileWriter.closeFile();
	}
}

