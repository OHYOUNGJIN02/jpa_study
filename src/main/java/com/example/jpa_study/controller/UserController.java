package com.example.jpa_study.controller;

import com.example.jpa_study.dto.AddUserReqDto;
import com.example.jpa_study.dto.EditUserReqDto;
import com.example.jpa_study.entity.User;
import com.example.jpa_study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody AddUserReqDto addUserReqDto){
        return ResponseEntity.ok(userService.addUser(addUserReqDto));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getUserList(){
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUserById(@RequestParam Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    @GetMapping("/find")
    public ResponseEntity<?> findByUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }
    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody EditUserReqDto editUserReqDto){
        return ResponseEntity.ok(userService.editUser(editUserReqDto));
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeUser(@RequestParam Integer userId){
        return ResponseEntity.ok(userService.removeUser(userId));
    }
    //12
}
