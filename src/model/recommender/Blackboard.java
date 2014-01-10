package model.recommender;

/**
 * 
 * @author Almudena Ruiz-Iniesta (almudenaris(at)gmail.com)
 *
 */
public abstract class Blackboard {

	private static BlackboardImp instance;

	public static Blackboard getInstance(){
		if (instance == null)
			instance = new BlackboardImp();
		return instance;			
	}
	
	public abstract void setParam(String id,Object value);
	public abstract Object getParam(String id);
}
