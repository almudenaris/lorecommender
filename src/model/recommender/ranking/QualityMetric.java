package model.recommender.ranking;

import java.util.Vector;

import model.datatransferobject.LO;

/**
 * 
 * @author Almudena Ruiz-Iniesta (almudenaris(at)gmail.com)
 *
 */
public abstract class QualityMetric {
	
		
	//este método lo debe implementar cada atributo
	public double computeQuality(LO lo){return 0;}
	
	public void add(QualityMetric q, double w){}
	
	public abstract boolean init();
	public abstract void end();
	
	// modificar vector de pesos
	public void setWeights(Vector<Double> weight){}
}
