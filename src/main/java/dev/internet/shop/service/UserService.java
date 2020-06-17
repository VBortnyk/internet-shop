package dev.internet.shop.service;

import dev.internet.shop.lib.Service;
import dev.internet.shop.model.User;

import java.util.Optional;

@Service
public interface UserService extends GenericService<User, Long> {

    User create(User user);

    User update(User user);

    Optional<User> findByLogin(String login);
}
