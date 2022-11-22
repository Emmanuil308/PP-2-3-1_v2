package com.kirillplaksin.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.ResourceBundle;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.kirillplaksin")
@EnableTransactionManagement
public class WebConfiguration implements WebMvcConfigurer {

    private EntityManagerFactory emf;

    @Bean
    @Scope("prototype")
    public EntityManager getEntityManager(@Autowired LocalContainerEntityManagerFactoryBean enfb) {
        if (emf == null) {
            emf = enfb.getObject();
        }
        return emf.createEntityManager();
    }

    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver internalResourceViewResolver =
                new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }

    @Bean
    public DataSource dataSource() {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        ResourceBundle rd = ResourceBundle.getBundle("db");

        try {
            dataSource.setDriverClass(rd.getString("db.driver"));
            dataSource.setJdbcUrl(rd.getString("db.URL"));
            dataSource.setUser(rd.getString("db.username"));
            dataSource.setPassword(rd.getString("db.pass"));

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        ResourceBundle rb = ResourceBundle.getBundle("hibernate");

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.kirillplaksin.entity");

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", rb.getString("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", rb.getString("hibernate.show_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", rb.getString("hibernate.hbm2ddl.auto"));

        emf.setJpaProperties(properties);
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.afterPropertiesSet();

        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

}
