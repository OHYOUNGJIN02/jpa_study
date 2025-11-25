package com.example.jpa_study.dto;

import com.example.jpa_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class EditUserReqDto {
    private Integer userId;
    private String username;
    private String password;
}
