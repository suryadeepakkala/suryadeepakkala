package com.dataken.dao;

import com.dataken.model.Delivery;
import com.dataken.model.Match;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DeliveryDAO extends MainDAO {

    private static final Logger log = LoggerFactory.getLogger(DeliveryDAO.class);

    public void persist(List<Delivery> list) {
        long start = System.currentTimeMillis();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for(Delivery delivery : list) {
                session.saveOrUpdate(delivery);
            }
            tx.commit();
            log.info("Saved the {} deliveries in {} ms", list.size(), (System.currentTimeMillis() - start));
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed saving the deliveries instances", ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
    }

    public void deleteAll() {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            int count = session.createQuery("delete from Delivery").executeUpdate();
            log.info("Deleted {} deliveries", count);
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed saving the deliveries instances", ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
    }
}
