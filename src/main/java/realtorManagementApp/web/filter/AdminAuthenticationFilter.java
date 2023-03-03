package realtorManagementApp.web.filter;


import realtorManagementApp.authentication.AuthenticationService;
import realtorManagementApp.authentication.impl.AuthenticationServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminAuthenticationFilter implements Filter {
    private AuthenticationService authenticationService;

    @Override

    public void init(FilterConfig filterConfig) throws ServletException {
        authenticationService = AuthenticationServiceImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest correctRequest = (HttpServletRequest) servletRequest;
        if (!authenticationService.isValidatedAsAdmin(correctRequest)) {
            ((HttpServletResponse) servletResponse).sendError(401);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}
