package mate.academy.internetshop.controllers.User;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.interfaces.OrderService;
import mate.academy.internetshop.service.interfaces.UserService;

public class UserAccountController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
    private UserService userService = (UserService)injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = userService.get(USER_ID);
        List<Product> products = user.getShoppingCart().getProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/users/personalAccount.jsp").forward(req, resp);

    }
}
