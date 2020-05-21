package main.java.dbconnection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.File;

public class SessionProvider
{
    private SessionFactory sessionFactory = buildSessionFactory();

    public SessionFactory buildSessionFactory()
    {
        try {
            return new AnnotationConfiguration().configure(
                    new File("src/hibernate.cfg.xml")).buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
