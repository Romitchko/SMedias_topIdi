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
import fr.epsi.Dao.InnovationDao;
import fr.epsi.Dao.InnovationDaoImpl;
import fr.epsi.entite.Categorie;
import fr.epsi.entite.Innovation;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class InnovationServiceImpl implements InnovationService {
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;

	public Innovation getInnovation(Long id) {
		InnovationDao innovationDao = new InnovationDaoImpl(em, utx);
		return  innovationDao.getInnovation(id);
	}
	
	public List<Innovation> getListInnovations() {
		InnovationDao innovationDao = new InnovationDaoImpl(em, utx);
		return  innovationDao.getListInnovations();
		
	}
	
	public void addInnovation(Innovation innovation) {
		InnovationDao innovationDao = new InnovationDaoImpl(em, utx);
		innovationDao.addInnovation(innovation);
	}

	public List<Innovation> getInnovationsUpToDown() {
		InnovationDao innovationDao = new InnovationDaoImpl(em, utx);
		return  innovationDao.getInnovationsUpToDown();
	}

	public List<Innovation> getInnovationsDownToUp() {
		InnovationDao innovationDao = new InnovationDaoImpl(em, utx);
		return  innovationDao.getInnovationsDownToUp();
	}
}
