package com.epam.esm;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GiftCertAdvancedApplication {

	public static void main(String[] args) throws LifecycleException {
		SpringApplication.run(GiftCertAdvancedApplication.class, args);

//         final ConfigurableEnvironment environment = new StandardEnvironment();

			TomcatURLStreamHandlerFactory.disable();

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
//                MainConfiguration.class);
//        TagService tagDAO = applicationContext.getBean(TagServiceImpl.class);
//        tagDAO.delete(3L);
//        applicationContext.close();

			String appBase = ".";
			Tomcat tomcat = new Tomcat();
			tomcat.setBaseDir(createTempDir());
			tomcat.setPort(8080);
			tomcat.getHost().setAppBase(appBase);
			tomcat.addWebapp("", appBase);
			tomcat.start();
			tomcat.getServer().await();
		}

		// based on AbstractEmbeddedServletContainerFactory
		private static String createTempDir() {
			try {
				File tempDir = File.createTempFile("tomcat.", "." + 8080);
				tempDir.delete();
				tempDir.mkdir();
				tempDir.deleteOnExit();
				return tempDir.getAbsolutePath();
			} catch (IOException ex) {
				throw new RuntimeException(
						"Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"),
						ex
				);
			}
		}


	}


