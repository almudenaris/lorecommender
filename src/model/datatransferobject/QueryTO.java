package model.datatransferobject;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase que permite el paso de datos entre la vista y el modelo
 * Almacena la consulta que realiza el usuario en una lista
 * 
 * @author Almudena Ruiz-Iniesta (almudenaris(at)gmail.com)
 *
 */
public class QueryTO {
	
	/** lista de String que almacena todos los términos de la consulta */
	private ArrayList<String> consulta;

	// identificador para saber qué consulta es
	private String id;
	
	/**
	 * Constructora por copia
	 * @param consulta
	 */
	public QueryTO(ArrayList<String> consulta) {
		super();
		this.consulta = consulta;
	}
	/**
	 * Constructora por defecto. Inicializa la lista
	 */
	public QueryTO(){
		consulta = new ArrayList<String>();
	}
	public QueryTO(List<String> subList) {
		this.consulta = new ArrayList<String>();
		this.consulta.addAll(subList);
	}
	public QueryTO(QueryTO listaConceptosQuery) {
		this.consulta = listaConceptosQuery.consulta;
		this.id = listaConceptosQuery.id;
	}
	/**
	 * Añade un nuevo concepto a la consulta
	 * @param concepto
	 */
	public void addConcepto(String concepto){
		if (! consulta.contains(concepto))
			consulta.add(concepto);
	}
	/**
	 * Devuelve la lista con todos los conceptos de la consulta
	 * @return lista de conceptos
	 */
	public ArrayList<String> getConsulta() {
		return consulta;
	}
	/**
	 * Modifica la lista de consulta por una nueva
	 * @param consulta
	 */
	public void setConsulta(ArrayList<String> consulta) {
		this.consulta = consulta;
	}
	
	public String toString(){
		return "[query: "+consulta.toString()+"]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean equals(Object q2){		
		if(!(q2 instanceof QueryTO))
			return false;
		else{
			QueryTO otherQuery = (QueryTO) q2;
			if (otherQuery.getConsulta().size() != this.consulta.size())
				return false;
			if (otherQuery.getConsulta().containsAll(consulta))
				return true;
		}			
		return false;
	}
}
