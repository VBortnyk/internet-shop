package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.interfaces.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.injector.Dao;
import mate.academy.internetshop.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        Storage.add(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.USERS.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.USERS;
    }

    @Override
    public User update(User user) {
        IntStream.range(0, Storage.USERS.size())
                .filter(index -> Storage.USERS.get(index).getId().equals(user.getId()))
                .findFirst()
                .ifPresent(index -> Storage.USERS.set(index, user));
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.USERS.removeIf(user -> user.getId().equals(id));
    }
}
