package emergon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder users = User.builder();
        auth.inMemoryAuthentication()
                .withUser(users.username("admin").password("{noop}1234").roles("ADMIN", "USER", "CUSTOMER"))
                .withUser(users.username("user").password("{noop}1234").roles("USER"));
    }
    
    
}
