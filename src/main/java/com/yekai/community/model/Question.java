package com.yekai.community.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    private static final long serialVersionUID = 1734005831676284178L;
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModify;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
}
