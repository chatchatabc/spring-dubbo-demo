package org.apache.spring.dubbo.consumer.application.common.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author linux_china
 */
@Data
public class UserVO {
    private String username;
    private String email;
    private String password;
    private String matchingPassword;
}
