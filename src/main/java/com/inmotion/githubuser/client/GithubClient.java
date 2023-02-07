package com.inmotion.githubuser.client;

import com.inmotion.githubuser.dao.UserDao;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(
        name = "github-client",
        url = "${client.github.baseUrl}"
)
public interface GithubClient {

    String AUTH_TOKEN = "Authorization";

    @GetMapping("/users?per_page=50")
    List<UserDao> getUsers(
            @RequestHeader(AUTH_TOKEN) String bearerToken
    );

}
