package checkerschinese;

import base.StartCoordinates;

public class StartCoordinateFactory {
	public StartCoordinates getStartCoordinates(int numberOfPlayer,int numberOfPiecesPerPlayer) {
		if(numberOfPlayer==2)
			return new ChineseStartCoordinatesFor2Players(numberOfPiecesPerPlayer);
		else if(numberOfPlayer==3)
			return new ChineseStartCoordinatesFor3Players(numberOfPiecesPerPlayer);
		else if(numberOfPlayer==4)
			return new ChineseStartCoordinatesFor4Players(numberOfPiecesPerPlayer);
		else if(numberOfPlayer==6)
			return new ChineseStartCoordinatesFor6Players(numberOfPiecesPerPlayer);
		return null;
	}
}
