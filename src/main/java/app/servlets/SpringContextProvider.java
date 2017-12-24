package app.servlets;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by HEDIN on 24.12.2017.
 */
public class SpringContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;

    public SpringContextProvider() {
        System.out.println("spring bean created");
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        System.out.println("spring bean created");
        SpringContextProvider.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextProvider.context = applicationContext;
    }
}
