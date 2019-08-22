package graphComponents;

import graphInterfaces.GameObjectInterface;

public class GameObject implements GameObjectInterface{

	Object object;
	
	public GameObject(Object object) {
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}


	
}
