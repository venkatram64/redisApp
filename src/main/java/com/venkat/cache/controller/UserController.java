package com.venkat.cache.controller;


import com.venkat.cache.model.User;
import com.venkat.cache.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/rest/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("user/{userId}")
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = userRepository.findById(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/addUser",consumes = {"application/json"},produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<User> addItem(@RequestBody User user, UriComponentsBuilder builder){
        userRepository.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/addUser/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    @ResponseBody
    public ResponseEntity<User> updateItem(@RequestBody User user){
        if(user != null){
            userRepository.update(user);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteItem(@PathVariable String id){
        userRepository.delete(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }


}
