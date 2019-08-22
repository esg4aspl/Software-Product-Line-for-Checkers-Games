package graphComponents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import graphInterfaces.*;



public abstract class AreaPointContainer<T extends AreaPointInterface> {

	private static int ID =1;
	private int id;
	private List<ConnectionInterface<T,AreaInterface>> areaConnections;
	private List<ConnectionInterface<T,EdgeInterface>> edgeConnections;
	private List<ConnectionInterface<T,PointInterface>> pointConnections;
	
	public AreaPointContainer() {
		this.areaConnections = new ArrayList<ConnectionInterface<T,AreaInterface>>();
		this.edgeConnections = new ArrayList<ConnectionInterface<T,EdgeInterface>>();
		this.pointConnections = new ArrayList<ConnectionInterface<T,PointInterface>>();
		this.id = ID;
		ID++;
	}	
		
	private int getId() {
		return id;
	}

	private List<ConnectionInterface<T, AreaInterface>> getAreaConnections() {
		return areaConnections;
	}

	private List<ConnectionInterface<T, EdgeInterface>> getEdgeConnections() {
		return edgeConnections;
	}

	private List<ConnectionInterface<T, PointInterface>> getPointConnections() {
		return pointConnections;
	}
	
	private boolean chcekPointConnectionAddable(ConnectionInterface<T, PointInterface> connection) {
		if(connection == null || connection.getOther(this)==null || connection.getAngleToOther(this) <0) {return false;} //for validation check connection existence	check object1 == this
		if(hasPoint((Point)connection.getOther(this)) ||hasPoint(connection.getAngleToOther(this))) {return false;}
		return true;
	}
	
	private boolean chcekEdgeConnectionAddable(ConnectionInterface<T, EdgeInterface> connection) {
		if(connection == null || connection.getOther(this)==null || connection.getAngleToOther(this) <0) {return false;} //for validation check connection existence	check object1 == this
		if(hasEdge((Edge)connection.getOther(this)) ||hasPoint(connection.getAngleToOther(this))) {return false;}
		return true;
	}
	
	private boolean chcekAreaConnectionAddable(ConnectionInterface<T, AreaInterface> connection) {
		if(connection == null || connection.getOther(this)==null || connection.getAngleToOther(this) <0) {return false;} //for validation check connection existence	check object1 == this
		if(hasArea((Area)connection.getOther(this)) ||hasPoint(connection.getAngleToOther(this))) {return false;}
		return true;
	}

	public boolean addPoint(ConnectionInterface<T, PointInterface> connection) {
		if(chcekPointConnectionAddable(connection)) {
		getPointConnections().add(connection);
			return true;
		}else {
			return false;
		}
	}
	public boolean addEdge(ConnectionInterface<T, EdgeInterface> connection) {
		if(chcekEdgeConnectionAddable(connection)) {
		getEdgeConnections().add(connection);
			return true;
		}else {
			return false;
		}	}
	public boolean addArea(ConnectionInterface<T, AreaInterface> connection) {
		if(chcekAreaConnectionAddable(connection)) {
		getAreaConnections().add(connection);
			return true;
		}else {
			return false;
		}	}

	public boolean addPoint(PointInterface point, double angle) {
		return addPoint(new Connection<T, PointInterface>((T) this, point, angle));
	}
	public boolean addEdge(EdgeInterface edge, double angle) {
		return addEdge(new Connection<T, EdgeInterface>((T) this,edge,angle));
	}
	public boolean addArea(AreaInterface area, double angle) {
		return addArea(new Connection<T, AreaInterface>((T) this, area, angle));
	}
	
	public boolean hasPoint(PointInterface point) {
		return getPointConnection(point) != null;
	}
	public boolean hasEdge(EdgeInterface edge) {
		return getEdgeConnection(edge) != null;
	}
	public boolean hasArea(AreaInterface area) {
		return getAreaConnection(area) != null;
	}
	
	public boolean hasPoint(double angle) {
		return getPointConnection(angle)!=null;
	}
	public boolean hasEdge(double angle) {
		return getEdgeConnection(angle)!=null;
	}
	public boolean hasArea(double angle) {
		return getAreaConnection(angle)!=null;
	}
	
	public ConnectionInterface<T, PointInterface> getPointConnection(PointInterface point) {
		if(point == null) {return null;} //for validation
		boolean cond = false;
		ConnectionInterface<T, PointInterface> pointConnectionReturn = null;
		Iterator<ConnectionInterface<T, PointInterface>> iterator = getPointConnections().iterator();
		while (!cond&&iterator.hasNext()) {
			ConnectionInterface<T, PointInterface> pointConnection=  iterator.next();
			if(point.equals((Point)pointConnection.getOther(this))) {
				cond = true;
				pointConnectionReturn = pointConnection;
			}
		}
		
		return pointConnectionReturn;	}
	public ConnectionInterface<T, EdgeInterface> getEdgeConnection(EdgeInterface edge) {
		if(edge == null) {return null;} //for validation
		boolean cond = false;
		ConnectionInterface<T, EdgeInterface> edgeConnectionReturn = null;
		Iterator<ConnectionInterface<T, EdgeInterface>> iterator = getEdgeConnections().iterator();
		while (!cond&&iterator.hasNext()) {
			ConnectionInterface<T, EdgeInterface> edgeConnection =  iterator.next();
			if(edge.equals((Edge)edgeConnection.getOther(this))) {
				cond = true;
				edgeConnectionReturn = edgeConnection;
			}
		}
		
		return edgeConnectionReturn;	}
	public ConnectionInterface<T, AreaInterface> getAreaConnection(AreaInterface area) {
		if(area == null) {return null;} //for validation
		boolean cond = false;		
		ConnectionInterface<T, AreaInterface> areaConnectionReturn = null;
		Iterator<ConnectionInterface<T, AreaInterface>> iterator = getAreaConnections().iterator();
		while (!cond&&iterator.hasNext()) {
			ConnectionInterface<T, AreaInterface> areaConnection =  iterator.next();
			if(area.equals((Area)areaConnection.getOther(this))) {
				cond = true;
				areaConnectionReturn = areaConnection;
			}
		}
		
		return areaConnectionReturn;	}
	
	public ConnectionInterface<T, PointInterface> getPointConnection(double angle) {
		if(angle<0) {return null;} //for validation
		ConnectionInterface<T, PointInterface> returnPointConnection = null;
		boolean cond = false;
		Iterator<ConnectionInterface<T, PointInterface>> iterator = getPointConnections().iterator();
		while (!cond&&iterator.hasNext()) {
			ConnectionInterface<T, PointInterface> connection =  iterator.next();
			if(connection.getAngleToOther(this)==angle) {
				cond = true;
				returnPointConnection =  connection;
			}
		}
		
		return returnPointConnection;	}
	public ConnectionInterface<T, EdgeInterface> getEdgeConnection(double angle) {
		if(angle<0) {return null;} //for validation
		ConnectionInterface<T, EdgeInterface> returnEdgeConnection = null;
		boolean cond = false;
		Iterator<ConnectionInterface<T, EdgeInterface>> iterator = getEdgeConnections().iterator();
		while (!cond&&iterator.hasNext()) {
			ConnectionInterface<T, EdgeInterface> connection =  iterator.next();
			if(connection.getAngleToOther(this)==angle) {
				cond = true;
				returnEdgeConnection =  connection;
			}
		}
		
		return returnEdgeConnection;	}
	public ConnectionInterface<T, AreaInterface> getAreaConnection(double angle) {
		if(angle<0) {return null;} //for validation
		ConnectionInterface<T, AreaInterface> returnAreaConnection = null;
		boolean cond = false;
		Iterator<ConnectionInterface<T, AreaInterface>> iterator = getAreaConnections().iterator();
		while (!cond&&iterator.hasNext()) {
			ConnectionInterface<T, AreaInterface> connection =  iterator.next();
			if(connection.getAngleToOther(this)==angle) {
				cond = true;
				returnAreaConnection =  connection;
			}
		}
		
		return returnAreaConnection;	}
	
	public PointInterface getPoint(double angle) {
		return (PointInterface) getPointConnection(angle).getOther(this);
	}
	public EdgeInterface getEdge(double angle) {
		return (EdgeInterface) getEdgeConnection(angle).getOther(this);
	}
	public AreaInterface getArea(double angle) {
		return (AreaInterface) getAreaConnection(angle).getOther(this);
	}
	
	public boolean equals(AreaPointInterface object) {
		if(object == null) {return false;}
		return ((AreaPointContainer<PointInterface>) object).getId()==this.getId();
	}
}
