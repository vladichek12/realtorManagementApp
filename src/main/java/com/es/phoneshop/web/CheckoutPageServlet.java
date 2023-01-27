package com.es.phoneshop.web;

import com.es.phoneshop.model.product.cart.Cart;
import com.es.phoneshop.model.product.order.Order;
import com.es.phoneshop.model.product.service.CartService;
import com.es.phoneshop.model.product.service.OrderService;
import com.es.phoneshop.model.product.service.impl.CartServiceImpl;
import com.es.phoneshop.model.product.service.impl.OrderServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CheckoutPageServlet extends HttpServlet {
    private CartService cartService;
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cartService = CartServiceImpl.getInstance();
        orderService = OrderServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("order",
                orderService.createOrderFromCart(cartService.getCart(request)));
        request.getRequestDispatcher("/WEB-INF/pages/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order currentOrder = orderService.createOrderFromCart(cartService.getCart(request));
        Map<String, String> possibleErrors = new HashMap<>();

        orderService.setRequiredStringCustomerInfo(
                currentOrder,
                "firstName",
                request,
                possibleErrors,
                currentOrder.getCustomerInfo()::setFirstName);

        orderService.setRequiredStringCustomerInfo(
                currentOrder,
                "lastName",
                request,
                possibleErrors,
                currentOrder.getCustomerInfo()::setLastName);

        orderService.setRequiredStringCustomerInfo(
                currentOrder,
                "deliveryAddress",
                request,
                possibleErrors,
                currentOrder.getCustomerInfo()::setDeliveryAddress);

        orderService.setRequiredPhoneNumberCustomerInfo(
                currentOrder,
                request,
                possibleErrors
        );

        orderService.setRequiredLocalDateCustomerInfo(
                currentOrder,
                request,
                possibleErrors);

        orderService.setRequiredPaymentMethodCustomerInfo(
                currentOrder,
                request,
                possibleErrors);

        if (possibleErrors.isEmpty()) {
            orderService.placeOrder(currentOrder);
            Cart currentCart = cartService.getCart(request);
            currentCart.getItems().clear();
            cartService.reCalculateCart(currentCart);
            response.sendRedirect(String.format("%s/order/overview/%s", request.getContextPath(), currentOrder.getSecureId()));
        } else {
            request.setAttribute("possibleErrors", possibleErrors);
            request.setAttribute("order", currentOrder);
            request.setAttribute("selectedPaymentMethod", currentOrder.getCustomerInfo().getPaymentMethod().toString());
            request.getRequestDispatcher("/WEB-INF/pages/checkout.jsp").forward(request, response);
        }
    }
}
