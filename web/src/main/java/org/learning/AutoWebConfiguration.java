package org.learning;

import io.javalin.Javalin;
import org.learning.annotation.GetMapping;
import org.learning.annotation.RestController;
import org.learning.di.annotation.AutoConfigurator;
import org.learning.di.annotation.Configure;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

@AutoConfigurator
public class AutoWebConfiguration {
    @Configure
    public void configure(Collection<Object> beans) {
        Javalin app = Javalin.create();
        registerRouter(beans, app);
        app.start(8080);
    }

    private void registerRouter(Collection<Object> beans, Javalin app) {
        Stream<Object> routeClasses = beans.stream().filter(bean -> bean.getClass().isAnnotationPresent(RestController.class));

        routeClasses.forEach(bean -> registerRoute(app, bean));
    }

    private void registerRoute(Javalin app, Object bean) {
        String rootPath = bean.getClass().getAnnotation(RestController.class).value();

        Arrays.stream(bean.getClass().getMethods()).filter(method -> method.isAnnotationPresent(GetMapping.class)).forEach(method -> {
            String path = method.getAnnotation(GetMapping.class).value();
            String fullPath = rootPath + path;

            app.get(fullPath, ctx -> {
                try {
                    Object result = method.invoke(bean);
                    ctx.result(result.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }
}

