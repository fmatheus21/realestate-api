package com.firecode.app.controller.security;

import com.firecode.app.model.entity.UsuarioEntity;
import com.firecode.app.model.service.UsuarioService;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> optional = usuarioService.findByUsuario(username);
        UsuarioEntity usuario = optional.orElseThrow(() -> new UsernameNotFoundException(null));
        return new UsuarioSecury(usuario, this.authorities(usuario));
    }

    private Collection<? extends GrantedAuthority> authorities(UsuarioEntity usuario) {
        Set<SimpleGrantedAuthority> authoritys = new HashSet<>();
        usuario.getPermissoes().forEach(p -> authoritys.add(new SimpleGrantedAuthority(p.getNome().toUpperCase())));
        return authoritys;
    }

}
