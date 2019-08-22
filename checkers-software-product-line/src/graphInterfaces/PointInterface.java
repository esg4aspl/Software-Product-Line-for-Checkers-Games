package graphInterfaces;

public interface PointInterface extends AreaPointInterface {
	/**
	 * method for addition of a connection to a point
	 * @param connection, which has this point, to be added
	 * @return if the addition done correctly
	 */
	public boolean addPoint(ConnectionInterface<PointInterface, PointInterface> connection);
	/**
	 * method for addition of a connection to a edge
	 * @param connection, which has this point, to be added
	 * @return if the addition done correctly
	 */
	public boolean addEdge(ConnectionInterface<PointInterface, EdgeInterface> connection);
	/**
	 * method for addition of a connection to a area
	 * @param connection, which has this point, to be added
	 * @return if the addition done correctly
	 */
	public boolean addArea(ConnectionInterface<PointInterface, AreaInterface> connection);
	
	/////////////////////////////////////////////////////////
	
	/**
	 * method for finding connection with given point
	 * @param point to be controlled
	 * @return connection to given point
	 */
	public ConnectionInterface<PointInterface, PointInterface> getPointConnection(PointInterface point);
	/**
	 * method for finding connection with given edge
	 * @param edge to be controlled
	 * @return connection to given edge
	 */
	public ConnectionInterface<PointInterface, EdgeInterface> getEdgeConnection(EdgeInterface edge);
	/**
	 * method for finding connection with given area
	 * @param area to be controlled
	 * @return connection to given area
	 */
	public ConnectionInterface<PointInterface, AreaInterface> getAreaConnection(AreaInterface area);
	
	/////////////////////////////////////////////////////////
	
	/**
	 * method finding for connection to the point at given angle
	 * @param angle to be controlled
	 * @return connection to the point at given angle
	 */
	public ConnectionInterface<PointInterface, PointInterface> getPointConnection(double angle);
	/**
	 * method finding for connection to the edge at given angle
	 * @param angle to be controlled
	 * @return connection to the edge at given angle
	 */
	public ConnectionInterface<PointInterface, EdgeInterface> getEdgeConnection(double angle);
	/**
	 * method finding for connection to the area at given angle
	 * @param angle to be controlled
	 * @return connection to the area at given angle
	 */
	public ConnectionInterface<PointInterface, AreaInterface> getAreaConnection(double angle);
}
