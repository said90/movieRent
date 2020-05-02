package com.movierent;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//Getting properties defined on application.properties file
	
	//certificate key
	@Value("${security.signing-key}")
	private String signingKey;

	//256 hash
	@Value("${security.encoding-strength}")
	private Integer encodingStrength;

	//nickname
	@Value("${security.security-realm}")
	private String securityRealm;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	//Spring security interface to get logged user information in base to the token
	@Autowired	
	private UserDetailsService userDetailsService;
	
	//Database source defined on application properties
	@Autowired
	private DataSource dataSource;	
	
	@Bean //To hash password text
	public BCryptPasswordEncoder passwordEnconder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//This is autowired because is looking for a bean this bean is defined on AutheticationManager as a Bean
	//get user details and when logging the password is going to be pass as a parameter to bCrypt and hasg the password
	@Autowired	
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
	}
	
	//r here defines the session is going to be stateless because the view is not handled from the backend
	// csrf is disabled because can have conflict with jwt 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http		
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .httpBasic()
        .realmName(securityRealm)
        .and()
        .csrf()
        .disable();        
	}
	
	// hash jwt in base to the signing key  
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signingKey);		
		return converter;
	}
	
	//defining where is going to be stored the jwt  
	// in this case is stored in database
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(this.dataSource);
	}
	
	
	//First Execution
	// Create an instance and pass all needed parameter to every method
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		// Defines a refresh token
		defaultTokenServices.setSupportRefreshToken(true);			
		defaultTokenServices.setReuseRefreshToken(false);	
		return defaultTokenServices;
	}
}
