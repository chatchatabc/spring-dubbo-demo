package org.apache.spring.dubbo.provider.user.util;

public interface CodecUtils {

    String generateSalt();

    String hash(String password, String salt);

    Boolean matches(String password, String hashedPass, String salt);
}
