package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    UserRepository userRepository;
    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog

        Image image = new Image();
        image.setBlog(blog);
        image.setDescription(description);
        image.setDimensions(dimensions);
        // now set this image in blog

        List<Image> imageList = blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);

        //  set user of blog if below doesnot work

        blogRepository.save(blog);
        return image;

    }

    public void deleteImage(Image image){
        // when image is deleted the blog has to be upadted
        // get blogs
        List<Blog> blogList = blogRepository.findAll();
        for(Blog blog:blogList)
        {
            // get imagelist of the blog
            List<Image> imageList = blog.getImageList();
            for(Image image1:imageList)
            {
                if(image1.equals(image))
                {
                    // delete this image form bl\og and upadte blog
                    imageList.remove(image);
                    blog.setImageList(imageList);
                    blogRepository.save(blog);
                }
            }
        }
        // now delete image
        imageRepository2.delete(image);
    }

    public Image findById(int id) {
       Image image = new Image();
       image = imageRepository2.findById(id).get();
       return image;
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        int count =0;
        if(image == null)
            return count;
       // image dimensions

        String imageDimension = image.getDimensions();
        String dim[] = imageDimension.split("X");
       int li = Integer.parseInt(dim[0]);
       int bi =Integer.parseInt(dim[1]);

       // screen dimensions
        String dim1[] = screenDimensions.split("X");
        int ls = Integer.parseInt(dim1[0]);
        int bs =Integer.parseInt(dim1[1]);

        count = (ls*bs)/(li*bi);
        return count;

    }
}
