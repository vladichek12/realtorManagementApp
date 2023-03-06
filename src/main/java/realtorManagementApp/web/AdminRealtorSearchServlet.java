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
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AdminRealtorSearchServlet extends HttpServlet {

    private UserService userService;
    private List<User> users;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = UserServiceImpl.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        List<User> results = performSearch(query);
        req.setAttribute("realtors", results);
        req.getRequestDispatcher("/WEB-INF/pages/admin/adminRealtorsPage.jsp").forward(req, resp);
    }

    private List<User> performSearch(String query) {
        return userService.findAllRealtors().stream()
                .filter(realtor -> realtor.getName().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }


}


