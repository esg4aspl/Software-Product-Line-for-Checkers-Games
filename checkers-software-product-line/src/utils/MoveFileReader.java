package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import core.*;
import base.Move;

public class MoveFileReader {
	
	private String filename;
	private File file;
	private Scanner sc;

	public MoveFileReader() {
		filename = "movelistForBlockedEndOfGameTest";
	    file = new File(filename); 
	  	try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public List<AbstractMove> readMoveListFromFile(AbstractReferee referee, List<AbstractMove> automaticMoveList) {

		String line;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			System.out.println(line);
			IPlayer player = referee.getPlayerbyID(Integer.parseInt(line.substring(0, 1)));
			IMoveCoordinate moveCoordinate = new MoveCoordinate(
					new Coordinate(Integer.parseInt(line.substring(3, 4)),
							Integer.parseInt(line.substring(5, 6))), 
					new Coordinate(Integer.parseInt(line.substring(10, 11)),
							Integer.parseInt(line.substring(12, 13))));

			AbstractMove move = new Move(player, moveCoordinate);
			automaticMoveList.add(move);
		}

		return automaticMoveList;
	}

}
