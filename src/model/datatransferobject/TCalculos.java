package model.datatransferobject;

public class TCalculos {
	double mA;
	double similitud;
	double uP;
	/**
	 * 
	 */
	public TCalculos() {
		super();		
	}
	/**
	 * @param ma
	 */
	public TCalculos(double ma) {
		super();
		mA = ma;
	}
	/**
	 * @param ma
	 * @param up
	 */
	public TCalculos(double ma, double up) {
		super();
		mA = ma;
		uP = up;
	}
	/**
	 * @return the mA
	 */
	public double getMA() {
		return mA;
	}
	/**
	 * @return the similitud
	 */
	public double getSimilitud() {
		return similitud;
	}
	/**
	 * @return the uP
	 */
	public double getUP() {
		return uP;
	}
	/**
	 * @param ma the mA to set
	 */
	public void setMA(double ma) {
		mA = ma;
	}
	/**
	 * @param similitud the similitud to set
	 */
	public void setSimilitud(double similitud) {
		this.similitud = similitud;
	}
	/**
	 * @param up the uP to set
	 */
	public void setUP(double up) {
		uP = up;
	}
	/*
	public String toString(){
		return "mediaAritmética: "+mA+ " UtilidadPedagógica: "+uP+" Similitud: "+similitud;//+"\n";		
	}
	*/
	public String toString(){
		return "mA: "+mA+ "\t UP: "+uP+"\t Sim: "+similitud;		
	}
	
}
