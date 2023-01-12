package org.apache.spring.dubbo.provider.infra.okhttp3;

import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.spring.dubbo.provider.domain.model.User;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface NetworkPort {
    Response get(String url) throws IOException;

    Response post(String url, RequestBody body) throws IOException;

    Response put(String url, RequestBody body) throws IOException;

    Response delete(String url) throws IOException;

    List<User> parseFromGson(Response response) throws IOException;

    RequestBody parseToGson(User user);
}
