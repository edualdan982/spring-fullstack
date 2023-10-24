package edual.spring.could.msvc.usuarios;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      httpSecurity.authorizeHttpRequests().requestMatchers("/autorized").permitAll().requestMatchers(HttpMethod.GET, "/")
              .hasAnyAuthority("SCOPE_read", "SCOPE_write")
              .requestMatchers(HttpMethod.POST, "/").hasAuthority("SCOPE_write")
              .requestMatchers(HttpMethod.PUT, "/{id}").hasAuthority("SCOPE_write")
              .requestMatchers(HttpMethod.DELETE, "/").hasAuthority("SCOPE_write")
              .anyRequest().authenticated()
              .and().sessionManagement(
              management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/msvc-usuarios-client"))
              .oauth2Client(withDefaults())
              .oauth2ResourceServer(server -> server.jwt());
    return httpSecurity.build();
  }
}
