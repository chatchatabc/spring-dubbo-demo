package org.apache.spring.dubbo.provider.facade;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.spring.dubbo.inter.user.UserService;
import org.apache.spring.dubbo.provider.domain.model.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@DubboService
public class UserServiceFacade implements UserService {

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    public Boolean findByEmail(String email, String password) throws IOException {

        Request request = new Request.Builder()
                .url("http://localhost:8090/users?select=email,password&email=eq."+ email +"&password=eq." + password)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            assert response.body() != null;
            String json = response.body().string();

            List<User> users = gson.fromJson(json, new TypeToken<List<User>>() {
            }.getType());
            return users.size() == 1;
        }
    }

    @Override
    public String createUser(String username ,String email, String password) throws IOException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);


        String json = gson.toJson(user);

        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("http://localhost:8090/users")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }

        return "success";

    }

    public void removeUser(String email) throws IOException {

        Request request = new Request.Builder()
                .url("http://localhost:8090/users?email=eq." + email)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

}
