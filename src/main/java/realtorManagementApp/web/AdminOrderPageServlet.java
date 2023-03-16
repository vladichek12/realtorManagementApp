package realtorManagementApp.web;

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
import java.util.List;

public class AdminOrderPageServlet extends HttpServlet {
   // private OrderService orderService;
    private UserService userService;
    //private List<Order> orders;
    private RoomService roomService;
    private List<Room> rooms;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //orderService = OrderServiceImpl.getInstance();
        roomService = RoomServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!userService.userIsValidated(request, response)) {
            response.sendRedirect(String.format("%s/login", request.getContextPath()));
            return;
        }
        request.setAttribute("realtors", userService.findAllRealtors());
        request.setAttribute("rooms",roomService.findAllProcessingRooms());
        request.getServletContext().getRequestDispatcher("/WEB-INF/pages/admin/adminOrdersPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("userId"));
        Room order = new Room(roomService.findById(id));
        roomService.delete(order);
        response.sendRedirect(String.format("%s/admin/orders", request.getContextPath()));
    }
}
