package realtorManagementApp.session;

import org.hibernate.Session;
import org.hibernate.Transaction;
import realtorManagementApp.cnf.HibernateSessionFactory;

public class SessionHandler {
    private static Session currentSession;
    private static Transaction currentTransaction;

    public static Session getCurrentSession() {
        return currentSession;
    }

    public static Session openSession() {
        return HibernateSessionFactory.getSessionFactory().openSession();
    }

    public static void closeSession() {
        currentSession.close();
    }

    public static Session openTransaction() {
        currentSession = openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public static void closeTransactionSession() {
        currentTransaction.commit();
        closeSession();
    }
}
