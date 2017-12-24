package app.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by HEDIN on 24.12.2017.
 */
public class SpringContextProvider {
    private static ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        SpringContextProvider.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
