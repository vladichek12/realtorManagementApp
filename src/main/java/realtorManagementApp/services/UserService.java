package realtorManagementApp.services;

import realtorManagementApp.entities.User;
import realtorManagementApp.exceptions.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> findAll();

    public List<User> findAllCustomers();

    public List<User> findAllRealtors();

    public User findUser(String login) throws UserNotFoundException;

    public User findUserById(Integer id) throws UserNotFoundException;

    public boolean userIsValidated(HttpServletRequest request, HttpServletResponse response) throws IOException;


    public void save(User user);

    public void delete(User user);

    public void update(User user);
}
