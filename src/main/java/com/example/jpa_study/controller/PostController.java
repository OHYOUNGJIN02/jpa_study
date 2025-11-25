package com.example.jpa_study.controller;

import com.example.jpa_study.dto.AddPostReqDto;
import com.example.jpa_study.dto.EditPostReqDto;
import com.example.jpa_study.entity.Post;
import com.example.jpa_study.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* JPA (Java Persistence API)
* 객체 지향 언어의 객체와 관계형 데이터베이스의 테이블 간의 매핑을 자동으로 처리
* -> SQL을 직접 쓰지 않고 자바 객체 중심으로 DB를 다룬다.
* 장점 : SQL 없이 빠르게 CRUD 가능, 코드량이 줄어듬
* 단점 : 복잡한 쿼리는 어렵고, 디버깅이 힘들다.
* */



@RestController
@RequestMapping("/post")

public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    ResponseEntity<?> addPost(@RequestBody AddPostReqDto addPostReqDto) {
        return  ResponseEntity.ok(postService.addPost(addPostReqDto));
    }
    @GetMapping("/all")
    ResponseEntity<?> getPostAll(){
        return ResponseEntity.ok(postService.getPostAll());
    }
    @GetMapping("/get")
    ResponseEntity<?> getPostByPostId(@RequestParam Integer postId){
        return ResponseEntity.ok(postService.getPostByPostId(postId));
    }
    @PostMapping("/edit")
    ResponseEntity<?> editPost(@RequestBody EditPostReqDto editPostReqDto){
        return ResponseEntity.ok(postService.editPost(editPostReqDto));
    }
    @PostMapping("/remove")
    ResponseEntity<?> removePost(@RequestParam Integer postId){
        return ResponseEntity.ok(postService.removePost(postId));
    }
    @GetMapping("/get/user")
    ResponseEntity<?> getPostByUserId(@RequestParam Integer userId){
        return ResponseEntity.ok(postService.getPostByPostId(userId));
    }
}
