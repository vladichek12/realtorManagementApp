package realtorManagementApp.web;

import realtorManagementApp.dao.UserDao;
import realtorManagementApp.dao.impl.UserDaoImpl;
import realtorManagementApp.entities.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminPageServlet extends HttpServlet {
    private UserDao userDao;
    private List<User> customers;
    private List<User> realtors;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = UserDaoImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        customers = userDao.findAllCustomers();
        realtors = userDao.findAllRealtors();
        request.setAttribute("realtors",realtors);
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/WEB-INF/pages/adminPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/loginPage.jsp").forward(request, response);
    }
}
