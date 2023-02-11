package org.apache.spring.dubbo.provider.Impl.domain.specification;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.BaseEncoding;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.spring.dubbo.provider.util.CodecUtils;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class CodecUtilsImpl implements CodecUtils {


    public String hash(String password, String salt) {
        return DigestUtils.sha1Hex(password  + ":" + salt);
    }
    public Boolean matches(String password, String hashedPassword, String salt) {
        return hash(password, salt).equals(hashedPassword);
    }

    public String generateSalt() {
        List<Character> characters = Lists.charactersOf("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        int length = 6;
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(characters.get(secureRandom.nextInt(characters.size())));
        }
        return BaseEncoding.base64().encode(builder.toString().getBytes(Charsets.UTF_8));
    }
}
