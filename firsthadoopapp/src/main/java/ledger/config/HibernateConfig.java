package ledger.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
// @PropertySource(value = { "classpath:application.properties" })
public class HibernateConfig {

	// @Autowired
	// private Environment env;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@123.212.43.251:1521/ARCHIVE");
		dataSource.setUsername("USERTEST");
		dataSource.setPassword("USERTEST");
		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		properties.put("hibernate.show_sql", "true");
		// properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS,
		// "org.springframework.orm.hibernate5.SpringSessionContext ");
		return properties;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan(new String[] { "ledger.index.model" });
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactory.setJpaProperties(getHibernateProperties());

		return entityManagerFactory;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	// Private fields
	@Autowired
	private Environment env;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	/*
	 * @Bean public LocalSessionFactoryBean getSessionFactory() {
	 * LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	 * sessionFactory.setDataSource(dataSource());
	 * sessionFactory.setPackagesToScan(new String[] { "ledger.index.model" });
	 * sessionFactory.setHibernateProperties(getHibernateProperties()); return
	 * sessionFactory; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Bean public DataSource dataSource() { DriverManagerDataSource dataSource
	 * = new DriverManagerDataSource();
	 * dataSource.setDriverClassName(env.getRequiredProperty("datasource.driver"
	 * )); dataSource.setUrl(env.getRequiredProperty("datasource.url"));
	 * dataSource.setUsername(env.getRequiredProperty("datasource.username"));
	 * dataSource.setPassword(env.getRequiredProperty("datasource.password"));
	 * return dataSource; }
	 * 
	 * private Properties getHibernateProperties() { Properties properties = new
	 * Properties(); properties.put(AvailableSettings.DIALECT,
	 * env.getRequiredProperty("hibernate.dialect"));
	 * properties.put(AvailableSettings.SHOW_SQL,
	 * env.getRequiredProperty("hibernate.show_sql"));
	 * properties.put(AvailableSettings.STATEMENT_BATCH_SIZE,
	 * env.getRequiredProperty("hibernate.batch.size"));
	 * properties.put(AvailableSettings.HBM2DDL_AUTO,
	 * env.getRequiredProperty("hibernate.hbm2ddl.auto"));
	 * properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS,
	 * env.getRequiredProperty("hibernate.current.session.context.class"));
	 * return properties; }
	 * 
	 *
	 * 
	 * 
	 * @Bean //이 함수는 스프링에서 사용하는 Bean(클래스, 인스턴스, Autowired에 사용되는 ) 을 리턴해준다!!
	 * 
	 * @Autowired // for DI into args SessionFactory By Using
	 * getSessionFactory() function above to generate SessionFactory. public
	 * HibernateTransactionManager transactionManager(SessionFactory
	 * sessionFactory) { HibernateTransactionManager txManager = new
	 * HibernateTransactionManager();
	 * txManager.setSessionFactory(sessionFactory); return txManager; }
	 * 
	 * 
	 */

}
