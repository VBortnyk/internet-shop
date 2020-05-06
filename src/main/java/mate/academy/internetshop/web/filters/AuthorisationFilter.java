package mate.academy.internetshop.web.filters;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.interfaces.UserService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AuthorisationFilter implements Filter {
    private HashMap<String, List<Role.RoleName>> protectedUrls = new HashMap<>();
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/users/all", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/create", List.of(Role.RoleName.USER));
        protectedUrls.put("/products/create", List.of(Role.RoleName.ADMIN));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestedUrl = req.getServletPath();

        if (protectedUrls.get(requestedUrl) == null) {
            chain.doFilter(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute("userId");
        if (userId == null || userService.get(userId) == null) {
            resp.sendRedirect("/login");
            return;
        }
        User user = userService.get(userId);
        if (isAuthorised(user, protectedUrls.get(requestedUrl))) {
            chain.doFilter(req, resp);
            return;
        } else {
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isAuthorised(User user, List<Role.RoleName> authorizedRoles) {
        for (Role.RoleName authorisedRole : authorizedRoles) {
            for (Role userRole : user.getRoles()) {
                if (authorisedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
