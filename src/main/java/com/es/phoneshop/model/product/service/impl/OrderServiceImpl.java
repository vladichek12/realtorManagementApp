package com.es.phoneshop.model.product.service.impl;

import com.es.phoneshop.model.product.cart.Cart;
import com.es.phoneshop.model.product.cart.CartItem;
import com.es.phoneshop.model.product.dao.OrderDao;
import com.es.phoneshop.model.product.dao.impl.ArrayListOrderDao;
import com.es.phoneshop.model.product.enums.PaymentMethod;
import com.es.phoneshop.model.product.order.Order;
import com.es.phoneshop.model.product.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
        orderDao = ArrayListOrderDao.getInstance();
    }

    @Override
    public Order createOrderFromCart(Cart example) {
        Order order = new Order();
        order.setItems(example.getItems().stream().
                map(cartItem -> {
                    try {
                        return (CartItem) cartItem.clone();
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                }).
                collect(Collectors.toList())
        );
        order.setSubTotalCost(example.getTotalCost());
        order.setDeliveryCost(calculateDeliveryCost());
        order.setTotalCost(order.getSubTotalCost().add(order.getDeliveryCost()));
        order.setTotalItems(example.getTotalItems());

        return order;
    }

    private BigDecimal calculateDeliveryCost() {
        return BigDecimal.TEN;
    }

    @Override
    public void setRequiredStringCustomerInfo(Order order,
                                              String requiredParameter,
                                              HttpServletRequest request,
                                              Map<String, String> errorsMap,
                                              Consumer<String> consumer) {
        String parameter = request.getParameter(requiredParameter);

        if (parameter == null || parameter.isEmpty()) {
            errorsMap.put(requiredParameter, "This field must be filled in!");
        } else {
            consumer.accept(parameter);
        }
    }

    @Override
    public void setRequiredPhoneNumberCustomerInfo(Order order,
                                                   HttpServletRequest request,
                                                   Map<String, String> errorsMap) {
        String phoneNumber = request.getParameter("phoneNumber");

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            errorsMap.put("phoneNumber", "This field must be filled in!");
        } else {
            if (!phoneNumber.matches("^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$")) {
                errorsMap.put("phoneNumber", "There were some errors in phone number!");
            } else {
                order.getCustomerInfo().setPhoneNumber(phoneNumber);
            }
        }
    }

    @Override
    public void setRequiredLocalDateCustomerInfo(Order order,
                                                 HttpServletRequest request,
                                                 Map<String, String> errorsMap) {

        String parameter = request.getParameter("deliveryDate");

        if (parameter == null || parameter.isEmpty()) {
            errorsMap.put("deliveryDate", "This field must be filled in!");
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy").withLocale(Locale.getDefault());
                LocalDate localDateParameter = LocalDate.parse(parameter, formatter);
                order.getCustomerInfo().setDeliveryDate(localDateParameter);
            } catch (DateTimeParseException e) {
                errorsMap.put("deliveryDate", "There were some errors in date!");
            }
        }
    }

    @Override
    public void setRequiredPaymentMethodCustomerInfo(Order order,
                                                     HttpServletRequest request,
                                                     Map<String, String> errorsMap) {
        String parameter = request.getParameter("paymentMethod");

        if (parameter == null || parameter.isEmpty()) {
            errorsMap.put("paymentMethod", "This field must be filled in!");
        } else {
            order.getCustomerInfo().setPaymentMethod(PaymentMethod.valueOf(parameter));
        }
    }

    @Override
    public void placeOrder(Order orderToPlace) {
        orderToPlace.setSecureId(String.valueOf(UUID.randomUUID()));
        orderDao.save(orderToPlace);
    }

    @Override
    public String parseSecureOrderIdFromRequest(HttpServletRequest request) {
        return request.getPathInfo().substring(1);
    }

}
