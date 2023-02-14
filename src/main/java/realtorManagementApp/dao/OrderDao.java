package realtorManagementApp.dao;

import realtorManagementApp.entities.Order;

import java.util.List;

public interface OrderDao {
    public List<Order> findAll();

    public void save(Order order);

    public void delete(Order order);

    public void update(Order order);
}
