package fr.epsi.Dao;

import fr.epsi.entite.Utilisateur;

public interface UtilisateurDao {
	void addUtilisateur(Utilisateur utilisateur);

	boolean checkUtilisateur(String email, String pass);

	boolean checkEmailUtilisateur(String email);

	Utilisateur getUtilisateur(Long id);
	
	Utilisateur getUtilisateur(String email);
}
