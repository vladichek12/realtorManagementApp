package realtorManagementApp.authentication;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    boolean isValidatedAsAdmin(HttpServletRequest request);
}
