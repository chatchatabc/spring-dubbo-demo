package org.apache.spring.dubbo.provider.domain.service;
import org.apache.spring.dubbo.provider.domain.model.User;
import java.io.IOException;
import java.util.List;

public interface CrudHttpService {
    String get(String url) throws IOException;

    Integer post(String url, String json) throws IOException;

//    Response put(String url, RequestBody body) throws IOException;

    void delete(String url) throws IOException;

    List<User> parseFromGson(String json);

    String parseToGson(User user);

}
