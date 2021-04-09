package fr.epsi.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fr.epsi.entite.Innovation;

public class InnovationDaoImpl implements InnovationDao {
	private EntityManager em;
	private UserTransaction utx;

	public InnovationDaoImpl(EntityManager em, UserTransaction utx) {
		this.em=em;
		this.utx=utx;
	}
	public Innovation getInnovation(Long id) {
		return em.find(Innovation.class, id);
	}
	
	public List<Innovation> getListInnovations() {
		return em.createQuery("SELECT i FROM Innovation i", Innovation.class).getResultList();
	}
	
	public void addInnovation(Innovation innovation) {
		try {
			utx.begin();
			em.persist(innovation);
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

	public List<Innovation> getInnovationsUpToDown() {
		String sql = "SELECT i FROM Innovation i ORDER BY i.numberOfVotes DESC";
		return em.createQuery(sql, Innovation.class).getResultList();
	}

	public List<Innovation> getInnovationsDownToUp() {
		String sql = "SELECT i FROM Innovation i ORDER BY i.numberOfVotes ASC";
		return em.createQuery(sql, Innovation.class).getResultList();
	}
}

