package com.pay.as.support;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTTests {

    @Test
    public void jwtTest () {
        String jwt = Jwts.builder()
                .setSubject("users")
                .setHeaderParam("type", "JWT")
                .claim("identify", "test")
                .signWith(SignatureAlgorithm.HS512, "keys")
                .compact();


        Jws<Claims> claims = Jwts.parser()
                .setSigningKey("keys")
                .parseClaimsJws(jwt);
        String identify = claims.getBody().get("identify").toString();
        String signature = claims.getSignature();


        System.out.println(jwt.length() + "   " + jwt);
        System.out.println(identify);
        System.out.println(signature);
    }

    @Test
    public void jwtSignatureTest () {
        String jwt_HS512 = Jwts.builder()
                .setSubject("users")
                .setHeaderParam("type", "JWT")
                .claim("identify", "test")
                .signWith(SignatureAlgorithm.HS512, "keys")
                .compact();
        System.out.println(jwt_HS512.length() + "   " + jwt_HS512);



        String jwt_HS384 = Jwts.builder()
                .setSubject("users")
                .setHeaderParam("type", "JWT")
                .claim("identify", "test")
                .signWith(SignatureAlgorithm.HS384, "keys")
                .compact();
        System.out.println(jwt_HS384.length() + "   " + jwt_HS384);



        String jwt_HS256 = Jwts.builder()
                .setSubject("users")
                .setHeaderParam("type", "JWT")
                .claim("identify", "test")
                .signWith(SignatureAlgorithm.HS256, "keys")
                .compact();
        System.out.println(jwt_HS256.length() + "   " + jwt_HS256);
    }

}
