package model.recommender;

import java.util.Hashtable;

/**
 * 
 * @author Almudena Ruiz-Iniesta (almudenaris(at)gmail.com)
 *
 */
public class BlackboardImp extends Blackboard {	
	
	private Hashtable<String,Object> params;
	
	public BlackboardImp(){
		params = new Hashtable<String, Object>();
	}
	
	public void setParam(String id, Object value){
		params.put(id, value);
	}
	
	public Object getParam(String id){
		return params.get(id);
	}
}
