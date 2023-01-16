package com.driver.controller;

import com.driver.models.*;
import com.driver.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {
@Autowired
BlogService blogService;

//createBlog: create a Blog using given userId, title, and content and link it to the given user.
//addImage: create an image based on given parameters and add it to the given blog.
//getAllBlogs: find count of all blogs irrespective of the user.
//deleteBlog: delete a blog based on id.

    @GetMapping
    public ResponseEntity<Integer> getAllBlogs() {
        int countOfBlogs = 0;
           List<Blog>blogList= blogService.showBlogs();
           countOfBlogs=blogList.size();
        return new ResponseEntity<>(countOfBlogs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBlog(@RequestParam Integer userId ,@RequestParam String title,@RequestParam String content)
    {
      blogService.createAndReturnBlog(userId,title,content);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{blogId}/add-image")
    public ResponseEntity<String> addImage(@PathVariable int blogId, @RequestParam String description, @RequestParam String dimensions) {
             blogService.addImage(blogId,description,dimensions);
            return new ResponseEntity<>("Added image successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable int blogId) {
         blogService.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}




