package model.recommender.filtering;

import java.util.List;

import model.datatransferobject.LO;

/**
 * 
 * @author Almudena Ruiz-Iniesta (almudenaris(at)gmail.com)
 *
 */
public class FilteringStage {
	
	public List<LO> filter(List<LO> theCandidates, Filter filter){
		LO theLO;				
		int i = 0;
		if (filter != null){
			while (i<theCandidates.size()){		
				theLO = theCandidates.get(i);
				if (filter.filter(theLO)) 
					theCandidates.remove(i);
				else i++;
			}
		}
		return theCandidates;
	}
	
	public boolean init(){
		// ¿necesitamos algo?
		return true;
	}

	public void end() {
		// ¿hace falta algo?
		
	}

}
