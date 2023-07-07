package com.epam.esm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ServletInitializer extends SpringBootServletInitializer {
    @Autowired
    ConfigurableEnvironment conf;
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
conf.setActiveProfiles("prod");
return application.sources(GiftCertAdvancedApplication.class);

    }
/*
    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        container.setInitParameter("spring.profiles.active", "PROD");
        container.addListener(new ContextLoaderListener(rootContext));

        ServletRegistration.Dynamic dispatcher = container
                .addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

}*/

    @Override
    public void onStartup(ServletContext servletContext)  {
        servletContext.setInitParameter("spring.profiles.active", "prod");
    }
}
