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
    private final String INVALID_LOGIN_OR_PASSWORD_MESSAGE = "Invalid login/password! Please check the data!";
    private final String INVALID_LOGIN_MESSAGE = "Invalid login! Try again please!";
    private final String INVALID_PASSWORD_MESSAGE = "Invalid password! Try again please!";

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
        String email = null, password = null;
        try {
            if (
                    (email = request.getParameter(LOGIN_REQUEST_PARAMETER)) == null ||
                            (password = request.getParameter(PASSWORD_REQUEST_PARAMETER)) == null ||
                            email.isEmpty() ||
                            password.isEmpty()) {
                throw new IllegalArgumentException(INVALID_LOGIN_OR_PASSWORD_MESSAGE);
            }
            User authenticatedUser = userDao.findUser(request.getParameter(LOGIN_REQUEST_PARAMETER));
            if (Hashing.
                    sha256().
                    hashString(request.getParameter(PASSWORD_REQUEST_PARAMETER), StandardCharsets.UTF_8).
                    toString().
                    equals(authenticatedUser.getPassword())) {
                request.getSession().setAttribute("currentUser", authenticatedUser);
                if (authenticatedUser.getUserRole().equals("ROLE_USER")) {
                    response.sendRedirect(String.format("%s/user", request.getContextPath()));
                } else {
                    response.sendRedirect(String.format("%s/admin/customers", request.getContextPath()));
                }
                return;
            } else {
                request.setAttribute(ERROR_ATTRIBUTE, INVALID_PASSWORD_MESSAGE);
            }
        } catch (UserNotFoundException unfe) {
            request.setAttribute(ERROR_ATTRIBUTE, INVALID_LOGIN_MESSAGE);
        } catch (IllegalArgumentException iae) {
            request.setAttribute(ERROR_ATTRIBUTE, iae.getMessage());
        }
        doGet(request, response);
    }
}
