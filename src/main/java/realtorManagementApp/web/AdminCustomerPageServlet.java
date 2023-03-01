package realtorManagementApp.web;

import realtorManagementApp.entities.User;
import realtorManagementApp.services.RoomService;
import realtorManagementApp.services.UserService;
import realtorManagementApp.services.impl.RoomServiceImpl;
import realtorManagementApp.services.impl.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminCustomerPageServlet extends HttpServlet {
    private UserService userService;
    private RoomService roomService;
    private List<User> customers;
    ;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = UserServiceImpl.getInstance();
        roomService = RoomServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!userService.userIsValidated(request, response)) {
            response.sendRedirect(String.format("%s/login", request.getContextPath()));
            return;
        }
        customers = userService.findAllCustomers();
        request.setAttribute("customers", customers);
        request.getServletContext().getRequestDispatcher("/WEB-INF/pages/admin/adminCustomersPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("userId"));
        User user = new User(userService.findUserById(id));
        roomService.findAllUserRooms(user).
                forEach(room -> roomService.delete(room));
        userService.delete(user);
        response.sendRedirect("adminCustomers");
    }
}
