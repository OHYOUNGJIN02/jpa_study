package com.example.jpa_study.service;

import com.example.jpa_study.dto.AddPostReqDto;
import com.example.jpa_study.dto.ApiRespDto;
import com.example.jpa_study.dto.EditPostReqDto;
import com.example.jpa_study.entity.Post;
import com.example.jpa_study.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class PostService {

    @Autowired
    private PostRepository postRepository;

    public ApiRespDto<?> addPost(AddPostReqDto addPostReqDto){
        Optional<Post> foundPost = postRepository.findByTitle(addPostReqDto.getTitle());
        if(foundPost.isPresent()) {
            return new ApiRespDto<>("fail", "게시글이 중복되어 있습니다.", null);
        }
        return new ApiRespDto<>("success", "추가 성공", postRepository.save(addPostReqDto.toEntity()));
    }

    public ApiRespDto<?> getPostAll(){
        return new ApiRespDto<>("success", "전체조회 성공", postRepository.findAll());
    }
    public ApiRespDto<?> getPostByUserId(Integer userId){
       return new ApiRespDto<>("success", "게시글 조회 완료", postRepository.findAllByUserId(userId));
    }

    public ApiRespDto<?> editPost(EditPostReqDto editPostReqDto){
        Optional<Post> foundPost = postRepository.findById(editPostReqDto.getPostId());
        if(foundPost.isPresent()) {
            return new ApiRespDto<>("fail", "게시글이 존재하지 않습니다.", null);
        }
        Post post = foundPost.get();
        post.setTitle(editPostReqDto.getTitle());
        post.setContent(editPostReqDto.getContent());
        post.setCreateDt(LocalDateTime.now());
        return new ApiRespDto<>("success", "수정 성공", postRepository.save(post));
    }
    public ApiRespDto<?> removePost(Integer postId){
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            return new ApiRespDto<>("failed", "해당하는게 없습니다", null);
        }
        postRepository.deleteById(postId);
        return new ApiRespDto<>("success", "삭제 성공",  null);
    }

    public ApiRespDto<?> getPostByPostId(Integer postId){
        Optional<Post> foundPost = postRepository.findById(postId);
        if (foundPost.isEmpty()){
            return new ApiRespDto<>("failed", "해당 게시글이 존재하지 않습니다", null);
        }
        return new ApiRespDto<>("success", "단건 조회 성공", foundPost.get());
    }
}
