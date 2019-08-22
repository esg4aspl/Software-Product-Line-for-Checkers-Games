package graphInterfaces;



public interface AreaInterface extends AreaPointInterface{
	/**
	 * method for addition of a connection to a point
	 * @param connection, which has this area, to be added
	 * @return if the addition done correctly
	 */
	public boolean addPoint(ConnectionInterface<AreaInterface, PointInterface> connection);
	/**
	 * method for addition of a connection to an edge
	 * @param connection, which has this area, to be added
	 * @return if the addition done correctly
	 */
	public boolean addEdge(ConnectionInterface<AreaInterface, EdgeInterface> connection);
	/**
	 * method for addition of a connection to an area
	 * @param connection, which has this area, to be added
	 * @return if the addition done correctly
	 */
	public boolean addArea(ConnectionInterface<AreaInterface, AreaInterface> connection);
	
	/////////////////////////////////////////////////////////
	
	/**
	 * method for finding connection with given point
	 * @param point to be controlled
	 * @return connection to given point
	 */
	public ConnectionInterface<AreaInterface, PointInterface> getPointConnection(PointInterface point);
	/**
	 * method for finding connection with given edge
	 * @param edge to be controlled
	 * @return connection to given edge
	 */
	public ConnectionInterface<AreaInterface, EdgeInterface> getEdgeConnection(EdgeInterface edge);
	/**
	 * method for finding connection with given area
	 * @param area to be controlled
	 * @return connection to given area
	 */
	public ConnectionInterface<AreaInterface, AreaInterface> getAreaConnection(AreaInterface area);
	
	/////////////////////////////////////////////////////////
	
	/**
	 * method finding for connection to the point at given angle
	 * @param angle to be controlled
	 * @return connection to the point at given angle
	 */
	public ConnectionInterface<AreaInterface, PointInterface> getPointConnection(double angle);
	/**
	 * method finding for connection to the edge at given angle
	 * @param angle to be controlled
	 * @return connection to the edge at given angle
	 */
	public ConnectionInterface<AreaInterface, EdgeInterface> getEdgeConnection(double angle);
	/**
	 * method finding for connection to the area at given angle
	 * @param angle to be controlled
	 * @return connection to the area at given angle
	 */
	public ConnectionInterface<AreaInterface, AreaInterface> getAreaConnection(double angle);

}
