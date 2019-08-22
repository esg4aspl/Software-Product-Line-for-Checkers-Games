package graphComponents;

import graphInterfaces.AreaInterface;
import graphInterfaces.EdgeWtihObjectInterface;
import graphInterfaces.GameObjectInterface;
import graphInterfaces.PointInterface;

public class EdgeWithObject extends Edge implements EdgeWtihObjectInterface {
	private GameObjectInterface gameObject;
	
	

	public EdgeWithObject(PointInterface point1, PointInterface point2, AreaInterface area1, AreaInterface area2,
			double angleFromPoint1toPoint2, double angleFromArea1toArea2, GameObjectInterface gameObject) {
		super(point1, point2, area1, area2, angleFromPoint1toPoint2, angleFromArea1toArea2);
		this.gameObject = gameObject;
	}



	public GameObjectInterface getGameObject() {
		return gameObject;
	}

}
