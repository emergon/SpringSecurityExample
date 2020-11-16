package emergon.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement//Spring will find classes annotated with @Transactional to take care of transactions.
@PropertySource(value = {"classpath:application.properties"})
public class DatabaseConfig {
    
    @Autowired
    private Environment env;
    
    //Declare datasource
    public DataSource datasource(){
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName(env.getRequiredProperty("jdbc.driver"));
        datasource.setUrl(env.getRequiredProperty("jdbc.url"));
        datasource.setUsername(env.getRequiredProperty("jdbc.username"));
        datasource.setPassword(env.getRequiredProperty("jdbc.password"));
        return datasource;
    }
    //Declare Hibernate Properties
    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }
    //Declare SessionFactory -- EntityManagerFactory
    //This method creates our SessionFactory
    @Bean(name = "sessionFactory")//This will be a bean so that we can inject it into our DAOs
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(datasource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setPackagesToScan("emergon.entity");//Where to find Entities
        return sessionFactory;
    }
    //Declare TransactionManager
    @Bean
    @Autowired//Inject a SessionFactory Object into this method argument
    public HibernateTransactionManager transanctionManager(SessionFactory factory){
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(factory);
        return txManager;
    }
}
