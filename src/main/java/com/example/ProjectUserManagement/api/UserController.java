package com.example.ProjectUserManagement.api;

import com.example.ProjectUserManagement.exception.UserExistException;
import com.example.ProjectUserManagement.exception.UserNotFoundException;
import com.example.ProjectUserManagement.exception.WeakPasswordException;
import com.example.ProjectUserManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userCreated) throws UserExistException, WeakPasswordException {
        userService.createUser(userCreated);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable long id) throws UserNotFoundException {
        UserEntity user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable long id, @RequestBody UserEntity userUpdated) throws UserExistException {
        UserEntity user = userService.updateUser(id, userUpdated);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
}
