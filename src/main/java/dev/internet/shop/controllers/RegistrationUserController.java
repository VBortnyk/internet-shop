package dev.internet.shop.controllers;

import dev.internet.shop.exceptions.DataProcessingException;
import dev.internet.shop.lib.Injector;
import dev.internet.shop.model.Role;
import dev.internet.shop.model.ShoppingCart;
import dev.internet.shop.model.User;
import dev.internet.shop.service.ShoppingCartService;
import dev.internet.shop.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationUserController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internet.shop");
    private final UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService
            = (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("login");
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String passwordConf = req.getParameter("pwd-confirmation");
        if (password.equals(passwordConf) && !password.isEmpty()) {
            try {
                User user = new User(name, login, password);
                user.setRoles(Set.of(Role.of("USER")));
                user = userService.create(user);
                ShoppingCart shoppingCart = new ShoppingCart(user.getId());
                shoppingCartService.create(shoppingCart);
                resp.sendRedirect(req.getContextPath() + "/storage");
            } catch (DataProcessingException e) {
                req.setAttribute(
                        "message", "User with login: "
                                + login + " already exists. Please chose another one");
                req.getRequestDispatcher(
                        "/WEB-INF/views/users/registration.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Passwords do not match. Please try again");
            req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
        }
    }
}
