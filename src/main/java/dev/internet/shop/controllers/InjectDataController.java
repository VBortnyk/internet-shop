package dev.internet.shop.controllers;

import dev.internet.shop.exceptions.DataProcessingException;
import dev.internet.shop.lib.Injector;
import dev.internet.shop.model.Product;
import dev.internet.shop.model.Role;
import dev.internet.shop.model.ShoppingCart;
import dev.internet.shop.model.User;
import dev.internet.shop.service.ProductService;
import dev.internet.shop.service.ShoppingCartService;
import dev.internet.shop.service.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internet.shop");
    private final ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);
    private final UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService
            = (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        productService.create(new Product("Apple", 30.0));
        productService.create(new Product("Pear", 45.0));
        productService.create(new Product("Plum", 50.0));
        List<Product> storage = productService.getAll();
        try {
            User user = new User("vip", "vip", "1");
            user.setRoles(Set.of(Role.of("USER")));
            User admin = new User("admin", "admin", "1");
            admin.setRoles(Set.of(Role.of("ADMIN")));
            userService.create(user);
            userService.create(admin);
            ShoppingCart adminCart = new ShoppingCart(admin.getId());
            ShoppingCart userCart = new ShoppingCart((user.getId()));
            shoppingCartService.create(adminCart);
            shoppingCartService.create(userCart);
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req, resp);
        }
        req.setAttribute("storage", storage);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
