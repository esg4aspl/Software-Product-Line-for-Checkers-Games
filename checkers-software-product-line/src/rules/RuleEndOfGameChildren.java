package rules;

import core.*;

public class RuleEndOfGameChildren implements IRule {

	public boolean evaluate(AbstractReferee referee) {
		return isBackRowReached(referee);
	}

	private boolean isBackRowReached(AbstractReferee referee) {
		IPlayer player = referee.getCurrentPlayer();
		IMoveCoordinate moveCoordinate = referee.getCurrentMoveCoordinate();
    	ICoordinate destinationCoordinate = moveCoordinate.getDestinationCoordinate();
    	int y = destinationCoordinate.getYCoordinate();
    	if (player.getId() == 0 && y == 7) return true;
    	if (player.getId() == 1 && y == 0) return true;
    	return false;
	}

}
