package graphInterfaces;

public interface EdgeInterface {

	/**
	 * method for returning left point connection of this edge
	 * @return left point connection of this edge
	 */
	public ConnectionInterface<EdgeInterface, PointInterface> getPointLeftConnection();
	/**
	 * method for returning right point connection of this edge
	 * @return right point connection of this edge
	 */
	public ConnectionInterface<EdgeInterface, PointInterface> getPointRightConnection();
	/**
	 * method for returning upper area connection of this edge
	 * @return upper area connection of this edge
	 */
	public ConnectionInterface<EdgeInterface, AreaInterface> getAreaUpConnection();
	/**
	 * method for returning down area connection of this edge
	 * @return down area connection of this edge
	 */
	public ConnectionInterface<EdgeInterface, AreaInterface> getAreaDownConnection();
	
	/////////////////////////////////////////////////////////

	/**
	 * method for returning left point of this edge
	 * @return left point of this edge
	 */
	public PointInterface getPointLeft();
	/**
	 * method for returning right point of this edge
	 * @return right point of this edge
	 */
	public PointInterface getPointRight();
	/**
	 * method for returning upper area  of this edge
	 * @return upper area  of this edge
	 */
	public AreaInterface getAreaUp();
	/**
	 * method for returning down area of this edge
	 * @return down area of this edge
	 */
	public AreaInterface getAreaDown();
	
	/////////////////////////////////////////////////////////
	
	/**
	 * method for controlling the belonging of given point
	 * @param point to be controlled for belonging
	 * @return this edge has given point
	 */
	public boolean hasPoint(PointInterface point);
	/**
	 * method for controlling the belonging of given area
	 * @param area to be controlled for belonging
	 * @return this edge has given area
	 */
	public boolean hasArea(AreaInterface area);
	
	/////////////////////////////////////////////////////////
	
	/**
	 * method for searching point at given angle
	 * @param angle to be controlled
	 * @return is there point at given angle
	 */
	public boolean hasPoint(double angle);
	/**
	 * method for searching area at given angle
	 * @param angle to be controlled
	 * @return is there area at given angle
	 */
	public boolean hasArea(double angle);
	
	/////////////////////////////////////////////////////////
	
	/**
	 * method for finding point at given angle
	 * @param angle to be controlled
	 * @return point at given angle
	 */
	public PointInterface getPoint(double angle);
	/**
	 * method for finding area at given angle
	 * @param angle to be controlled
	 * @return area at given angle
	 */
	public AreaInterface getArea(double angle);
	
	/////////////////////////////////////////////////////////
	
	/**
	 * method for equality
	 * @param object to be controlled
	 * @return if the given object is equal to this area/point
	 */
	public boolean equals(EdgeInterface edge);

}
