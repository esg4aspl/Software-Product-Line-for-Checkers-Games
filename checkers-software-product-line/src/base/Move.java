package base;

import core.*;

public class Move extends AbstractMove {

	public Move(IPlayer player, IMoveCoordinate moveCoordinate) {
		super(player, moveCoordinate);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		AbstractMove m = (AbstractMove)obj;
		return this.getPlayer().equals(m.getPlayer()) 
				&& this.getMoveCoordinate().equals(m.getMoveCoordinate());
	}
	
	

}
