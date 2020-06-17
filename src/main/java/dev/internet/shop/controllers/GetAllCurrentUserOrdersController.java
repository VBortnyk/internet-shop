package dev.internet.shop.controllers;

import dev.internet.shop.lib.Injector;
import dev.internet.shop.model.Order;
import dev.internet.shop.service.OrderService;
import dev.internet.shop.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllCurrentUserOrdersController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internet.shop");
    private final OrderService orderService
            = (OrderService) INJECTOR.getInstance(OrderService.class);
    private final UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        List<Order> allOrders = orderService.getUserOrders(userService.get(userId));
        req.setAttribute("orders", allOrders);
        req.getRequestDispatcher("/WEB-INF/views/orders/allUserOrders.jsp").forward(req, resp);
    }
}
