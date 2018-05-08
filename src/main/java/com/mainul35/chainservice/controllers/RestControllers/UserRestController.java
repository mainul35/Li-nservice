package com.mainul35.chainservice.controllers.RestControllers;


import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mainul35.chainservice.model.domain.sqlDomains.UserEntity;
import com.mainul35.chainservice.repositories.mongoRepositories.UserMongoRepository;
import com.mainul35.chainservice.service.UserService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/")
    public List<UserEntity> listUser(){
        return userService.findAll();
    }

    @PostMapping("/users/save")
    public boolean create(@RequestBody UserEntity user){
        return userService.save(user);
    }

    @DeleteMapping("/users/delete")
    public String delete(@PathVariable(value = "id") Long id){
//        userService.delete(id);
        return "success";
    }

}
