package org.example.service;



import org.example.entity.User;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User save(User user)
    {
        return this.userRepository.save(user);
    }

    public User createUser(String username,String password) throws UsernameAlreadyExistsException {
        User user = new User();

        try {
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            Optional<User> userDup = userRepository.findByUsername(username);
            if (userDup.isPresent()) {
                throw new UsernameAlreadyExistsException("username already exists");
            }
            User u=  userRepository.save(user);
            u.setPassword("");
            return u;
        }catch (UsernameAlreadyExistsException e) {
            logger.error("username already exists: {}", e.getMessage());
            throw new RuntimeException("Email already exists"); // This will also trigger rollback
        }catch (Exception e) {
            logger.error("An error occurred while creating the user: {}", e.getMessage(), e);
            throw new RuntimeException("An error occurred while creating the user                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           "+e.getMessage(), e); // This will also trigger rollback
        }
    }



    /*public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        // Check if the token is valid and not expired
        if (!user.isResetTokenValid(token)) {
            throw new IllegalArgumentException("Token has expired or is invalid");
        }

        // Encrypt the new password
        String encodedPassword = passwordEncoder.encode(newPassword);

        // Update the user's password
        user.setPassword(encodedPassword);

        // Clear the reset token and expiration time
        user.setResetToken(null, 0);

        // Save the updated user in the repository
        userRepository.save(user);
    }*/

   public class UsernameAlreadyExistsException extends Exception {
        public UsernameAlreadyExistsException(String message) {
            super(message);
        }
    }



    public User updateUser(User user) throws Exception {

        Optional<User> user1 = this.userRepository.findByUsername(user.getUsername());

        if(user1.isPresent()) {
            User user2 = user1.get();
            user2.setPassword(passwordEncoder.encode(user.getPassword()));
            user2.setUsername(user.getUsername());
            try {
                return userRepository.save(user2);
            } catch (DuplicateKeyException e) {
                throw new Exception("Duplicate key");
            }
        }
        throw new Exception("Update user fails");
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public Optional<User> findByUserName(String email) {
        return userRepository.findByUsername(email);
    }

    public Optional<User> findById(Integer id) {
        return  userRepository.findById(id);
    }



    public void deleteUser(User user)
    {
        User user1 = this.userRepository.findByUsername(user.getUsername()).get();
        this.userRepository.delete(user1);
    }
}