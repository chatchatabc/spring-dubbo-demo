package org.apache.spring.dubbo.provider.Impl.domain;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.spring.dubbo.inter.MyUserService;
import org.springframework.stereotype.Service;

@DubboService
@Service
public class MyUserServiceImpl implements MyUserService {

    @Override
    public String getByEmail(String email) {
        return "hello " + email + " Request from " + RpcContext.getContext().getRemoteAddress();
    }
}
