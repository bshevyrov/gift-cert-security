package com.epam.esm;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GiftCertAdvancedApplication.class);

    }

    @Override
    public void onStartup(ServletContext servletContext) {
//        servletContext.setInitParameter("spring.profiles.active", "prod");
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(AppConfig.class);
        servletContext.setInitParameter("spring.profiles.active", "prod");
//        container.addListener(new ContextLoaderListener(rootContext));
//
//        ServletRegistration.Dynamic dispatcher = servletContext
//                .addServlet("dispatcher", new DispatcherServlet(rootContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");

    }
}
