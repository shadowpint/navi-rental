package com.example.geektrust.dao;

import com.example.geektrust.config.HibernateUtil;
import com.example.geektrust.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class VehicleDao {
	
	public void saveVehicle(Vehicle Vehicle) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(Vehicle);
			
			// operation 2
			session.get(Vehicle.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}




	public Vehicle getVehicleByName(String name) {

		Transaction transaction = null;
		Vehicle vehicle = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an Vehicle object
			String hql = " FROM Vehicle S WHERE S.vehicleId = :vehicleId";
			Query query = session.createQuery(hql);
			query.setParameter("vehicleId", name);
			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
				vehicle = (Vehicle) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return vehicle;
	}

}
