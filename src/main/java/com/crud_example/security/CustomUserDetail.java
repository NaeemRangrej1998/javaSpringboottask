package com.crud_example.security;

import com.crud_example.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetail implements UserDetails {
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetail(UserEntity userInfo) {
        email=userInfo.getEmail();
        password=userInfo.getPassword();
        authorities= Arrays.stream(userInfo.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
//    private final UserEntity userEntity;
//
//    public CustomUserDetail(UserEntity userEntity) {
//        this.userEntity = userEntity;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        HashSet<SimpleGrantedAuthority> simpleGrantedAuthorities = new  HashSet<>();
//        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(this.userEntity.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
