package org.apache.spring.dubbo.inter;

import java.util.concurrent.CompletableFuture;

public interface MyUserService {

   String getByEmail(String email);

   default CompletableFuture<String> getEmailAsync(String name) {
      return CompletableFuture.completedFuture(getByEmail(name));
   }
}
