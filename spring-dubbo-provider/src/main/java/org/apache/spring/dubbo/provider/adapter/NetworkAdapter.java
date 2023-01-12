package org.apache.spring.dubbo.provider.adapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.apache.spring.dubbo.provider.domain.model.User;
import org.apache.spring.dubbo.provider.infra.okhttp3.NetworkPort;

import java.io.IOException;
import java.util.List;

public class NetworkAdapter implements NetworkPort {

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    public Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute();) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response;
        }
    }

    @Override
    public Response post(String url, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute();) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response;
        }
    }

    @Override
    public Response put(String url, RequestBody body) throws IOException {
        return null;
    }

    @Override
    public Response delete(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response;
        }
    }

    @Override
    public RequestBody parseToGson(User user) {
        String json = gson.toJson(user);
        return RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
    }

    @Override
    public List<User> parseFromGson(Response response) throws IOException {
        try (response) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            assert response.body() != null;
            String json = response.body().string();
            return gson.fromJson(json, new TypeToken<List<User>>() {
            }.getType());
        }
    }
}
