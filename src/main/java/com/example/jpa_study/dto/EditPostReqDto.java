package com.example.jpa_study.dto;

import com.example.jpa_study.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor

public class EditPostReqDto {
    private Integer postId;
    private String title;
    private String content;


}
