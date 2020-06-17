package dev.internet.shop.controllers;

import dev.internet.shop.lib.Injector;
import dev.internet.shop.model.Order;
import dev.internet.shop.service.OrderService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllExistingOrdersController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internet.shop");
    private final OrderService orderService
            = (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Order> allOrders = orderService.getAll();
        req.setAttribute("orders", allOrders);
        req.getRequestDispatcher("/WEB-INF/views/orders/allOrders.jsp").forward(req, resp);
    }
}
