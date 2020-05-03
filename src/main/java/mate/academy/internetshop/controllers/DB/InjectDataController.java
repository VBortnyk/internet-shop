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

public class InjectDataController extends HttpServlet {

    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Product phone = new Product("Phone", 1500.0);
        Product laptop = new Product("Laptop", 1050.0);
        Product charger = new Product("Charger", 100.0);

        productService.create(phone);
        productService.create(laptop);
        productService.create(charger);

        List<Product> storage = productService.getAll();

        req.setAttribute("storage", storage);
        resp.sendRedirect(req.getContextPath() + "/");

    }
}
