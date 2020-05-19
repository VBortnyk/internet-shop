package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.interfaces.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.interfaces.UserService;
import mate.academy.internetshop.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        String hashedPassword = HashUtil.hashPassword(user.getPassword(), salt);
        user.setPassword(hashedPassword);
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
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public boolean delete(Long userId) {
        return userDao.delete(userId);
    }
}
