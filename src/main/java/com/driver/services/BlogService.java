package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
        List<Blog> blogList = new ArrayList<>();
      blogList = blogRepository1.findAll();
      return blogList;

    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time

        // this is the user
        User user = userRepository1.findById(userId).get();

        Blog blog = new Blog();
        blog.setUser(user);
        blog.setTitle(title);
        blog.setContent(content);
        List<Blog> currentListOfBlogs = new ArrayList<>();
        currentListOfBlogs = user.getBlogsWritten();
        currentListOfBlogs.add(blog);
        user.setBlogsWritten(currentListOfBlogs);


        userRepository1.save(user);




        //updating the blog details

        //Updating the userInformation and changing its blogs

    }

    public Blog findBlogById(int blogId){
        //find a blog
        Blog blog = new Blog();
        blog= blogRepository1.findById(blogId).get();
        return blog;
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        //get the blog and update it since 1 blog has many images

        Blog blog = blogRepository1.findById(blogId).get();
        image.setBlog(blog);
        List<Image> currentListOfImages=new ArrayList<>();
        currentListOfImages = blog.getImageList();
        currentListOfImages.add(image);
        blog.setImageList(currentListOfImages);


        blogRepository1.save(blog);
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog = blogRepository1.findById(blogId).get();
        //now we need to delete this blog from user and also delete images of this blog

        // how to get user from blogid;
        List<User>userList = new ArrayList<>();
         userList = userRepository1.findAll();
        // all user here
         for(User user:userList)
         {
             // all blogs of each user here

             List<Blog>blogList = user.getBlogsWritten();
             for(Blog blog1:blogList)
             {
                 // now check in each user's blog this blogid and delete the blog from user
                 int blogid = blog1.getId();
                 if(blogid==blogId)
                 {
                     blogList.remove(blog1);
                     user.setBlogsWritten(blogList);
                     userRepository1.save(user);
                 }

             }
         }
         // now delete blog
        blogRepository1.delete(blog);



    }
}
