package realtorManagementApp.dao;

import realtorManagementApp.entities.Order;

import java.util.List;

public interface OrderDao {
    public List<Order> findAll();

    public Order findById(Integer id);

    public void save(Order order);

    public void delete(Order order);

    public void update(Order order);
}
