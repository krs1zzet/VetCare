package com.cmdotenter.VetCare.service.impl;

import com.cmdotenter.VetCare.dto.request.BaseUserRequest;
import com.cmdotenter.VetCare.entity.User;
import com.cmdotenter.VetCare.repository.UserRepository;
import com.cmdotenter.VetCare.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void save(BaseUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        user.setPassword(request.getPassword());
        userRepository.save(user);
    }
    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Did not find user id - " + id));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        User theUser = user.orElseThrow(() -> new RuntimeException("Did not find user id - " + id));
        userRepository.deleteById(theUser.getId());

    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
