package rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.AbstractBoard;
import core.AbstractPiece;
import core.AbstractReferee;
import core.Coordinate;
import core.CoordinatePieceMap;
import core.Direction;
import core.ICoordinate;
import core.IPlayer;
import core.IRule;

public class RuleEndOfGamePiecesOfPlayerOnFinishCoordinates implements IRule {
	private List<ICoordinate> southCoordinates;
	private List<ICoordinate> southEastCoordinates;
	private List<ICoordinate> northEastCoordinates;
	private List<ICoordinate> northCoordinates;
	private List<ICoordinate> northWestCoordinates;
	private List<ICoordinate> southWestCoordinates;
	private Map<Direction, List<ICoordinate>> finalCoordinates;
	
	public RuleEndOfGamePiecesOfPlayerOnFinishCoordinates() {
		southCoordinates = new ArrayList<ICoordinate>();
		southEastCoordinates = new ArrayList<ICoordinate>();
		northEastCoordinates = new ArrayList<ICoordinate>();
		northCoordinates = new ArrayList<ICoordinate>();
		northWestCoordinates = new ArrayList<ICoordinate>();
		southWestCoordinates = new ArrayList<ICoordinate>();
		finalCoordinates = new HashMap<Direction, List<ICoordinate>>();
		init();
	}

	
	@Override
	public boolean evaluate(AbstractReferee referee) {
		AbstractBoard board = referee.getBoard();
		CoordinatePieceMap pieceMap = board.getCoordinatePieceMap();
		IPlayer currentPlayer = referee.getCurrentPlayer();
		AbstractPiece currentPlayerPiece = currentPlayer.getPieceList().get(0);
		List<AbstractPiece> currentPlayerPieceList = currentPlayer.getPieceList();
		for(ICoordinate finalCoordinate : finalCoordinates.get(currentPlayerPiece.getGoalDirection())){
			if(!currentPlayerPieceList.contains(pieceMap.getPieceAtCoordinate(finalCoordinate))) {
				return false;
			}
		}		
		return true;
	}
	
	
	private void init() {
		//south
		southCoordinates.add(new Coordinate(12,0));
		southCoordinates.add(new Coordinate(11,1));
		southCoordinates.add(new Coordinate(13,1));
		southCoordinates.add(new Coordinate(10,2));
		southCoordinates.add(new Coordinate(12,2));
		southCoordinates.add(new Coordinate(14,2));
		southCoordinates.add(new Coordinate(9,3));
		southCoordinates.add(new Coordinate(11,3));
		southCoordinates.add(new Coordinate(13,3));
		southCoordinates.add(new Coordinate(15,3));
		
		//south-east
		southEastCoordinates.add(new Coordinate(24,4));
		southEastCoordinates.add(new Coordinate(22,4));
		southEastCoordinates.add(new Coordinate(23,5));
		southEastCoordinates.add(new Coordinate(20,4));
		southEastCoordinates.add(new Coordinate(21,5));
		southEastCoordinates.add(new Coordinate(22,6));
		southEastCoordinates.add(new Coordinate(18,4));
		southEastCoordinates.add(new Coordinate(19,5));
		southEastCoordinates.add(new Coordinate(20,6));
		southEastCoordinates.add(new Coordinate(21,7));	
		
		//north-east
		northEastCoordinates.add(new Coordinate(24,12));
		northEastCoordinates.add(new Coordinate(23,11));
		northEastCoordinates.add(new Coordinate(22,12));
		northEastCoordinates.add(new Coordinate(22,10));
		northEastCoordinates.add(new Coordinate(21,11));
		northEastCoordinates.add(new Coordinate(20,12));
		northEastCoordinates.add(new Coordinate(21,9));
		northEastCoordinates.add(new Coordinate(20,10));
		northEastCoordinates.add(new Coordinate(19,11));
		northEastCoordinates.add(new Coordinate(18,12));
		
		//north
		northCoordinates.add(new Coordinate(12,16));
		northCoordinates.add(new Coordinate(13,15));
		northCoordinates.add(new Coordinate(11,15));
		northCoordinates.add(new Coordinate(14,14));
		northCoordinates.add(new Coordinate(12,14));
		northCoordinates.add(new Coordinate(10,14));
		northCoordinates.add(new Coordinate(15,13));
		northCoordinates.add(new Coordinate(13,13));
		northCoordinates.add(new Coordinate(11,13));
		northCoordinates.add(new Coordinate(9,13));
		
		//north-west
		northWestCoordinates.add(new Coordinate(0,12));
		northWestCoordinates.add(new Coordinate(2,12));
		northWestCoordinates.add(new Coordinate(1,11));
		northWestCoordinates.add(new Coordinate(4,12));
		northWestCoordinates.add(new Coordinate(3,11));
		northWestCoordinates.add(new Coordinate(2,10));
		northWestCoordinates.add(new Coordinate(6,12));
		northWestCoordinates.add(new Coordinate(5,11));
		northWestCoordinates.add(new Coordinate(4,10));
		northWestCoordinates.add(new Coordinate(3,9));
		
		//south-west
		southWestCoordinates.add(new Coordinate(0,4));
		southWestCoordinates.add(new Coordinate(1,5));
		southWestCoordinates.add(new Coordinate(2,4));
		southWestCoordinates.add(new Coordinate(2,6));
		southWestCoordinates.add(new Coordinate(3,5));
		southWestCoordinates.add(new Coordinate(4,4));
		southWestCoordinates.add(new Coordinate(3,7));
		southWestCoordinates.add(new Coordinate(4,6));
		southWestCoordinates.add(new Coordinate(5,5));
		southWestCoordinates.add(new Coordinate(6,4));
		
		finalCoordinates.put(Direction.S, southCoordinates);
		finalCoordinates.put(Direction.SE, southEastCoordinates);
		finalCoordinates.put(Direction.NE, northEastCoordinates);
		finalCoordinates.put(Direction.N, northCoordinates);
		finalCoordinates.put(Direction.NW, northWestCoordinates);
		finalCoordinates.put(Direction.SW, southWestCoordinates);
	}

}
