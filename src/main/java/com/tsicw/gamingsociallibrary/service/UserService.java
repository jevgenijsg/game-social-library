package com.tsicw.gamingsociallibrary.service;

import com.tsicw.gamingsociallibrary.repository.domain.User;

public interface UserService {


    User findUserByName(String username);

    void save(User user);
}
