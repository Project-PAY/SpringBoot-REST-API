package com.pay.as.service;

import com.pay.as.support.JWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService implements JWT {

    private final String CLAIM = "USER";

    public boolean isUser() {
        Map map = doGet(CLAIM);
        return map != null;
    }


    public String createUser(@Valid Long index,
                             @Valid String identify,
                             @Valid String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("index", index);
        map.put("identify", identify);
        map.put("name", name);

        String token = doCreate(CLAIM, map);
        return token;
    }


    public void destroyUser() {
        // Destory User JWT
        doDestroy(CLAIM);
    }


    @Override
    public String doCreate(String claim, Map map) {
        String token = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("at", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + 3600))
                .setSubject(claim)
                .claim(claim, map)
                .signWith(SignatureAlgorithm.HS256, generateKey())
                .compact();

        return token;
    }

    @Override
    public void doDestroy(String claim) {
        // Destory JWT
    }


    @Override
    public Map<String, Object> doGet(String claim) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Jws<Claims> claims;

        claims = Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(request.getHeader(HEADER));

        Map<String, Object> map = (Map<String, Object>) claims.getBody().get(claim);

        return map;
    }


    @Override
    public byte[] generateKey() {
        return java.util.Base64.getEncoder().encode(SALT.getBytes());
    }

}
