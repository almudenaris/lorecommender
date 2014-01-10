package model.recommender.filtering;

import model.datatransferobject.LO;

/**
 * 
 * @author Almudena Ruiz-Iniesta (almudenaris(at)gmail.com)
 *
 */
public interface Filter {
	public boolean init();
	public void end();
	public boolean filter(LO theLO);
}
