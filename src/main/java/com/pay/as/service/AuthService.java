package com.pay.as.service;

import com.pay.as.domain.UserDomain;
import com.pay.as.repository.AuthRepository;
import com.pay.as.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService implements AuthRepository {

    @Autowired
    private UserRepository userRepository;

    private final String CLAIM = "USER";


    public String auth(@Valid String identify,
                       @Valid String password) {
        UserDomain userDomain = userRepository.findByIdentifyAndPasswordAndAvailable(identify, password, "able");
        return createUser(userDomain.getIndex(), userDomain.getIdentify(), userDomain.getName());
    }


    public boolean isUser(@Valid String token) {
        Map map = doGet(CLAIM, token);
        return userRepository.getOne(Long.parseLong(String.valueOf(map.get("index")))) != null;
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
        doDestroy(CLAIM);
    }


    @Override
    public String doCreate(String claim, Map map) {
        String token = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("at", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .setSubject(claim)
                .claim(claim, map)
                .signWith(SignatureAlgorithm.HS256, generateKey())
                .compact();

        return token;
    }

    @Override
    public void doDestroy(String claim) {
    }


    @Override
    public Map<String, Object> doGet(String claim, String token) {
        Jws<Claims> claims;

        claims = Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(token);

        Map<String, Object> map = (Map<String, Object>) claims.getBody().get(claim);

        return map;
    }


    @Override
    public byte[] generateKey() {
        return java.util.Base64.getEncoder().encode(SALT.getBytes());
    }

}
