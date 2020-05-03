package mate.academy.internetshop.controllers.Order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.interfaces.OrderService;

public class ShowOrderDetailsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private static final OrderService orderService
            = (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String orderId = req.getParameter("id");
        Long id = Long.valueOf(orderId);
        Order order = orderService.get(id);
        List<Product> products = order.getProducts();

        req.setAttribute("order", products);
        req.getRequestDispatcher("/WEB-INF/views/orders/userOrderDetails.jsp").forward(req, resp);

    }
}
