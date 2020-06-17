package dev.internet.shop.security;

import dev.internet.shop.exceptions.AuthenticationException;
import dev.internet.shop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
