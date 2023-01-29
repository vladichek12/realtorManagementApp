package com.es.phoneshop.web;

import com.es.phoneshop.model.product.dao.OrderDao;
import com.es.phoneshop.model.product.dao.impl.ArrayListOrderDao;
import com.es.phoneshop.model.product.order.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OverviewPageServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private ServletConfig config;
    @Mock
    private HttpSession session;
    private OrderDao orderDao;
    private final OverviewPageServlet servlet = new OverviewPageServlet();

    @Before
    public void setup() throws ServletException {
        servlet.init(config);
        when(request.getLocale()).thenReturn(Locale.getDefault());
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getSession()).thenReturn(session);
        orderDao = ArrayListOrderDao.getInstance();
        Order orderTest = new Order();
        orderTest.setSecureId("1a-2");
        orderDao.save(orderTest);
        when(request.getPathInfo()).thenReturn("/1a-2");
    }


    @Test
    public void testDoGet() throws IOException, ServletException {
        servlet.doGet(request, response);
        verify(request).setAttribute(eq("order"), any());
    }

}