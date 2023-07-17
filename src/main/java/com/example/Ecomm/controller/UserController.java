package com.example.Ecomm.controller;

import com.example.Ecomm.model.User;
import com.example.Ecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private   UserService userService;



    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User model){
        System.out.println(model);
        User registerdUser = userService.registerUser(
                model.getFirstName()
                ,model.getLastName()
                ,model.getEmail()
                ,model.getPassword()
        );
        return  ResponseEntity.ok(registerdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String>  login(@RequestBody User userModel){
        System.out.println("login request"+userModel);
        User loginUser = userService.authenticate(userModel.getEmail(), userModel.getPassword());
        if(loginUser!=null){
            return ResponseEntity.ok("Logged In Succesfully");
        }
        return ResponseEntity.ok("User not found");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}/email")
    public ResponseEntity<User> updateEmail(@PathVariable int id, @RequestParam String email){
        User updatedUser = userService.updateEmail(id, email);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<User> updatePassword(@PathVariable int id, @RequestParam String password) {
        User updatedUser = userService.updatePassword(id, password);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
