package dev.internet.shop.controllers;

import dev.internet.shop.lib.Injector;
import dev.internet.shop.service.ProductService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductController extends HttpServlet {
    private static final Injector INJECTOR
            = Injector.getInstance("dev.internet.shop");
    private final ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productId = req.getParameter("id");
        Long id = Long.valueOf(productId);
        productService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/products/create");
    }
}
