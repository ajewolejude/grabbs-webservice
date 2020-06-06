package com.boca.grabswebservice.service;
import com.boca.grabswebservice.exception.UsernameAlreadyExistsException;
import com.boca.grabswebservice.model.User;
import com.boca.grabswebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setEmail(newUser.getEmail());
            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setPassword("");
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }

    }

    public User getUserByUsername(String username){

        return userRepository.findByEmail(username);
    }

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }




}
