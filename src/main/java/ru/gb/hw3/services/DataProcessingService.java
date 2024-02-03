package ru.gb.hw3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.hw3.User;
import ru.gb.hw3.UserRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class DataProcessingService {
    private final UserRepository userRepository;

    @Autowired
    public DataProcessingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> sortByName() {
        return userRepository.getUsers().stream().sorted(Comparator.comparing(User::getName)).toList();
    }

    public List<User> sortByEmail() {
        return userRepository.getUsers().stream().sorted(Comparator.comparing(User::getEmail)).toList();
    }

    public List<User> sortByAge() {
        return userRepository.getUsers().stream().sorted(Comparator.comparing(User::getAge)).toList();
    }

    public List<User> filterByAge(Integer age) {
        return userRepository.getUsers().stream().filter(u -> u.getAge() >= age).toList();
    }
}
