package mate.academy.internetshop.controllers.product;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.service.interfaces.ProductService;
import mate.academy.internetshop.service.interfaces.UserService;

public class GetAllProductsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);
    private UserService userService
            = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Product> products = productService.getAll();
        req.setAttribute("products", products);

        Long userId = (Long) req.getSession().getAttribute("userId");
        if (userService.get(userId).getRoles().contains(Role.RoleName.ADMIN)) {
            req.getRequestDispatcher("/WEB-INF/views/storage/storageAdmin.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/storage/storageUser.jsp").forward(req, resp);
        }
    }
}
