package com.example.ProjectUserManagement.service;

import com.example.ProjectUserManagement.api.UserEntity;
import com.example.ProjectUserManagement.dao.UserRepository;
import com.example.ProjectUserManagement.exception.UserExistException;
import com.example.ProjectUserManagement.exception.UserNotFoundException;
import com.example.ProjectUserManagement.exception.WeakPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity getUserById(long id) throws UserNotFoundException {
        UserEntity user = userRepository.getById(id);
        if (user == null){
            throw  new UserNotFoundException("The user with id "+id+"does not exist.");
        }

        return user;
    }

    public UserEntity createUser(UserEntity newUser) throws UserExistException, WeakPasswordException {
        if (checkUser(newUser)){
            throw new UserExistException("This user already exist.");
        }
        if (!checkPassword(newUser.getPassword())){
            throw new WeakPasswordException("The password must be at least 8 character, have digits and letters.");
        }
        return userRepository.save(newUser);
    }

    public UserEntity updateUser(long id, UserEntity userUpdated) throws UserExistException {
        if (checkUser(userUpdated)){
            throw new UserExistException("This user already exist.");
        }
        UserEntity user = userRepository.getById(id);

        user.setId(userUpdated.getId());
        user.setCreated_at(userUpdated.getCreated_at());
        user.setEmail(userUpdated.getEmail());
        user.setPassword(userUpdated.getPassword());

        userRepository.save(user);

        return user;
    }

    private boolean checkUser(UserEntity user){
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity userEntity : users){
            if (userEntity.equals(user)){
                return true;
            }
        }
        return false;
    }
    private boolean checkPassword(String password){
        if (password.length() < 8){
            return false;
        }
        boolean isLetter = false;
        boolean isDigit = false;

        for (int i=0; i<password.length(); i++){
            if (Character.isDigit(password.charAt(i))){
                isDigit = true;
            }
            if (Character.isLetter(password.charAt(i))){
                isLetter = true;
            }
        }
        return isDigit && isLetter;
    }



}
