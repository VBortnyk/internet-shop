package mate.academy.internetshop.controllers.cart;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.interfaces.ShoppingCartService;

public class GetProductsInCartController extends HttpServlet {
    private static final Injector injector
            = Injector.getInstance("mate.academy.internetshop");
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("userId");
        List<Product> products = shoppingCartService.getByUserId(userId).getProducts();

        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/shoppingCarts/details.jsp").forward(req, resp);
    }
}
