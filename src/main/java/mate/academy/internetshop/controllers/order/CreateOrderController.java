package mate.academy.internetshop.controllers.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.service.interfaces.OrderService;
import mate.academy.internetshop.service.interfaces.ShoppingCartService;

public class CreateOrderController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector
            = Injector.getInstance("mate.academy.internetshop");
    private OrderService orderService
            = (OrderService) injector.getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("userId");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);

        if (shoppingCart.getProducts().size() != 0) {
            orderService.completeOrder(shoppingCart);
            resp.sendRedirect(req.getContextPath() + "/orders/all");
        } else {
            req.setAttribute("message", "Shopping cart is empty");
            req.getRequestDispatcher("/WEB-INF/views/storage/storageUser.jsp").forward(req, resp);
        }
    }
}
