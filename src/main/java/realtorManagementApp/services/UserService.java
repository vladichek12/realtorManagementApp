package realtorManagementApp.services;

import realtorManagementApp.entities.User;
import realtorManagementApp.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public List<User> findAllCustomers();

    public List<User> findAllRealtors();

    public User findUser(String login) throws UserNotFoundException;

    public void save(User user);

    public void delete(User user);

    public void update(User user);
}
