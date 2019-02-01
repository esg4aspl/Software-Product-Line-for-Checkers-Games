package core;

public class MoveOpResult {

	boolean isSuccessful;
	boolean isCurrentPlayerTurnAgain;
	
	public MoveOpResult(boolean isSuccessful, boolean isCurrentPlayerTurnAgain) {
		this.isSuccessful = isSuccessful;
		this.isCurrentPlayerTurnAgain = isCurrentPlayerTurnAgain;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public boolean isCurrentPlayerTurnAgain() {
		return isCurrentPlayerTurnAgain;
	}
	
}
