package realtorManagementApp.authentication.impl;

import realtorManagementApp.authentication.AuthenticationService;
import realtorManagementApp.entities.User;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationServiceImpl implements AuthenticationService {
    private static volatile AuthenticationServiceImpl instance;

    public static AuthenticationServiceImpl getInstance() {
        AuthenticationServiceImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthenticationServiceImpl.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new AuthenticationServiceImpl();
            }
        }
        return localInstance;
    }

    private AuthenticationServiceImpl() {

    }

    @Override
    public boolean isValidatedAsAdmin(HttpServletRequest request) {
        User candidate = (User) request.getSession().getAttribute("currentUser");
        if (candidate == null) {
            return false;
        }
        return candidate.getUserRole().equals("ROLE_ADMIN");
    }
}
