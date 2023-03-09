package realtorManagementApp.web;

import realtorManagementApp._enum.Roles;
import realtorManagementApp._enum.Types;
import realtorManagementApp.entities.Room;
import realtorManagementApp.entities.User;
import realtorManagementApp.services.RoomService;
import realtorManagementApp.services.impl.RoomServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class OrderSearchServlet extends HttpServlet {

    private RoomService roomService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        roomService = RoomServiceImpl.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        String[] filters = req.getParameterValues("filter");
        User thisUser = (User) req.getSession().getAttribute("currentUser");

        List<Room> results = performSearch(query, thisUser);
        if (filters != null && filters.length > 0) {
            List<String> filterList = new ArrayList<>();
            for (String f : filters) {
                filterList.add(Types.valueOf(f).getTitle());
            }
            results = results.stream()
                    .filter(result -> filterList.contains(result.getType()))
                    .collect(Collectors.toList());
        }

        req.setAttribute("rooms", results);
        req.getRequestDispatcher("/WEB-INF/pages/orders.jsp").forward(req, resp);
    }

    private List<Room> performSearch(String query, User thisUser) {
        if (thisUser.getUserRole().equals(Roles.ROLE_USER.getTitle()))
            return roomService.findAllUserRooms(thisUser).stream()
                    .filter(room -> room.getDescription().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
        return roomService.findAllRealtorRooms(thisUser).stream()
                .filter(room -> room.getDescription().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());

    }


}


