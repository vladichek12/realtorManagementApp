package realtorManagementApp.web;

import realtorManagementApp.entities.Room;
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

public class UpdateRoomServlet extends HttpServlet {
    private RoomService roomService;
    private UserService userService;
    private AddressService addressService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        roomService = RoomServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
        addressService = AddressServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!userService.userIsValidated(request, response)) {
            response.sendRedirect(String.format("%s/login", request.getContextPath()));
            return;
        }
        Room neededRoom = roomService.findById(Integer.parseInt(request.getPathInfo().substring(1)));
        request.setAttribute("neededRoom", neededRoom);
        request.getRequestDispatcher("/WEB-INF/pages/updateRoom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
            Room neededRoom = roomService.findById(Integer.parseInt(request.getPathInfo().substring(1)));
            neededRoom.setNumberOfRooms(numberOfRooms);
            neededRoom.setSquare(square);
            neededRoom.getAddress().setCity(city);
            neededRoom.getAddress().setHouseNumber(houseNumber);
            neededRoom.getAddress().setStreet(street);
            roomService.update(neededRoom);
            response.sendRedirect(String.format("%s/user", request.getContextPath()));
        }
    }
}
