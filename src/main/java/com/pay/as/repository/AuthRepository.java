package com.pay.as.repository;

import java.util.Map;

public interface AuthRepository {

    String HEADER = "PAY-AUTH-TOKEN";
    String SALT = "paySecret";

    String doCreate(String claim, Map map);

    void doDestroy(String claim);

    Map<String, Object> doGet(String claim, String token);

    byte[] generateKey();

}
