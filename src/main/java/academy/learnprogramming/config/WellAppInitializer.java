package academy.learnprogramming.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Slf4j
public class WellAppInitializer implements WebApplicationInitializer {
    private static final String DISPATCHER_SERVLET_NAME="dispatcher";
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Started");
        AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        DispatcherServlet dispatcherServlet=new DispatcherServlet(context);
        ServletRegistration.Dynamic registration=servletContext.addServlet(DISPATCHER_SERVLET_NAME,dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");

    }
}
