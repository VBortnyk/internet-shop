package mate.academy.internetshop.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.interfaces.ShoppingCartService;
import mate.academy.internetshop.service.interfaces.UserService;

public class DeleteUserController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String userId = req.getParameter("id");
        Long id = Long.valueOf(userId);
        userService.delete(id);
        shoppingCartService.delete(shoppingCartService.getByUserId(id).getId());
        resp.sendRedirect(req.getContextPath() + "/");

    }
}
