package rules;

import core.*;

public class RuleEndOfGameGeneral implements IRule {

	public boolean evaluate(AbstractReferee referee) {
		return areAllPiecesEliminated(referee);
	}

	private boolean areAllPiecesEliminated(AbstractReferee referee) {
		IPlayerList playerList = referee.getPlayers();
		IPlayer currentPlayer = referee.getCurrentPlayer();
		int currentPlayerID = currentPlayer.getId();
		currentPlayerID++;
		if (currentPlayerID >= referee.getNumberOfPlayers()) currentPlayerID = 0;
		IPlayer otherPlayer = playerList.getPlayer(currentPlayerID);
		if (otherPlayer.getNumberOfPieces() > 0) return false;
		return true;	
	}

}
