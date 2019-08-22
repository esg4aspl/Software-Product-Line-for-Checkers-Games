package graphComponents;

import graphInterfaces.*;

public class Edge implements EdgeInterface{

	private static int ID =1;
	private int id;
	private ConnectionInterface<EdgeInterface, PointInterface> pointLeftConnection;
	private ConnectionInterface<EdgeInterface, PointInterface> pointRightConnection;
	private ConnectionInterface<EdgeInterface, AreaInterface> areaUpConnection;
	private ConnectionInterface<EdgeInterface, AreaInterface> areaDownConnection;
	
	
	//assume when angleFromPoint1toPoint2 is zero, area1 is above from area2 
	public Edge(PointInterface point1, PointInterface point2, AreaInterface area1, AreaInterface area2,double angleFromPoint1toPoint2,double angleFromArea1toArea2) {
		checkValid(point1,point2,area1,area2,angleFromPoint1toPoint2,angleFromArea1toArea2);	//should be checking validation by angles between each other
		this.pointLeftConnection = new Connection<EdgeInterface, PointInterface>(this,point1,(angleFromPoint1toPoint2+180)%360);
		this.pointRightConnection = new Connection<EdgeInterface, PointInterface>(this, point2, angleFromPoint1toPoint2);
		this.areaUpConnection = new Connection<EdgeInterface, AreaInterface>(this, area1, (angleFromArea1toArea2+180)%360);
		this.areaDownConnection = new Connection<EdgeInterface, AreaInterface>(this, area2, angleFromArea1toArea2);
		this.id = ID;
		ID++;
	}
	private void checkValid(PointInterface point1, PointInterface point2, AreaInterface area1, AreaInterface area2,double angleFromPoint1toPoint2,double angleFromArea1toArea2) {
		//cannot check conditions between points and areas with respect to angle
		boolean point1cond1 = point1.hasPoint(point2);
		if(point1cond1) {
			point1cond1 = ((Point)point1.getPointConnection(angleFromPoint1toPoint2).getOther(point1)).equals(point2);
		}
		boolean point1cond2 = point1.hasArea(area1);
		boolean point1cond3 = point1.hasArea(area2);
		boolean point1cond = point1cond1&&point1cond2&&point1cond3;
		
		boolean point2cond1 = point2.hasPoint(point1);
		if(point2cond1) {
			point1cond1 = ((Point)point2.getPointConnection((angleFromPoint1toPoint2+180)%360).getOther(point2)).equals(point1);
		}
		boolean point2cond2 = point2.hasArea(area1);
		boolean point2cond3 = point2.hasArea(area2);
		boolean point2cond = point2cond1&&point2cond2&&point2cond3;
		
		boolean area1cond1 = area1.hasPoint(point1);
		boolean area1cond2 = area1.hasPoint(point2);
		boolean area1cond3 = area1.hasArea(area2);
		if(area1cond3) {
			area1cond3 = ((Area)area1.getAreaConnection(angleFromArea1toArea2).getOther(area1)).equals(area2);
		}
		boolean area1cond = area1cond1&&area1cond2&&area1cond3;
		
		boolean area2cond1 = area2.hasPoint(point1);
		boolean area2cond2 = area2.hasPoint(point2);
		boolean area2cond3 = area2.hasArea(area1);
		if(area2cond3) {
			area2cond3 = ((Area)area2.getAreaConnection((angleFromArea1toArea2+180)%360).getOther(area2)).equals(area1);
		}
		boolean area2cond = area2cond1&&area2cond2&&area2cond3;
		
		if(point1cond&&point2cond&&area1cond&&area2cond)
			throw new IllegalArgumentException();

	}
	
	private int getId() {
		return id;
	}

	public ConnectionInterface<EdgeInterface, PointInterface> getPointLeftConnection() {
		return pointLeftConnection;
	}
	public ConnectionInterface<EdgeInterface, PointInterface> getPointRightConnection() {
		return pointRightConnection;
	}
	public ConnectionInterface<EdgeInterface, AreaInterface> getAreaUpConnection() {
		return areaUpConnection;
	}
	public ConnectionInterface<EdgeInterface, AreaInterface> getAreaDownConnection() {
		return areaDownConnection;
	}
	public PointInterface getPointLeft() {
		return (PointInterface) getPointLeftConnection().getOther(this);
	}

	public PointInterface getPointRight() {
		return (PointInterface) getPointRightConnection().getOther(this);
	}

	public AreaInterface getAreaUp() {
		return (AreaInterface) getAreaUpConnection().getOther(this);
	}

	public AreaInterface getAreaDown() {
		return (AreaInterface) getAreaDownConnection().getOther(this);
	}

	public boolean hasPoint(PointInterface point) {
		if(point == null) {return false;}
		return point.equals(getPointLeft())||point.equals(getPointRight());
	}

	public boolean hasArea(AreaInterface area) {
		if(area == null) {return false;}
		return area.equals(getAreaUp()) || area.equals(getAreaDown());
	}

	public boolean hasPoint(double angle) {
		if(angle < 0) {return false;}
		return getPoint(angle)!=null;
	}

	public boolean hasArea(double angle) {
		if(angle < 0) {return false;}
		return getArea(angle)!=null;
	}

	public PointInterface getPoint(double angle) {
		if(angle < 0) {return null;} //for validation
		if(getPointLeftConnection().getAngleToOther(this)==angle) {
			return getPointLeft();
		}else if (getPointRightConnection().getAngleToOther(this)==angle) {
			return getPointRight();
		}else {
			return null;
		}	}

	public AreaInterface getArea(double angle) {
		if(angle < 0) {return null;} //for validation
		if (getAreaUpConnection().getAngleToOther(this)==angle) {
			return getAreaUp();
		}else if (getAreaDownConnection().getAngleToOther(this)==angle) {
			return getAreaDown();
		}else {
			return null;
		}	}

	public boolean equals(EdgeInterface edge) {
		if(false) {return false;} //for validation
		return ((Edge) edge).getId()==this.getId();
	}
}
