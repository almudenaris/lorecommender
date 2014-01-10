package model.recommender.querygenerator;

import model.datatransferobject.QueryTO;

/**
 * 
 * @author Almudena Ruiz-Iniesta (almudenaris(at)gmail.com)
 *
 */
public abstract class QueryElicitationStage {	
	
	public abstract boolean init();
	
	public abstract void end();
	
	public abstract QueryTO generateQuery();
	
	
}
