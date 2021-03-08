package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.User;
import com8.markmcilwrath.domain.entity.UserEntity;
import com8.markmcilwrath.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(String firstName, String lastName, String email, boolean admin) {
        UserEntity createEntity = new UserEntity(UUID.randomUUID().toString(), firstName, lastName, email, admin);
        UserEntity createdEntity = userRepository.save(createEntity);
        return new User(createdEntity.getUserId(), createdEntity.getFirstName(), createdEntity.getLastName(),
                createdEntity.getEmail(), createdEntity.isAdmin());
    }


    public void delete(String uuid) {
        userRepository.deleteByUserId(uuid);
    }

    public User getUser(String uuid) throws NotFoundException {
        UserEntity entity = userRepository.findByUserId(uuid);

        if (entity == null) {
            throw new NotFoundException("Vendore: " + uuid + "Not found");
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


}
