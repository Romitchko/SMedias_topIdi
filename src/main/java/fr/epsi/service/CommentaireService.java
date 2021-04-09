package fr.epsi.service;

import java.util.List;

import fr.epsi.entite.Commentaire;
import fr.epsi.entite.Innovation;

public interface CommentaireService {
	Commentaire getCommentaire(Long id);
	
	List<Commentaire> getListCommentaires(Innovation innovation);
	
	void addCommentaire(Commentaire commentaire);
	
	void deleteCommentaire(Commentaire commentaire);
}
