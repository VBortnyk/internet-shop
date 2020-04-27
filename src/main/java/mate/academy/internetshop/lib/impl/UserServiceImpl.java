package mate.academy.internetshop.lib.impl;

import java.util.List;
import mate.academy.internetshop.dao.interfaces.UserDao;
import mate.academy.internetshop.lib.injector.Inject;
import mate.academy.internetshop.lib.injector.Service;
import mate.academy.internetshop.lib.interfaces.UserService;
import mate.academy.internetshop.model.User;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).get();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }
}
