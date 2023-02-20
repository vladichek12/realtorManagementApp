package realtorManagementApp.web;


import com.google.common.hash.Hashing;
import realtorManagementApp._enum.Roles;
import realtorManagementApp.entities.User;
import realtorManagementApp.exceptions.UserNotFoundException;
import realtorManagementApp.services.UserService;
import realtorManagementApp.services.impl.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RegistrationPageServlet extends HttpServlet {
    private UserService userService;

    private final String LOGIN_REQUEST_PARAMETER = "login";
    private final String PASSWORD_REQUEST_PARAMETER = "password";
    private final String ERROR_ATTRIBUTE = "error";

    private final String INVALID_LOGIN_OR_PASSWORD_MESSAGE = "Invalid login/password! Please check the data!";


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userAlreadyExists;
        String login = null, password = null;
        try {
            login = request.getParameter(LOGIN_REQUEST_PARAMETER);
            password = request.getParameter(PASSWORD_REQUEST_PARAMETER);
            if (
                    login == null ||
                            request.getParameter(PASSWORD_REQUEST_PARAMETER) == null ||
                            request.getParameter("name") == null ||
                            login.isEmpty() ||
                            request.getParameter(PASSWORD_REQUEST_PARAMETER).isEmpty() ||
                            request.getParameter("name").isEmpty()) {
                throw new IllegalArgumentException(INVALID_LOGIN_OR_PASSWORD_MESSAGE);
            }
            userAlreadyExists = userService.findUser(login);
            request.setAttribute(ERROR_ATTRIBUTE, "User with such login already exists!");
        } catch (UserNotFoundException unfe) {
            userAlreadyExists = new User(login,
                    Hashing.
                            sha256().
                            hashString(password, StandardCharsets.UTF_8).
                            toString(), Roles.ROLE_USER.toString(), request.getParameter("name"));//тут использовал  енам этот
            userService.save(userAlreadyExists);
            response.sendRedirect(String.format("%s/user", request.getContextPath()));
            return;
        } catch (IllegalArgumentException iae) {
            request.setAttribute(ERROR_ATTRIBUTE, iae.getMessage());
        }
        doGet(request, response);
    }
}
