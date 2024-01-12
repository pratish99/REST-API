package com.apiPractise.demo.Controller;

import com.apiPractise.demo.Common.Response;
import com.apiPractise.demo.Entity.User;
import com.apiPractise.demo.Model.UserModel;
import com.apiPractise.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Boolean> createUser(@RequestBody UserModel userModel) {
        Response<Boolean> response = userService.createUser(userModel);
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserModel> getUserByid(@PathVariable("id") Long id) {
        Response<UserModel> response = userService.getUserById(id);
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable("id") Long id, @RequestBody UserModel userModel) {
        Response<UserModel> response = userService.updateUser(id, userModel);
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) {
        Response<Boolean> response = userService.deleteUser(id);
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserModel>> getAllUsers(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "phoneNumber", required = false) String phoneNumber) {
        Response<List<UserModel>> response = userService.getAllUsers(name, phoneNumber);
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }
}
