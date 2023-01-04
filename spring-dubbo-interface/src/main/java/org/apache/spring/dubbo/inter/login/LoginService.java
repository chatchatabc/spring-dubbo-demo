package org.apache.spring.dubbo.inter.login;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface LoginService {

   Boolean getByEmail(String email, String password) throws IOException;

   default CompletableFuture<Boolean> getEmailAsync(String email, String password) {
       return CompletableFuture.supplyAsync(() -> {
           try {
               return getByEmail(email, password);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       });
   }
}
