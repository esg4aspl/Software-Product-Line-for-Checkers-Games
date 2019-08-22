package graphComponents;

import graphInterfaces.AreaWithObjectInterface;
import graphInterfaces.GameObjectInterface;

public class AreaWithObject extends Area implements AreaWithObjectInterface {
	private GameObjectInterface gameObject;

	
	
	public AreaWithObject(GameObjectInterface gameObject) {
		super();
		this.gameObject = gameObject;
	}



	public GameObjectInterface getGameObject() {
		return gameObject;
	}
}
