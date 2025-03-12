package com.KoffeeClan.AssignMent.controllers;

import com.KoffeeClan.AssignMent.models.blogModel;
import com.KoffeeClan.AssignMent.services.blogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class homeControllers {

    private final blogService serv;
    public homeControllers(blogService serv){
        this.serv = serv;
    }
    @RequestMapping("/")
    public ResponseEntity<String> getHome(){
            return ResponseEntity.ok("checking at : "+LocalDateTime.now());
    }
   @PostMapping("/addBlogs")
    public ResponseEntity<blogModel> addBlog(@RequestParam String title,@RequestParam String content,@RequestParam String auth){
       System.out.println("add blogs called ");
        blogModel newBlog = new blogModel();
        newBlog.setAuthor(auth);
        newBlog.setTitle(title);
        newBlog.setContent(content);
        blogModel savedBlog = serv.addBlog(newBlog);
       System.out.println(savedBlog.getAuthor()+" "+savedBlog.getContent()+" "+savedBlog.getTitle()+" "+savedBlog.getCreatedAt());
        return ResponseEntity.ok(savedBlog);
    }

    @GetMapping("/getBlogs")
    public ResponseEntity<List<blogModel>> getBlogs() {
        List<blogModel> blogs = serv.getBlogs();
        if(blogs.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(blogs);
        }
    }
    @GetMapping("/blogs")
    public ResponseEntity<Page<blogModel>> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size)  {
        Page<blogModel> pagedBlogs= serv.findAll(page,size);
        if (pagedBlogs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(pagedBlogs);
        }
    }

   @GetMapping("/blogs/{id}")
    public ResponseEntity<blogModel> getBlogsById(@PathVariable int id){
        Long x = Long.valueOf(id);
        blogModel find = serv.findBlogById(x);
        return ResponseEntity.ok(find);
   }

   @DeleteMapping("/blogs/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable int id){
        Long x = Long.valueOf(id);
        String result = serv.deleteBlog(x);
        return ResponseEntity.ok(result);
   }

   @PutMapping("/blogs/{id}")
    public ResponseEntity<blogModel> updateBlog(@PathVariable long id,@RequestParam String title,@RequestParam String content,@RequestParam String auth){
        blogModel blog = serv.findBlogById(id);
        if(blog!=null){
            blog.setTitle(title);
            blog.setAuthor(auth);
            blog.setContent(content);
            blog.setCreatedAt(LocalDateTime.now());
            serv.updateBlog(blog);
            return ResponseEntity.ok(blog);
        }
            return ResponseEntity.notFound().build();
   }



}
