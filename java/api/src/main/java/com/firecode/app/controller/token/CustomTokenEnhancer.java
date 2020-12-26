package com.firecode.app.controller.token;

import com.firecode.app.controller.security.UserSecury;
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

        UserSecury user = (UserSecury) authentication.getPrincipal();

        Map<String, Object> info = new HashMap<>();
        info.put("name", user.getUser().getIdPerson().getName());
        info.put("username", user.getUsername());
        info.put("image", domain + avatar + user.getUser().getAvatar());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }

}
