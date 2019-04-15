package rules;



import core.AbstractReferee;
import core.IPlayer;
import core.IPlayerList;
import core.IRule;

public class RuleEndOfGameIfEachPlayerHasOnePiece implements IRule {

	@Override
	public boolean evaluate(AbstractReferee referee) {
		// TODO Auto-generated method stub
		IPlayerList playerList = referee.getPlayers();
		for(IPlayer player : playerList.getPlayers()) {
			if (player.getNumberOfPieces()!=1) {
				return false;
			}
		}
		return true;
	}

}
