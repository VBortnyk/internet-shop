package dev.internet.shop.controllers;

import dev.internet.shop.lib.Injector;
import dev.internet.shop.model.ShoppingCart;
import dev.internet.shop.service.OrderService;
import dev.internet.shop.service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateOrderController extends HttpServlet {
    private static final Injector INJECTOR
            = Injector.getInstance("dev.internet.shop");
    private final OrderService orderService
            = (OrderService) INJECTOR.getInstance(OrderService.class);
    private final ShoppingCartService shoppingCartService
            = (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        if (shoppingCart.getProducts().size() != 0) {
            orderService.completeOrder(shoppingCart);
            resp.sendRedirect(req.getContextPath() + "/orders/all-user-orders");
        } else {
            req.setAttribute("message", "Shopping cart is empty");
            req.getRequestDispatcher("/WEB-INF/views/storage/storageUser.jsp").forward(req, resp);
        }
    }
}
