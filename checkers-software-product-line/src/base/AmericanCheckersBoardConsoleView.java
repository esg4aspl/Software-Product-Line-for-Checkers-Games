package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	private ICoordinate inputHelper(String message) {
		System.out.println(message);
		String coordinate = scanner.nextLine(); 
		
		//this pattern provide the input (number)(any number of non numerical character)(number)
		Pattern patternForInput = Pattern.compile("\\d+\\D+\\d+");
		//this is used to provide the extract numbers from string
		Pattern extract = Pattern.compile("\\d+");
		
		Matcher matchForInput = patternForInput.matcher(coordinate);
		boolean flagForinput = matchForInput.matches();
		while(!flagForinput) {
			System.out.println("Pattern for input is (Number)(Any number of non-numeric character)(Number)");
			System.out.println(message);
			coordinate = scanner.nextLine(); 
			matchForInput = patternForInput.matcher(coordinate);
			flagForinput = matchForInput.matches();
		}
		
		Matcher matcherNumeric = extract.matcher(coordinate);
		matcherNumeric.find();
		int coordinatex = Integer.parseInt(matcherNumeric.group());
		matcherNumeric.find();
		int coordinatey = Integer.parseInt(matcherNumeric.group());
		return new Coordinate(coordinatex, coordinatey);
	}
	
	public IMoveCoordinate getNextMove(IPlayer currentPlayer) {
		
		ICoordinate sourceCoordinate = inputHelper("Enter Source Coordinate x,y for Player " + currentPlayer.getId());
		ICoordinate destinationCoordinate = inputHelper("Enter Destination Coordinate x,y for Player " + currentPlayer.getId());
		int scx = sourceCoordinate.getXCoordinate();
		int scy = sourceCoordinate.getYCoordinate();
		int dcx = sourceCoordinate.getXCoordinate();
		int dcy = sourceCoordinate.getYCoordinate();
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

