//package ru.aston.farmershop.config;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//public class WebConfig implements WebApplicationInitializer {
//
//  @Override
//  public void onStartup(ServletContext servletContext) throws ServletException {
//    // Веб-контекст
//    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
//    applicationContext.register(AppConfig.class);
//
//    // Передаем DispatcherServlet контекст приложения
//    DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
//    ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", dispatcherServlet);
//    registration.setLoadOnStartup(1);
//
//    // Точка входа сервлета
//    registration.addMapping("/api/*");
//
//  }
//}
