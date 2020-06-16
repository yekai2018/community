package com.yekai.community.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AccessTokenDTO implements Serializable {
    private static final long serialVersionUID = 1660341004447821684L;
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
