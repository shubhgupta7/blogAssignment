package com.KoffeeClan.AssignMent.controllers;

import com.KoffeeClan.AssignMent.models.blogModel;
import com.KoffeeClan.AssignMent.services.blogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class homeControllers {

    @Autowired
    public blogService serv;

    @RequestMapping("/")
    public ResponseEntity<String> getHome(){
            return ResponseEntity.ok("checking at : "+LocalDateTime.now());
    }

   @PostMapping("/addBlogs")
    public String addBlog(@RequestParam String title,@RequestParam String content,@RequestParam String auth){
        blogModel newBlog = new blogModel();
        newBlog.setAuthor(auth);
        newBlog.setTitle(title);
        newBlog.setContent(content);
        serv.addBlog(newBlog);
        return newBlog.getContent()+" "+newBlog.getTitle()+" "+newBlog.getId()+""+newBlog.getCreatedAt()+newBlog.getAuthor();
   }

    @GetMapping("/getAll")
    public List<blogModel> getBlogs() {
        List<blogModel> blogs = serv.getBlogs();
        if(blogs.isEmpty()){
            return null;
        }
        else{
            return blogs;
        }
    }

    @GetMapping("/getBlogs")
    public Page<blogModel> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size)  {
        return serv.findAll(page,size);
    }

   @GetMapping("/getBlogs/{id}")
    public blogModel getBlogsById(@PathVariable int id){
        Long x = Long.valueOf(id);
        return serv.findBlogById(x);
   }

   @DeleteMapping("/{id}")
    public String deleteBlog(@PathVariable int id){
        Long x = Long.valueOf(id);
        String result = serv.deleteBlog(x);
        return result;
   }
   @PutMapping("/{id}")
    public blogModel updateBlog(@PathVariable long id,@RequestParam String title,@RequestParam String content,@RequestParam String auth){
        blogModel blog = serv.findBlogById(id);
        if(blog!=null){
            blog.setTitle(title);
            blog.setAuthor(auth);
            blog.setContent(content);
            blog.setCreatedAt(LocalDateTime.now());
            serv.updateBlog(blog);
            return blog;
        }
            return blog;
   }



}
