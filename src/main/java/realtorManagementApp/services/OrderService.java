package realtorManagementApp.services;

import realtorManagementApp.entities.Order;
import realtorManagementApp.entities.Room;
import realtorManagementApp.entities.User;

import java.util.List;

public interface OrderService {
    public List<Order> findAll();

    public Order findById(Integer id);

    public void save(Order order);

    public void delete(Order order);

    public void update(Order order);
}
