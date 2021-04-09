package fr.epsi.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fr.epsi.entite.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao{
	
	EntityManager em;
	UserTransaction utx;

	public UtilisateurDaoImpl(EntityManager em, UserTransaction utx) {
		this.em=em;
		this.utx=utx;
	}
	public void create(Utilisateur u) {
		try {
			utx.begin();
			em.persist(u);
			
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
		
		public void addUtilisateur(Utilisateur utilisateur) {
			
			try {
				utx.begin();
				em.persist(utilisateur);
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
		
		public boolean checkUtilisateur(String email, String pwd) {
			// TODO Auto-generated method stub
			String sql   = "select u from Utilisateur u where u.email = :email And u.password = :pwd ";		
			Query  query = em.createQuery(sql, Utilisateur.class);
			query.setParameter("email", email);
			query.setParameter("pwd", pwd);
			
			List<Utilisateur> utilisateur = (List<Utilisateur>) query.getResultList();
			return utilisateur.isEmpty();
		}

		public boolean checkEmailUtilisateur(String email) {
			// TODO Auto-generated method stub
			Query  query = em.createQuery("select u from Utilisateur u where u.email = :email", Utilisateur.class).setParameter("email", email);
			List<Utilisateur> utilisateur = (List<Utilisateur>) query.getResultList();		
			return utilisateur.isEmpty();
		}
		
		public Utilisateur getUtilisateur(Long id) {
			return em.find(Utilisateur.class, id);
		}
		
		public Utilisateur getUtilisateur(String email) {
			return em.createQuery("select u from Utilisateur u where u.email = :email", Utilisateur.class).setParameter("email", email).getSingleResult();
		}

}
