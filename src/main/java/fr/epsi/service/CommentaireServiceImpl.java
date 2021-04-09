package fr.epsi.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.Dao.CommentaireDao;
import fr.epsi.Dao.CommentaireDaoImpl;
import fr.epsi.entite.Commentaire;
import fr.epsi.entite.Innovation;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CommentaireServiceImpl implements CommentaireService {
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	public Commentaire getCommentaire(Long commentaireId) {
		CommentaireDao commentaireDao = new CommentaireDaoImpl(em, utx);
		return commentaireDao.getCommentaire(commentaireId);
	}
	
	public List<Commentaire> getListCommentaires(Innovation innovation){
		CommentaireDao commentaireDao = new CommentaireDaoImpl(em, utx);
		return commentaireDao.getListCommentaires(innovation);
	}
	
	public void addCommentaire(Commentaire commentaire) {
		CommentaireDao commentaireDao = new CommentaireDaoImpl(em, utx);
		commentaireDao.addCommentaire(commentaire);
	}
	
	public void deleteCommentaire(Commentaire commentaire) {
		CommentaireDao commentaireDao = new CommentaireDaoImpl(em, utx);
		commentaireDao.deleteCommentaire(commentaire);
	}
}
