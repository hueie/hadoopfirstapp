package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ledger.config.HibernateConfig;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"hello", "ledger.index.service", "ledger.index.dao"})
@EnableJpaRepositories("ledger.index.dao")
@EntityScan(basePackageClasses=ledger.index.model.IndexVO.class)
public class Application {

    public static void main(String[] args) {
    	
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    	ctx.register(HibernateConfig.class);
    	ctx.refresh();
    	 
        SpringApplication.run(Application.class, args);
    }

}
