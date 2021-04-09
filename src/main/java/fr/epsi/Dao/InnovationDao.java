package fr.epsi.Dao;

import java.util.List;

import fr.epsi.entite.Innovation;

public interface InnovationDao {
	Innovation getInnovation(Long id);	
		
		List<Innovation> getListInnovations();
		
		List<Innovation> getInnovationsUpToDown();
		
		List<Innovation> getInnovationsDownToUp();
		
		void addInnovation(Innovation innovation);
}
