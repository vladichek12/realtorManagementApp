package com.es.phoneshop.web;

import com.es.phoneshop.model.product.exception.OutOfStockException;
import com.es.phoneshop.model.product.service.CartService;
import com.es.phoneshop.model.product.service.impl.CartServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartPageServlet extends HttpServlet {
    private CartService cartService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cartService = CartServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String[] productIds = request.getParameterValues("productId");
        String[] quantities = request.getParameterValues("quantity");

        for (int i = 0; i < productIds.length; i++) {
            try {
                cartService.update(
                        cartService.getCart(request),
                        Long.parseLong(productIds[i]),
                        Integer.parseInt(quantities[i]),
                        request);
            } catch (OutOfStockException e) {
                request.setAttribute("error", "Not enough items in stock! Available: " + e.getAvailableStock());
                response.sendRedirect(String.format("%s/products/%d?message=&error=Not enough items in stock! Available:%d", request.getContextPath(), Integer.parseInt(productIds[i]), e.getAvailableStock()));
                return;
            } catch (NumberFormatException e1) {
                request.setAttribute("error", "Not a number!");
                response.sendRedirect(String.format("%s/products/%d?message=&error=Not a number!", request.getContextPath(), Integer.parseInt(productIds[i])));
                return;
            }
        }
        doGet(request, response);
    }
}
