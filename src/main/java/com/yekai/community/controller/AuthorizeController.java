package com.yekai.community.controller;

import com.yekai.community.dto.AccessTokenDTO;
import com.yekai.community.dto.GithubUser;
import com.yekai.community.mapper.UserMapper;
import com.yekai.community.model.User;
import com.yekai.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = AccessTokenDTO.builder()
                .client_id(clientId)
                .client_secret(clientSecret)
                .redirect_uri(redirectUri)
                .code(code)
                .state(state)
                .build();
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            String token = UUID.randomUUID().toString();
            User user = User.builder()
                    .name(githubUser.getName())
                    .accountId(String.valueOf(githubUser.getId()))
                    .token(token)
                    .gmtCreate(System.currentTimeMillis())
                    .avatarUrl(githubUser.getAvatarUrl())
                    .build();
            user.setGmtModify(user.getGmtCreate());
            userMapper.insert(user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
