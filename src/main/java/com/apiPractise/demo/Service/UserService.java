package com.apiPractise.demo.Service;

import com.apiPractise.demo.Common.Response;
import com.apiPractise.demo.Model.UserModel;


import java.util.List;

public interface UserService {
    Response<Boolean> createUser(UserModel userModel);
    Response <UserModel> getUserById(Long id);
    Response <UserModel> updateUser(Long id, UserModel userModel);
    Response <Boolean> deleteUser(Long id);
    Response<List<UserModel>> getAllUsers(String name, String phoneNumber);
}
