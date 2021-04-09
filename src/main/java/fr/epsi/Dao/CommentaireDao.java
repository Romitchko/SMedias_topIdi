package fr.epsi.Dao;

import java.util.List;

import fr.epsi.entite.Commentaire;
import fr.epsi.entite.Innovation;

public interface CommentaireDao {
	Commentaire getCommentaire(Long commentaireId);
	
	List<Commentaire> getListCommentaires(Innovation innovation);
	
	void addCommentaire(Commentaire commentaire);
	
	void deleteCommentaire(Commentaire commentaire);
}