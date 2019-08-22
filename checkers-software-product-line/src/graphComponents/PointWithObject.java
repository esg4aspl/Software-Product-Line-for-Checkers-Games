package graphComponents;

import graphInterfaces.GameObjectInterface;
import graphInterfaces.PointWithObjectInterface;

public class PointWithObject extends Point implements PointWithObjectInterface {
	private GameObjectInterface gameObject;
	
	
	
	public PointWithObject(GameObjectInterface gameObject) {
		super();
		this.gameObject = gameObject;
	}



	public GameObjectInterface getGameObject() {
		return gameObject;
	}
}
