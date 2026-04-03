package org.learning;

import org.learning.di.annotation.AutoConfigurator;
import org.learning.di.annotation.Configure;

import java.util.Collection;

@AutoConfigurator
public class AutoWebConfiguration {
    @Configure
    public void configure(Collection<Object> beans) {
        System.out.println("\n=================Auto Web Configuration=====================");
        for (Object bean : beans) {
            System.out.println(bean.getClass().getName());
        }
        System.out.println("=================Auto Web Configuration=====================\n");
    }
}
