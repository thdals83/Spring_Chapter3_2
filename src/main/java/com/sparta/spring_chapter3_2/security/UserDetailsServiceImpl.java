package com.sparta.spring_chapter3_2.security;


import com.sparta.spring_chapter3_2.model.User;
import com.sparta.spring_chapter3_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("ddd");
        System.out.println(username);
        User user = userRepository.findByUsername(username);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        return new UserDetailsImpl(user);
    }
}