package fr.epsi.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fr.epsi.entite.Commentaire;
import fr.epsi.entite.Innovation;

public class CommentaireDaoImpl implements CommentaireDao {
	private EntityManager em;
	private UserTransaction utx;

	public CommentaireDaoImpl(EntityManager em, UserTransaction utx) {
		this.em = em;
		this.utx = utx;
	}
	
	public Commentaire getCommentaire(Long commentaireId) {
		return em.find(Commentaire.class, commentaireId);
	}

	public List<Commentaire> getListCommentaires(Innovation innovation) {
		return em.createQuery("SELECT c FROM Commentaire c JOIN c.utilisateur  WHERE c.innovation = :innovation ORDER BY c.createdAt ASC", Commentaire.class).setParameter("innovation", innovation).getResultList();
	}

	public void addCommentaire(Commentaire commentaire) {
		try {
			utx.begin();
			em.persist(commentaire);
			utx.commit();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public void deleteCommentaire(Commentaire commentaire) {
		try {
		    utx.begin();
		    Query  query = em.createQuery("DELETE FROM Comment c WHERE c.id = :id");
			query.setParameter("id", commentaire.getId());
			query.executeUpdate();
			utx.commit();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

