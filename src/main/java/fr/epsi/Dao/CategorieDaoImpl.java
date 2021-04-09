package fr.epsi.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import fr.epsi.entite.Categorie;

public class CategorieDaoImpl implements CategorieDao {

	private EntityManager em;
	private UserTransaction utx;

	public CategorieDaoImpl (EntityManager em, UserTransaction utx) {
		this.em = em;
		this.utx = utx;
	}

	public Categorie getCategorie(Long id) {
		return em.find(Categorie.class, id);
	}
	
	public List<Categorie> getListCategories() {
		return em.createQuery("SELECT c FROM Categorie c", Categorie.class).getResultList();
	}
	
}
