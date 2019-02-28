package rules;


import base.Pawn;
import core.AbstractReferee;
import core.IPlayer;
import core.AbstractPiece;
import core.IRule;
import core.Zone;
import core.IPlayerList;

public class RuleDrawIfNoPromoteForFortyTurn implements IRule {
	private int previousTurnNumberOfKing=0;
	private int noPromoteTurnNumber=0;
	@Override
	public boolean evaluate(AbstractReferee referee) {
		// TODO Auto-generated method stub
		int currentNumberOfKings = findNumberOfKings(referee);
		if(currentNumberOfKings > previousTurnNumberOfKing) {
			noPromoteTurnNumber = 0;
			}
		else {
			noPromoteTurnNumber++;
			if(noPromoteTurnNumber == 40)
				return true;
			}
		previousTurnNumberOfKing=currentNumberOfKings;	
		return false;
	}
	private int findNumberOfKings(AbstractReferee referee) {
		int currentNumberOfKing=0;
		int numberOfPlayer=referee.getNumberOfPlayers();
		IPlayerList playerList = referee.getPlayers();
		for(int i=0; i<numberOfPlayer; i++) {
			IPlayer tmpPlayer= playerList.getPlayer(i);
			for(AbstractPiece piece : tmpPlayer.getPieceList()) {
				if(!(piece instanceof Pawn) && piece.getCurrentZone().equals(Zone.ONBOARD))
					currentNumberOfKing++;
			}
		}
		return currentNumberOfKing;
		
	}

}
