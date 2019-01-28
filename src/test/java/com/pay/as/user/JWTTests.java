package com.pay.as.user;

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

}
