package mate.academy.internetshop.lib.interfaces;

import java.util.List;
import mate.academy.internetshop.lib.injector.Service;
import mate.academy.internetshop.model.User;

@Service
public interface UserService {

    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);

}
