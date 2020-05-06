package mate.academy.internetshop.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.interfaces.UserService;

public class AuthenticationFilter implements Filter {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String url = req.getServletPath();
        if (url.equals("/login") || url.equals("/registration")) {
            chain.doFilter(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute("userId");
        if (userId == null || userService.get(userId) == null) {
            resp.sendRedirect("/login");
            return;
        }
        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }
}
