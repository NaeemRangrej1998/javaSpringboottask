package com.crud_example.security;

import com.crud_example.entity.UserEntity;
import com.crud_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Configuration
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = this.userRepository.findByEmail(username);

//        System.out.println("user = " + user.toString());
//        if (user==null) {
//            throw new UsernameNotFoundException("no user found");
//        }

        return user.map(CustomUserDetail::new).orElseThrow(()->new UsernameNotFoundException("User Does Not Exist"));

//        return new CustomUserDetail(user);
    }
}
