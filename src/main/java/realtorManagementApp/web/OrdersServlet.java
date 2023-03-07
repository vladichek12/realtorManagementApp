package realtorManagementApp.web;

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
import java.util.Objects;

public class OrdersServlet extends HttpServlet {
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
        if (!userService.userIsValidated(request, response)) {
            response.sendRedirect(String.format("%s/login", request.getContextPath()));
            return;
        }
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (Objects.equals(currentUser.
                        getUserRole(),
                "ROLE_REALTOR")) {
            request.setAttribute("rooms", roomService.findAllRealtorRooms(currentUser));
        } else {
            request.setAttribute("rooms", roomService.findAllUserRooms(currentUser));
        }
        request.getRequestDispatcher("/WEB-INF/pages/orders.jsp").forward(request, response);
    }
}
