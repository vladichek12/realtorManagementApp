package realtorManagementApp.web;

import realtorManagementApp.entities.Address;
import realtorManagementApp.entities.Room;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUpdateOrderServlet extends HttpServlet {
    private UserService userService;
    private RoomService roomService;
    private AddressService addressService;
    // private OrderService orderService;
    private List<User> realtors;
    ;
    private List<Room> rooms;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = UserServiceImpl.getInstance();
        roomService = RoomServiceImpl.getInstance();
        addressService = AddressServiceImpl.getInstance();
        //orderService = OrderServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!userService.userIsValidated(request, response)) {
            response.sendRedirect(String.format("%s/login", request.getContextPath()));
            return;
        }
        request.setAttribute("room", roomService.findById(Integer.valueOf(request.getPathInfo().substring(1))));
        request.getServletContext().getRequestDispatcher("/WEB-INF/pages/admin/updateOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = null, street = null, description = null;
        int houseNumber = 0, numberOfRooms = 0;
        long square = 0, id = Long.parseLong(request.getParameter("id")), price = 0;
        Map<String, String> possibleErrors = new HashMap<>();


        city = addressService.checkParameterString("city", request, possibleErrors, city);
        street = addressService.checkParameterString("street", request, possibleErrors, street);
        description = addressService.checkParameterString("description", request, possibleErrors, description);

        houseNumber = addressService.checkParameterInteger("houseNumber", request, possibleErrors, houseNumber);
        numberOfRooms = addressService.checkParameterInteger("numberOfRooms", request, possibleErrors, numberOfRooms);

        square = addressService.checkParameterLong("square", request, possibleErrors, square);
        price = addressService.checkParameterLong("price", request, possibleErrors, price);

        if (!possibleErrors.isEmpty()) {
            request.setAttribute("possibleErrors", possibleErrors);
            doGet(request, response);
        } else {
            Room roomToUpdate = roomService.findById((int) id);
            roomToUpdate.setNumberOfRooms(numberOfRooms);
            roomToUpdate.setSquare(square);
            roomToUpdate.setAddress(new Address(city, street, houseNumber));
            roomToUpdate.setPrice(price);
            roomToUpdate.setDescription(description);
            roomService.update(roomToUpdate);
            response.sendRedirect(String.format("%s/admin/orders", request.getContextPath()));
        }

    }
}
