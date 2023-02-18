package realtorManagementApp.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import realtorManagementApp.dao.OrderDao;
import realtorManagementApp.dao.RoomDao;
import realtorManagementApp.entities.Order;
import realtorManagementApp.entities.User;
import realtorManagementApp.exceptions.EntityNotFoundException;
import realtorManagementApp.exceptions.UserNotFoundException;
import realtorManagementApp.session.SessionHandler;

import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private static volatile OrderDao instance;

    public static OrderDao getInstance() {
        OrderDao localInstance = instance;
        if (localInstance == null) {
            synchronized (RoomDao.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new OrderDaoImpl() {
                    };
            }
        }
        return localInstance;
    }

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
    public Order findById(Integer id) {
        currentSession = SessionHandler.openTransaction();
        String sqlQuery = "SELECT * FROM orders WHERE id=:id";

        Query query = currentSession.createSQLQuery(sqlQuery).addEntity(Order.class);
        query.setParameter("id", id);

        Optional<Order> order = query.stream().findFirst();
        if (order.isEmpty()) {
            throw new EntityNotFoundException(String.format("No order with such id found:%s", id));
        }
        return order.get();
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
