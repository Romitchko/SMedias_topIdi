package fr.epsi.service;

import java.util.List;

import fr.epsi.entite.Innovation;

public interface InnovationService {
	
	Innovation getInnovation(Long id);
	
	List<Innovation> getListInnovations();
	
	List<Innovation> getInnovationsUpToDown();
	
	List<Innovation> getInnovationsDownToUp();
	
	void addInnovation(Innovation innovation);

}