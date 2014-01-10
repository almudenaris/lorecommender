package model.recommender;

import java.util.List;

import model.datatransferobject.LO;
import model.datatransferobject.QueryTO;
import model.datatransferobject.TransferCRecuperado;
import model.recommender.filtering.Filter;
import model.recommender.filtering.FilteringStage;
import model.recommender.querygenerator.QueryElicitationStage;
import model.recommender.ranking.QualityMetric;
import model.recommender.ranking.RatingStage;
import model.recommender.retrieval.RetrievalStage;
import model.recommender.selection.SelectionStage;

/**
 * Main CBRecommender Class<br>
 * Clase principal del framework que contiene toda la infraestructura necesaria
 * para implementar un sistema de recomendación. <br>
 * Es la responsable de controlar el flujo de ejecución.
 * Contiene referencias a todas las etapas del proceso de recomendación.<br>
 * 
 * @author Almudena Ruiz-Iniesta (almudenaris(at)gmail.com)
 */

public abstract class KBRecommender {
	
	/** Etrapa de recuperación */
	protected RetrievalStage retrieval;
	/** Etapa de filtrado */
	protected FilteringStage filtering;
	/** Filtro utilizado en la etapa de filrado */
	protected Filter filter;
	/** Etapa de valoración */
	protected RatingStage rating;
	/** Etapa de selección */
	protected SelectionStage selection;
	/** Etapa de elaboración de consulta */
	protected QueryElicitationStage queryElicitation;
	/** Métrica de calidad empleada en la etapa de valoración*/
	protected QualityMetric qualityMetric;
	
	/** Conjunto final de elementos recomendados */
	TransferCRecuperado conjuntoPropuesto;
	
	/** Atributo que nos indica el umbral de progreso en el camino de aprendizaje */
	public static final double umbralProgreso = 5.0;
	

	/** Crea un objeto CBRecommender. Actualmente no hace nada. */
	public KBRecommender(){
		
	}
	

	//************** Template Methods ****************/
	
	/**
	 * Inicializa CBRecommender con la creación de las distintas etapas de recomendación
	 * y la configuración de la métrica de calidad. 
	 */
	public boolean init(){
		// ***  creación de etapas **** //
		queryElicitation = createQueryElicitationStage();
		retrieval = createRetrievalStage();
		filtering = new FilteringStage();
		filter = createFilter();
		qualityMetric = createQualityMetric();
		rating = new RatingStage();
		rating.configureQuality(qualityMetric);
		selection = createSelectionStage();
		return true;
	}
	/**
	 * Inicializa las distintas etapas del proceso de recomendación.<br>
	 * 
	 * @return devuelve true si todo el proceso de inicialización ha ido correctamente, false en caso contrario
	 */
	public boolean initRecommendation(){
		configureRecommendation();
		if (queryElicitation.init()){
			// hay que esperar a que termine la elección de consulta
			//cuando hay UI, sino no es necesaria
			//Blackboard.getInstance().setParam("query", queryElicitation.generateQuery());
			if (filter == null)
			return 	qualityMetric.init() &&
				rating.init() && retrieval.init() && selection.init() && qualityMetric.init();
			else
				return filter.init() && filtering.init() && qualityMetric.init() &&
				rating.init() && retrieval.init() && selection.init() && qualityMetric.init();
		}
		return false;
	}
	
	/**
	 * Método principal que ejecuta la recomendación siguiendo el flujo de las cinco etapas
	 * 
	 * @return conjunto de elementos recomendados
	 */
	public TransferCRecuperado recommend(){
		//¿mejorar la comprobación de errores?
		List<LO> conjuntoCandidatos;		
		//recuperamos
		conjuntoCandidatos = retrievalStage();
		if (conjuntoCandidatos != null){
			//filtramos
			conjuntoCandidatos = filteringStage(conjuntoCandidatos);
			// ordenamos el conjunto
			conjuntoCandidatos = rankingStage(conjuntoCandidatos);
			//seleccionamos los mejores			
			List<LO> conjuntoFinal = selectionStage(conjuntoCandidatos);
			return  new TransferCRecuperado(conjuntoFinal);
			//return conjuntoPropuesto;
		}
		else{
			System.out.println("Error: Se esperaba un conjunto recuperado en: "+ this.getClass().toString());
			return null;
		}
	}
	/**
	 * Finaliza CBRecommender invocando a todos los métodos finales de las disintas etapas
	 * de recomendación.
	 */
	public void endRecommendation(TransferCRecuperado listRecommendation){
		finishRecommendation(listRecommendation);
		queryElicitation.end();		
		retrieval.end();
		filtering.end();
		rating.end();
		selection.end();		
	}
	
	/* ***************** Hooks *************************
	 * métodos que debe implementar cada nuevo recomendador que creemos	 
	 ****************************************************/
	
	public abstract QueryElicitationStage createQueryElicitationStage();
	public abstract RetrievalStage createRetrievalStage();
	public abstract SelectionStage createSelectionStage();
	public abstract Filter createFilter();
	public abstract QualityMetric createQualityMetric();
	public abstract void configureRecommendation();
	public abstract void finishRecommendation(TransferCRecuperado listRecommendation);
	
	
	/**
	 * Devuelve el conjunto de elementos recomendados 
	 */
	public TransferCRecuperado getConjuntoPropuesto() {
		return conjuntoPropuesto;
	}	
	
		
	//estrategias que deben implementar cada uno de los recomendadores que queramos definir
	protected List<LO> retrievalStage(){
		QueryTO  query = (QueryTO) Blackboard.getInstance().getParam("query");
		if (query == null || query.getConsulta()== null)
			return null;
		else 
			return retrieval.retrieve(query);		
	}

	protected List<LO> filteringStage(List<LO> conjuntoCandidatos){		
		return filtering.filter(conjuntoCandidatos, filter);
	}
	
	protected List<LO> rankingStage(List<LO> setCandidates){		
		return rating.rate(setCandidates);		
	}	
	
	protected List<LO> selectionStage(List<LO> setCandidates){
		return selection.select(setCandidates);		
	}
	
	
				
	
	
}
