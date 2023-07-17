package com.example.Ecomm.service;

import com.example.Ecomm.model.User;
import com.example.Ecomm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;

    public User registerUser(String firstName, String lastName , String password , String email){
        if(email !=null && password!=null){
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            return userRepository.save(user);
        }else{
            return null;
        }
    }

    public User authenticate(String email , String password){
        return  userRepository.findByEmailAndPassword(email,password).orElse(null);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User updateEmail(int id, String email) {
        Optional<User> optionalUseruser = userRepository.findById(id);
        if(optionalUseruser.isPresent()){
            User user = optionalUseruser.get();
            user.setEmail(email);
            return userRepository.save(user);
        }
        return null;
    }

    public User updatePassword(int id, String newPassword) {
        User user = findById(id);
        if (user!=null) {
            user.setPassword(newPassword);
            return userRepository.save(user);
        }
        return null;
    }

    public User findById(int id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user;
        }
        return null;

    }

}
