package by.project.cinema.service;

import by.project.cinema.model.User;
import by.project.cinema.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean create(User user) {
        return userRepository.create(user);
    }

    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public boolean delete(int id) {
        return userRepository.delete(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean isExistUser(int id) {
        return userRepository.isExistUser(id);
    }

    @Override
    public User signIn(String username, String password) {
        return userRepository.signIn(username, password);
    }
}
