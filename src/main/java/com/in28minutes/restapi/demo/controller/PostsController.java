package com.in28minutes.restapi.demo.controller;

import com.in28minutes.restapi.demo.entity.Post;
import com.in28minutes.restapi.demo.entity.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostsController {
    @Autowired
    private UserDaoService service;

    @RequestMapping(value = "/users/{userId}/posts", method = RequestMethod.GET)
    public Optional<Post> retrievePosts(@PathVariable(value = "userId") Integer userId){
        return service.retrievePosts(userId);
    }
}
