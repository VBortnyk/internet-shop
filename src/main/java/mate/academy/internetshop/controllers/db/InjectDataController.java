package mate.academy.internetshop.controllers.db;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.interfaces.ProductService;
import mate.academy.internetshop.service.interfaces.ShoppingCartService;
import mate.academy.internetshop.service.interfaces.UserService;

public class InjectDataController extends HttpServlet {

    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);
    private UserService userService
            = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User admin = new User("admin", "admin","1");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);

        ShoppingCart shoppingCart = new ShoppingCart(admin.getId());
        shoppingCartService.create(shoppingCart);

        List<Product> storage = productService.getAll();
        req.setAttribute("storage", storage);
        resp.sendRedirect(req.getContextPath() + "/");

    }
}
