package realtorManagementApp.web;

import realtorManagementApp.entities.Room;
import realtorManagementApp.services.RoomService;
import realtorManagementApp.services.impl.RoomServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoomDetailsServlet extends HttpServlet {
    private RoomService roomService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        roomService = RoomServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getParameter("id");
        Room room = roomService.findById(Integer.valueOf(roomId));
        request.setAttribute("room", room);
        request.getRequestDispatcher("/WEB-INF/pages/roomDetails.jsp").forward(request, response);
    }
}

