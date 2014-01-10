package model.recommender.retrieval;


import java.util.List;

import model.datatransferobject.LO;
import model.datatransferobject.QueryTO;


/**
 * 
 * @author Almudena Ruiz-Iniesta
 *
 */
public abstract class RetrievalStage {
	
	public abstract List<LO> retrieve(QueryTO consulta);
	public abstract boolean init();
	public abstract void end();
}
