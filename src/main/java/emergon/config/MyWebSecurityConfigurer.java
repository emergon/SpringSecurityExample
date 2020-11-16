package emergon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userService;
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        UserBuilder users = User.builder();
//        auth.inMemoryAuthentication()
//                .withUser(users.username("admin").password("{noop}1234").roles("ADMIN", "USER", "CUSTOMER"))
//                .withUser(users.username("user").password("{noop}1234").roles("USER"));
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//Restrict access based on the HttpServletRequest
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()//Any request to the application must be authenticated (logged in)
                .and()//Return again the HttpSecurity Object to add more rules
                .formLogin()//We will customize the login process
                .loginPage("/loginPage")//The login form will be found in this url
                .loginProcessingUrl("/authenticate")//The check of credentials will be performed by this url
                .permitAll()//Allow everyone to see login page. Users don't have to be logged in
                .and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/access-denied");
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
