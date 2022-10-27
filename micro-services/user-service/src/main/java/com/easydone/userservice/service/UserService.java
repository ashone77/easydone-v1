package com.easydone.userservice.service;

import VO.ResponseTemplate;
import com.easydone.userservice.entity.User;
import com.easydone.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createNewUser(User user) {
        Optional<User> userOptional = userRepository
                .findUserByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("User already taken!");
        }
        return userRepository.save(user);
    }


    public User deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException(
                    "User with ID : " + userId + " does not exist."
            );
        }
        userRepository.deleteById(userId);

        return null;
    }

    public ResponseEntity<User> updateStudent(Long userid,User user) {
        Optional<User> userOptional = userRepository.findById(userid);
        if(userOptional.isPresent()){
            User _user = userOptional.get();
            _user.setEmail(user.getEmail());
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            _user.setPassword(user.getPassword());
            _user.setCity(user.getCity());
            _user.setCountry(user.getCountry());
            _user.setState(user.getState());

            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public User getUserByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            return userRepository.findByUserId(userId);
        }
        else {
            throw new IllegalStateException("User with ID" +
                    userId + " does not exist.");
        }
    }

    public String signin(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if(userOptional.isPresent()){
            User _user = userOptional.get();
            if(user.getPassword().equals(_user.getPassword())){
                return "valid";
            }
        }
        return "invalid";

    }

    public ResponseTemplate login(User user) {
        ResponseTemplate responseTemplate = new ResponseTemplate();
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if(userOptional.isPresent()){
            User _user = userOptional.get();
            if(user.getPassword().equals(_user.getPassword())){
                responseTemplate.setUserId(_user.getUserId());
                responseTemplate.setUsername(_user.getFirstName());
                responseTemplate.setIsValid(true);
                responseTemplate.setPhoneno(_user.getPhone());
                return responseTemplate;
            }
        }
        return null;
    }

//    @Transactional
//    public void updateStudent(Long userid,
//                              String fName,
//                              String lName,
//                              String email) {
//        User user = userRepository.findById(userid)
//                .orElseThrow(() -> new IllegalStateException(
//                        "User with ID : " + userid + " does not exist."
//                ));
//
//        if(fName != null &&
//                fName.length() > 0 &&
//                !Objects.equals(user.getFirstName(),fName)){
//            user.setFirstName(fName);
//        }
//
//        if(lName != null &&
//                lName.length() > 0 &&
//                !Objects.equals(user.getLastName(),lName)){
//            user.setLastName(lName);
//        }
//
//        if(email != null &&
//                email.length() > 0 &&
//                !Objects.equals(user.getEmail(), email)){
//            Optional<User> userOptional = userRepository.findUserByEmail(email);
//            if(userOptional.isPresent()){
//                throw new IllegalStateException("Email is already taken!");
//            }
//            user.setEmail(email);
//        }
//    }

}
