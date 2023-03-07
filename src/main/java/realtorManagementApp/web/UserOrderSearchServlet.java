package realtorManagementApp.web;

import realtorManagementApp._enum.Types;
import realtorManagementApp.entities.Room;
import realtorManagementApp.services.RoomService;
import realtorManagementApp.services.UserService;
import realtorManagementApp.services.impl.RoomServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class UserOrderSearchServlet extends HttpServlet {

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
        List<Room> results = performSearch(query);
        if (filters != null && filters.length > 0) {
            List<String> filterList = Arrays.asList(filters);

            results = results.stream()
                    .filter(result -> filterList.contains(result.getType().toLowerCase()))
                    .collect(Collectors.toList());
        }

        req.setAttribute("rooms", results);
        req.getRequestDispatcher("/WEB-INF/pages/userPage.jsp").forward(req, resp);
    }

    private List<Room> performSearch(String query) {
        return roomService.findAll().stream()
                .filter(room -> room.getDescription().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }



}

