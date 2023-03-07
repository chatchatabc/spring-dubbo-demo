package org.apache.spring.dubbo.provider.user.util.error;


import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class AppErrorFactory {
    public static AppErrorLogger getLogger(Class<?> clazz) {

        return new AppErrorLogger(LoggerFactory.getLogger(clazz));

    }

    public static AppErrorLogger getLogger(String name) {
        return new AppErrorLogger(LoggerFactory.getLogger(name));
    }
}

