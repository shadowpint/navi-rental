package com.example.geektrust.dao;

import com.example.geektrust.config.HibernateUtil;
import com.example.geektrust.model.Branch;
import com.example.geektrust.model.VehicleBooking;
import com.example.geektrust.model.VehicleMap;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class VehicleMapDao {

	public void saveVehicleMap(VehicleMap vehicleMap) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(vehicleMap);
			
			// operation 2
			session.get(VehicleMap.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public VehicleMap getVehicleMap(String branchName, String vehicleId) {

		Transaction transaction = null;
		VehicleMap vehicleMap = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an Branch object
			String hql = " FROM VehicleMap S WHERE S.vehicle.vehicleId = :vehicleId and S.branch.name=:branchName";
			Query query = session.createQuery(hql);
			query.setParameter("vehicleId", vehicleId);
			query.setParameter("branchName", branchName);
			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
				vehicleMap = (VehicleMap) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return vehicleMap;
	}

	public VehicleMap getVehicleMapByVehicleType(String branchName, String vehicleType) {

		Transaction transaction = null;
		VehicleMap vehicleMap = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an Branch object
			String hql = " FROM VehicleMap S WHERE S.vehicle.type = :vehicleType and S.branch.name=:branchName and S.available=:available";
			Query query = session.createQuery(hql);
			query.setParameter("vehicleType", vehicleType);
			query.setParameter("branchName", branchName);
			query.setParameter("available", true);
			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
				vehicleMap = (VehicleMap) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return vehicleMap;
	}

	public List<VehicleMap> getVehicleMapByAvailability(String branchName,Long startTime, Long endTime) {

		Transaction transaction = null;
		List<VehicleMap> vehicleMapList = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an Branch object
			String hql = " FROM VehicleMap S WHERE  S.branch.name=:branchName and S.available=:available";
			Query query = session.createQuery(hql);
			query.setParameter("branchName", branchName);
			query.setParameter("available", true);
			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
				for(Object result:results)
					vehicleMapList.add((VehicleMap)result);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return vehicleMapList;
	}





	public void updateVehicleMap(VehicleMap vehicleMap) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// save the Branch object
			String hql = "UPDATE VehicleMap set available = :available " + "WHERE id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("available", vehicleMap.getAvailable());
			query.setParameter("id", vehicleMap.getId());
			int result = query.executeUpdate();


			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
