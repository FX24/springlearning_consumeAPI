package com.inmotion.githubuser.service;

import com.inmotion.githubuser.client.GithubClient;
import com.inmotion.githubuser.dao.UserDao;
import com.inmotion.githubuser.util.UserPDFExporter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final GithubClient githubClient;

    @Value("${secret.github.token}")
    private String token;
    @Override
    public List<UserDao> getUsers() {
        List<UserDao> userDaoList = githubClient.getUsers(
            token
        );
        return userDaoList;
    }

    @Override
    public void exportToPdf(HttpServletResponse response) throws IOException {
        List<UserDao> users = this.getUsers();
        UserPDFExporter exporter = new UserPDFExporter(users);
        exporter.export(response);
    }
}
