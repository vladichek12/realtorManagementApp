package realtorManagementApp.web;

import realtorManagementApp._enum.Statuses;
import realtorManagementApp.entities.Room;
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

public class ConnectUsersServlet extends HttpServlet {
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
        Room room = roomService.findById(Integer.valueOf(request.getPathInfo().substring(1)));
        room.setStatus(Statuses.STATUS_PROCESSING.getTitle());
        roomService.update(room);
        request.setAttribute("room", room);
        request.getRequestDispatcher("/WEB-INF/pages/roomInfo.jsp").forward(request, response);
    }
}


