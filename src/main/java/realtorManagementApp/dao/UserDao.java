package realtorManagementApp.dao;

import realtorManagementApp.entities.User;

import java.util.List;

public interface UserDao {

    public List<User> findAll();

    public User findUser(String login);

    public void save(User user);

    public void delete(User user);

    public void update(User user);
}
