package realtorManagementApp.web;

import realtorManagementApp.entities.Address;
import realtorManagementApp.entities.Room;
import realtorManagementApp.entities.User;
import realtorManagementApp.services.AddressService;
import realtorManagementApp.services.RoomService;
import realtorManagementApp.services.UserService;
import realtorManagementApp.services.impl.AddressServiceImpl;
import realtorManagementApp.services.impl.RoomServiceImpl;
import realtorManagementApp.services.impl.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogOutServlet extends HttpServlet {
    private RoomService roomService;
    private AddressService addressService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        roomService = RoomServiceImpl.getInstance();
        addressService = AddressServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("currentUser", null);
        response.sendRedirect(String.format("%s/",request.getContextPath()));
    }
}
