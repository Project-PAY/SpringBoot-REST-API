package com.pay.as.support;

import java.util.Map;

public interface JWT {

    String HEADER = "PAY-AUTH-TOKEN";
    String SALT = "paySecret";

    String doCreate(String claim, Map map);

    void doDestroy(String claim);

    Map<String, Object> doGet(String claim);

    byte[] generateKey();

}
