package realtorManagementApp.web;

import jakarta.servlet.annotation.WebServlet;
import realtorManagementApp.entities.Room;
import realtorManagementApp.services.RoomService;
import realtorManagementApp.services.impl.RoomServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class CatalogServlet extends HttpServlet {

    private RoomService roomService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        roomService = RoomServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> roomList = roomService.findAll().stream().limit(8).collect(Collectors.toList());
        request.setAttribute("rooms", roomList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
