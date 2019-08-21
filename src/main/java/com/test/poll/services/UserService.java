package com.test.poll.services;

import com.test.poll.dao.UserRepository;
import com.test.poll.domains.User;
import com.test.poll.exceptions.UserNotFoundException;
import com.test.poll.utils.AssertsUtils;
import com.test.poll.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssertsUtils assertsUtils;

    @Autowired
    private PasswordUtils passwordUtils;

    public User findByUserName(String username) throws UserNotFoundException {
        this.assertsUtils.isNotNullOrEmpty(username, "username");
        User user = this.userRepository.findFirst1ByUsername(username);
        if(user == null) {
            throw new UserNotFoundException("User not found!!");
        }
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {
        this.assertsUtils.isNotNull(user, "user object cannot be null!.");

        String providedPassword = user.getPassword();
        this.assertsUtils.isNotNullOrEmpty(providedPassword, "providedPassword");

        String newPassword = this.passwordUtils.generateSecurePassword(providedPassword);
        this.assertsUtils.isNotNullOrEmpty(newPassword, "newPassword");

        user.setPassword(newPassword);

        this.userRepository.save(user);
        this.assertsUtils.isNotNull(user, "an error occurred while saving the user.");

        return user;
    }

    public User findById(String id) throws UserNotFoundException {
        this.assertsUtils.isNotNullOrEmpty(id, "id");

        Optional<User> user = this.userRepository.findById(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException("User not found!!");
        }
        return user.get();
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
