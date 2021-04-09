package fr.epsi.service;

import fr.epsi.entite.Innovation;
import fr.epsi.entite.Utilisateur;
import fr.epsi.entite.Vote;

public interface VoteService {
	Long getTotalVotes(Innovation innovation);
	
	void like(Vote vote, Innovation innovation);
	
	void dislike(Vote vote, Innovation innovation);
	
	void deleteVote(Vote vote, Innovation innovation);
	
	Boolean checkIfAlreadyVoted(Utilisateur utilisateur, Innovation innovation);
	
	Vote getUtilisateurVote(Innovation innovation, Utilisateur utilisateur);
	
	Long getLikes(Innovation innovation);
	
	Long getDislikes(Innovation innovation);
}
