package realtorManagementApp.services.impl;

import realtorManagementApp.dao.UserDao;
import realtorManagementApp.dao.impl.UserDaoImpl;
import realtorManagementApp.entities.User;
import realtorManagementApp.exceptions.UserNotFoundException;
import realtorManagementApp.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static volatile UserServiceImpl instance;

    private UserDao userDao;

    public static UserServiceImpl getInstance() {
        UserServiceImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (UserServiceImpl.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new UserServiceImpl();
            }
        }
        return localInstance;
    }

    private UserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findAllCustomers() {
        return userDao.findAllCustomers();
    }

    @Override
    public List<User> findAllRealtors() {
        return userDao.findAllRealtors();
    }

    @Override
    public User findUser(String login) throws UserNotFoundException {
        return userDao.findUser(login);
    }

    @Override
    public User findUserById(Integer id) throws UserNotFoundException {
        return userDao.findUserById(id);
    }

    @Override
    public boolean userIsValidated(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return request.getSession().getAttribute("currentUser") != null;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
