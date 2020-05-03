package mate.academy.internetshop.controllers.Order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.interfaces.OrderService;

public class DeleteOrderController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String orderId = req.getParameter("id");
        Long id = Long.valueOf(orderId);
        orderService.delete(id);

        resp.sendRedirect(req.getContextPath() + "/orders/all");
    }
}
