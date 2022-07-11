package com.example.geektrust.dao;

import com.example.geektrust.config.HibernateUtil;
import com.example.geektrust.model.VehicleBooking;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class VehicleBookingDao {
	
	public void saveVehicleBooking(VehicleBooking VehicleBooking) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(VehicleBooking);
			
			// operation 2
			session.get(VehicleBooking.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	




	public VehicleBooking getVehicleBookingByBranchAndVehicleType(int branchId,String vehicleType) {

		Transaction transaction = null;
		VehicleBooking vehicleBooking = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an VehicleBooking object
			String hql = " FROM VehicleBooking S WHERE S.vehicleMap.branch.id = :branchId and S.vehicleMap.vehicle.type=:vehicleType";
			Query query = session.createQuery(hql);
			query.setParameter("branchId", branchId);
			query.setParameter("vehicleType", vehicleType);
			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
				vehicleBooking = (VehicleBooking) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return vehicleBooking;
	}
	public VehicleBooking getVehicleBookingByVehicleMap(int vehiclemapId) {

		Transaction transaction = null;
		VehicleBooking vehicleBooking = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an VehicleBooking object
			String hql = " FROM VehicleBooking S WHERE S.vehicleMap.id = :vehiclemapId";
			Query query = session.createQuery(hql);
			query.setParameter("vehiclemapId", vehiclemapId);

			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
				vehicleBooking = (VehicleBooking) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return vehicleBooking;
	}

}
