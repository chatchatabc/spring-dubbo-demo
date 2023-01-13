package org.apache.spring.dubbo.provider.adapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.apache.spring.dubbo.provider.domain.model.User;
import org.apache.spring.dubbo.provider.infra.okhttp3.NetworkPort;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class NetworkAdapter implements NetworkPort {

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute();) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            System.out.println(response);
            assert response.body() != null;
            return response.body().string();
        }
    }

    @Override
    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute();) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.message();
        }
    }

    @Override
    public Response put(String url, RequestBody body) throws IOException {
        return null;
    }

    @Override
    public void delete(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    @Override
    public String parseToGson(User user) {
        return gson.toJson(user);
    }

    @Override
    public List<User> parseFromGson(String json) {
        return gson.fromJson(json, new TypeToken<List<User>>() {
        }.getType());
    }
}
