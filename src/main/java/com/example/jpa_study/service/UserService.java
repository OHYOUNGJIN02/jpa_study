package com.example.jpa_study.service;

import com.example.jpa_study.dto.AddUserReqDto;
import com.example.jpa_study.dto.ApiRespDto;
import com.example.jpa_study.dto.EditUserReqDto;
import com.example.jpa_study.entity.User;
import com.example.jpa_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ApiRespDto<?> addUser(AddUserReqDto addUserReqDto){
        Optional<User> foundUser = userRepository.findByUsername(addUserReqDto.getUsername());
        if (foundUser.isPresent()){
            return new ApiRespDto<>("failed", "유저 네임 중복", null);
        }
        return new ApiRespDto<>("success", "성공", userRepository.save(addUserReqDto.toEntity()));
    }

    public ApiRespDto<?> getUserList(){
        return new ApiRespDto<>("success", "유저 리스트", userRepository.findAll());
    }
    public ApiRespDto<?> getUserById(Integer userId){
        Optional<User> foundUser = userRepository.findByUserId(userId);
        if (!foundUser.isPresent()){
            return new ApiRespDto<>("failed", "존재하지 않는 유저 id 입니다", null);
        }
        return new ApiRespDto<>("success", "단건 조회 성공",  userRepository.findByUserId(userId));

    }
    public ApiRespDto<?> findByUsername(String username){
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (!foundUser.isPresent()){
            return new ApiRespDto<>("failed", "존재하지 않는 유저 네임 입니다", null);
        }
        return new ApiRespDto<>("success", "단건 조회 성공", foundUser.get());
    }

    public ApiRespDto<?> editUser(EditUserReqDto editUserReqDto) {
        Optional<User> foundUser = userRepository.findByUsername(editUserReqDto.getUsername());
        if (!foundUser.isPresent()) {
            return new ApiRespDto<>("failed", "유저가 존재하지 않습니다", null);
        }
        User user = foundUser.get();
        user.setUsername(editUserReqDto.getUsername());
        user.setPassword(editUserReqDto.getPassword());
        user.setCreateDt(LocalDateTime.now());
        return new ApiRespDto<>("success", "수정 성공",  userRepository.save(user));
    }

    public ApiRespDto<?> removeUser(Integer userId){
        Optional<User> user = userRepository.findByUserId(userId);
        if (!user.isPresent()) {
            return new ApiRespDto<>("failed", "해당 하는 아이디가 없습니다", null);
        }
        userRepository.deleteById(userId);
        return new ApiRespDto<>("success", "성공적으로 삭제 되었습니다", null);
    }
}
