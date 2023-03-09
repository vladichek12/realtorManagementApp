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
        roomService.delete(roomService.findById(Integer.parseInt(request.getPathInfo().substring(1))));
        response.sendRedirect(String.format("%s/orders", request.getContextPath()));
    }
}
