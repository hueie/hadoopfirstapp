package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration
//@ComponentScan
//@EnableJpaRepositories
//@EntityScan
@ComponentScan(basePackages = {"hello", "ledger.index.service", "ledger.index.dao", "ledger.index.web"})
@EnableJpaRepositories("ledger.index.dao")
@EntityScan(basePackageClasses=ledger.index.model.IndexVO.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
