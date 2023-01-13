package org.apache.spring.dubbo.provider.infra.port.okhttp3;
import org.apache.spring.dubbo.provider.domain.model.User;
import java.io.IOException;
import java.util.List;

public interface NetworkPort {
    String get(String url) throws IOException;

    String post(String url, String json) throws IOException;

//    Response put(String url, RequestBody body) throws IOException;

    void delete(String url) throws IOException;

    List<User> parseFromGson(String json);

    String parseToGson(User user);

}
