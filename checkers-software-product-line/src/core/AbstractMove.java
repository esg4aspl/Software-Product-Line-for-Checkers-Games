package core;

public abstract class AbstractMove {

	private IPlayer player;
	private IMoveCoordinate moveCoordinate;
	
	public AbstractMove(IPlayer player, IMoveCoordinate moveCoordinate) {
		this.player = player;
		this.moveCoordinate = moveCoordinate;
	}
	
	public IPlayer getPlayer() {
		return player;
	}
	
	public IMoveCoordinate getMoveCoordinate() {
		return moveCoordinate;
	}

	@Override
	public String toString() {
		return player.getId() + ":" + moveCoordinate;
	}

}
