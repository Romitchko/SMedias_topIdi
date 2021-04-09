package fr.epsi.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.Dao.VoteDao;
import fr.epsi.Dao.VoteDaoImpl;
import fr.epsi.entite.Innovation;
import fr.epsi.entite.Utilisateur;
import fr.epsi.entite.Vote;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class VoteServiceImpl implements VoteService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;

	public Long getTotalVotes(Innovation innovation) {
		VoteDao voteDao = new VoteDaoImpl(em, utx);
		return voteDao.getTotalVotes(innovation);
	}

	public void like(Vote vote, Innovation innovation) {
		VoteDao voteDao = new VoteDaoImpl(em, utx);
		voteDao.like(vote, innovation);
	}

	public void dislike(Vote vote, Innovation innovation) {
		VoteDao voteDao = new VoteDaoImpl(em, utx);
		voteDao.dislike(vote, innovation);
	}
	
	public void deleteVote(Vote vote, Innovation innovation) {
		VoteDao voteDao = new VoteDaoImpl(em, utx);
		voteDao.deleteVote(vote, innovation);
	}

	public Boolean checkIfAlreadyVoted(Utilisateur utilisateur, Innovation innovation) {
		VoteDao voteDao = new VoteDaoImpl(em, utx);
		return voteDao.checkIfAlreadyVoted(utilisateur, innovation);
	}

	public Vote getUtilisateurVote(Innovation innovation, Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		VoteDao voteDao = new VoteDaoImpl(em, utx);
		return voteDao.getUtilisateurVote(innovation, utilisateur);
	}

	public Long getLikes(Innovation innovation) {
		VoteDao voteDao = new VoteDaoImpl(em, utx);
		return voteDao.getLikes(innovation);
	}
	
	public Long getDislikes(Innovation innovation) {
		VoteDao voteDao = new VoteDaoImpl(em, utx);
		return voteDao.getDislikes(innovation);
	}

}
