package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.User;
import com8.markmcilwrath.domain.entity.UserEntity;
import com8.markmcilwrath.repository.UserRepository;
import javassist.NotFoundException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(String firstName, String lastName, String email, boolean admin)
    {
        UserEntity createEntity = new UserEntity(UUID.randomUUID().toString(), firstName, lastName, email, admin);
        UserEntity createdEntity = userRepository.save(createEntity);
        return new User(createdEntity.getUserId(), createdEntity.getFirstName(), createdEntity.getLastName(),
                createdEntity.getEmail(), createdEntity.isAdmin());
    }


    public User update(User user) throws InvocationTargetException, IllegalAccessException {
        UserEntity updateEntity = userRepository.findByEmail(user.getEmail());
        updateEntity.setFirstName(user.getFirstname());
        updateEntity.setLastName(user.getLastname());
        updateEntity.setAdmin(user.isAdmin());

        userRepository.save(updateEntity);
        return new User(updateEntity.getUserId(), updateEntity.getFirstName(), updateEntity.getLastName(),
                updateEntity.getEmail(), updateEntity.isAdmin());

    }


    public void delete(String uuid) {
        userRepository.deleteByUserId(uuid);
    }

    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }



    public User getUser(String uuid) throws NotFoundException {
        UserEntity entity = userRepository.findByUserId(uuid);

        if (entity == null) {
            throw new NotFoundException("User: " + uuid + " Not found");
        }

        User user = new User(entity.getUserId(), entity.getFirstName(), entity.getLastName(),
                entity.getEmail(), entity.isAdmin());
        return user;

    }

    public User getUserByEmail(String email) throws NotFoundException {
        UserEntity entity = userRepository.findByEmail(email);

        if (entity == null) {
            throw new NotFoundException("User: " + email + " Not found");
        }

        User user = new User(entity.getUserId(), entity.getFirstName(), entity.getLastName(),
                entity.getEmail(), entity.isAdmin());
        return user;

    }

    public Set<User> getAllUsers() {
        Iterable<UserEntity> entityList = userRepository.findAll();
        Set<User> users = new HashSet<>();
        for (UserEntity entity : entityList)
        {
            User user = new User(entity.getUserId(), entity.getFirstName(), entity.getLastName(),
                    entity.getEmail(), entity.isAdmin());
            users.add(user);
        }
        return users;
    }

    public UserEntity getUserEntity(String userID) throws NotFoundException
    {
        UserEntity entity = userRepository.findByUserId(userID);
        if (entity == null)
        {
            throw new NotFoundException("User Not found");
        }
        return entity;
    }


}
