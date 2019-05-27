package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.*;
import base.Move;

public class MoveFileReader {
	
	private String filename;
	private File file;
	private Scanner sc;

	public MoveFileReader() {
		filename = "completeGameForTurkish";
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
			Pattern pattern = Pattern.compile("\\d+");
			Matcher matcher = pattern.matcher(line);
			matcher.find();
			IPlayer player = referee.getPlayerbyID(Integer.parseInt(matcher.group()));
			matcher.find();
			int sourceX = Integer.parseInt(matcher.group());
			matcher.find();
			int sourceY = Integer.parseInt(matcher.group());
			matcher.find();
			int destinationX = Integer.parseInt(matcher.group());
			matcher.find();
			int destinationY = Integer.parseInt(matcher.group());
			
			IMoveCoordinate moveCoordinate = new MoveCoordinate(new Coordinate(sourceX,sourceY),
					new Coordinate(destinationX,destinationY));
			AbstractMove move = new Move(player, moveCoordinate);
			automaticMoveList.add(move);
			/*
			IPlayer player = referee.getPlayerbyID(Integer.parseInt(line.substring(0, 1)));
			IMoveCoordinate moveCoordinate = new MoveCoordinate(
					new Coordinate(Integer.parseInt(line.substring(3, 4)),
							Integer.parseInt(line.substring(5, 6))), 
					new Coordinate(Integer.parseInt(line.substring(10, 11)),
							Integer.parseInt(line.substring(12, 13))));
			AbstractMove move = new Move(player, moveCoordinate);
			automaticMoveList.add(move);
			*/
		}

		return automaticMoveList;
	}
}
