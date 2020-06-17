package dev.internet.shop.controllers;

import dev.internet.shop.lib.Injector;
import dev.internet.shop.model.Product;
import dev.internet.shop.model.ShoppingCart;
import dev.internet.shop.service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetProductsInCartController extends HttpServlet {
    private static final Injector INJECTOR
            = Injector.getInstance("dev.internet.shop");
    private final ShoppingCartService shoppingCartService
            = (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        List<Product> products = shoppingCartService.getAllProducts(shoppingCart.getId());
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/shoppingCarts/details.jsp").forward(req, resp);
    }
}
