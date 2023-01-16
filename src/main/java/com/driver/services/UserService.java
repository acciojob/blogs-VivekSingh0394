package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;


    @Autowired
    BlogService blogService3;

    public void createUser(User user){
        userRepository3.save(user);
    }

    public void deleteUser(int userId){


        userRepository3.deleteById(userId);



    }

    public void updateUser(User user){

        userRepository3.save(user);// this will simply update the row overriding the previous data also id will be updated
        // since we are sending a whole new object it will replace the previous one
    }

    public User findUserByUsername(String username){

        return userRepository3.findByUsername(username);
    }
}
