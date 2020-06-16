package com.yekai.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubUser implements Serializable {

    private static final long serialVersionUID = 5646918205747173127L;

    private String name;
    private Long id;
    private String bio;
}
