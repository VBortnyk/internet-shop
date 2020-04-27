package mate.academy.internetshop.service.interfaces;

import java.util.List;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;

@Service
public interface UserService extends GenericService<User, Long> {

    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);

}

