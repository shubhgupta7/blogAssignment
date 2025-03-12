package com.KoffeeClan.AssignMent.services;

import com.KoffeeClan.AssignMent.models.blogModel;
import com.KoffeeClan.AssignMent.repositories.blogRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class blogService {
    @Autowired
    public blogRepo br;
    public List<blogModel> getBlogs() {
        return br.findAll();
    }

    public blogModel addBlog(blogModel blog) {
        return br.save(blog);
    }
    public blogModel findBlogById(Long x) {
        return br.findById(x).orElse(null);
    }


    public Page<blogModel> findAll(int page, int size) {
        return br.findAll(PageRequest.of(page, size));
    }
    public String deleteBlog(Long x) {
            br.deleteById(x);
            return "Deleted";
    }

    public blogModel updateBlog(blogModel blog) {
        return br.save(blog);
    }
}