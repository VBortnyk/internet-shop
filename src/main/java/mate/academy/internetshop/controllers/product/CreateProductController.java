package mate.academy.internetshop.controllers.product;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.interfaces.ProductService;

public class CreateProductController extends HttpServlet {

    private static final Injector injector
            = Injector.getInstance("mate.academy.internetshop");
    private ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> storage = productService.getAll();
        req.setAttribute("storage", storage);
        req.getRequestDispatcher("/WEB-INF/views/products/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String priceString = req.getParameter("price");
        Double price = Double.parseDouble(priceString);
        if (!name.equals("") && !price.equals("")) {
            Product product = new Product(name, price);
            productService.create(product);
            resp.sendRedirect(req.getContextPath() + "/products/create");
        } else {
            req.setAttribute("message", "Incorrect data. All fields should be filled properly");
            req.getRequestDispatcher("/WEB-INF/views/products/create.jsp").forward(req, resp);
        }

    }
}
