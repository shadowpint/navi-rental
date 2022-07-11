package com.example.geektrust.dao;

import com.example.geektrust.config.HibernateUtil;
import com.example.geektrust.model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class CustomerDao {
	
	public void saveCustomer(Customer customer) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(customer);
			
			// operation 2
			session.get(Customer.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	





	public Customer getCreateCustomer(String mobileNumber, String firstName) {

		Transaction transaction = null;
		Customer customer = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an Vehicle object
			String hql = " FROM Customer S WHERE S.mobileNumber = :mobileNumber";
			Query query = session.createQuery(hql);
			query.setParameter("mobileNumber", mobileNumber);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				customer = (Customer) results.get(0);
			}


			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return customer;
	}

}
