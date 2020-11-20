package com.firecode.app.controller.token;

import com.firecode.app.controller.security.UsuarioSecury;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Value("${api.path.avatar}")
    private String avatar;

    @Value("${api.domain}")
    private String domain;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        UsuarioSecury usuario = (UsuarioSecury) authentication.getPrincipal();

        Map<String, Object> info = new HashMap<>();
        info.put("Nome", usuario.getUsuario().getIdPessoa().getNomeRazaosocial());
        info.put("usuario", usuario.getUsername());
        info.put("image", domain + avatar + usuario.getUsuario().getAvatar());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }

}
