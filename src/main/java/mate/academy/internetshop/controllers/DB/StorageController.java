package mate.academy.internetshop.controllers.DB;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.interfaces.ProductService;

public class StorageController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Product> storage = productService.getAll();

        req.setAttribute("storage", storage);
        req.getRequestDispatcher("/WEB-INF/views/storage/storageAdmin.jsp").forward(req, resp);
    }
}
