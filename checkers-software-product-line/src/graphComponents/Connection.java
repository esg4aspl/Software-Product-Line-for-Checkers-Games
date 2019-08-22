package graphComponents;

import graphInterfaces.ConnectionInterface;

public class Connection<T1,T2> implements ConnectionInterface<T1, T2>{
	private T1 object1;
	private T2 object2;
	private double angle1to2;
	public Connection(T1 object1, T2 object2, double angle1to2) {
		checkValidation(object1,object2,angle1to2);
		this.object1 = object1;
		this.object2 = object2;
		this.angle1to2 = angle1to2%360;
	}
	

	private void checkValidation(T1 object1, T2 object2, double angle1to2) {
		if (object1 == null ||
			object2 == null ||
			object1.equals(object2)||
			angle1to2<0)
			throw new IllegalArgumentException();
		
	}


	private T1 getObject1() {
		return object1;
	}
	private T2 getObject2() {
		return object2;
	}
	private double getAngle1to2() {
		return angle1to2;
	}
	
	public boolean hasConnection(Object object) {
		boolean cond1 = false;
		boolean cond2 = false;
		
		try {
			cond1 =  getObject1().equals((T1)object);
		}catch (ClassCastException e) {
			// TODO: handle exception
		}
		try {
			cond2 =  getObject2().equals((T2)object);;
		}catch (ClassCastException e) {
			// TODO: handle exception
		}
		return cond1||cond2;
	}
	
	public double getAngleToOther(Object object) {
		T1 objT1 = null;
		T2 objT2 = null;
		double angle = -1;
		try {
			objT1 = (T1)object;
		}catch (ClassCastException e) {
			// TODO: handle exception
		}
		try {
			objT2 =  (T2)object;
		}catch (ClassCastException e) {
			// TODO: handle exception
		}
		
		if(objT1!=null && objT1.equals(getObject1())) {
			angle = getAngle1to2();
			
		}else if (objT2!=null && objT2.equals(getObject2())){
			angle = (getAngle1to2()+180)%360;
		}
		
		return angle;
	}
	
	public Object getOther(Object object) {
		T1 objT1 = null;
		T2 objT2 = null;
		Object objReturn = null;
		try {
			objT1 = (T1)object;
		}catch (ClassCastException e) {
			// TODO: handle exception
		}
		try {
			objT2 =  (T2)object;
		}catch (ClassCastException e) {
			// TODO: handle exception
		}
		
		if(objT1!=null && objT1.equals(getObject1())) {
			objReturn = getObject2();
			
		}else if (objT2!=null && objT2.equals(getObject2())){
			objReturn = getObject1();
		}
		return objReturn;
	}
	
	public boolean equals(ConnectionInterface connection) {
		if(connection == null) {return false;}	//for validation
		boolean cond0 = connection.hasConnection(getObject1());
		if(cond0) {
			boolean cond1 = connection.getOther(object1).equals(getObject2());
			boolean con2 = connection.getAngleToOther(getObject1()) == getAngle1to2();
			return cond1&&con2;
		}else {
			return false;
		}			
	}
	public Connection<T2, T1> createReverseConnection() {
		return new Connection<T2, T1>(object2, object1, (angle1to2+180)%360);
		
	}
	public String toString() {
		return "Connection from object1 (" + object1 + ") to object2 (" + object2 + ") with angle " + angle1to2 + ".";
	}
}
