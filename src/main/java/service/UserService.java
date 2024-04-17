package service;

import model.entities.User;
import model.repos.UserRepository;
import org.springframework.stereotype.Service;


public interface UserService {
    User registerNewUserAccount(User user);
    boolean authenticateUser(String login, String password);
    User getUserById(Long id);
    void saveUser(User user);
    void deleteUser(Long id);
}
