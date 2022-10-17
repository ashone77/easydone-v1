package com.easydone.userservice.controller;

import com.easydone.userservice.VO.ResponseTemplate;
import com.easydone.userservice.entity.User;
import com.easydone.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/v1/users/")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public User getUserByUserId(@PathVariable("userId") Long userId){
        return userService.getUserByUserId(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public User deleteUser(@PathVariable("userId") Long userId){
        return userService.deleteUser(userId);
    }

//    @PutMapping(path = "{userId}")
//    public void updateUser(
//            @PathVariable("userId") Long userid,
//            @RequestParam(required = false) String firstName,
//            @RequestParam(required = false) String lastName,
//            @RequestParam(required = false) String email
//    ) {
//        userService.updateStudent(userid,firstName,lastName,email);
//    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long userid, @RequestBody User user){
        userService.updateStudent(userid,user);
    }

    @PostMapping(path = "/signin")
    public String signin(@RequestBody User user){
        System.out.println(user);
        return userService.signin(user);
    }

    @PostMapping(path = "/login")
    public ResponseTemplate login(@RequestBody User user){
        return userService.login(user);
    }

}
