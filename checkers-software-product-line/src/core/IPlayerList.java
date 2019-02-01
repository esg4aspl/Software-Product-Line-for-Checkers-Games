package core;

import java.util.List;

public interface IPlayerList {

	public int getNumberOfPlayers();
	public IPlayer getPlayer(int index);
	public List<IPlayer> getPlayers();
	public void addPlayer(IPlayer player);
	public void removePlayer(IPlayer player);
	public String getPlayerStatus();

}
