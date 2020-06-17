package dev.internet.shop.controllers;

import dev.internet.shop.lib.Injector;
import dev.internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internet.shop");
    private final UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String userName = userService.get(userId).getName();
        req.setAttribute("name", userName);
        req.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req, resp);
    }
}
