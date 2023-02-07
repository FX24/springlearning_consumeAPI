package com.inmotion.githubuser.service;

import com.inmotion.githubuser.dao.UserDao;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<UserDao> getUsers();
    void exportToPdf(HttpServletResponse response) throws IOException;
}
