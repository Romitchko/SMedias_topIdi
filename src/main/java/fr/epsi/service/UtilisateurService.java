package fr.epsi.service;

import fr.epsi.entite.Utilisateur;

public interface UtilisateurService {
		void addUtilisateur(Utilisateur utilisateur);
		
		boolean checkUtilisateur(String email, String pass);

		boolean checkEmailUtilisateur(String email);

		Utilisateur getUtilisateur(Long userId);
		
		
		Utilisateur getUtilisateur(String email);

}