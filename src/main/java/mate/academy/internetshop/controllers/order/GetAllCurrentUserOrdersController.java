package mate.academy.internetshop.controllers.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.interfaces.OrderService;
import mate.academy.internetshop.service.interfaces.UserService;

public class GetAllCurrentUserOrdersController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
    private UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("userId");
        List<Order> allOrders = orderService.getUserOrders(userService.get(userId));
        req.setAttribute("orders", allOrders);

        req.getRequestDispatcher("/WEB-INF/views/orders/allUserOrders.jsp").forward(req, resp);
    }
}
