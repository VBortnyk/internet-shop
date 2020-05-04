package mate.academy.internetshop.controllers.user;

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

public class GetUserOrderDetails extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
    private UserService userService = (UserService)injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("userId");
        List<Order> orders = orderService.getUserOrders(userService.get(userId));

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/views/orders/allUserOrders.jsp").forward(req, resp);

    }
}
