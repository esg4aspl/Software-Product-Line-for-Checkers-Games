package base;

import java.util.List;
import java.util.ArrayList;

import core.*;

public class PlayerList implements IPlayerList {

	List<IPlayer> playerList;
	
	public PlayerList() {
		playerList = new ArrayList<IPlayer>();
	}
	
	public void addPlayer(IPlayer player) {
		playerList.add(player);
	}

	public void removePlayer(IPlayer player) {
		playerList.remove(player);
	}

	public IPlayer getPlayer(int index) {
		return playerList.get(index);
	}
	
	public int getNumberOfPlayers() {
		return playerList.size();
	}
	
	public List<IPlayer> getPlayers() {
		return playerList;
	}

	public String getPlayerStatus() {
		String stringToReturn = "";
		for(IPlayer player : playerList)
			stringToReturn = stringToReturn + 
				player + player.getNumberOfPieces() + " \n";
		return stringToReturn;
	}
	
	@Override
	public String toString() {
		String players = "";
		for(IPlayer p : playerList)
			players += p+"\n";
		return players;
	}
}
