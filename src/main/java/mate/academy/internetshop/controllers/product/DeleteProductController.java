package mate.academy.internetshop.controllers.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.interfaces.ProductService;

public class DeleteProductController extends HttpServlet {
    private static final Injector injector
            = Injector.getInstance("mate.academy.internetshop");
    private ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String productId = req.getParameter("id");
        Long id = Long.valueOf(productId);
        productService.delete(id);

        resp.sendRedirect(req.getContextPath() + "/storage");
    }
}
