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

public class RoomPageServlet extends HttpServlet {
    private RoomService roomService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        roomService = RoomServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(
                "room",
                roomService.findById(
                        Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/WEB-INF/pages/roomInfo.jsp").forward(request, response);
    }
}
