package com.touati.org.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/img/**", "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/swagger-ui.html").permitAll()
                .antMatchers("/regions", "/countries", "/locations")
                .hasAnyAuthority("AD_READ_PRIVILEGE", "AC_READ_PRIVILEGE", "FI_READ_PRIVILEGE", "SA_READ_PRIVILEGE")
                .antMatchers("/jobs", "employees", "/departments")
                .hasAnyAuthority("AD_READ_PRIVILEGE", "AC_READ_PRIVILEGE", "FI_READ_PRIVILEGE")
                .antMatchers("/jobs/**", "/employees/**")
                .hasAnyAuthority("AD_WRITE_PRIVILEGE", "AC_WRITE_PRIVILEGE", "FI_WRITE_PRIVILEGE")
                .antMatchers("/departments/**", "/regions/**", "/countries/**", "/locations/**")
                .hasAnyAuthority("AD_WRITE_PRIVILEGE")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .passwordEncoder(passwordEncoder)
        ;
    }
}