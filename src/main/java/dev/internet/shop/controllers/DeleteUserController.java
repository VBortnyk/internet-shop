package dev.internet.shop.controllers;

import dev.internet.shop.lib.Injector;
import dev.internet.shop.service.ShoppingCartService;
import dev.internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    private static final Injector INJECTOR
            = Injector.getInstance("dev.internet.shop");
    private final UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService
            = (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String userId = req.getParameter("id");
        Long id = Long.valueOf(userId);
        shoppingCartService.delete(shoppingCartService.getByUserId(id).getId());
        userService.delete(id);
        req.getRequestDispatcher("/WEB-INF/views/orders/allOrders.jsp").forward(req, resp);
    }
}
