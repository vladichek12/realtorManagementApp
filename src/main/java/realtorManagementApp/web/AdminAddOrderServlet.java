package realtorManagementApp.web;

import realtorManagementApp.entities.Room;
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
import java.util.Arrays;
import java.util.List;

public class AdminAddOrderServlet extends HttpServlet {
    private UserService userService;
    private RoomService roomService;
    // private OrderService orderService;
    private List<User> realtors;
    ;
    private List<Room> rooms;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = UserServiceImpl.getInstance();
        roomService = RoomServiceImpl.getInstance();
        //orderService = OrderServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!userService.userIsValidated(request, response)) {
            response.sendRedirect(String.format("%s/login", request.getContextPath()));
            return;
        }
        realtors = userService.findAllRealtors();
        rooms = roomService.findAll();
        request.setAttribute("realtors", realtors);
        request.setAttribute("rooms", rooms);
        request.getServletContext().getRequestDispatcher("/WEB-INF/pages/admin/addNewOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> tempRealtorId = Arrays.asList(request.getParameterValues("realtors"));
        List<String> tempRoomId = Arrays.asList(request.getParameterValues("rooms"));
        int realtorId = Integer.parseInt(tempRealtorId.get(0));
        int roomId = Integer.parseInt(tempRoomId.get(0));
        User realtor = userService.findUserById(realtorId);
        Room room = roomService.findById(roomId);
//        Order order = new Order(realtor,room);
//        orderService.save(order);
        response.sendRedirect("adminOrders");
    }
}
