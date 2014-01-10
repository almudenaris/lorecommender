package model.recommender.selection;

import java.util.List;

import model.datatransferobject.LO;

/**
 * Main Selection interface
 * Última etapa que interviene en el proceso de recomendación.
 * Se encarga de elaborar el conjunto final de elementos recomendados.
 * 
 * Cada recomendador debe implementar (o hacer uso de las existentes) una etapa de selección
 * 
 * @author Almudena Ruiz-Iniesta
 *
 */
public abstract class SelectionStage {
	/** 
	 * método encargado de la inicialización de la etapa
	 * tomarán valor aquellos parámetros necesarios
	 * 
	 * @return true si todo ha salido bien
	 */
	public abstract boolean init ();
	/**
	 * método encargado de realizar la selección de los candidatos que finalmente van a ser recomendados
	 * 
	 * @param setCandidates conjunto de elementos candidatos a ser recomendados. Vienen de la etapa anterior 
	 * con un valor de calidad asignado.
	 * @return lista de elementos recomendados.
	 */
	public abstract List<LO> select(List<LO> setCandidates);
	/**
	 * método final que se encarga de liberar la etapa y dejarla lista para la siguiente ejecución
	 */
	public abstract void end();
}
