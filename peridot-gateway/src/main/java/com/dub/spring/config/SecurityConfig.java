package com.dub.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import org.springframework.context.annotation.AdviceMode;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true, order = 0, mode = AdviceMode.PROXY,
        proxyTargetClass = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
		  
	@Bean
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
	    	return new DefaultWebSecurityExpressionHandler();
	}
	

	@Lazy
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{    
		return super.authenticationManagerBean();
	}
	

    @Bean
    protected SessionRegistry sessionRegistryImpl() {
        return new SessionRegistryImpl();
    }

    
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("Carol").password("sator").roles("ACTUATOR", "CUSTOMER_USER")
			.and()
			.withUser("Alice").password("arepo").roles("ACTUATOR", "CUSTOMER_USER",
													"CREATE", "UPDATE")
			.and()
			.withUser("Richard").password("rotas").roles("ACTUATOR", "CUSTOMER_USER", 
												"CREATE", "UPDATE", "DELETE")
		
			.and()
			.withUser("Marissa").password("wombat").roles("ACTUATOR", "DOG_USER")
			.and()
			.withUser("Steve").password("apple").roles("DOG_USER",
													"CREATE", "UPDATE")
			.and()
			.withUser("Bill").password("orange").roles("DOG_USER",
												"CREATE", "UPDATE","DELETE");
	}

    
    @Override
    protected void configure(HttpSecurity security) 
    		throws Exception {
        security
                .authorizeRequests()                                                        	
                    .antMatchers("/login").permitAll()
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/logout").permitAll()    
                    .antMatchers("/**")
                    	//.hasAnyAuthority("VIEW", "CREATE", "UPDATE", "DELETE")
                    	.hasAnyRole("DOG_USER", "CUSTOMER_USER")
                    				
                    .and().formLogin()
                    .loginPage("/login").failureUrl("/login?loginFailed")
                    .defaultSuccessUrl("/index")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                    .permitAll()
                .and().sessionManagement()
                    .sessionFixation().changeSessionId()
                    .maximumSessions(1).maxSessionsPreventsLogin(false)
                    .sessionRegistry(this.sessionRegistryImpl())
                .and().and().csrf().disable();
        
    }            
}

