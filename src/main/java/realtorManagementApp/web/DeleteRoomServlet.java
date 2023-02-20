package realtorManagementApp.web;

import realtorManagementApp.services.RoomService;
import realtorManagementApp.services.impl.RoomServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRoomServlet extends HttpServlet {
    private RoomService roomService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        roomService = RoomServiceImpl.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int neededRoomId = Integer.parseInt(request.getPathInfo().substring(1));
        roomService.delete(roomService.findById(neededRoomId));
        response.sendRedirect(String.format("%s/user", request.getContextPath()));
    }
}
