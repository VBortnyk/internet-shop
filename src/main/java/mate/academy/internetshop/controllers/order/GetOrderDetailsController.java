package mate.academy.internetshop.controllers.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.interfaces.OrderService;
import mate.academy.internetshop.service.interfaces.ProductService;
import mate.academy.internetshop.service.interfaces.UserService;

public class GetOrderDetailsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private static final OrderService orderService
            = (OrderService) injector.getInstance(OrderService.class);
    private static final UserService userService
            = (UserService) injector.getInstance(UserService.class);
    private static final ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String orderId = req.getParameter("id");
        Long id = Long.valueOf(orderId);
        List<Product> products = productService.getAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/orders/orderInfo.jsp").forward(req, resp);
    }
}
