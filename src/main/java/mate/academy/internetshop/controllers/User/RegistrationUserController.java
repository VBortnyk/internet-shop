package mate.academy.internetshop.controllers.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.interfaces.ShoppingCartService;
import mate.academy.internetshop.service.interfaces.UserService;

public class RegistrationUserController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private UserService userService
            = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("login");
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String passwordConf = req.getParameter("pwd-confirmation");

        if (password.equals(passwordConf) && !password.equals("")) {

            User user = new User(name, login, password);
            ShoppingCart shoppingCart = new ShoppingCart(user);

            userService.create(user);
            shoppingCartService.create(shoppingCart);
            resp.sendRedirect(req.getContextPath() + "/storage");

        } else {
            req.setAttribute("message", "Passwords do not match. Please try again");
            req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);

        }

    }
}
