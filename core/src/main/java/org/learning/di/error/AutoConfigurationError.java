package org.learning.di.error;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AutoConfigurationError extends RuntimeException {

    public AutoConfigurationError(String message) {
        super(message);
    }

    public static AutoConfigurationError invalidCountOfConfigureMethods(Class<?> clz, ArrayList<Method> methods) {
        String message = String.format("Configuration class %s has %d. Expected 1. Methods: %s",
                clz.getName(),
                methods.size(), methods.stream().map(Method::getName).collect(Collectors.joining(", "))
        );

        return  new AutoConfigurationError(message);
    }

    public static AutoConfigurationError errorWhileInvokingConfigureMethod(Object clz, Method configureMethod, Throwable throwable) {
        String message = String.format("Error while invoking configure method %s of class %s", configureMethod.getName(), clz.getClass().getName());
        return new AutoConfigurationError(message);
    }
}
