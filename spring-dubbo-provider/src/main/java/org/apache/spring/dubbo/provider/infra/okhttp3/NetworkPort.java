package org.apache.spring.dubbo.provider.infra.okhttp3;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.spring.dubbo.provider.domain.model.User;

import java.io.IOException;
import java.util.List;

public interface NetworkPort {
    String get(String url) throws IOException;

    String post(String url, String json) throws IOException;

    Response put(String url, RequestBody body) throws IOException;

    void delete(String url) throws IOException;

    List<User> parseFromGson(String json) throws IOException;

    String parseToGson(User user);

}
