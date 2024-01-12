package com.apiPractise.demo.Service.Impl;
import com.apiPractise.demo.Common.Response;
import com.apiPractise.demo.Entity.User;
import com.apiPractise.demo.Model.UserModel;
import com.apiPractise.demo.Repository.UserRepository;
import com.apiPractise.demo.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Response<Boolean> createUser(UserModel userModel) {
        if (userModel.getPhone().length() > 10) return new Response<>(false, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
        User user = new User();
        user.setName(userModel.getName());
        user.setPhoneNumber(userModel.getPhone());
        userRepository.save(user);
        return new Response<>(true);
    }

    @Override
    public Response<UserModel> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) return new Response<>(null, HttpStatus.NOT_FOUND);
        else return new Response<>(user.map(value -> new UserModel(value.getName(), value.getPhoneNumber())).orElse(null));
    }

    @Override
    public Response<UserModel> updateUser(Long id, UserModel userModel) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {

            User entity = user.get();
            entity.setName(userModel.getName());
            entity.setPhoneNumber(userModel.getPhone());
            userRepository.save(entity);
        }
        if(user.isEmpty()) return new Response<>(null, HttpStatus.NOT_FOUND);
        else return new Response<>(user.map(value -> new UserModel(value.getName(), value.getPhoneNumber())).orElse(null));
    }

    @Override
    public Response<Boolean> deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new Response<>(true);
        } else {
            return new Response<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Response<List<UserModel>> getAllUsers(String name, String phoneNumber) {
        List<User> users;
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(phoneNumber)){
            users = userRepository.findAll();
        }
        else{
            users = userRepository.findByName(name, phoneNumber);
        }
        if(users.isEmpty()) return new Response<>(null, HttpStatus.NOT_FOUND);
        else return new Response<>(users.stream().map(user -> new UserModel(user.getName(), user.getPhoneNumber())).toList());
    }


}
