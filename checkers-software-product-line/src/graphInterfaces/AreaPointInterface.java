package graphInterfaces;

public interface AreaPointInterface {
	
	/**
	 * method for addition of point with given angle
	 * @param point to be added
	 * @param angle from this area/point to given point
	 * @return if the addition done correctly
	 */
	public boolean addPoint(PointInterface point, double angle);
	/**
	 * method for addition of edge with given angle
	 * @param edge to be added
	 * @param angle from this area/point to given edge
	 * @return if the addition done correctly
	 */
	public boolean addEdge(EdgeInterface edge, double angle);
	/**
	 * method for addition of area with given angle
	 * @param area to be added
	 * @param angle from this area/point to given area
	 * @return if the addition done correctly
	 */
	public boolean addArea(AreaInterface area, double angle);
	
	/////////////////////////////////////////////////////////
	
	/**
	 * method for controlling the belonging of given point
	 * @param point to be controlled for belonging
	 * @return this area/point has given point
	 */
	public boolean hasPoint(PointInterface area);
	/**
	 * method for controlling the belonging of given edge
	 * @param edge to be controlled for belonging
	 * @return this area/point has given edge
	 */
	public boolean hasEdge(EdgeInterface edge);
	/**
	 * method for controlling the belonging of given area
	 * @param area to be controlled for belonging
	 * @return this area/point has given area
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
	 * method for searching edge at given angle
	 * @param angle to be controlled
	 * @return is there edge at given angle
	 */
	public boolean hasEdge(double angle);
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
	 * method for finding edge at given angle
	 * @param angle to be controlled
	 * @return edge at given angle
	 */
	public EdgeInterface getEdge(double angle);
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
	public boolean equals(AreaPointInterface object);
}
