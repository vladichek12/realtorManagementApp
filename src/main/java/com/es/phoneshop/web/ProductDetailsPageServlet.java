package com.es.phoneshop.web;

import com.es.phoneshop.model.product.cart.Cart;
import com.es.phoneshop.model.product.dao.ProductDao;
import com.es.phoneshop.model.product.dao.impl.ArrayListProductDao;
import com.es.phoneshop.model.product.entity.Product;
import com.es.phoneshop.model.product.exception.OutOfStockException;
import com.es.phoneshop.model.product.service.CartService;
import com.es.phoneshop.model.product.service.impl.CartServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ProductDetailsPageServlet extends HttpServlet {
    private ProductDao productDao;
    private CartService cartService;

    private Set<Product> recentProducts;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productDao = ArrayListProductDao.getInstance();
        cartService = CartServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo().substring(1).contains("/")) {
            request.setAttribute("product", productDao.getProduct(
                    parseProductIdFromRequestWithHistory(request)));
            request.getRequestDispatcher("/WEB-INF/pages/productPriceHistory.jsp").forward(request, response);
        } else {
            Product product = productDao.getProduct(parseProductIdFromRequestWithoutHistory(request));
            request.setAttribute("product", product);
            includeProductInRecentProducts(product, request);
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long productId = parseProductIdFromRequestWithoutHistory(request);
        int quantity;
        Cart cart = cartService.getCart(request);
        try {
            NumberFormat numberFormat = NumberFormat.getNumberInstance(request.getLocale());
            quantity = numberFormat.parse(request.getParameter("quantity")).intValue();
        } catch (ParseException exception) {
            request.setAttribute("error", "Not a number!");
            response.sendRedirect(request.getContextPath() + "/products" + "/" + productId + "?message=&error=Not a number!");
            return;
        }
        try {
            cartService.add(cart, productId, quantity);
        } catch (OutOfStockException e) {
            request.setAttribute("error", "Not enough items in stock! Available: " + e.getAvailableStock());
            response.sendRedirect(request.getContextPath() + "/products" + "/" + productId + "?message=&error=Not enough items in stock! Available:" + e.getAvailableStock());
            return;
        }

        request.setAttribute("product", productDao.getProduct(productId));
        request.setAttribute("cart", cartService.getCart(request));
        response.sendRedirect(request.getContextPath() + "/products" + "/" + productId + "?message=Product was added to cart successfully!");
    }

    private Long parseProductIdFromRequestWithHistory(HttpServletRequest request) throws NumberFormatException {
        return Long.parseLong(request.getPathInfo().substring(1, request.getPathInfo().lastIndexOf('/')));
    }

    private Long parseProductIdFromRequestWithoutHistory(HttpServletRequest request) throws NumberFormatException {
        return Long.parseLong(request.getPathInfo().substring(1));
    }

    private void includeProductInRecentProducts(Product product, HttpServletRequest request) {
        if (recentProducts == null) {
            recentProducts = new LinkedHashSet<>();
        }
        if (recentProducts.size() > 2 && !recentProducts.contains(product)) {
            Product productToRemove = recentProducts.iterator().next();
            recentProducts.remove(productToRemove);
        }
        recentProducts.add(product);
        setRecentProductsInSession(request, recentProducts);
    }

    private void setRecentProductsInSession(HttpServletRequest request, Set<Product> products) {
        HttpSession currentSession = request.getSession();
        synchronized (currentSession) {
            currentSession.setAttribute("recentProducts", products);
        }
    }
}
