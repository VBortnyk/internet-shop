package mate.academy.internetshop.controllers.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.service.interfaces.ProductService;
import mate.academy.internetshop.service.interfaces.ShoppingCartService;

public class AddToCartController extends HttpServlet {

    private static final Injector injector
            = Injector.getInstance("mate.academy.internetshop");
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String productId = req.getParameter("id");
        Long id = Long.parseLong(productId);
        Product product = productService.get(id);

        Long userId = (Long) req.getSession().getAttribute("userId");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);

        shoppingCartService.addProduct(shoppingCart, product);
        resp.sendRedirect(req.getContextPath() + "/storage");

    }
}
