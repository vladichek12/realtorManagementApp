package realtorManagementApp.services.impl;

import realtorManagementApp.dao.OrderDao;
import realtorManagementApp.dao.RoomDao;
import realtorManagementApp.dao.impl.OrderDaoImpl;
import realtorManagementApp.dao.impl.RoomDaoImpl;
import realtorManagementApp.entities.Order;
import realtorManagementApp.entities.Room;
import realtorManagementApp.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static volatile OrderServiceImpl instance;

    private OrderDao orderDao;

    public static OrderServiceImpl getInstance() {
        OrderServiceImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (OrderServiceImpl.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new OrderServiceImpl();
            }
        }
        return localInstance;
    }

    private OrderServiceImpl() {
        orderDao = OrderDaoImpl.getInstance();
    }



    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return orderDao.findById(id);
    }

    @Override
    public void save(Order order) {
            orderDao.save(order);
    }

    @Override
    public void delete(Order order) {
        orderDao.delete(order);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }
}
