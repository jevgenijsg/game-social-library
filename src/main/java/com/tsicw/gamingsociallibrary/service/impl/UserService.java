package com.tsicw.gamingsociallibrary.service.impl;

import com.tsicw.gamingsociallibrary.repository.GameRepository;
import com.tsicw.gamingsociallibrary.repository.UserRepository;
import com.tsicw.gamingsociallibrary.repository.domain.Game;
import com.tsicw.gamingsociallibrary.repository.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    //method needs rework - ugly
    public void removeGame(Long userId, Long gameId){
        User user = userRepository.getById(userId);
        Game gameToRemove = gameRepository.getById(gameId);
        user.getCollection().remove(gameToRemove);
        userRepository.save(user);
    }

    public void updateUserData(User user){
        User updatedUser = User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .collection(user.getCollection())
                .roles(user.getRoles())
                .build();
        userRepository.save(updatedUser);
    }
}
