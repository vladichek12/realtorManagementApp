package realtorManagementApp.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import realtorManagementApp._enum.Roles;
import realtorManagementApp.dao.UserDao;
import realtorManagementApp.entities.User;
import realtorManagementApp.exceptions.UserNotFoundException;
import realtorManagementApp.session.SessionHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static volatile UserDao instance;

    public static UserDao getInstance() {
        UserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new UserDaoImpl();
            }
        }
        return localInstance;
    }

    private Session currentSession;

    private UserDaoImpl() {
    }

    @Override
    public List<User> findAll() {
        currentSession = SessionHandler.openTransaction();
        List<User> users = currentSession.createSQLQuery("select * from users").addEntity(User.class).list();
        SessionHandler.closeTransactionSession();
        return users;
    }

    @Override
    public List<User> findAllCustomers() {
        currentSession = SessionHandler.openTransaction();
        String sqlQuery = "SELECT * FROM users WHERE user_role=:role";

        Query query = currentSession.createSQLQuery(sqlQuery).addEntity(User.class).setParameter("role", "ROLE_USER");
        List<User> customers = query.list();
        SessionHandler.closeTransactionSession();

        return customers;
    }

    @Override
    public List<User> findAllRealtors() {
        currentSession = SessionHandler.openTransaction();
        String sqlQuery = "SELECT * FROM users WHERE user_role=:role";

        Query query = currentSession.createSQLQuery(sqlQuery).addEntity(User.class).setParameter("role", "ROLE_REALTOR");
        List<User> customers = query.list();

        SessionHandler.closeTransactionSession();

        return customers;
    }

    @Override
    public User findUser(String login) {
        currentSession = SessionHandler.openTransaction();
        String sqlQuery = "SELECT * FROM users WHERE email=:email";

        Query query = currentSession.createSQLQuery(sqlQuery).addEntity(User.class);
        query.setParameter("email", login);

        Optional<User> user = query.stream().findFirst();
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("No user with such login found:%s", login));
        }
        SessionHandler.closeTransactionSession();
        return user.get();
    }

    @Override
    public User findUserById(Integer id) {
        currentSession = SessionHandler.openTransaction();
        String sqlQuery = "SELECT * FROM users WHERE id=:id";

        Query query = currentSession.createSQLQuery(sqlQuery).addEntity(User.class);
        query.setParameter("id", id);

        Optional<User> user = query.stream().findFirst();
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("No user with such id found:%s", id));
        }
        SessionHandler.closeTransactionSession();
        return user.get();
    }

    @Override
    public void save(User user) {
        currentSession = SessionHandler.openTransaction();
        currentSession.save(user);
        SessionHandler.closeTransactionSession();
    }

    @Override
    public void delete(User user) {
        currentSession = SessionHandler.openTransaction();
        currentSession.delete(user);
        SessionHandler.closeTransactionSession();
    }

    @Override
    public void update(User user) {
        currentSession = SessionHandler.openTransaction();
        currentSession.update(user);
        SessionHandler.closeTransactionSession();
    }
}
