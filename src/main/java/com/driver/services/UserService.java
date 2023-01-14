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
        User user = userRepository3.findById(userId).get();
        // new code after deleteuser failder till 32
       List<Blog>blogList = user.getBlogList();
        for(Blog blog:blogList)
        {
            int id = blog.getId();
            blogService3.deleteBlog(id);
        }
        userRepository3.delete(user);



    }

    public void updateUser(User user){

        userRepository3.save(user);
    }

    public User findUserByUsername(String username){

        return userRepository3.findByUsername(username);
    }
}
