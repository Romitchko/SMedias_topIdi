package fr.epsi.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.Dao.UtilisateurDao;
import fr.epsi.Dao.UtilisateurDaoImpl;
import fr.epsi.entite.Utilisateur;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UtilisateurServiceImpl implements UtilisateurService {
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;

	public void addUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		UtilisateurDao utilisateurDao = new UtilisateurDaoImpl(em, utx);
		utilisateurDao.addUtilisateur(utilisateur);
	}

	public boolean checkUtilisateur(String email, String pass) {
		// TODO Auto-generated method stub
		UtilisateurDao utilisateurDao = new UtilisateurDaoImpl(em, utx);
		return utilisateurDao.checkUtilisateur(email, pass);
	}

	public boolean checkEmailUtilisateur(String email) {
		// TODO Auto-generated method stub
		UtilisateurDao userDao = new UtilisateurDaoImpl(em, utx);
		return userDao.checkEmailUtilisateur(email);
	}

	public Utilisateur getUtilisateur(Long id) {
		// TODO Auto-generated method stub
		UtilisateurDao utilisateurDao = new UtilisateurDaoImpl(em, utx);
		return utilisateurDao.getUtilisateur(id);
	}
	
	public Utilisateur getUtilisateur(String email) {
		// TODO Auto-generated method stub
		UtilisateurDao utilisateurDao = new UtilisateurDaoImpl(em, utx);
		return utilisateurDao.getUtilisateur(email);
	}
}
