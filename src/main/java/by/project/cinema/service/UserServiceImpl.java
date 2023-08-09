package by.project.cinema.service;

import by.project.cinema.model.User;
import by.project.cinema.repository.UserRepository;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean create(User user) {
        return userRepository.create(user);
    }
}
