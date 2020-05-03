package mate.academy.internetshop.service.interfaces;

import java.util.Optional;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;

@Service
public interface UserService extends GenericService<User, Long> {
    User create(User user);

    User update(User user);

    Optional<User> findByLogin(String login);
}
