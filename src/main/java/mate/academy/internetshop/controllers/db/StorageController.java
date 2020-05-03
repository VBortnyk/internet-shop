package mate.academy.internetshop.controllers.db;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.interfaces.ProductService;
import mate.academy.internetshop.service.interfaces.UserService;

public class StorageController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);
    private UserService userService
            = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Product> storage = productService.getAll();

        Long userId = (Long) req.getSession().getAttribute("userId");
        String userLogin = userService.get(userId).getLogin();
        req.setAttribute("storage", storage);
        if (userLogin.equals("admin")) {
            req.getRequestDispatcher("/WEB-INF/views/storage/storageAdmin.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/storage/storageUser.jsp").forward(req, resp);
        }
    }
}
