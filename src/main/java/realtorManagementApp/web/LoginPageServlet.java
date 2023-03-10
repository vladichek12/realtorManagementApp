package realtorManagementApp.web;

import com.google.common.hash.Hashing;
import realtorManagementApp.dao.UserDao;
import realtorManagementApp.dao.impl.UserDaoImpl;
import realtorManagementApp.entities.User;
import realtorManagementApp.exceptions.UserNotFoundException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginPageServlet extends HttpServlet {
    private final String LOGIN_REQUEST_PARAMETER = "login";
    private final String PASSWORD_REQUEST_PARAMETER = "password";
    private final String NAME_REQUEST_PARAMETER = "name";
    private final String ERROR_ATTRIBUTE = "error";
    private final String INVALID_LOGIN_OR_PASSWORD_MESSAGE = "Неверный логин/пароль! Попробуйте еще раз";
    private final String INVALID_LOGIN_MESSAGE = "Неверный логин! Попробуйте еще раз";
    private final String INVALID_PASSWORD_MESSAGE = "Неверный пароль! Попробуйте еще раз";

    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = UserDaoImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/loginPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email, password;
        try {
            if (
                    (email = request.getParameter(LOGIN_REQUEST_PARAMETER)) == null ||
                            (password = request.getParameter(PASSWORD_REQUEST_PARAMETER)) == null ||
                            email.isEmpty() ||
                            password.isEmpty()) {
                throw new IllegalArgumentException(new String(INVALID_LOGIN_OR_PASSWORD_MESSAGE.getBytes(), StandardCharsets.UTF_8));
            }
            User authenticatedUser = userDao.findUser(request.getParameter(LOGIN_REQUEST_PARAMETER));
            if (Hashing.
                    sha256().
                    hashString(request.getParameter(PASSWORD_REQUEST_PARAMETER), StandardCharsets.UTF_8).
                    toString().
                    equals(authenticatedUser.getPassword())) {
                request.getSession().setAttribute("currentUser", authenticatedUser);
                if (authenticatedUser.getUserRole().equals("ROLE_USER") || authenticatedUser.getUserRole().equals("ROLE_REALTOR")) {
                    response.sendRedirect(String.format("%s/user", request.getContextPath()));
                } else {
                    response.sendRedirect(String.format("%s/admin/customers", request.getContextPath()));
                }
                return;
            } else {
                request.setAttribute(ERROR_ATTRIBUTE, new String(INVALID_PASSWORD_MESSAGE.getBytes(), StandardCharsets.UTF_8));
            }
        } catch (UserNotFoundException unfe) {
            request.setAttribute(ERROR_ATTRIBUTE, new String(INVALID_LOGIN_MESSAGE.getBytes(), StandardCharsets.UTF_8));
        } catch (IllegalArgumentException iae) {
            request.setAttribute(ERROR_ATTRIBUTE, iae.getMessage());
        }
        doGet(request, response);
    }
}
