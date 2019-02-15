package rules;

import java.util.List;

import base.Player;
import core.AbstractReferee;
import core.AbstractPiece;
import core.IRule;
import core.Zone;
import core.IPlayer;
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
			Player tmpPlayer= (Player)playerList.getPlayer(i);
			for(AbstractPiece piece : tmpPlayer.getPieceList()) {
				if((piece.getIcon().equals("A") || piece.getIcon().equals("Z")) && piece.getCurrentZone().equals(Zone.ONBOARD))
					currentNumberOfKing++;
			}
		}
		return currentNumberOfKing;
		
	}

}
