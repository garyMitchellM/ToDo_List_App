package com.hibernate.todotomcat;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

    //Creates a single static instance of SessionFactory that is used by the whole app
    private static final SessionFactory factory;

    //This runs once when the class is first loaded
    static {
        try {
            //Create Hibernate configuration and add the Task class as an annotated entity
            Configuration config = new Configuration();
            config.addAnnotatedClass(Task.class);
            config.configure();

            //Builds the session factory using the Configuration object
             factory = config.buildSessionFactory();

        } catch (Throwable ex) {
            //If the factory creation fails, print the error and the stack trace
            System.err.println("SessionFactory creation failed: " + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    //This gives access to the SessionFactory instance created earlier
    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
