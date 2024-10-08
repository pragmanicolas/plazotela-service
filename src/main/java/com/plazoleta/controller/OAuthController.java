package com.plazoleta.controller;

import com.plazoleta.service.CognitoOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OAuthController {

    private final CognitoOAuthService cognitoOAuthService;

    @Autowired
    public OAuthController(CognitoOAuthService cognitoOAuthService){
        this.cognitoOAuthService = cognitoOAuthService;
    }

    @GetMapping("/login/oauth2/code/cognito")
    public String handleCognitoCallback(@RequestParam("code") String code){
        //String accessToken = cognitoOAuthService.exchangeCodeForToken(code);
        System.out.println("Token recibido: " + code);
        return "Token recibido: " + code;
    }
}
