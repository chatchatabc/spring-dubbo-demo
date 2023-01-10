package org.apache.spring.dubbo.provider.impl.register;

import com.google.gson.*;
import okhttp3.*;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.spring.dubbo.inter.register.api.RegisterService;
import org.apache.spring.dubbo.provider.domain.User;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@DubboService
public class RegisterServiceImpl implements RegisterService {

        @Override
        public String insertUser(String username ,String email, String password) throws IOException {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setUsername(username);

            OkHttpClient client = new OkHttpClient();

            Gson gson = new Gson();

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

}
