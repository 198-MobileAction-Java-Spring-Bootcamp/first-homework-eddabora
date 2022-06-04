package com.example.mobileacitonProject.service;

import com.example.mobileacitonProject.model.UserStatus;
import com.example.mobileacitonProject.model.User;
import com.example.mobileacitonProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public void register(User newUser) {
        User user = new User();
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setEmail(newUser.getEmail());
        user.setTelephone(newUser.getTelephone());
        userRepository.save(user);
        log.info("User registration is successful.");
    }

    public void updateUser(Long userId, User updateUser) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found."));

        if(!Objects.isNull(user)){

            user.setName(updateUser.getName());
            user.setSurname(updateUser.getSurname());
            user.setEmail(updateUser.getEmail());
            user.setTelephone(updateUser.getTelephone());

            userRepository.save(user);

            log.info(userId + "updated!");
        }

    }


    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    public User updateUserStatus(User updateUser) {
        User oldUser = null;
        Optional<User> optionalUsers = Optional.ofNullable(userRepository.findById(updateUser.getUserId()).orElse(null));
        if (optionalUsers.isPresent()) {
            oldUser = optionalUsers.get();
            oldUser.setName(oldUser.getName());
            oldUser.setSurname(oldUser.getSurname());
            oldUser.setTelephone(oldUser.getTelephone());
            oldUser.setEmail(oldUser.getEmail());
            oldUser.setUserStatus(UserStatus.PASSIVE);
            userRepository.save(oldUser);
        } else {
            return new User();
        }
        return oldUser;
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User got deleted";
    }



}
