package com.es.phoneshop.model.product.dao;

import com.es.phoneshop.model.product.exception.OrderNotFoundException;
import com.es.phoneshop.model.product.order.Order;

public interface OrderDao {
    Order getOrderBySecureId(String secureId) throws OrderNotFoundException;
    void save(Order order);

}
