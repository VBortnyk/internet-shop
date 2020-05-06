package mate.academy.internetshop.controllers;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.interfaces.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private UserService userService
            = (UserService) injector.getInstance(UserService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("userId");
        String userName = userService.get(userId).getName();

        req.setAttribute("name", userName);
        req.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req, resp);
    }
}
