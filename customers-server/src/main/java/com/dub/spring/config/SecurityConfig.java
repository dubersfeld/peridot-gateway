package com.dub.spring.config;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true, order = 0, mode = AdviceMode.PROXY,
        proxyTargetClass = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	
    
    

    @Override
    protected void configure(HttpSecurity security) 
    		throws Exception {
        security
        	.authorizeRequests()
        	.antMatchers("/index").permitAll()
        	//.antMatchers("/dummy").permitAll()
        	.antMatchers("/createCustomer").hasRole("CUSTOMER_USER") 
        	.antMatchers("/deleteCustomer").hasRole("CUSTOMER_USER")
        	.antMatchers("/updateCustomer").hasRole("CUSTOMER_USER")
        	.antMatchers("/allCustomers").hasRole("CUSTOMER_USER")
            .and()
        	.csrf().disable();//.authorizeRequests()
                	//.antMatchers(HttpMethod.GET).permitAll()
                 	//.antMatchers(HttpMethod.POST).permitAll()
      
    }
   
   
    
    
}