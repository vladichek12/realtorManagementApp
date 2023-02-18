package realtorManagementApp.dao;

import realtorManagementApp.entities.User;

import java.util.List;

public interface UserDao {

    public List<User> findAll();

    public List<User> findAllCustomers();

    public List<User> findAllRealtors();

    public User findUser(String login);

    public User findUserById(Integer id);

    public void save(User user);

    public void delete(User user);

    public void update(User user);
}
