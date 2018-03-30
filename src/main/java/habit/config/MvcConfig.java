package habit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/habit").setViewName("habit");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/add").setViewName("add");
        registry.addViewController("/task").setViewName("task");
        registry.addViewController("/progress").setViewName("progress");

    }


    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:8889/habit-builder-v2");
        driverManagerDataSource.setUsername("habit-builder-v2");
        driverManagerDataSource.setPassword("lc101");
        return driverManagerDataSource;
    }
}