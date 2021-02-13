package uz.zako.springboot.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zako.springboot.domain.User;
import uz.zako.springboot.service.UserService;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity save(@RequestBody User user){
        if (!userService.checkPassword(user.getPassword())){
            return new ResponseEntity("Parol uzunligi 6 dan kam", HttpStatus.BAD_REQUEST);
        }
        if (!userService.checkUserName(user.getUserName())){
            return new ResponseEntity("Ushbu user oldin ro'yxatdan o'tgan", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.createUser(user));
    }


}
