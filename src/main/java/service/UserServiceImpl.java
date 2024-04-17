package service;

import model.entities.User;
import model.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.regex.Pattern.matches;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean loginExists(String login) {
        return userRepository.findByLogin(login) != null;
    }
    @Override
    public User registerNewUserAccount(User user) {
        if (loginExists(user.getLogin())) {
            throw new UserRegistrationException(
                    "There is an account with that login: "
                            +
                            user.getLogin()
            );
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        saveUser(user);
        return user;
    }

    @Override
    public boolean authenticateUser(String login, String password) {
        User existingUser = userRepository.findByLogin(login);
        if (existingUser != null) {
            String storedPassword = existingUser.getPassword();
            return passwordEncoder.matches(password, storedPassword);
        }
        return false;
    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}