package com.blockapp.service.impl;

import com.blockapp.entity.User;
import com.blockapp.service.UserService;
import com.blockapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getById(Long id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() throws Exception {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {
        userRepository.deleteById(id);
    }
}
