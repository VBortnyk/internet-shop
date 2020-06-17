package dev.internet.shop.controllers;

import dev.internet.shop.lib.Injector;
import dev.internet.shop.model.Product;
import dev.internet.shop.model.Role;
import dev.internet.shop.service.ProductService;
import dev.internet.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllProductsController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("dev.internet.shop");
    private final ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);
    private final UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);

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
