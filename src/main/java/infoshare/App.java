package infoshare;

import infoshare.services.roles.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class App{
    @Autowired
    private RoleService roleService;

    @Service
    public static class MyService {

        public String sampleService() {
            return "Application Started";
        }
    }

    public static void main( String[] args ){
        SpringApplication.run(App.class,args);
    }
}
