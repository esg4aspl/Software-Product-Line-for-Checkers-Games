package rules;

import java.util.ArrayList;
import java.util.List;

import core.AbstractBoard;
import core.AbstractPiece;
import core.AbstractReferee;
import core.CoordinateBasedOperations;
import core.ICoordinate;
import core.IMoveCoordinate;
import core.IPlayer;
import core.IRule;
import core.MoveCoordinate;

public class RuleIfAnyPieceCanBeCapturedThenMoveMustBeThat implements IRule {
	/* In this method all pieces of current player are checked whether there are any any move to capture 
	 * opponent's piece.If so, add this move to a list and then check current move of player is equal to any of 
	 * move that is already inserted in list. If that move is in list then move is correct else user should select one 
	 * of them. 
	 * */
	@Override
	public boolean evaluate(AbstractReferee referee) {
		// TODO Auto-generated method stub
		IPlayer currentPlayer = referee.getCurrentPlayer();
		IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
		CoordinateBasedOperations cbo = referee.getBoard().getCBO();
		//later this will change getPieceList method can be moved in interface
		AbstractBoard board = referee.getBoard();
		List<AbstractPiece> pieceList =currentPlayer.getPieceList();
		List<IMoveCoordinate> possibleCapturedMoves = new ArrayList<IMoveCoordinate>();
		for(AbstractPiece piece : pieceList) {
			//get current coordinate of piece and try all possible moves to check
			//if any capturing can be seen
			ICoordinate sourceCoordinateForCheck = piece.getCurrentCoordinate();
			for(ICoordinate possibleMove:cbo.findAllowedContinousJumpList(piece)) {
				IMoveCoordinate moveCoord = new MoveCoordinate(sourceCoordinateForCheck,possibleMove);
				List<ICoordinate> path = referee.getBoard().getCBO().findPath(piece, moveCoord);
				
				for(int i=1; i<path.size()-1; i++) {
					ICoordinate coordinateOnPath = path.get(i);
					AbstractPiece pieceAtCoord = board.getCoordinatePieceMap().getPieceAtCoordinate(coordinateOnPath);
					if(pieceAtCoord!=null) {
						if(!pieceAtCoord.getPlayer().equals(currentPlayer))
							possibleCapturedMoves.add(moveCoord);
					}
						
				}
			}
		}
		if(possibleCapturedMoves.size()>0) {
			if(possibleCapturedMoves.contains(moveCoordinate)){
				return true;
			}
			else {
				System.out.println("If any opponent's pieces can be captured then it must be captured first!!!!");
				printListTest(possibleCapturedMoves);
				return false;
			}
			
		}
		return true;
	}
	//captured list
	//this part is just for test
	private void printListTest(List<IMoveCoordinate> list) {
		System.out.println("FIRST YOU SHOULD MOVE : ");
		for(IMoveCoordinate m : list) {
			System.out.println(m.getSourceCoordinate()+" - "+m.getDestinationCoordinate());
		}
		System.out.println("--------------------------");
	}

}
