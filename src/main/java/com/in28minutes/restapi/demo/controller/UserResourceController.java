package com.in28minutes.restapi.demo.controller;

import com.in28minutes.restapi.demo.entity.Post;
import com.in28minutes.restapi.demo.entity.User;
import com.in28minutes.restapi.demo.entity.UserDaoService;
import com.in28minutes.restapi.demo.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class UserResourceController {

    private UserDaoService service;

    public UserResourceController(UserDaoService service) {
        this.service = service;
    }

    @RequestMapping( value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers(){
        return service.findAll();
    }

    @RequestMapping( value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable (value = "id") Integer id){
            return service.findOne(id);

    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto request){
        User user = service.createUser(request);

        /*       version 1
        ResponseEntity.status(HttpStatus.CREATED)
                .header("location","http://localhost:8080/users/"+user.getId())
                .body(user);*/

        //version 2
//        HttpHeaders responseHeader = new HttpHeaders();
//        responseHeader.set("location","http://localhost:8080/users/"+user.getId());
//        return new ResponseEntity<>(user,responseHeader, HttpStatus.CREATED);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest() //set up a url from current request
                .path("/{id}") //add a path
                .buildAndExpand(user.getId()) //replace {id} with id of created user
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.POST)
    public void deleteUser(@PathVariable(value = "id") Integer id){
        service.deleteUser(id);
    }

    @RequestMapping(value = "/users/{userId}/posts", method = RequestMethod.GET)
    public Optional<Post> retrievePosts(@PathVariable(value = "userId") Integer userId){
        return service.retrievePosts(userId);
    }

}
