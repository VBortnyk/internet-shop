package dev.internet.shop.service.impl;

import dev.internet.shop.dao.UserDao;
import dev.internet.shop.lib.Inject;
import dev.internet.shop.lib.Service;
import dev.internet.shop.service.UserService;
import dev.internet.shop.model.User;
import dev.internet.shop.util.HashUtil;

import java.util.List;
import java.util.Optional;

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
