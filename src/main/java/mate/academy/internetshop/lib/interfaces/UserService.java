package mate.academy.internetshop.lib.interfaces;

import mate.academy.internetshop.lib.injector.Service;
import mate.academy.internetshop.model.User;

import java.util.List;

@Service
public interface UserService {

    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);

}
