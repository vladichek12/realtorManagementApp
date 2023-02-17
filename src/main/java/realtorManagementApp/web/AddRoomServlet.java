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
import java.util.HashMap;
import java.util.Map;

public class AddRoomServlet extends HttpServlet {
    private RoomService roomService;
    private AddressService addressService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        roomService = RoomServiceImpl.getInstance();
        addressService = AddressServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!userService.userIsValidated(request, response)) {
            response.sendRedirect(String.format("%s/login", request.getContextPath()));
            return;
        }
        request.getRequestDispatcher("/WEB-INF/pages/addNewRoom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = null, street = null;
        int houseNumber = 0, numberOfRooms = 0;
        long square = 0;
        Map<String, String> possibleErrors = new HashMap<>();

        city = addressService.checkParameterString("city", request, possibleErrors, city);
        street = addressService.checkParameterString("street", request, possibleErrors, street);

        houseNumber = addressService.checkParameterInteger("houseNumber", request, possibleErrors, houseNumber);
        numberOfRooms = addressService.checkParameterInteger("numberOfRooms", request, possibleErrors, numberOfRooms);

        square = addressService.checkParameterLong("square", request, possibleErrors, square);

        if (!possibleErrors.isEmpty()) {
            request.setAttribute("possibleErrors", possibleErrors);
            doGet(request, response);
        } else {
            Room roomToSave = new Room(square, numberOfRooms, new Address(city, street, houseNumber));
            roomToSave.setUser((User) request.getSession().getAttribute("currentUser"));
            roomService.save(roomToSave);
            response.sendRedirect(String.format("%s/user", request.getContextPath()));
        }
    }
}
