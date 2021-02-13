package uz.zako.springboot.service;

import org.springframework.stereotype.Service;
import uz.zako.springboot.domain.User;
import uz.zako.springboot.repository.UserRepository;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Boolean checkPassword(String password){
        return password.length() >=6;
    }

    public Boolean checkUserName(String userName){
        return !userRepository.existsByUserName(userName);
    }
}
