/**
 * 
 */
package model.datatransferobject;

import java.util.ArrayList;
import java.util.List;

import jcolibri.exception.NoApplicableSimilarityFunctionException;

import model.dao.LogicaOntologia;

/**
 * 
 * @author Almudena Ruiz-Iniesta (almudenaris(at)gmail.com)
 *
 */
public class TransferCRecuperado {
	
	private String id;
	/** lista de @TransferLO que almacena todos los términos de la consulta */
	private List<LO> conjuntoRecuperado;
	
	//valores medios del conjunto para estadísticas
	private double diversidadMedia;
	private double upMedia;
	private double maMedia;
	private double simMedia;
	private double calidadMedia;
	//atributo para calcular la disimilitud de la lista de recuperados (uso estadístico)
	private double disimilitud;
	
	/**
	 * Constructor por copia
	 * @param transfer a copiar
	 */
	public TransferCRecuperado(TransferCRecuperado transfer){
		conjuntoRecuperado = new ArrayList<LO>();
		conjuntoRecuperado.addAll(transfer.conjuntoRecuperado);
		diversidadMedia = transfer.diversidadMedia;
		upMedia = transfer.upMedia;
		maMedia = transfer.maMedia;
		simMedia = transfer.simMedia;
		calidadMedia = transfer.calidadMedia;
		disimilitud = transfer.disimilitud;
	}
	/**
	 * Constructor por copia
	 * @param conjuntoRecuperado lista de elementos recuperados
	 */
	public TransferCRecuperado(List<LO> conjuntoRecuperado) {
		super();		
		this.conjuntoRecuperado = null;
		this.conjuntoRecuperado = conjuntoRecuperado;
	}
	/**
	 * Constructor por defecto.
	 * Genera un conjunto vacío 
	 */
	public TransferCRecuperado() {
		conjuntoRecuperado = new ArrayList<LO>();
	}
	
	/**
	 * @return the disimilitud
	 */
	public double getDisimilitud() {
		return disimilitud;
	}

	/**
	 * @param disimilitud the disimilitud to set
	 */
	public void setDisimilitud(double disimilitud) {
		this.disimilitud = disimilitud;
	}


	/**
	 * @return the conjuntoRecuperado
	 */
	public List<LO> getConjuntoRecuperado() {
		return conjuntoRecuperado;
	}

	/**
	 * @param conjuntoRecuperado the conjuntoRecuperado to set
	 */
	public void setConjuntoRecuperado(ArrayList<LO> conjuntoRecuperado) {
		this.conjuntoRecuperado = conjuntoRecuperado;
	}

	

	/**
	 * @return the diversidadMedia
	 */
	public double getDiversidadMedia() {
		return diversidadMedia;
	}

	/**
	 * @param diversidadMedia the diversidadMedia to set
	 */
	public void setDiversidadMedia(double diversidadMedia) {
		this.diversidadMedia = diversidadMedia;
	}

	/**
	 * @return the upMedia
	 */
	public double getUpMedia() {
		return upMedia;
	}

	/**
	 * @param upMedia the upMedia to set
	 */
	public void setUpMedia(double upMedia) {
		this.upMedia = upMedia;
	}

	/**
	 * @return the maMedia
	 */
	public double getMaMedia() {
		return maMedia;
	}

	/**
	 * @param maMedia the maMedia to set
	 */
	public void setMaMedia(double maMedia) {
		this.maMedia = maMedia;
	}

	/**
	 * @return the simMedia
	 */
	public double getSimMedia() {
		return simMedia;
	}

	/**
	 * @param simMedia the simMedia to set
	 */
	public void setSimMedia(double simMedia) {
		this.simMedia = simMedia;
	}

	/**
	 * @param conjuntoRecuperado the conjuntoRecuperado to set
	 */
	public void setConjuntoRecuperado(List<LO> conjuntoRecuperado) {
		this.conjuntoRecuperado = conjuntoRecuperado;
	}

	

	@Override
	public String toString() {
		//String s = "valores medios del conjunto: \t\t\t"+upMedia+"\t"+simMedia+"\t"+calidadMedia+"\t"+disimilitud+"\n";
		String s = "[recommendation: "+id+"]"+conjuntoRecuperado.toString()+"]";	
		return s;
	}

	/**
	 * @return the calidadMedia
	 */
	public double getCalidadMedia() {
		return calidadMedia;
	}

	/**
	 * @param calidadMedia the calidadMedia to set
	 */
	public void setCalidadMedia(double calidadMedia) {
		this.calidadMedia = calidadMedia;
	}

	public void calculaDisimilitud(){
		/*
		 * The diversity of the recommendation list is defined as the 
		 * average dissimilarity between all pairs of items in the 
		 * recommendation list
		 */
		LogicaOntologia instancia = LogicaOntologia.dameInstancia();
		double disimilitudIntermedia = 0;
		for (int i=0; i<this.conjuntoRecuperado.size(); i++){
			for (int j=i; j<this.conjuntoRecuperado.size();j++){
				try {
					if (i!= j)
						disimilitudIntermedia += (1 - instancia.calculaSimilitud(this.conjuntoRecuperado.get(i).getConcepts(),
												this.conjuntoRecuperado.get(j).getConcepts()));
					
				} catch (NoApplicableSimilarityFunctionException e) {
					e.printStackTrace();
				}
			}
		}		  
		disimilitud = disimilitudIntermedia /((this.conjuntoRecuperado.size()*(this.conjuntoRecuperado.size()-1))/2);
	}
	
	public void calculaMedias(){
		calculaDisimilitud();
		//similitud media
		double simAux = 0, upAux = 0;
		for (LO i:conjuntoRecuperado){
			simAux += i.getSim();
			upAux += i.getUP();
		}
		simMedia = simAux/conjuntoRecuperado.size();
		upMedia = upAux/conjuntoRecuperado.size();
	}
	
	public void add(LO lo){
		if (this.conjuntoRecuperado != null)
			this.conjuntoRecuperado.add(lo);
		else{
			this.conjuntoRecuperado = new ArrayList<LO>();
			this.conjuntoRecuperado.add(lo);
		}
			
	}
	public int searchByID(LO loAux) {
		//sabemos que existe el LO, lo buscamos en la lista de recuperados
		for(int i=0; i<conjuntoRecuperado.size(); i++){
			LO lo = conjuntoRecuperado.get(i);
			if (lo.equals(loAux))
				return i;
		}
		return -1;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
