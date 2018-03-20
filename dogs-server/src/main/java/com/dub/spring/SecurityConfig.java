package com.dub.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true, order = 0, mode = AdviceMode.PROXY,
        proxyTargetClass = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	//@Autowired
	//private FindByIndexNameSessionRepository<ExpiringSession> sessionRepository;
	 
    /*
    @Override
   	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   		auth.inMemoryAuthentication()
   			.withUser("Marissa").password("wombat").roles("USER")
   			.and()
   			.withUser("Steve").password("apple").roles("USER")
   			.and()
   			.withUser("Bill").password("orange").roles("USER");
   	}
   */
    
    
    /*
    @Override
    protected void configure(HttpSecurity security) 
    		throws Exception {
        security
                .authorizeRequests()
                	//.antMatchers(HttpMethod.GET).permitAll()
                 	//.antMatchers(HttpMethod.POST).permitAll()
                 	//.antMatchers("/").permitAll()
                	//.antMatchers("/**").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/logout").permitAll()    
                    .antMatchers("/**").hasAuthority("ROLE_USER")                                                
                    .and().formLogin()
                    //.loginProcessingUrl("http://localhost:5555/customers/login")
                    .loginPage("/login").failureUrl("/login?loginFailed")
                    //.loginPage("/login").failureUrl("/login?loginFailed")
                    .defaultSuccessUrl("/index")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and().logout()
                    .logoutUrl("logout")
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                    .permitAll()
                    /*
                .and().sessionManagement()
                    .sessionFixation().changeSessionId()
                    .maximumSessions(1).maxSessionsPreventsLogin(false)
                    .sessionRegistry(this.sessionRegistryImpl())
                
                .and().csrf().disable();
        
    }       
    */
    
   
    @Override
    protected void configure(HttpSecurity security) 
    		throws Exception {
        security
        	.authorizeRequests()
        	.antMatchers("/index").permitAll()
        	.antMatchers("/allDogs").hasRole("DOG_USER")
        	.antMatchers("/createDog").hasRole("DOG_USER")
        	.antMatchers("/updateDog").hasRole("DOG_USER")
        	.antMatchers("/deleteDog").hasRole("DOG_USER")
        //.authorizeRequests()
        //.antMatchers("/**").hasAuthority("ROLE_USER")     
            .and()
        	.csrf().disable();//.authorizeRequests()
                	//.antMatchers(HttpMethod.GET).permitAll()
                 	//.antMatchers(HttpMethod.POST).permitAll()
      
    }
   
    /*
    @Bean
    @SuppressWarnings("unchecked")
    public SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry(this.sessionRepository);
    }
    */
    
    
}