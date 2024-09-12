package com.plazoleta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class CognitoOAuthService {

    private final RestTemplate restTemplate;

    @Autowired
    public CognitoOAuthService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String exchangeCodeForToken(String code){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "31c99r2vcr4m22uapq95v7v9f8");
        body.add("code", code);
        body.add("redirect_uri", "https://plazoleta.com/login/oauth2/code/cognito");

        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity("https://plazoleta2.auth.us-east-2.amazoncognito.com/oauth2/token", request, Map.class);

        Map<String, String> tokenResponse = response.getBody();
        return tokenResponse.get("access_token");
    }
}
