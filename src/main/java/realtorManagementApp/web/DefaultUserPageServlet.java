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

public class DefaultUserPageServlet extends HttpServlet {
    private RoomService roomService;
    private UserService userService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        roomService = RoomServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!userService.userIsValidated(request, response)) {
            response.sendRedirect(String.format("%s/login", request.getContextPath()));
            return;
        }
        request.setAttribute("rooms", roomService.findAll());
        request.setAttribute("currentUserRooms",
                roomService.findAllUserRooms((User) request.getSession().getAttribute("currentUser")));
        request.getRequestDispatcher("/WEB-INF/pages/userPage.jsp").forward(request, response);
    }
}


