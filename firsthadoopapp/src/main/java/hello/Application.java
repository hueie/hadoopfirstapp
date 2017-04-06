package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ledger.config.HibernateConfig;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	/*
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    	ctx.register(HibernateConfig.class);
    	ctx.refresh();
    	 */
        SpringApplication.run(Application.class, args);
    }

}
