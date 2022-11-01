package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserRegistrationDTO;
import com.bridgelabz.bookstore.model.UserRegistrationData;
import com.bridgelabz.bookstore.services.IUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 *  1) @RestController :-
 *           @RestController is used for making restful web services with the help of the @RestController annotation.
 *           This annotation is used at the class level and allows the class to handle the requests made by the client
 * 2) @RequestMapping :-
 *           @RequestMapping used to map web requests onto specific handler classes and/or handler methods.
 *           RequestMapping can be applied to the controller class as well as methods
 *
 */
@RestController
@RequestMapping("/user")
public class UserRegistrationController {
    /**
     * 3) @AutoMapping :-
     *          @Autowiring feature of spring framework enables you to inject the object dependency implicitly.
     *          It internally uses setter or constructor injection.
     *
     * - Autowired IBookService interface so we can inject its dependency here
     */
    @Autowired
    IUserRegistrationService iUserRegistrationService;
    /**
     * 4) @PostMapping :-
     *           @PostMapping annotation maps HTTP POST requests onto specific handler methods.
     *           It is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. POST)
     *
     * 5) @RequestBody :-
     *            @RequestBody annotation is applicable to handler methods of Spring controllers.
     *            This annotation indicates that Spring should deserialize a request body into an object.
     *            This object is passed as a handler method parameter
     */

    @PostMapping("/register")
    public ResponseEntity<String> AddAddressDetails(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        String token = iUserRegistrationService.addRecord(userRegistrationDTO);
        ResponseDTO respDTO = new ResponseDTO("Registration successful", token);
        return new ResponseEntity(respDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        UserRegistrationData response = iUserRegistrationService.loginUser(loginDTO);
        ResponseDTO responseDTO = new ResponseDTO("Login Successful", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @GetMapping("/findAll")
    public ResponseEntity<ResponseDTO> findAllDetail() {
        List<UserRegistrationData> userList = iUserRegistrationService.findAll();
        ResponseDTO responseDTO = new ResponseDTO("All Users", userList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    /**
     * 6) @PathVariable :-
     *           @PathVariable is a Spring annotation which indicates that a method parameter should be bound to a URI template variable. It has the following optional elements: name - name of the path variable to bind to.
     *           required - tells whether the path variable is required.
     * - Ability to get book data by id
     */

    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDTO> FindById(@PathVariable int Id) {
        UserRegistrationData response = iUserRegistrationService.FindById(Id);
        ResponseDTO responseDto = new ResponseDTO("User details by id", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @PostMapping("/resetPassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody LoginDTO loginDTO) {
        String response = iUserRegistrationService.resetPassword(loginDTO);
        ResponseDTO responseDTO = new ResponseDTO("Password successfully changed", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDTO> verifyUser(@PathVariable String token) {
        UserRegistrationData user =iUserRegistrationService.verifyUser(token);
        ResponseDTO responseDTO = new ResponseDTO("verified", user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
