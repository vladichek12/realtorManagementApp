package realtorManagementApp.dao.impl;

import org.hibernate.Session;
import realtorManagementApp.dao.OrderDao;
import realtorManagementApp.entities.Order;
import realtorManagementApp.session.SessionHandler;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private Session currentSession;

    public OrderDaoImpl() {
    }

    @Override
    public List<Order> findAll() {
        currentSession = SessionHandler.openTransaction();
        List<Order> addresses = currentSession.createSQLQuery("select * from orders").addEntity(Order.class).list();
        SessionHandler.closeTransactionSession();
        return addresses;
    }

    @Override
    public void save(Order order) {
        currentSession = SessionHandler.openTransaction();
        currentSession.save(order);
        SessionHandler.closeTransactionSession();
    }

    @Override
    public void delete(Order order) {
        currentSession = SessionHandler.openTransaction();
        currentSession.delete(order);
        SessionHandler.closeTransactionSession();
    }

    @Override
    public void update(Order order) {
        currentSession = SessionHandler.openTransaction();
        currentSession.update(order);
        SessionHandler.closeTransactionSession();
    }
}
