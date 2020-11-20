package com.firecode.app.controller.security;

import com.firecode.app.model.entity.UsuarioEntity;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioSecury extends User {

    private UsuarioEntity usuario;

    public UsuarioSecury(UsuarioEntity usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getUsuario(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

}
