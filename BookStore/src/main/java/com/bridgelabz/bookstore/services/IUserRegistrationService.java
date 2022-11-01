package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserRegistrationDTO;
import com.bridgelabz.bookstore.model.UserRegistrationData;

import java.util.List;
import java.util.Optional;

public interface IUserRegistrationService {
    String addRecord(UserRegistrationDTO addressDto);
    List<UserRegistrationData> findAll();
    UserRegistrationData FindById(int id);
    UserRegistrationData loginUser(LoginDTO loginDTO);
    String resetPassword(LoginDTO loginDTO);
    UserRegistrationData verifyUser(String token);
}