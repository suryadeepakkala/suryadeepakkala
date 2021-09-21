package com.dataken.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MainDAO {

    private static final Logger log = LoggerFactory.getLogger(MainDAO.class);
    private static final String tracePrefix = "[" + MainDAO.class.getSimpleName() + "]: ";
    private static final String HIBERNATE_CFG_PATH = "./ipl-service/src/main/resources/hibernate.cfg.xml";
    protected static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                log.info(tracePrefix + "-------- Building Session Factory ---------");
                StandardServiceRegistry standardRegistry = null;
                File file = new File(HIBERNATE_CFG_PATH);
                standardRegistry = new StandardServiceRegistryBuilder().configure(file).build();
                Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
                log.info(tracePrefix + "Metadata built");
                sessionFactory = metaData.getSessionFactoryBuilder().build();
            }
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void reinitializeSessionFactory() {
        sessionFactory = null;
        sessionFactory = buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
