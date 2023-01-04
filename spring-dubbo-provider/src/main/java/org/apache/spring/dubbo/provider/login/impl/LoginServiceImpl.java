package org.apache.spring.dubbo.provider.login.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.spring.dubbo.inter.login.LoginService;
import org.apache.spring.dubbo.provider.login.domain.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@DubboService
public class LoginServiceImpl implements LoginService {

    @Override
    public Boolean getByEmail(String email, String password) throws IOException {
//        System.out.println(email);
//        System.out.println(password);
//        return true;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8090/users?select=email,password&email=eq."+ email +"&password=eq." + password)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String json = response.body().string();
            Gson gson = new Gson();
            List<User> users = gson.fromJson(json, new TypeToken<List<User>>() {
            }.getType());
            if (users.size() == 1) {
                return true;
            } else {
                return false;
            }
        }
    }
}
