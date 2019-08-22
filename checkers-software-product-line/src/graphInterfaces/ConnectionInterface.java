package graphInterfaces;


public interface ConnectionInterface<T1,T2> {
	/**
	 * negative angles used to report error
	 */

	/**
	 * method for controlling this connection has given object
	 * @param object to be controlled
	 * @return this connection has given object
	 */
	public boolean hasConnection(Object object);
	/**
	 * method for giving information about angle between this connection objects
	 * @param object is the one objects of this connection
	 * @return angle from given object to other object of this connection
	 */
	public double getAngleToOther(Object object);
	/**
	 * method for giving information about other object of this connection
	 * @param object is the one objects of this connection
	 * @return other object of this connection
	 */
	public Object getOther(Object object);
	/**
	 * method for creating an equivalent connection
	 * this method helps to giving a connection as a parameter for an method
	 * @return reversed connection of this connection
	 */
	public ConnectionInterface<T2, T1> createReverseConnection();
	/**
	 * method for equality
	 * @param connection to be controlled
	 * @return if the given connection is equal to this connection
	 */
	public boolean equals(ConnectionInterface connection);
}
