package com.idt.assignment.service;

import com.idt.assignment.exception.BusinessException;
import com.idt.assignment.model.User;
import com.idt.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

/**
 * Provides user management business layer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * @return finds user by its id
     * @throws BusinessException not found exception if user was not found
     */
    public User getById(String id) {
        User foundUser = findById(id);
        if (foundUser != null) {
            return foundUser;
        }
        throw new BusinessException(format("User with id %s was not found", id));
    }

    /**
     * Finds all existing users
     *
     * @return found users
     */
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * @return finds user by its id
     */
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Updates user content by its id
     *
     * @param id user's id
     * @param userToUpdate updated user
     */
    @Transactional
    public void updateById(String id, User userToUpdate) {
        if (!id.equals(userToUpdate.getId())) {
            throw new BusinessException("User id can not be changed");
        }
        User user = getById(id);
        log.info("User {} has been updated to {}", user,  userToUpdate);
        userRepository.save(userToUpdate);
    }

}
