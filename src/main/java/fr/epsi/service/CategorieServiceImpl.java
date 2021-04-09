package fr.epsi.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.Dao.CategorieDao;
import fr.epsi.Dao.CategorieDaoImpl;
import fr.epsi.entite.Categorie;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CategorieServiceImpl implements CategorieService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	public List<Categorie> getListCategories() {		
		CategorieDao categorieDao = new CategorieDaoImpl(em, utx);
		return categorieDao.getListCategories();
	}
	
	public Categorie getCategorie(Long id) {
		CategorieDao categorieDao = new CategorieDaoImpl(em, utx);
		return categorieDao.getCategorie(id);
	}
	
}
