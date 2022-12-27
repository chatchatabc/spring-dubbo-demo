package org.apache.spring.dubbo.inter;

import java.util.concurrent.CompletableFuture;

public interface LoginService {

   Boolean getByEmail(String email, String password);



   default CompletableFuture<Boolean> getEmailAsync(String email, String password) {
       return CompletableFuture.supplyAsync(() -> getByEmail(email, password));
   }
}
