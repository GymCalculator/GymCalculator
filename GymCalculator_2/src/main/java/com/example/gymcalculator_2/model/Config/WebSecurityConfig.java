package com.example.gymcalculator_2.model.Config;

import com.example.gymcalculator_2.model.CustomOAuth2User;
import com.example.gymcalculator_2.model.Enumerator.Provider;
import com.example.gymcalculator_2.service.UserService;
import com.example.gymcalculator_2.service.impl.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
    private final UserService userService;
    @Autowired
    private CustomOAuth2UserService oauth2UserService;


    public WebSecurityConfig(PasswordEncoder passwordEncoder,
                             CustomUsernamePasswordAuthenticationProvider authenticationProvider, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauth2UserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {

                        CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
                        String oauth2ClientName = oauth2User.getOauth2ClientName();

                        Provider provider = Provider.valueOf(oauth2ClientName.toUpperCase());
                        System.out.println(oauth2User.getAttributes());

                        if (provider == Provider.FACEBOOK)
                            userService.processOAuthPostLogin(oauth2User.getName(),
                                    String.valueOf(oauth2User.getImageData().get("url")),
                                    oauth2ClientName, oauth2User.getEmail());

                        else if (provider == Provider.GOOGLE)
                            userService.processOAuthPostLogin(oauth2User.getName(), oauth2User.getPicture(),
                                    oauth2ClientName, oauth2User.getEmail());

                        response.sendRedirect("http://localhost:9090/");
                    }
                });

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=Invalid_credentials")
                .defaultSuccessUrl("/home", true) // todo try here
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

}

