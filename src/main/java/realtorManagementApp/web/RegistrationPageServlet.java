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
    private final String PHONE_REQUEST_PARAMETER = "phoneNumber";
    private final String ERROR_ATTRIBUTE = "error";

    private final String INVALID_LOGIN_OR_PASSWORD_MESSAGE = "Неверный логин/пароль! Проверьте введенные данные";


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("currentUser", null);
        request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userAlreadyExists;
        String login = null, password = null, role = null, phoneNumber = null;
        try {
            login = request.getParameter(LOGIN_REQUEST_PARAMETER);
            password = request.getParameter(PASSWORD_REQUEST_PARAMETER);
            switch (request.getParameter("role")) {
                case "realtor": {
                    role = Roles.ROLE_REALTOR.toString();
                    break;
                }
                case "customer": {
                    role = Roles.ROLE_USER.toString();
                    break;
                }
            }
            if (
                    login == null ||
                            request.getParameter(PASSWORD_REQUEST_PARAMETER) == null ||
                            request.getParameter("name") == null ||
                            login.isEmpty() ||
                            request.getParameter(PASSWORD_REQUEST_PARAMETER).isEmpty() ||
                            request.getParameter("name").isEmpty()) {
                throw new IllegalArgumentException(new String(INVALID_LOGIN_OR_PASSWORD_MESSAGE.getBytes(), StandardCharsets.UTF_8));
            }
            userAlreadyExists = userService.findUser(login);
            request.setAttribute(ERROR_ATTRIBUTE, new String("Пользователь с таким логином уже существует!".getBytes(), StandardCharsets.UTF_8));
        } catch (UserNotFoundException unfe) {
            phoneNumber = request.getParameter(PHONE_REQUEST_PARAMETER);
            userAlreadyExists = new User(login,
                    Hashing.
                            sha256().
                            hashString(password, StandardCharsets.UTF_8).
                            toString(), role, request.getParameter("name"), phoneNumber);
            //userAlreadyExists.setEmail(request.getParameter("name"));
            userService.save(userAlreadyExists);
            request.getSession().setAttribute("currentUser", userAlreadyExists);
            response.sendRedirect(String.format("%s/user", request.getContextPath()));
            return;
        } catch (IllegalArgumentException iae) {
            request.setAttribute(ERROR_ATTRIBUTE, iae.getMessage());
        }
        doGet(request, response);
    }
}
