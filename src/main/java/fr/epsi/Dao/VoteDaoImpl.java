package fr.epsi.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fr.epsi.entite.Innovation;
import fr.epsi.entite.Utilisateur;
import fr.epsi.entite.Vote;

public class VoteDaoImpl implements VoteDao {
	private EntityManager em;
	private UserTransaction utx;
	
	public VoteDaoImpl(EntityManager entityManager, UserTransaction userTransaction) {
		this.em = entityManager;
		this.utx = userTransaction;
	}
	
	public Long getTotalVotes(Innovation innovation) {
		// TODO Auto-generated method stub
		return (long) em.createQuery("SELECT v FROM Vote v WHERE v.innovation = :innovation", Vote.class).setParameter("innovation", innovation).getMaxResults();
	}

	public void like(Vote vote, Innovation innovation) {
		try {
			utx.begin();
			em.persist(vote);
			Innovation _innovation = em.find(Innovation.class, innovation.getId());
			_innovation.setNumberOfVotes(_innovation.getNumberOfVotes() + 1);
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

	public void dislike(Vote vote, Innovation innovation) {
		try {
			utx.begin();
			em.persist(vote);
			Innovation _innovation = em.find(Innovation.class, innovation.getId());
			_innovation.setNumberOfVotes(_innovation.getNumberOfVotes() + 1);
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
	
	public void deleteVote(Vote vote, Innovation innovation) {
		try {
		    utx.begin();
		    Query  query = em.createQuery("DELETE FROM Vote v WHERE v.id = :id");
			query.setParameter("id", vote.getId());
			query.executeUpdate();
			Innovation _innovation = em.find(Innovation.class, innovation.getId());
			_innovation.setNumberOfVotes(_innovation.getNumberOfVotes() - 1);
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

	public Boolean checkIfAlreadyVoted(Utilisateur utilisateur, Innovation innovation) {
		Query  query = em.createQuery("SELECT v FROM Vote v WHERE v.utilisateur = :utilisateur AND v.innovation = :innovation", Vote.class);
		query.setParameter("utilisateur", utilisateur);
		query.setParameter("innovation", innovation);		
		List<Vote> vote = (List<Vote>) query.getResultList();
		return !vote.isEmpty();
	}

	public Vote getUtilisateurVote(Innovation innovation, Utilisateur utilisateur) {
		try {
			return (Vote) em.createQuery("SELECT v FROM Vote v WHERE v.innovation = :innovation AND v.utilisateur = :utilisateur", Vote.class)
					.setParameter("innovation", innovation)
					.setParameter("utilisateur", utilisateur)
					.getSingleResult();
		} catch (NoResultException e){
    		return null;
	    }
		
	}

	public Long getLikes(Innovation innovation) {
		return (Long) em.createQuery("SELECT COUNT(v) FROM Vote v WHERE v.innovation = :innovation AND v.vote = :vote")
				.setParameter("vote", 1)
				.setParameter("innovation", innovation)
				.getSingleResult();
	}
	
	public Long getDislikes(Innovation innovation) {
		return (Long) em.createQuery("SELECT COUNT(v) FROM Vote v WHERE v.innovation = :innovation AND v.vote = :vote")
				.setParameter("vote", 0)
				.setParameter("Innovation", innovation)
				.getSingleResult();
	}

}
