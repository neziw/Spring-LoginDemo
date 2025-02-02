/*
 * This file is part of "Spring-LoginDemo", licensed under MIT License.
 *
 *  Copyright (c) 2025 neziw
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package ovh.neziw.springlogindemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.SecurityFilterChain;
import ovh.neziw.springlogindemo.component.CustomAuthenticationFailureHandler;
import ovh.neziw.springlogindemo.component.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    public WebSecurityConfig(final CustomAuthenticationSuccessHandler authenticationSuccessHandler, final CustomAuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity, final SessionRegistry sessionRegistry) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable
        ).authorizeHttpRequests(registry -> registry.requestMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated()
        ).formLogin(customizer -> customizer.loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/", true)
                .successHandler(this.authenticationSuccessHandler)
                .failureHandler(this.authenticationFailureHandler)
        ).logout(customizer -> customizer.logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        ).sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/login?invalidSession")
                .maximumSessions(1)
                .expiredUrl("/login?sessionExpired")
                .sessionRegistry(sessionRegistry)
        );
        return httpSecurity.build();
    }
}