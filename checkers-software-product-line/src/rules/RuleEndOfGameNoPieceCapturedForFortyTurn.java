package rules;

import core.AbstractReferee;
import core.IPlayer;
import core.IPlayerList;
import core.IRule;

public class RuleEndOfGameNoPieceCapturedForFortyTurn implements IRule {
	private int uncapturedPieceTurnCounter;
	private int numberOfPiecePreviousTurn;
	private final int turnConstraint = 40;
	
	public RuleEndOfGameNoPieceCapturedForFortyTurn() {
		uncapturedPieceTurnCounter = turnConstraint;
		numberOfPiecePreviousTurn = 0;
	}
	@Override
	public boolean evaluate(AbstractReferee referee) {				
		IPlayerList players = referee.getPlayers();
		int numberOfPiecesOnBoard = 0;
		for(IPlayer p : players.getPlayers()) 
			numberOfPiecesOnBoard += p.getNumberOfPieces();
		
		if(numberOfPiecePreviousTurn != numberOfPiecesOnBoard) {
			uncapturedPieceTurnCounter = turnConstraint;
			numberOfPiecePreviousTurn = numberOfPiecesOnBoard;
		}
		else {
			uncapturedPieceTurnCounter--;															
		}	

		return uncapturedPieceTurnCounter == 0;
	}


}