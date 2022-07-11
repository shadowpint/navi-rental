package com.example.geektrust.dao;

import com.example.geektrust.config.HibernateUtil;
import com.example.geektrust.model.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class BranchDao {
	
	public void saveBranch(Branch Branch) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(Branch);
			
			// operation 2
			session.get(Branch.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	




	public Branch getBranchByName(String name) {

		Transaction transaction = null;
		Branch branch = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an Branch object
			String hql = " FROM Branch S WHERE S.name = :name";
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
				branch = (Branch) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return branch;
	}

}
