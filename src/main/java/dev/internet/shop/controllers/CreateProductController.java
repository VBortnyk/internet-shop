package dev.internet.shop.controllers;

import dev.internet.shop.lib.Injector;
import dev.internet.shop.model.Product;
import dev.internet.shop.service.ProductService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProductController extends HttpServlet {
    private static final Injector INJECTOR
            = Injector.getInstance("dev.internet.shop");
    private final ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> products = productService.getAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/products/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String priceString = req.getParameter("price");
        if (!name.isEmpty() && !priceString.isEmpty()) {
            Double price = Double.parseDouble(priceString);
            Product product = new Product(name, price);
            productService.create(product);
            resp.sendRedirect(req.getContextPath() + "/products/create");
        } else {
            req.setAttribute("message", "Incorrect data. All fields should be filled properly");
            req.getRequestDispatcher("/WEB-INF/views/products/create.jsp").forward(req, resp);
        }
    }
}
