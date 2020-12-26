package com.firecode.app.controller.security;

import com.firecode.app.model.entity.UserEntity;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserSecury extends User {

    private UserEntity user;

    public UserSecury(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }

}
